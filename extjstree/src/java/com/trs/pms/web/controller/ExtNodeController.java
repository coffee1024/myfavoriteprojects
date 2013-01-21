package com.trs.pms.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trs.pms.domain.ExtNode;
import com.trs.pms.domain.ExtTree;
import com.trs.pms.service.ExtNodeManager;

import freemarker.core.ReturnInstruction.Return;


/**
 * @Title 控制类
 * @CrTime 2012-8-3 上午10:10:47
 * @Version 1.0
 */
// 标注业务层组件
@Controller
@RequestMapping("/tree.do")
public class ExtNodeController extends BaseController<ExtNode, Long> {
	
	@Autowired
	ExtNodeManager extNodeManager;
	/**
	 * 分页获取本收藏夹下所有的图片，
	 * 当folderid为空时则获取所有收藏
	 * @throws IOException 
	 */
	//默认方法
	@RequestMapping
	public void getIndexInfo(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<ExtNode> list=extNodeManager.getAll();
		ExtNode rootExtNode=extNodeManager.get(1L);
//		System.out.println(list);
//		System.out.println(rootExtNode);
		ExtTree extTree=new ExtTree(rootExtNode, list);
		//封装方法根据需求自己重定义
		String json=extTree.toJsonTree();
		System.out.println(json);
		Writer writer=response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}
	@RequestMapping(params="method=add")
	public void add(ModelMap map, HttpServletRequest request,
			HttpServletResponse response){
		System.err.println("add");
		try {
			JSONObject object=readJsonFromRequest(request);
			String parentId=object.getString("parentId");
			String text=object.get("text").toString();
			String leaf=object.getString("leaf");
			ExtNode extNode=new ExtNode();
			extNode.setParentId(Long.parseLong(parentId));
			extNode.setText(text);
			extNode.setLeaf(Boolean.parseBoolean(leaf));
			extNodeManager.save(extNode);
			returnSuccessJson(response, extNode);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@RequestMapping(params="method=update")
	public void update(ModelMap map, HttpServletRequest request,
			HttpServletResponse response){
		System.err.println("update");
		try {
			JSONObject object = readJsonFromRequest(request);
			String id=object.get("id").toString();
//        String parentId=object.get("parentId").toString();
			String text=object.get("text").toString();
			ExtNode extNode=extNodeManager.get(Long.parseLong(id));
			extNode.setText(text);
			extNodeManager.save(extNode);
			returnSuccessJson(response, extNode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(params="method=delete")
	public void delete(ModelMap map, HttpServletRequest request,
			HttpServletResponse response){
		System.err.println("delete");
		try {
			JSONObject object=readJsonFromRequest(request);
			String id=object.get("id").toString();
			//String parentId=object.get("parentId").toString();
			extNodeManager.delete(Long.parseLong(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private JSONObject readJsonFromRequest(HttpServletRequest request) throws Exception{
		 BufferedReader br = new BufferedReader(new InputStreamReader(  
	              (ServletInputStream) request.getInputStream(), "utf-8"));  
		StringBuffer sb = new StringBuffer("");  
		String temp;  
		while ((temp = br.readLine()) != null) {  
		sb.append(temp);  
		}  
		br.close();  
		String acceptjson = sb.toString();  
		System.out.println(acceptjson);
		JSONObject object=JSONObject.fromObject(acceptjson);
		return object;
	}
	private void returnSuccessJson(HttpServletResponse response,ExtNode node) throws IOException{
		JSONObject object=JSONObject.fromObject(node);
//		String string="{\"success\": true,\"records \":["+object.toString()+"]}";
		String string="{\"success\": true}";
		System.out.println("return json:"+string);
		Writer out=response.getWriter();
		out.write(string);
		out.flush();
		out.close();
	}
}