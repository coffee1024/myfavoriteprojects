package com.baidu.beans;
import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Tieba {
		
		/**
		 * 获得贴吧主页所有帖子的页面地址
		 * @return ArrayList<String 地址链接>
		 * @throws IOException 
		 */
		public static HashMap<String,String> getHomePageHashMap(String homePage) throws IOException{
			if(homePage.contains("http://tieba.baidu.com/")){
				Document doc = Jsoup.connect(homePage).get();
		        Elements links = doc.select("a[href*=/p/]");
		        HashMap<String,String> hs=new HashMap<String,String>();
		        for (Element link : links) {
		        	if(link.attr("abs:title")!=null&&(!link.attr("abs:title").equals(""))){
			            hs.put(link.attr("abs:href"),link.attr("abs:title").replace("http://tieba.baidu.com/","").replace("/", "").replace("\\", ""));
		        	}
		        }
				return hs;
			}else{
				return null;
			}
		}	
		
		
		
		/**
		 * 获得明细页内所有的图片
		 * @param detailsPage
		 * @return
		 * @throws IOException
		 */
		public static Set<String> getDetailsPageImageList(String detailsPage) throws IOException{
			if(detailsPage.contains("http://tieba.baidu.com/p/")){
		        Set<String> set=new HashSet<String>();
				//读取第一页，查看一共有多少页
				detailsPage=detailsPage+"?see_lz=1";
				Document doc = Jsoup.connect(detailsPage).get();
				//获得本帖子共有多少页
				Elements totalPage = doc.select("span[class=red]");
				int pageNumber=0;
				 for (Element src : totalPage) {
					 try{
						 if(pageNumber==0){
							 pageNumber=Integer.parseInt(src.text());
						 }
					 }catch(Exception e){
						 System.out.println("总页码数转换失败");
					 }
				 }
				for(int i=1;i<=pageNumber;i++){
					doc = Jsoup.connect(detailsPage+"&pn="+i).get();
			        Elements image = doc.select("img[src*=imgsrc.baidu.com/forum/]");
			        for (Element src : image) {
			                try{
			                	int width=Integer.parseInt(src.attr("width"));
			                	int height=Integer.parseInt(src.attr("height"));
			                	if(width>=400&&height>=400){
			                		set.add(src.attr("abs:src"));
			                	}
			                }catch(Exception e){
			                	System.out.println("图片宽度和高度转换失败");
			                }
			        }
				}
				return set;
			}else{
				return null;
			}
		}
}
