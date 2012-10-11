package com.travel.test;

import com.travel.utils.DESUtil;
import com.travel.utils.LoadSuggestWords;

/**
 * @ author 刘光强
 * @ date 2012-3-19 下午3:24:27 
 * @ version 1.0
 **/
public class Test {
	public static void main(String[] args) throws Exception {
//		 AdminUser adminUser = new AdminUser();
//		 adminUser.setUserName("root");
//		 adminUser.setPassword("root");
//		 adminUser.setPhone("18618435430");
//		 adminUser.setDescription("很好很好的人");
//		 adminUser.setAdminGroup(DaoFactory.getGroupDao().getGroupById(1));
//		 DaoFactory.getUserDao().addUser(adminUser);
		// System.out.println(DaoFactory.getUserDao().getUserByName("admin").getAdminGroup());
		// LoginManager loginManager=new LoginManager();
		// System.out.println(loginManager.doLogin("admin", "admin"));
		// String searchword =
		// java.net.URLDecoder.decode("127.0.0.1%3A1016%2Fupload%2F&avatartype=virtual",
		// "utf-8");
		// System.out.println(searchword);
		// System.out.println(java.net.URLEncoder.encode("10.0.0.9/csipuser/fileupload"));
//		 Runtime rt = Runtime.getRuntime();
//		 Process process =rt.exec("mysqldump");
//		System.out.println(DaoFactory.getUserDao().getUserById(1));
//		System.out.println(DBUtils.backup());
//		System.out.println(DBUtils.load("H:/DBBACK/2012-04-25_13.42.46.bak"));
		 // Execute the request
//		 BufferedReader in = new BufferedReader(
//			       new InputStreamReader(response.getEntity().getContent(),"utf-8"));
//		 StringBuffer resultBuffer = new StringBuffer();
//	       String inputLine = null;
//	       while((inputLine = in.readLine()) != null)
//	       {
//	         resultBuffer.append(inputLine);
//	         resultBuffer.append("\n");
//	       }
//	       in.close();
//	       System.out.println(resultBuffer.toString());
//		HttpClient client=new DefaultHttpClient();
//		HttpGet httpget = new HttpGet("http://webservice.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx/getCountryCityByIp?theIpAddress=221.216.137.109");
//		HttpResponse response = client.execute(httpget);
//		 	SAXReader saxReader = new SAXReader();
//			Document document= saxReader.read(response.getEntity().getContent());
//			Element element=document.getRootElement();
//			Element e=(Element)element.elements().get(1);
//			System.out.println(e.getTextTrim());
//		client.getConnectionManager().shutdown();
//		
//		int a=DaoFactory.getRouteDao().getCount();
//		java.util.List list=DaoFactory.getRouteDao().getAllRoutes();
//		System.out.println(a);
//		System.out.println(list);
//		System.out.println(StringUtils.trim(null));
//		String IDNEX_PATH = "E:/travel/customer";
//		BooleanClause.Occur[] flags = {BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
//		List list=BaseSearch.search(IDNEX_PATH,new String[] {"*","李岩","测试"}, new String[] {"user_id","user_name","description"},flags,1,10);
//		System.out.println(list.size());
//		list.clear();
//		System.out.println(list.size());
//		for (int i = 0; i < list.size(); i++) {
//			List<String> list2=(List<String>)list.get(i);
//			for (int j = 0; j < list2.size(); j++) {
//				System.out.println(list2.get(j));
//			}
//			System.out.println("----------------------------------");
//		}
//		BufferedReader b=new BufferedReader(new FileReader(new File("D:/WORK/paoding/dic/locale/cc_cedict.dic")));
//		BufferedWriter w=new BufferedWriter(new FileWriter(new File("D:/WORK/paoding/dic/locale/cc_cedict_new.dic")));
//		String string="";
//		while ((string=b.readLine())!=null) {
//			if (string.length()!=1) {
//				System.out.println(string);
//				w.write(string+"\r\n");
//			}
//		}
//		b.close();
//		w.flush();
//		w.close();
//		BufferedReader b=new BufferedReader(new FileReader(new File("H:/各种词典/pinyin_new.txt")));
//		BufferedWriter w=new BufferedWriter(new FileWriter(new File("H:/各种词典/pinyin_new_new.txt")));
//		String string="";
//		String[] strs;
//		while ((string=b.readLine())!=null) {
//				strs=string.split(" ");
//				System.out.println(strs[1]+" "+strs[0]);
//				w.write(strs[1]+" "+strs[0]+"\r\n");
//		}
//		b.close();
//		w.flush();
//		w.close();
//	}
//		String string="简单来说就是网上邻居使用此地址传输IP地址由两主机部分组成，即网主机络地址主机和主机地址。网络地址主机表主机示其属于互联主机网的哪一个网络，主机地址表示其属于该网络中的哪一台主机主机。二者是主从关系。IP地址的四大类型标识的是网络中的某台主机。　IPv4的地址长度为32位，共4个字节，但实际中我们用点分十进制记法。在有类IP地址的规定中，第一部分是1~126为A类地址，128~191为B类地址，那么中间留的127.0.0.1被称为本地回环地址，主要作用有两个：一是主机测试本机主机的网络主机配置，能PING通127.0.0.1说明本机的网卡和IP协议安装都没有问题；另一个作用是某些SERVER/CLIENT的应用程序在运行时需调用服务器上的资源，一般要指定SERVER的IP地址，但当该程序要在同一台机器上运行而没有别的SERVER时就可以把SERVER的资源装在本机，SERVER的IP地址设为127.0.0.1也同样可以运行。";
//		System.out.println(GetKeyWords.cutKeyWordsString(string,5));
//		String string="liuguangqiang";
//		System.out.println(Base64Encoder.encode(string.getBytes()));
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put("lgq", 1);
//		map.put("lgq", 2);
//		System.out.println(map.get("lgq"));
//		System.out.println(1%10000);
//		IndexSuggestWords suggestWords=new IndexSuggestWords();
//		suggestWords.init();
//		File file =new File("E:/temp.txt");
//		file.createNewFile();
//		DeleteIndex.delete("E:/travel/travelagency", "2", "id");
//		LoadSuggestWords loadSuggestWords=new LoadSuggestWords();
//		loadSuggestWords.init();
//		DaoFactory.getSuggestWordDao().insertResultSuggest("刘光强");
//		List<LuceneTable> tables=LuceneConfig.tables;
//		System.out.println(tables.get(0).getTableName());
//		System.out.println(tables.get(0).getFields().get(0).getFieldName());
//		System.out.println(tables.get(0).getFields().get(0).getIndex());
		DESUtil desUtil=new DESUtil();
		System.out.println(desUtil.encrypt("admin"));
	}
}

