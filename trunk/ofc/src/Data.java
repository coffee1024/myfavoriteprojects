import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.Element;
import jofc2.model.elements.LineChart;
import jofc2.model.elements.PieChart;

/**
 *@author  liuguangqiang
 *@date    2012-9-10 上午11:31:16
 *@version 1.0
 **/
public class Data extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json-rpc;charset=utf-8");
		PrintWriter out = response.getWriter();
//		CHART CHART=NEW CHART("统计测试", "FONT-SIZE:30PX;COLOR:#FF0000;");
//		CHART.ADdElements(getLine());
		String json=getLine().toString();
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
	}
	private Element getPieChart(){
		PieChart pie = new PieChart();

		pie.setFontSize(15);// 设置字体

		pie.addSlice(200000000, " 实收费用 " );// 分类

		pie.addSlice(60000000, " 欠费金额 " );

		pie.addSlice(30000000, " 报停金额 " );

		pie.addSlice(20000000, " 减免金额 " );




		pie.setStartAngle(100);// 设置角度

		pie.setAnimate( true );

		// 设置颜色

		pie.setColours( new String[] { "0x336699" , "0x88AACC" , "0x999933" ,

		"0x666699" , "0xCC9933" , "0x006666" , "0x3399FF" , "0x993300" ,

		"0xAAAA77" , "0x666666" , "0xFFCC66" , "0x6699CC" , "0x663366" ,

		"0x9999CC" , "0xAAAAAA" , "0x669999" , "0xBBBB55" , "0xCC6600" ,

		"0x9999FF" , "0x0066CC" , "0x99CCCC" , "0x999999" , "0xFFCC00" ,

		"0x009999" , "0x99CC33" , "0xFF9900" , "0x999966" , "0x66CCCC" ,

		"0x339966" , "0xCCCC33" });

		pie.setTooltip( "#val# / #total#<br> 占百分之 #percent#");// 鼠标移动上去后提示内容

		pie.setRadius(100);
		return pie;
	}
	private Chart getLine(){
		try{

			LineChart lineChart = new LineChart();

			lineChart.setFontSize(12);// 设置字体

			lineChart.setTooltip("#val#%");//设置鼠标移到点上显示的内容

			LineChart.Dot dot1 = new LineChart.Dot(70);//按照顺序设置各个点的值
			LineChart.Dot dot2 = new LineChart.Dot(85);
			LineChart.Dot dot3 = new LineChart.Dot(44);
			LineChart.Dot dot4 = new LineChart.Dot(67);
			LineChart.Dot dot5 = new LineChart.Dot(90);
			LineChart.Dot dot6 = new LineChart.Dot(64);
			LineChart.Dot dot7 = new LineChart.Dot(28);
			LineChart.Dot dot8 = new LineChart.Dot(99);


			lineChart.addDots(dot1);//按照顺序把点加入到图形中
			lineChart.addDots(dot2);
			lineChart.addDots(dot3);
			lineChart.addDots(dot4);
			lineChart.addDots(dot5);
			lineChart.addDots(dot6);
			lineChart.addDots(dot7);
			lineChart.addDots(dot8);
			
			
			LineChart line = new LineChart();

			line.setFontSize(12);// 设置字体
			
			line.setTooltip("#val#%");//设置鼠标移到点上显示的内容

			LineChart.Dot dot11 = new LineChart.Dot(21);//按照顺序设置各个点的值
			LineChart.Dot dot22 = new LineChart.Dot(23);
			LineChart.Dot dot33 = new LineChart.Dot(67);
			LineChart.Dot dot44 = new LineChart.Dot(56);
			LineChart.Dot dot55 = new LineChart.Dot(96);
			LineChart.Dot dot66 = new LineChart.Dot(35);
			LineChart.Dot dot77 = new LineChart.Dot(65);
			LineChart.Dot dot88 = new LineChart.Dot(45);


			line.addDots(dot11);//按照顺序把点加入到图形中
			line.addDots(dot22);
			line.addDots(dot33);
			line.addDots(dot44);
			line.addDots(dot55);
			line.addDots(dot66);
			line.addDots(dot77);
			line.addDots(dot88);

			XAxis x = new XAxis(); // X 轴
			x.addLabels("5月份");
			x.addLabels("6月份");
			x.addLabels("7月份");
			x.addLabels("8月份");
			x.addLabels("9月份");
			x.addLabels("10月份");
			x.addLabels("11月份");
			x.addLabels("12月份");

			//x.setColour("0x000000");


			long max = 100; // //Y 轴最大值

			YAxis y = new YAxis(); //y 轴 

			y.setMax(max+0.0); //y 轴最大值 

			y.setSteps(10); // 步进 

			line.setColour("#ff0000");
			line.setText("");
			Chart flashChart = new Chart( " 历年收费率报表 " , "font-size:12px;color:#ff0000;" ); // 整个图的标题 


			flashChart.addElements(lineChart); // 把饼图加入到图表 
			flashChart.addElements(line);
			Text yText = new Text("test","font-size: 12px;color: #ff0000");

			flashChart.setYAxis(y); 

			flashChart.setXAxis(x); 

			flashChart.setYLegend(yText);//设置y轴显示内容
			return flashChart;
			}catch(Exception ex){
			ex.printStackTrace();
			}
		return null;

	}
}
