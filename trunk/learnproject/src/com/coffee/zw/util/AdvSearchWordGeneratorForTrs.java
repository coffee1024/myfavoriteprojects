package com.coffee.zw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * TRSServer数据库检索条件工具类
 * 
 * @author Stone.W
 * 
 */
public class AdvSearchWordGeneratorForTrs {
	/**
	 * 根据请求获取检索条件，返回检索条件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getCondition(HttpServletRequest request)
			throws Exception {

		if (StringUtils.isNotBlank(request.getParameter("searchExp"))) {
			return request.getParameter("searchExp");
		} else {
			// 拼接最终检索条件
			StringBuffer hql = new StringBuffer();
			
			// 定义一个stringBuffer用于拼接临时检索语句
			StringBuffer searchHql = new StringBuffer();
			
			// 针对资料库的高级检索，必须应该是已编状态的图片
//			searchHql.append(" and pictureState = " + PicStateConstants.ArchivePictureState.EDITED.getValue());
			// 默认站点信息
			searchHql.append(" and siteId = " + Integer.parseInt(Config.DEFAULT_SITE));
			// 默认显示未删图片，查看已删需要权限
			searchHql.append(" and deleteFlag = 0");
			// 锁定状态
			String isLock = request.getParameter("pictureLock");
			if(StringUtils.isNotEmpty(isLock)){
				int lock = Integer.parseInt(isLock);
				switch(lock){
					case 0:break;
					case 1:
						searchHql.append(" and isLocked = 0");
						break;
					case 2:
						searchHql.append(" and isLocked = 1");
						break;
				}
			}
			// 图片密级，默认显示普通图片，查看其它密级图片需要权限
			String security = request.getParameter("pictureSecurity");
			if(StringUtils.isNotEmpty(security)){
				int sty = Integer.parseInt(security);
				switch(sty){
					case 0:
						searchHql.append(" and securityType = 0");
						searchHql.append(" and pictureState = " + PicStateConstants.ArchivePictureState.EDITED.getValue());
						break;
					case 1:
						searchHql.append(" and securityType = 1");
						searchHql.append(" and pictureState = " + PicStateConstants.ArchivePictureState.HOLDING.getValue());
						break;
					case 2:
						searchHql.append(" and securityType = 2");
						searchHql.append(" and pictureState = " + PicStateConstants.ArchivePictureState.HOLDING.getValue());
						break;
				}
			}else{
				searchHql.append(" and securityType = 0");
				searchHql.append(" and pictureState = " + PicStateConstants.ArchivePictureState.EDITED.getValue());
			}
			//图片类型 	新闻图片 or 历史图片
			String isHistory = request.getParameter("pictureType");
			if(StringUtils.isNotEmpty(isHistory)){
				int history = Integer.parseInt(isHistory);
				if(history!=0){
					searchHql.append(" and isHistoryPicture = "+(history-1));
				}
			}
			//成品、选用见报条件
			String incpk=request.getParameter("incpk");
			if(StringUtils.isNotEmpty(incpk)){
				int cpk=Integer.parseInt(incpk);
				String ischoose=request.getParameter("fzcbStatus");
				if(StringUtils.isNotEmpty(ischoose)){
					int choose=Integer.parseInt(ischoose);
					if(cpk==1||cpk==-1){//选择是成品
						switch(choose){
						case -1:
							if(cpk==-1){
								break;
							}else{
								searchHql.append(" and isreferredbyproduct = '%1'");
							}
							break;
						case 1://选用
							searchHql.append(" and isreferredbyproduct = '%11'");
							break;
						case 2://见报
							searchHql.append(" and isreferredbyproduct = '%1?1'");
							break;
						}
					}else if(cpk==0){//选择不是成品
						searchHql.append(" and isreferredbyproduct not = '%1'");
					}
				}
			}
			
			// 定义一个List，用来保存需要对哪些字段进行检索
			List<String> colums = new ArrayList<String>();
			// 若为模糊检索，对所有字段进行过滤
			if (request.getParameter("searchType").equals("0")) {
				colums.add("id");
				colums.add("title");
				colums.add("keywords");
				colums.add("place");
				colums.add("memo");
				colums.add("exModel");
				colums.add("sourcepictureid");
				colums.add("sourcepicturename");
				colums.add("filename");
				colums.add("uploader");
				colums.add("authorname");
			} else {
				// 若为按字段检索，则对选择的字段进行过滤
//				String[] columsString = request.getParameterValues("colums");
//				if (columsString != null) {
//					for (int i = 0; i < columsString.length; i++) {
//						if (StringUtils.isNotBlank(columsString[i])) {
//							colums.add(columsString[i]);
//						}
//					}
//				}
				if(StringUtils.isNotBlank(request.getParameter("cb_id"))){
					colums.add("id");
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_title"))){
					colums.add("title");		
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_keyword"))){
					colums.add("keywords");
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_memo"))){
					colums.add("place");
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_place"))){
					colums.add("memo");
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_exModel"))){
					colums.add("exModel");
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_authorName"))){
					colums.add("authorname");
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_filename"))){
					colums.add("filename");
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_sourcePD"))){
					colums.add("sourcepictureid");		
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_uploader"))){
					colums.add("uploader");
				}
				if(StringUtils.isNotBlank(request.getParameter("cb_sourcePN"))){
					colums.add("sourcepicturename");
				}
			}


			if (colums.size() > 0) {
				// 与输入框中的全部字词有关
				if (StringUtils.isNotBlank(request
						.getParameter("andSearchText"))) {
					String[] andSearchText = SearchStringUtils.toTrsString(
							request.getParameter("andSearchText")).trim()
							.split(" ");
					for (int i = 0; i < andSearchText.length; i++) {
						if (StringUtils.isNotBlank(andSearchText[i])) {
							searchHql.append(" and ( ");
							int k = 0;
							for (int j = 0; j < colums.size(); j++) {
								if (!colums.get(j).equals("id")) {
//									if(colums.get(j).equals("title")){
//										if (k == 0) {
//											searchHql.append("title = " + andSearchText[i]);
//											k++;
//										} else {
//											searchHql.append(" or title = " + andSearchText[i]);
//										}
//									}else{
										if (k == 0) {
											searchHql.append(colums.get(j) + " = %"
													+ andSearchText[i] + "%");
											k++;
										} else {
											searchHql.append(" or " + colums.get(j)
													+ " = %" + andSearchText[i]
													+ "%");
										}
//									}
								} else {
									if (StringUtils.isNumeric(andSearchText[i])
											&& andSearchText[i].compareTo("9223372036854775807") <= 0 && andSearchText[i].length() <= 19) {
										Long id = Long
												.parseLong(andSearchText[i]);
										searchHql.append("id = " + id);
									} else {
										searchHql.append("id = 1.11111");
									}
									k++;
								}
							}
							searchHql.append(" )");
						}
					}
				}
				// 与输入框中的字词完全匹配
				if (StringUtils.isNotBlank(request
						.getParameter("sameSearchText"))) {
					String sameSearchText = SearchStringUtils.toTrsString(
							request.getParameter("sameSearchText")).trim();
					searchHql.append(" and ( ");
					int k = 0;
					for (int i = 0; i < colums.size(); i++) {
						if (!colums.get(i).equals("id")) {
							if (k == 0) {
								searchHql.append(colums.get(i) + " = "
										+ sameSearchText);
								k++;
							} else {
								searchHql.append(" or " + colums.get(i) + " = "
										+ sameSearchText);
							}
						} else {
							if (StringUtils.isNumeric(sameSearchText)
									&& sameSearchText.compareTo("9223372036854775807") <= 0 && sameSearchText.length() <= 19) {
								Long id = Long.parseLong(sameSearchText);
								searchHql.append("id = " + id);
							} else {
								searchHql.append("id = 1.11111");
							}
							k++;
						}
					}
					searchHql.append(" ) ");
				}
				// 与输入框中的任何一个字词有关
				if (StringUtils
						.isNotBlank(request.getParameter("orSearchText"))) {
					String[] orSearchText = SearchStringUtils.toTrsString(
							request.getParameter("orSearchText")).trim().split(" ");
					int m = 0;
					for (int i = 0; i < orSearchText.length; i++) {
						if (StringUtils.isNotBlank(orSearchText[i])) {
							if (m == 0) {
								searchHql.append(" and (( ");
								m++;
							} else {
								searchHql.append(" or ( ");
							}
							int k = 0;
							for (int j = 0; j < colums.size(); j++) {
								if (!colums.get(j).equals("id")) {
//									if(colums.get(j).equals("title")){
//										if (k == 0) {
//											searchHql.append("title = " + orSearchText[i]);
//											k++;
//										} else {
//											searchHql.append(" or title = " + orSearchText[i]);
//										}
//									}else{
										if (k == 0) {
											searchHql.append(colums.get(j) + " = %"
													+ orSearchText[i] + "%");
											k++;
										} else {
											searchHql.append(" or " + colums.get(j)
													+ " = %" + orSearchText[i] + "%");
										}
//									}
								} else {
									if (StringUtils.isNumeric(orSearchText[i])
											&& orSearchText[i].compareTo("9223372036854775807") <= 0 && orSearchText[i].length() <= 19) {
										Long id = Long
												.parseLong(orSearchText[i]);
										searchHql.append("id = " + id);
									} else {
										searchHql.append("id = 1.11111");
									}
									k++;
								}
							}
							searchHql.append(" )");
						}
					}
					searchHql.append(")");
				}
				// 与输入框中的字词均无关
				if (StringUtils.isNotBlank(request
						.getParameter("notSearchText"))) {
					String[] notSearchText = SearchStringUtils.toTrsString(
							request.getParameter("notSearchText")).trim()
							.split(" ");
					for (int i = 0; i < notSearchText.length; i++) {
						if (StringUtils.isNotBlank(notSearchText[i])) {
							searchHql.append(" and ( ");
							int k = 0;
							for (int j = 0; j < colums.size(); j++) {
								if (!colums.get(j).equals("id")) {
//									if(colums.get(j).equals("title")){
//										if (k == 0) {
//											searchHql.append("title != " + notSearchText[i]);
//											k++;
//										} else {
//											searchHql.append(" and title != " + notSearchText[i]);
//										}
//									}else{
										if (k == 0) {
											searchHql.append(colums.get(j)
													+ " != %" + notSearchText[i] + "%");
											k++;
										} else {
											searchHql.append(" and "
													+ colums.get(j) + " != %"
													+ notSearchText[i] + "%");
										}
//									}
								} else {
									if (StringUtils.isNumeric(notSearchText[i])
											&& notSearchText[i].compareTo("9223372036854775807") <= 0 && notSearchText[i].length() <= 19) {
										Long id = Long
												.parseLong(notSearchText[i]);
										searchHql.append("id != " + id);
									} else {
										searchHql.append("id != 1.11111");
									}
									k++;
								}
							}
							searchHql.append(" )");
						}
					}
				}
			}
			// 图片宽度的检索条件
			if (StringUtils.isNotBlank(request.getParameter("pictureWidth"))
					&& StringUtils.isNumeric(request
							.getParameter("pictureWidth"))) {
				Integer pictureWidth = Integer.parseInt(request
						.getParameter("pictureWidth"));
				String pictureWidthType = request
						.getParameter("pictureWidthType");
				if (pictureWidthType.equals("0")) {
					searchHql.append(" and pictureWidth = " + pictureWidth);
				}
				if (pictureWidthType.equals("1")) {
					searchHql.append(" and pictureWidth > " + pictureWidth);
				}
				if (pictureWidthType.equals("2")) {
					searchHql.append(" and pictureWidth < " + pictureWidth);
				}
			}
			// 图片高度的检索条件
			if (StringUtils.isNotBlank(request.getParameter("pictureHeight"))
					&& StringUtils.isNumeric(request
							.getParameter("pictureHeight"))) {
				Integer pictureHeight = Integer.parseInt(request
						.getParameter("pictureHeight"));
				String pictureHeightType = request
						.getParameter("pictureHeightType");
				if (pictureHeightType.equals("0")) {
					searchHql.append(" and pictureHeight = " + pictureHeight);
				}
				if (pictureHeightType.equals("1")) {
					searchHql.append(" and pictureHeight > " + pictureHeight);
				}
				if (pictureHeightType.equals("2")) {
					searchHql.append(" and pictureHeight < " + pictureHeight);
				}
			}
			
			/*// 发布时间的检索条件
			if (StringUtils.isNotBlank(request.getParameter("pDateBegin"))) {
				Date pDateBegin = null;
				try {
					pDateBegin = new SimpleDateFormat("yyyy-MM-dd")
							.parse(request.getParameter("pDateBegin"));
					String pdb = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
							.format(pDateBegin);
					if (StringUtils.isNotBlank(pdb)) {
						searchHql.append(" and publishTime >= " + pdb);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotBlank(request.getParameter("pDateEnd"))) {
				Date pDateEnd = null;
				try {
					pDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request
							.getParameter("pDateEnd"));
					// 对得到的日期加上一天
					pDateEnd = org.apache.commons.lang.time.DateUtils.addDays(
							pDateEnd, 1);
					String pde = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
							.format(pDateEnd);
					if (StringUtils.isNotBlank(pde)) {
						searchHql.append(" and publishTime < " + pde);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}*/
			
			// 上传时间的检索条件
			if (StringUtils.isNotBlank(request.getParameter("pDateBegin"))) {
				Date pDateBegin = null;
				try {
					pDateBegin = new SimpleDateFormat("yyyy-MM-dd")
							.parse(request.getParameter("pDateBegin"));
					String pdb = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
							.format(pDateBegin);
					if (StringUtils.isNotBlank(pdb)) {
						searchHql.append(" and createTime >= " + pdb);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotBlank(request.getParameter("pDateEnd"))) {
				Date pDateEnd = null;
				try {
					pDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request
							.getParameter("pDateEnd"));
					// 对得到的日期加上一天
					pDateEnd = org.apache.commons.lang.time.DateUtils.addDays(
							pDateEnd, 1);
					String pde = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
							.format(pDateEnd);
					if (StringUtils.isNotBlank(pde)) {
						searchHql.append(" and createTime < " + pde);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}	
			// 拍摄时间的检索条件
			if (StringUtils.isNotBlank(request.getParameter("tDateBegin"))) {
				Date tDateBegin = null;
				try {
					tDateBegin = new SimpleDateFormat("yyyy-MM-dd")
							.parse(request.getParameter("tDateBegin"));
					String tdb = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
							.format(tDateBegin);
					if (StringUtils.isNotBlank(tdb)) {
						searchHql.append(" and filmTime >= " + tdb);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotBlank(request.getParameter("tDateEnd"))) {
				Date tDateEnd = null;
				try {
					tDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request
							.getParameter("tDateEnd"));
					// 对得到的日期加上一天
					tDateEnd = org.apache.commons.lang.time.DateUtils.addDays(
							tDateEnd, 1);
					String tde = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
							.format(tDateEnd);
					if (StringUtils.isNotBlank(tde)) {
						searchHql.append(" and filmTime < " + tde);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			// 图片比例的检索条件
			if (StringUtils.isNotBlank(request.getParameter("picProportion"))
					&& StringUtils.isNumeric(request
							.getParameter("picProportion"))) {
				int picProportion = Integer.parseInt(request
						.getParameter("picProportion"));
				switch (picProportion) {
				case 1:
					searchHql.append(" and orientation = 'landscape'");
					break;
				case 2:
					searchHql.append(" and orientation = 'portrait'");
					break;
				case 3:
					searchHql.append(" and orientation = 'square'");
					break;
				}
			}
			// 图片色调的检索条件
			if (StringUtils.isNotBlank(request.getParameter("picTone"))
					&& StringUtils.isNumeric(request.getParameter("picTone"))) {
				int picTone = Integer.parseInt(request.getParameter("picTone"));
				switch (picTone) {
				case 1:
					searchHql.append(" and colorType = 'red'");
					break;
				case 2:
					searchHql.append(" and colorType = 'orange'");
					break;
				case 3:
					searchHql.append(" and colorType = 'yellow'");
					break;
				case 4:
					searchHql.append(" and colorType = 'green'");
					break;
				case 5:
					searchHql.append(" and colorType = 'cyan'");
					break;
				case 6:
					searchHql.append(" and colorType = 'blue'");
					break;
				case 7:
					searchHql.append(" and colorType = 'purple'");
					break;
				case 8:
					searchHql.append(" and colorType = 'white'");
					break;
				case 9:
					searchHql.append(" and colorType = 'black'");
					break;
				}
			}
			// 图片格式的检索条件
			if (StringUtils.isNotBlank(request.getParameter("picFormat"))
					&& StringUtils.isNumeric(request.getParameter("picFormat"))) {
				int picFormat = Integer.parseInt(request
						.getParameter("picFormat"));
				switch (picFormat) {
				case 1:
					searchHql.append(" and filename = '%_t.jpg'");
					break;
				case 2:
					searchHql.append(" and filename = '%_p.jpg'");
					break;
				case 3:
					searchHql.append(" and ( filename = '%_r.jpg' or filename = '%_n.jpg' )");
					break;
				}
			}
			// 图片大小的检索条件
			if (StringUtils.isNotBlank(request.getParameter("pictureSize"))
					&& StringUtils.isNumeric(request
							.getParameter("pictureSize"))) {
				int pictureSize = Integer.parseInt(request
						.getParameter("pictureSize"));
				switch (pictureSize) {
				case 1:
					searchHql.append(" and pictureLength < 524288");
					break;
				case 2:
					searchHql
							.append(" and pictureLength >= 524288 and pictureLength <= 1048576");
					break;
				case 3:
					searchHql
							.append(" and pictureLength >= 1048576 and pictureLength <= 2097152");
					break;
				case 4:
					searchHql.append(" and pictureLength > 2097152");
					break;
				}
			}
			// 所属分类的检索条件
			if (StringUtils.isNotBlank(request.getParameter("categoryInfo"))) {
				String[] categoryAll = request.getParameter("categoryInfo")
						.split(";;");
				if (StringUtils.isNotBlank(categoryAll[0])) {
					String[] categoryIds = categoryAll[0].split(";");
					int k = 0;
					searchHql.append(" and ( ");
					for (int i = 0; i < categoryIds.length; i++) {
						if (StringUtils.isNotBlank(categoryIds[i])
								&& StringUtils.isNumeric(categoryIds[i])) {
							if (k == 0) {
								searchHql.append("categoryInfo = %" + categoryIds[i] + "%");
								k++;
							} else {
								searchHql.append(" or categoryInfo = %" + categoryIds[i] + "%");
							}
						}
					}
					searchHql.append(" )");
				}
			}
			 // 所属栏目的检索条件
			 if (StringUtils.isNotBlank(request.getParameter("channelInfo"))){
				 String[] channelIds = request.getParameter("channelInfo").split(";");
				 StringBuffer ids = new StringBuffer();
				 for (int i = 1; i < channelIds.length; i++) {
					 ids.append(channelIds[i]);
					 if(i < channelIds.length - 1){
						 ids.append(" or ");
					 }
				 }
				 searchHql.append(" and channelId = ( "+ ids.toString() + " )");
			 }
			// 截取 and或者 or开头的字符串
			if (searchHql.length() > 0) {
				String subString = "";
				if (searchHql.indexOf(" and") == 0) {
					subString = searchHql.substring(4, searchHql.length());
				} else if (searchHql.indexOf(" or") == 0) {
					subString = searchHql.substring(3, searchHql.length());
				} else {
					subString = searchHql.toString();
				}
				hql.append(subString);
			} else {
				// 无检索条件的情况
				hql = hql.append(" id != 1.11111 ");
			}
			// 高级检索语句
			String queryString = hql.toString();
			return queryString;
		}
	}
}