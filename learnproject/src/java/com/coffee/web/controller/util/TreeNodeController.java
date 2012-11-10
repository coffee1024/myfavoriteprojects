package com.coffee.web.controller.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.tree.TreeNode;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/treenode.do")
public class TreeNodeController {
	
//	@RequestMapping
//	public String directPage(HttpServletRequest req,ModelMap mod){
//		return "commons/show_tree";
//	}
//	
//	@RequestMapping(params="method=toFounder")
//	public String toFounder(HttpServletRequest req,ModelMap mod){
//		String type=req.getParameter("type");
//		mod.put("type", type);
//		return "commons/choose-founder";
//	}
//	
//	@RequestMapping(params = "method=chnl")
//	public String getColumns(HttpServletRequest req,HttpServletResponse res){
//		try {
//			Long chnlId=0l;
//			if(req.getParameter("id")!=null && req.getParameter("id")!=""){
//				chnlId = Long.parseLong(req.getParameter("id"));
//			}
//			List<Channel> list = cm.getChildrenChannel(chnlId,false);
//			PrintWriter out = res.getWriter();
//			StringBuffer sb = new StringBuffer();
//			sb.append("[");
//			for(int i=0;list!=null && i<list.size();i++){
//				Channel chnl=list.get(i);
//				sb.append("{ attributes : { \"id\" : \"");
//				sb.append(chnl.getChannelId());
//				sb.append("\"}, data : {\"title\": \"");
//				sb.append(chnl.getChannelName());
//				sb.append("\", \"attributes\":{\"href\":\"");
//				sb.append(chnl.getChannelId());
//				sb.append("\", \"title\":\" ");
//				sb.append(chnl.getChannelId()).append("\"} }");
//				if(cm.getChildrenChannel(chnl.getChannelId(),false).size()>0){
//				sb.append(", state : \"closed\"");
//				}
//				sb.append("}");
//				if(i<list.size()-1){
//					sb.append(",");
//				}
//			}
//			sb.append("]");
//			System.out.println(sb.toString());
//			out.write(sb.toString());
//			out.flush();
////			map.put("tree", sb.toString());
////			return "test/treetest";
//			return null;
//		} 
//		catch (Exception e) {
//			return null;
//		}
//	}
//
//	@RequestMapping(params = "method=cooperator")
//	public String getCooperator(HttpServletRequest req,HttpServletResponse res){
//		try {
//			Long chnlId=0l;
//			if(req.getParameter("id")!=null && req.getParameter("id")!=""){
//				chnlId = Long.parseLong(req.getParameter("id"));
//			}
//			List<TreeTemplateNode<String>> clist=apm.getAllChildNodes();
//			String nodeInfo=toGetCooperator(req.getParameter("id"),clist);
//			PrintWriter out = res.getWriter();
//			System.out.println(nodeInfo);
//			out.write(nodeInfo);
//			out.flush();
////			map.put("tree", sb.toString());
////			return "test/treetest";
//			return null;
//		} 
//		catch (Exception e) {
//			return null;
//		}
//	}
//	
//	@SuppressWarnings("finally")
//	private String toGetCooperator(String nodeId,List<TreeTemplateNode<String>> clist){
//		StringBuffer sb = new StringBuffer();
//		try{
//			List<TreeTemplateNode<String>> list = apm.getchildNodes(nodeId,clist);
//			sb.append("[");
//			for(int i=0;list!=null && i<list.size();i++){
//				TreeTemplateNode<String> node=list.get(i);
//				sb.append("{ attributes : { \"id\" : \"");
//				sb.append(node.getTreeNode());
//				sb.append("\"}, data : {\"title\": \"");
//				sb.append(node.getNodeName());
//				sb.append("\", \"attributes\":{\"href\":\"");
//				sb.append(node.getTreeNode());
//				sb.append("\", \"title\":\" ");
//				sb.append(node.getNodeName());
//				sb.append("\", \"fid\":\"");
//				sb.append(node.getTreeNode()).append("\"} }");
//				List<TreeTemplateNode<String>> sonList=apm.getchildNodes(node.getTreeNode(), clist);
//				if(sonList.size()>0){
//					sb.append(", children : ");
//					sb.append(toGetCooperator(node.getTreeNode(),clist));
//					sb.append(", state : \"open\"");
//				}
//				sb.append("}");
//				if(i<list.size()-1){
//					sb.append(",");
//				}
//			}
//			sb.append("]");
//		}catch(Exception e){
//		}finally{
//			return sb.toString();
//		}
//	}
//	
//	@RequestMapping(params = "method=cate")
//	public String getCategory(ModelMap map, HttpServletResponse response,HttpServletRequest request) throws Exception  {
//		String cid = request.getParameter("id");
//		String site = request.getParameter("site");
//		String method = request.getParameter("lib");
//		String type=request.getParameter("type");
////		System.out.println(cid);
////		System.out.println(site);
////		System.out.println(method);
//		Long id=0L;
//		Long siteId=1L;
//		if(StringUtils.isNotEmpty(cid)){
//			try {
//				id=Long.parseLong(cid);
//			} catch (NumberFormatException e) {
//			}
//		}
//		if(StringUtils.isNotEmpty(site)){
//			try {
//				siteId=Long.parseLong(site);
//			} catch (NumberFormatException e) {
//			}
//		}
//		List<Category> list=cam.getSonCategory(id,siteId,type);
//		
//		PrintWriter out = response.getWriter();
//		StringBuffer sb = new StringBuffer();
////		item.append("<root>").append("<item id=\"0\" >").append(
////				"<content><name ><![CDATA[数据库]]></name></content>").append(
////				"</item>").append("</root>");
////		out.write(item.toString());
////		out.flush();
//		
//		sb.append("[");
//		for(int i=0;list!=null && i<list.size();i++){
//			Category cat=list.get(i);
//			//{ attributes : { "id" : "node_2" }, data : "Node 2", state : "closed"},
//			sb.append("{ attributes : { \"id\" : \"");
//			sb.append(cat.getCategoryId());
//			sb.append("\"}, data : {\"title\": \"");
//			sb.append(cat.getCategoryName());
////			sb.append("\", \"attributes\":{\"href\":\"http://www.baidu.com/\"} }");
//			sb.append("\", \"attributes\":{\"href\":\"");
//			sb.append(method);
//			sb.append("\", \"title\":\"");
//			sb.append(cat.getCategoryId()).append("\"} }");
//			if(cam.getSonCategory(cat.getCategoryId(), siteId,type).size()>0){
//			sb.append(", state : \"closed\"");
//			}
//			sb.append("}");
//			if(i<list.size()-1){
//				sb.append(",");
//			}
//		}
//		sb.append("]");
////		System.out.println(sb.toString());
//		out.write(sb.toString());
//		out.flush();
////		map.put("tree", sb.toString());
////		return "test/treetest";
//		return null;
//	}

}
