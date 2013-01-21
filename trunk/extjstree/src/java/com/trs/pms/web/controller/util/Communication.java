package com.trs.pms.web.controller.util;

/**
 * @title 提供操作与前台页面交互的信息的方法
 * @crTime 2010-4-28
 */
public class Communication {
	/**
	 * 通过信息内容返回一个div框接一段打开此框的的js
	 * 
	 * @param content
	 * @return
	 */
	public static String alert(String content) {
		/*return "<div id=\"book_div\" style=\"position:absolute;filter:alpha(opacity=50);opacity:0.5;background-color:#000000;z-index:110;top:0px;left:0px;width:100%;text-align:center;display:none;\"></div><div id=\"con_div\" align=\"center\" style=\"position:absolute;background-color:#ffffff;z-index:110;top:100px;width:350px;border:1px solid #666666;padding:10px;display:none;\">"
				+ content
				+ "<br /><a href=# onclick=\"closedivwin()\">关闭</a></div><script>opendivwin();autoclosedivwin();</script>";*/
		return "<div id=\"con_div\" align=\"center\" style=\"position:absolute;background-color:#ffffff;z-index:110;top:100px;width:350px;border:1px solid #666666;padding:10px;display:none;\">"
		+ content
		+ "<br /><a href=# onclick=\"$(\".pageOverlay\").hide();\">关闭</a></div><script>$(\".loading\").hide();$(\"#con_div\").show(); </script>";
	}

	/**
	 * 通过信息内容返回一个div框接一段打开此框的的js,不带遮蔽层
	 * 
	 * @param content
	 * @return
	 */
	public static String alertNoBackGround(String content) {
		return "<div id=\"con_div\" align=\"center\" style=\"position:absolute;background-color:#ffffff;z-index:110;top:100px;width:350px;border:1px solid #666666;padding:10px;display:none;\">"
				+ content
				+ "<br /><a href=# onclick=\"closedivwin()\">关闭</a></div><script>opendivwin();autoclosedivwin();</script>";
	}
	
	/**
	 * 调用父页面的js
	 * @param content
	 * @return
	 */
	public static String alertNoBackTips(String content){
		return "<span style=\"display:none\"><script>parent.showTips('"+content+"');</script></span>";
	}
	
	public static String alertHeaderTips(String content){
		return "<span style=\"display:none\"><script>parent.showHeaderTip('"+content+"');</script></span>";
	}
	
	public static String showHeaderTips(String content){
		return "<span style=\"display:none\"><script>showTipese('"+content+"');</script></span>";
	}
	
	public static String showHeaderTipsByGet(String content){
		return "<span style=\"display:none\"><script>parent.showHeaderTipByGet('"+content+"');</script></span>";
	}
	
	/**
	 * 发布库专用,同时刷新左侧栏目树
	 * @param content
	 * @return
	 */
	public static String alertNoBackAndRefreshLeft(String content){
		return "<div id=\"con_div\" align=\"center\" style=\"position:absolute;background-color:#ffffff;z-index:110;top:100px;width:350px;border:1px solid #666666;padding:10px;display:none;\">"
		+ content
		+ "<br /><a href=# onclick=\"closedivwin()\">关闭</a></div><script>opendivwin();autoclosedivwin();$('#tree_view',parent.document.body).attr('src','pubpic.do?method=tree');</script>";
	}

	/**
	 * 通过信息内容返回一个div框接一段打开此框的的js并刷新页面
	 * @param content
	 * @return
	 */
	public static String alertNoBackAndRefresh(String content){
		return "<div id=\"con_div\" align=\"center\" style=\"position:absolute;background-color:#ffffff;z-index:110;top:100px;width:350px;border:1px solid #666666;padding:10px;display:none;\">"
		+ content
		+ "<br /><a href=# onclick=\"closedivwin()\">关闭</a></div><script>opendivwin();autoclosedivwinandrefresh();</script>";
	}
}
