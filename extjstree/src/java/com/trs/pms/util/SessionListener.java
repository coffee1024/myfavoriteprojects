package com.trs.pms.util;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionListener implements HttpSessionListener {
	 public static Map userMap = new HashMap();
	     private   MySessionContext myc=MySessionContext.getInstance();
	
	     /**
	      * session创建时，人为保存此session
	      */
	     public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	         myc.AddSession(httpSessionEvent.getSession());
	     }
	
	     /**
	      * session销毁时，人为移除此session
	      */
	     public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
	         HttpSession session = httpSessionEvent.getSession();
	         myc.DelSession(session);
	     }
}
