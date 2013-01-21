package com.trs.pms.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *@author  liuguangqiang
 *@date    2012-11-21 下午12:05:21
 *@version 1.0
 **/
public class ExtTree {
		 private StringBuffer returnStr = new StringBuffer();

		 /**
		  * 具有权限的叶子树列表
		  */
		 private List<ExtNode> leafList = new ArrayList<ExtNode>();
		 /**
		  * 根据叶子节点所生成的菜单树列表
		  */
		 private List<ExtNode> menuListTree = new ArrayList<ExtNode>();

		 /**
		  * 生成标准的josntree列表
		  */
		 private List<ExtNode> treeList = new ArrayList<ExtNode>();

		 /**
		  * 是否是权限树的标志,当标志树为true时,生成权限树.注:把所有的叶子节点上加上checkbox对话框.
		  */
		 private boolean isAuthorityTree = false;

		 /**
		  * 根据这个跟节点生成树.
		  */
		 private ExtNode root = null;

		 /**
		  * 构造一个标准树或一个权限树的实例.
		  * 
		  * @param root
		  *            ExtNode 树的根节点.根据这个根节点从treeList列表中生成树.
		  * @param treeList
		  *            List<ExtNode> 树列表,来自数据库的权限表.
		  */
		 public ExtTree(ExtNode root, List<ExtNode> treeList) {// 构造方法里初始化模拟List
		  this.root = root;
		  this.treeList = treeList;
		 }

		 /***************************************************************************
		  * 构造一个根据叶子权限节点列表,生成的树的实例.
		  * 
		  * @param root
		  *            ExtNode 树的根节点.根据这个根节点从treeList列表中生成树.
		  * @param leafList
		  *            List<ExtNode> 叶子节点的列表.来自数据库中的权限表.
		  * @param treeList
		  *            List<ExtNode> 树列表,来自数据库的权限树表.
		  */
		 public ExtTree(ExtNode root, List<ExtNode> leafList, List<ExtNode> treeList) {
		  this.root = root;
		  this.leafList = leafList;
		  this.treeList = treeList;

		 }

		 /**
		  * 判断该节点是否有子节点
		  * 
		  * @param node
		  * @return 有子节点放回true.
		  */
		 public boolean hasChild(ExtNode node) {
		  return getChildList(node).size() > 0 ? true : false;
		 }

		 /**
		  * 获取该节点的子节点列表.
		  * 
		  * @param node
		  * @return 返回子节点列表.
		  */
		 public List<ExtNode> getChildList(ExtNode node) {
		  List<ExtNode> li = new ArrayList<ExtNode>();
		  Iterator<ExtNode> it = this.treeList.iterator();
		  while (it.hasNext()) {
		   ExtNode n = (ExtNode) it.next();
		   if (n.getParentId() == node.getId()) {
		    li.add(n);
		   }
		  }
		  return li;
		 }

		 /**
		  * 更具该节点的父节点id 获取父节点对象.
		  * 
		  * @param pid
		  * @return 返回父节点对象.
		  */
		 public ExtNode getParentNode(Long pid) {
		  ExtNode pNode = null;
		  Iterator<ExtNode> it = this.treeList.iterator();
		  while (it.hasNext()) {
		   ExtNode n = (ExtNode) it.next();
		   if (pid == 0) {
		    break;
		   }
		   if (pid == n.getId()) {
		    pNode = n;
		    break;
		   }
		  }
		  return pNode;
		 }

		 /**
		  * 用递归方法计算出该节点的所有子节点.(如果this.isAuthorityTree ==
		  * ture生成权限树字符串,在叶子节点上加上checkbox选项框)
		  * 
		  * @param node
		  */
		 public void recursionAllSubNodeFn(ExtNode node) {
		  if (hasChild(node)) {
		   returnStr.append("{id:");
		   returnStr.append(node.getId());
		   returnStr.append(",parentId:");
		   returnStr.append(node.getParentId());
		   returnStr.append(",text:");
		   returnStr.append("\"" + node.getText() + "\"");
		   returnStr.append(",expanded:true");
		   returnStr.append(",children:[");
		   List<ExtNode> childList = getChildList(node);
		   Iterator<ExtNode> it = childList.iterator();
		   while (it.hasNext()) {
		    ExtNode n = (ExtNode) it.next();
		    recursionAllSubNodeFn(n);
		   }
		   returnStr.append("]},");
		  } else {
		   returnStr.append("{id:");
		   returnStr.append(node.getId());
		   returnStr.append(",parentId:");
		   returnStr.append(node.getParentId());
		   returnStr.append(",text:");
		   returnStr.append("\"" + node.getText() + "\"");
//		   if (isAuthorityTree && node.getId() != 1) {
//		    returnStr.append(",leaf:");
//		    returnStr.append(node.getLeaf());
//		   }
//		   returnStr.append(",leaf:true},");
		   
		   returnStr.append(",leaf:");
		   returnStr.append(node.getLeaf());
		   returnStr.append("},");
		  }

		 }

		 /**
		  * 用递归方法计算出该叶子节点的父节点树.并把这些父节点添加到父节点树中(this.menuTreeList).
		  * 
		  * @param node
		  */
		 public void recursionAllPNodeFn(ExtNode node) {
		  node = this.getParentNode(node.getParentId());
		  if (node == null) {
		   return;
		  }

		  if (this.menuListTree.contains(node)) {
		   return;
		  }
		  this.menuListTree.add(node);
		  recursionAllPNodeFn(node);

		 }

		 /**
		  * 修饰一下才能满足Extjs的Json格式.
		  * 
		  * @param returnStr
		  *            用递归方法生成的jonstree字符串.
		  * @return 返回满足Extjs动态树的Json格式字符串.
		  */
		 public String modifyStr(String returnStr) {
		  return ("[" + returnStr + "]").replaceAll(",]", "]");

		 }

		 /**
		  * 根据叶子节点权限列表生成菜单树.
		  * 
		  * @param leafList
		  * @return 返回一个菜单树列表.
		  */
		 public List<ExtNode> getMenuTreeList(List<ExtNode> leafList) {
		  Iterator<ExtNode> it = leafList.iterator();
		  while (it.hasNext()) {
		   ExtNode node = (ExtNode) it.next();
		   this.recursionAllPNodeFn(node);
		  }

		  return this.menuListTree;
		 }

		 /**
		  * 根据数据源生成标准的exjts 动态树字符串.
		  * 
		  * @return 返回标准树字符串.
		  */
		 public String toJsonTree() {

		  /*
		   * ----测试数据---- Iterator it = this.treeList.iterator(); while
		   * (it.hasNext()) { ExtNode n = (ExtNode) it.next();
		   * System.out.println("nodeid" + n.getId() + " nodepid" +
		   * n.getParentId()); } ----测试数据----
		   */
		  this.recursionAllSubNodeFn(this.root);
		  return this.modifyStr(this.returnStr.toString());
		 }
		 

		 /**
		  * 根据数据源生成具有权限的exjts 动态树字符串.
		  * 
		  * @return 返回权限树字符串.
		  */
		 public String toJsonAuthoratyTree() {
		  this.isAuthorityTree = true;
		  return this.toJsonTree();
		 }

		 /**
		  * 根据数据源和权限列表,生成extjs动态树的权限菜单字符串.
		  * 
		  * @return 返回菜单列表字符串.
		  */
		 public String toJsonMenuTree() {
		  this.treeList = this.getMenuTreeList(this.leafList);
		  this.isAuthorityTree = false;
		  return this.toJsonTree();
		 }

		}

