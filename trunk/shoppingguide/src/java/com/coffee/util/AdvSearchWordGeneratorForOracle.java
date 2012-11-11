package com.coffee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 * Oracle数据库检索条件工具类
 * 
 * 
 */
public class AdvSearchWordGeneratorForOracle {
	/**
	 * 根据请求获取检索条件，并将参数信息存放到map中
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getCondition(HttpServletRequest request)
			throws Exception {

		// 定义一个Map，用来保存检索条件的参数信息
		Map<String, Object> map = new Hashtable<String, Object>();
		// 拼接最终检索条件
		StringBuffer hql = new StringBuffer();
		String showType=request.getParameter("showType");
		if(StringUtils.isNotBlank(showType)){
			if(showType.equals("0")){
				hql.append("select T1,T3 from ArchivePicture T1,PictureOrganization T2,PictureGroup T3 where T1.id=T2.pictureId and T2.groupId=T3.id and ");//和组图关联表做连接查询
			}
			else if(showType.equals("1")){
				//以组图的形式展现
				hql.append("select distinct T3 from ArchivePicture T1,PictureOrganization T2,PictureGroup T3 where T1.id=T2.pictureId and T2.groupId=T3.id and ");
			}
			else
			{
				//默认以图片形式展现
				hql.append("select T1,T3 from ArchivePicture T1,PictureOrganization T2,PictureGroup T3 where T1.id=T2.pictureId and T2.groupId=T3.id and ");//和组图关联表做连接查询
			}
		}
		// 定义一个stringBuffer用于拼接临时检索语句
		StringBuffer searchHql = new StringBuffer();

		String picstate=null;
		Object obj = request.getAttribute("picstate");
		if (obj != null) {
			picstate = obj.toString();
		}
		
		// 针对资料库的高级检索，必须应该是已编状态的图片
		searchHql.append(" and T3.status =:picstate");
		
	     //String picstate=request.getParameter("picstate");
		
		if(StringUtils.isNotBlank(picstate)&&StringUtils.isNumeric(picstate)){
			map.put("picstate", Integer.parseInt(picstate));
		}else{
			map.put("picstate", PicStateConstants.ArchivePictureState.EDITED.getValue());
		}
		
		// 默认站点信息
		searchHql.append(" and T1.siteId = :siteId");
		map.put("siteId", Integer.parseInt(Config.DEFAULT_SITE));
		// 默认显示未删图片，查看已删需要权限
		searchHql.append(" and T2.deleteFlag = :delflag");
		map.put("delflag", 0);
		//默认显示所在组图未删除的图片
		searchHql.append(" and T3.deleteFlag = :delflag");
		map.put("delflag", 0);
		// 锁定状态
		String isLock = request.getParameter("pictureLock");
		if(StringUtils.isNotEmpty(isLock)){
			int lock = Integer.parseInt(isLock);
			switch(lock){
				case 0:break;
				case 1:
					searchHql.append(" and T1.isLocked = :isLock");
					map.put("isLock", 0);
					break;
				case 2:
					searchHql.append(" and T1.isLocked = :isLock");
					map.put("isLock", 1);
					break;
			}
		}
		// 图片密级，默认显示普通图片，查看其它密级图片需要权限
		String security = request.getParameter("pictureSecurity");
		if(StringUtils.isNotEmpty(security)){
			int sty = Integer.parseInt(security);
			switch(sty){
				case 0:
					searchHql.append(" and T1.securityType = :security");
					map.put("security", 0);
					//map.put("picstate", PicStateConstants.ArchivePictureState.EDITED.getValue());
					break;
				case 1:
					searchHql.append(" and T1.securityType = :security");
					map.put("security", 1);
					//map.put("picstate", PicStateConstants.ArchivePictureState.HOLDING.getValue());
					break;
				case 2:
					searchHql.append(" and T1.securityType = :security");
					map.put("security", 2);
					//map.put("picstate", PicStateConstants.ArchivePictureState.HOLDING.getValue());
					break;
			}
		}else{
			searchHql.append(" and T1.securityType = :security");
			map.put("security", 0);
			//map.put("picstate", PicStateConstants.ArchivePictureState.EDITED.getValue());
		}
		//图片类型 	新闻图片 or 历史图片
		String isHistory = request.getParameter("pictureType");
		if(StringUtils.isNotEmpty(isHistory)){
			int history = Integer.parseInt(isHistory);
			if(history!=0){
				searchHql.append(" and T1.isHistoryPicture = :history");
				map.put("history", history-1);
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
							searchHql.append(" and T1.is_referred_by_product like :type");
							map.put("type", "%1");
						}
						break;
					case 1://选用
						searchHql.append(" and T1.is_referred_by_product like :type");
						map.put("type", "%11");
						break;
					case 2://见报
						searchHql.append(" and T1.is_referred_by_product like :type");
						map.put("type", "%1_1");
						break;
					}
				}else if(cpk==0){//选择不是成品
					searchHql.append(" and T1.is_referred_by_product not like :type");
					map.put("type", "%1");
				}
			}
		}

		// 定义一个List，用来保存需要对哪些字段进行检索
		List<String> colums = new ArrayList<String>();
		String searchType=request.getParameter("searchType");
		if (searchType!=null&&searchType.equals("0")) {
			//图片的检索条件
			colums.add("T1.id");
			colums.add("T1.title");
			colums.add("T1.keywords");
			colums.add("T1.place");
			colums.add("T1.memo");
			colums.add("T1.exModel");
			colums.add("T1.authorName");
			colums.add("T1.filename");
			//colums.add("T1.source_picture_id");原档案号
			colums.add("T1.uploader");
			colums.add("T1.sourcePictureName");
			
			//组图的检索条件
			colums.add("T3.title");
			colums.add("T3.keywords");
			colums.add("T3.place");
			colums.add("T3.memo");
			colums.add("T3.creator");
			colums.add("T3.creatorName");
			colums.add("T3.author");
			colums.add("T3.authorTrueName");
		} else {
//			String[] columsString = request.getParameterValues("colums");
//			if (columsString != null) {
//				for (int i = 0; i < columsString.length; i++) {
//					if (StringUtils.isNotBlank(columsString[i])) {
//						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+columsString[i]);
//						colums.add(columsString[i]);
//					}
//				}
//			}
			if(StringUtils.isNotBlank(request.getParameter("cb_id"))){
				colums.add("T1.id");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_title"))){
				colums.add("T1.title");	
				colums.add("T3.title");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_keyword"))){
				colums.add("T1.keywords");
				colums.add("T3.keywords");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_memo"))){
				colums.add("T1.memo");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_groupMemo"))){
				colums.add("T3.memo");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_place"))){
				colums.add("T1.place");
				colums.add("T3.place");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_exModel"))){
				colums.add("T1.exModel");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_authorName"))){
				colums.add("T1.authorName");
				colums.add("T3.author");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_filename"))){
				colums.add("T1.filename");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_sourcePD"))){
				colums.add("T1.source_picture_id");		
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_uploader"))){
				colums.add("T1.uploader");
				colums.add("T3.creatorName");
			}
			if(StringUtils.isNotBlank(request.getParameter("cb_sourcePN"))){
				colums.add("T1.sourcePictureName");
			}
		}
		
		if (colums.size() > 0) {
			// 与输入框中的全部字词有关
			if (StringUtils.isNotBlank(request.getParameter("andSearchText"))) {
				String[] andSearchText = SearchStringUtils.stringFilter(
						request.getParameter("andSearchText")).trim()
						.split(" ");
				for (int i = 0; i < andSearchText.length; i++) {
					if (StringUtils.isNotBlank(andSearchText[i])) {
						searchHql.append(" and ( ");
						int k = 0;
						for (int j = 0; j < colums.size(); j++) {
							if (!colums.get(j).equals("T1.id")) {
								if (k == 0) {
									searchHql.append("LOWER(" + colums.get(j)
											+ ")" + " like:andSearchText" + i);
									k++;
								} else {
									searchHql.append(" or LOWER("
											+ colums.get(j) + ")"
											+ " like:andSearchText" + i);
								}
								map.put("andSearchText" + i, "%"
										+ andSearchText[i].toLowerCase() + "%");
							} else {
								if (StringUtils.isNumeric(andSearchText[i])
										&& andSearchText[i].compareTo("9223372036854775807") <= 0
										&& andSearchText[i].length() <= 19) {
									Long id = Long.parseLong(andSearchText[i]);
									searchHql.append("T1.id =:andId" + i);
									map.put("andId" + i, id);
								} else {
									searchHql.append("0 = 1");
								}
								k++;
							}
						}
						searchHql.append(" )");
					}
				}
			}
			// 与输入框中的字词完全匹配
			if (StringUtils.isNotBlank(request.getParameter("sameSearchText"))) {
				String sameSearchText = SearchStringUtils.stringFilter(
						request.getParameter("sameSearchText")).trim();
				searchHql.append(" and ( ");
				int k = 0;
				for (int i = 0; i < colums.size(); i++) {
					if (!colums.get(i).equals("T1.id")) {
						if (k == 0) {
							searchHql.append(colums.get(i)
									+ " like:sameSearchText");
							k++;
						} else {
							searchHql.append(" or " + colums.get(i)
									+ " like:sameSearchText");
						}
						map.put("sameSearchText", "%" + sameSearchText + "%");
					} else {
						if (StringUtils.isNumeric(sameSearchText)
								&& sameSearchText
										.compareTo("9223372036854775807") <= 0
								&& sameSearchText.length() <= 19) {
							Long id = Long.parseLong(sameSearchText);
							searchHql.append("T1.id =:sameId");
							map.put("sameId", id);
						} else {
							searchHql.append("0 = 1");
						}
						k++;
					}
				}
				searchHql.append(" ) ");
			}
			// 与输入框中的任何一个字词有关
			if (StringUtils.isNotBlank(request.getParameter("orSearchText"))) {
				String[] orSearchText = SearchStringUtils.stringFilter(
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
							if (!colums.get(j).equals("T1.id")) {
								if (k == 0) {
									searchHql.append("LOWER(" + colums.get(j)
											+ ")" + " like:orSearchText" + i);
									k++;
								} else {
									searchHql.append(" or LOWER("
											+ colums.get(j) + ")"
											+ " like:orSearchText" + i);
								}
								map.put("orSearchText" + i, "%"
										+ orSearchText[i].toLowerCase() + "%");
							} else {
								if (StringUtils.isNumeric(orSearchText[i])
										&& orSearchText[i]
												.compareTo("9223372036854775807") <= 0
										&& orSearchText[i].length() <= 19) {
									Long id = Long.parseLong(orSearchText[i]);
									searchHql.append("T1.id =:orId" + i);
									map.put("orId" + i, id);
								} else {
									searchHql.append("0 = 1");
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
			if (StringUtils.isNotBlank(request.getParameter("notSearchText"))) {
				String[] notSearchText = SearchStringUtils.stringFilter(
						request.getParameter("notSearchText")).trim()
						.split(" ");
				for (int i = 0; i < notSearchText.length; i++) {
					if (StringUtils.isNotBlank(notSearchText[i])) {
						searchHql.append(" and ( ");
						int k = 0;
						for (int j = 0; j < colums.size(); j++) {
							if (!colums.get(j).equals("T1.id")) {
								if (k == 0) {
									searchHql.append("( LOWER(" + colums.get(j)
											+ ")" + " not like:notSearchText"
											+ i + " or " + colums.get(j)
											+ " is  null )");
									k++;
								} else {
									searchHql.append(" and ( LOWER("
											+ colums.get(j) + ")"
											+ " not like:notSearchText" + i
											+ " or " + colums.get(j)
											+ " is  null )");
								}
								map.put("notSearchText" + i, "%"
										+ notSearchText[i].toLowerCase() + "%");
							} else {
								if (StringUtils.isNumeric(notSearchText[i])
										&& notSearchText[i]
												.compareTo("9223372036854775807") <= 0
										&& notSearchText[i].length() <= 19) {
									Long id = Long.parseLong(notSearchText[i]);
									searchHql.append("T1.id <>:notId" + i);
									map.put("notId" + i, id);
								} else {
									searchHql.append("1 = 1");
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
				&& StringUtils.isNumeric(request.getParameter("pictureWidth"))) {
			Integer pictureWidth = Integer.parseInt(request
					.getParameter("pictureWidth"));
			String pictureWidthType = request.getParameter("pictureWidthType");
			if (pictureWidthType.equals("0")) {
				searchHql.append(" and T1.pictureWidth =:pictureWidth");
			}
			if (pictureWidthType.equals("1")) {
				searchHql.append(" and T1.pictureWidth >:pictureWidth");
			}
			if (pictureWidthType.equals("2")) {
				searchHql.append(" and T1.pictureWidth <:pictureWidth");
			}
			map.put("pictureWidth", pictureWidth);
		}
		// 图片高度的检索条件
		if (StringUtils.isNotBlank(request.getParameter("pictureHeight"))
				&& StringUtils.isNumeric(request.getParameter("pictureHeight"))) {
			Integer pictureHeight = Integer.parseInt(request
					.getParameter("pictureHeight"));
			String pictureHeightType = request
					.getParameter("pictureHeightType");
			if (pictureHeightType.equals("0")) {
				searchHql.append(" and T1.pictureHeight =:pictureHeight");
			}
			if (pictureHeightType.equals("1")) {
				searchHql.append(" and T1.pictureHeight >:pictureHeight");
			}
			if (pictureHeightType.equals("2")) {
				searchHql.append(" and T1.pictureHeight <:pictureHeight");
			}
			map.put("pictureHeight", pictureHeight);
		}

		/*StringBuffer sbTemp = new StringBuffer(
				"select pictureId from PublishPicture as p where 1 = 1");

		// 发布时间的检索条件
		if (StringUtils.isNotBlank(request.getParameter("pDateBegin"))) {
			Date pDateBegin = null;
			try {
				pDateBegin = new SimpleDateFormat("yyyy-MM-dd").parse(request
						.getParameter("pDateBegin"));
				if (pDateBegin != null) {
					sbTemp.append(" and p.createTime >=:pDateBegin");
				}
				map.put("pDateBegin", pDateBegin);
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
				if (pDateEnd != null) {
					sbTemp.append(" and p.createTime <:pDateEnd");
				}
				map.put("pDateEnd", pDateEnd);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// 如果选择了根据发布时间检索
		if (sbTemp.indexOf("and") != -1) {
			searchHql.append(" and id in ( " + sbTemp + " )");
		}*/

		// 上传时间的检索条件
		if (StringUtils.isNotBlank(request.getParameter("pDateBegin"))) {
			Date pDateBegin = null;
			try {
				pDateBegin = new SimpleDateFormat("yyyy-MM-dd").parse(request
						.getParameter("pDateBegin"));
				if (pDateBegin != null) {
					searchHql.append(" and T1.createTime >=:pDateBegin");
				}
				map.put("pDateBegin", pDateBegin);
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
				if (pDateEnd != null) {
					searchHql.append(" and T1.createTime <:pDateEnd");
				}
				map.put("pDateEnd", pDateEnd);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// 拍摄时间的检索条件
		if (StringUtils.isNotBlank(request.getParameter("tDateBegin"))) {
			Date tDateBegin = null;
			try {
				tDateBegin = new SimpleDateFormat("yyyy-MM-dd").parse(request
						.getParameter("tDateBegin"));
				if (tDateBegin != null) {
					searchHql.append(" and T1.filmTime >=:tDateBegin");
				}
				map.put("tDateBegin", tDateBegin);
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
				if (tDateEnd != null) {
					searchHql.append(" and T1.filmTime <:tDateEnd");
				}
				map.put("tDateEnd", tDateEnd);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// 图片比例的检索条件
		if (StringUtils.isNotBlank(request.getParameter("picProportion"))
				&& StringUtils.isNumeric(request.getParameter("picProportion"))) {
			int picProportion = Integer.parseInt(request
					.getParameter("picProportion"));
			switch (picProportion) {
			case 1:
				searchHql.append(" and T1.orientation = 'landscape'");
				break;
			case 2:
				searchHql.append(" and T1.orientation = 'portrait'");
				break;
			case 3:
				searchHql.append(" and T1.orientation = 'square'");
				break;
			}
		}
/*		// 图片色调的检索条件
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
		}*/
		// 图片格式的检索条件
		if (StringUtils.isNotBlank(request.getParameter("picFormat"))
				&& StringUtils.isNumeric(request.getParameter("picFormat"))) {
			int picFormat = Integer.parseInt(request.getParameter("picFormat"));
			switch (picFormat) {
			case 1:
				searchHql.append(" and T1.filename like '%_t.jpg'");
				break;
			case 2:
				searchHql.append(" and T1.filename like '%_p.jpg'");
				break;
			case 3:
				searchHql.append(" and ( T1.filename like '%_r.jpg' or filename like '%_n.jpg' )");
			}
		}
		// 图片大小的检索条件
		if (StringUtils.isNotBlank(request.getParameter("pictureSize"))
				&& StringUtils.isNumeric(request.getParameter("pictureSize"))) {
			int pictureSize = Integer.parseInt(request
					.getParameter("pictureSize"));
			switch (pictureSize) {
			case 1:
				searchHql.append(" and T1.pictureLength < 524288");
				break;
			case 2:
				searchHql
						.append(" and T1.pictureLength >= 524288 and pictureLength <= 1048576");
				break;
			case 3:
				searchHql
						.append(" and T1.pictureLength >= 1048576 and pictureLength <= 2097152");
				break;
			case 4:
				searchHql.append(" and T1.pictureLength > 2097152");
				break;
			}
		}
		// 所属分类的检索条件
		if (StringUtils.isNotBlank(request.getParameter("categoryInfo"))) {
			String[] categoryAll = request.getParameter("categoryInfo").split(
					";;");
			if (StringUtils.isNotBlank(categoryAll[0])) {
				String[] categoryIds = categoryAll[0].split(";");
				int k = 0;
				searchHql.append(" and ( ");
				for (int i = 0; i < categoryIds.length; i++) {
					if (StringUtils.isNotBlank(categoryIds[i])
							&& StringUtils.isNumeric(categoryIds[i])) {
						if (k == 0) {
							//searchHql.append("T1.categoryInfo like:categoryId or T3.categoryInfo like:categoryId"+ i);
							searchHql.append("T1.categoryInfo like:categoryId"+i+" or T3.categoryInfo like:categoryId"+i);
							k++;
						} else {
							//searchHql.append(" or T1.categoryInfo like:categoryId or T3.categoryInfo like:categoryId"+ i);
							searchHql.append(" or T1.categoryInfo like:categoryId"+i+" or T3.categoryInfo like:categoryId"+i);
						}
						//map.put("categoryId" + i, "%;" + categoryIds[i] + ";%");
						map.put("categoryId"+i, "%;" + categoryIds[i] + ";%");
					}
				}
				searchHql.append(" )");
			}
		}
/*		// 所属栏目的检索条件
		if (StringUtils.isNotBlank(request.getParameter("channelInfo"))) {
			String[] channelIds = request.getParameter("channelInfo")
					.split(";");
			StringBuffer ids = new StringBuffer();
			int k = 0;
			for (int i = 0; i < channelIds.length; i++) {
				if (StringUtils.isNotBlank(channelIds[i])
						&& StringUtils.isNumeric(channelIds[i])) {
					if (k == 0) {
						ids.append(":channelIds" + i);
						k++;
					} else {
						ids.append(",:channelIds" + i);
					}
					map.put("channelIds" + i, Long.parseLong(channelIds[i]));
				}
			}
			searchHql
					.append(" and id in (select p.pictureId from PublishPicture as p where p.channelId in ( "
							+ ids + " ))");
		}*/
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
			hql = hql.append(" 1 = 1");
		}

		// 设置排序方式
//		String orderBy = request.getParameter("orderBy");
//		String order = request.getParameter("order");
//		if (StringUtils.isNotEmpty(orderBy)) {
//			hql.append(" order by " + orderBy);
//		} else {
//			hql.append(" order by T1.id");
//		}
//		if (StringUtils.isNotEmpty(order)) {
//			hql.append(" " + order);
//		} else {
//			hql.append(" DESC");
//		}

		// 高级检索语句
		String queryString = hql.toString();
		map.put("hql", queryString);
		return map;
	}
}