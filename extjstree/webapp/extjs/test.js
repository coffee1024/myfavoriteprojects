Ext.require([
    'Ext.tree.*',
    'Ext.data.*'
]);
Ext.onReady(function() {
    
	Ext.define('My.Model.Test', {
	    extend: 'Ext.data.Model',
	    fields: [
	        { name: 'id', type: 'int' },
	        { name: 'text', type: 'string' }
	    ],
	    proxy : {
	        type: 'ajax',
	        api: {
	            create: 'tree.do?method=add',
	            read: 'tree.do',
	            update: 'tree.do?method=update',
	            destroy: 'tree.do?method=delete'
	        }
	    },
	    changeName: function(name) {
	        this.set('text',name);
	        this.save();
	    },
	    addChild: function(node){
	    	node.save();
	    	this.appendChild(node);
	    }
	});

	var store = Ext.create('Ext.data.TreeStore', {
	    model: 'My.Model.Test',
	    root: {
	    	id:-1,
	        text: '最大根节点',
	        expanded: true
	    },
	    autoLoad:false
	});

	var tree=Ext.create('Ext.tree.Panel', {
	    renderTo: "tree-div",
	    width: 300,
	    height: 600,
	    title: 'Test',
	    store: store
//	    columns: [
//	        { xtype: 'treecolumn', header: 'Name', dataIndex: 'text', flex: 1 }
//	    ]
	});
	 tree.on("itemcontextmenu",function(view,record,item,index,e,eOpts){
			e.preventDefault();
			e.stopEvent();
			var obj = record;
//			while (!obj.parentNode.isRoot()) {
//				obj = obj.parentNode;
//			}
			var rootId = obj.getId();
			if (rootId==-1) {
				alert("不能修改根节点");
				return;
			} else {
				var nodemenu = new Ext.menu.Menu({
					floating : true,
					items : [{
						text : "添加文件夹",
						// icon:'images/add.gif',
						// iconCls:'leaf',
						handler : function() {
							if (record.get('leaf') == false) {
								Ext.MessageBox.prompt("标题", "请输入",
										function(btn, text) {
											if (btn == "ok") {
												var node=Ext.create("My.Model.Test",{
													text : text,
													parentId:record.getId(),
													expanded : true,
													leaf : false
												});
												record.addChild(node);
												store.reload();
											}
										}, this, false, // 表示文本框为多行文本框
										"新添加文件夹");
							}

						},
						listeners : {
							render : function(com) {
								if(rootId==-1||record.get('leaf') == true)
								com.setVisible(false);
							}
						}
					}, {
						text : "添加子节点",
						handler : function() {
							Ext.MessageBox.prompt("标题", "请输入",
									function(btn, text) {
										if (btn == "ok") {
											var node=Ext.create("My.Model.Test",{
												text : text,
												parentId:record.getId(),
												expanded : false,
												leaf : true
											});
											record.addChild(node);
											store.reload();
										}
									}, this, false, // 表示文本框为多行文本框
									"新添加子节点");
						},
					listeners : {
						render : function(com) {
							//测试代码
							if(rootId==-1||record.get('leaf') == true)
							com.setVisible(false);
						}
					}
					}, {
						text : "编辑",
						// icon:'images/leaf.gif',
						// iconCls:'leaf',
						handler : function() {
							Ext.MessageBox.prompt("标题", "请输入",
									function(btn, text) {
										if (btn == "ok") {
											record.changeName(text);
										}
									}, this, false, // 表示文本框为多行文本框
									record.data.text);
						}
					}, {
						text : "删除",
						// icon:'images/delete.gif',
						// iconCls:'leaf',
						handler : function(){
							Ext.MessageBox.confirm("确认","确定删除该节点？",
								function(btn){
								if (btn=="yes") {
									record.remove(record);
								}
							},null);
						}
					}]
				});
				nodemenu.showAt(e.getXY());
			}
		});
});
