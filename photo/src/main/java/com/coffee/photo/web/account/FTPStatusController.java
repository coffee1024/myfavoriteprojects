package com.coffee.photo.web.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ftpserver.FtpServer;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coffee.photo.utils.ftp.FtpServerListener;

/**
 * 
 * @author liuguangqiang
 */
@Controller
@RequestMapping(value = "/ftp/status")
public class FTPStatusController {

	@RequestMapping(method = RequestMethod.GET)
	public void status(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 FtpServer server = (FtpServer) request.getServletContext().getAttribute(FtpServerListener.FTPSERVER_CONTEXT_NAME);
	        response.setCharacterEncoding("utf-8");
	        PrintWriter wr = response.getWriter();
	        
	        wr.print("<html>");
	        wr.print("<head>");
	        wr.print("<title>FtpServer status servlet</title>");
	        wr.print("</head>");
	        wr.print("<body>");
	        wr.print("<form method='post'>");


	        if(server.isStopped()) {
	            wr.print("<p>FtpServer is stopped.</p>");
	        } else {
	            if(server.isSuspended()) {
	                wr.print("<p>FtpServer is suspended.</p>");
	                wr.print("<p><input type='submit' name='resume' value='Resume'></p>");
	                wr.print("<p><input type='submit' name='stop' value='Stop'></p>");
	            } else {
	                wr.print("<p>FtpServer is running.</p>");
	                wr.print("<p><input type='submit' name='suspend' value='Suspend'></p>");
	                wr.print("<p><input type='submit' name='stop' value='Stop'></p>");
	            }
	        }
	        
	        wr.print("</form>");
	        wr.print("</body>");
	        wr.print("</html>");
	}

	@RequestMapping(method = RequestMethod.POST)
	public void changeStatus(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 FtpServer server = (FtpServer) request.getServletContext().getAttribute(FtpServerListener.FTPSERVER_CONTEXT_NAME);
		 response.setCharacterEncoding("utf-8");
		 PrintWriter wr = response.getWriter();
	        if(request.getParameter("stop") != null) {
	            server.stop();
	            wr.write("Stop FTP Success");
	        } else if(request.getParameter("resume") != null) {
	            server.resume();
	            wr.write("Resume FTP Success");
	        } else if(request.getParameter("suspend") != null) {
	            server.suspend();
	            wr.write("Suspend FTP Success");
	        }else{
	        	wr.write("do nothing");
	        }
	}

}
