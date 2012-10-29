package com.coffee.zw.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 * TRSServer数据库检索条件工具类(负责生成待编库、资料库、成品库、统计库中相关检索条件)
 * 
 * @author Stone.W
 * 
 */
public class SearchWordGeneratorForTrs {
	/**
	 * 根据请求获取检索条件，返回检索条件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getCondition(HttpServletRequest request)
			throws Exception {
			// 拼接最终检索条件
			StringBuffer hql = new StringBuffer();

			StringBuffer sbHql = new StringBuffer();

			// 先从attribute中取图片状态信息，再从parameter中图片状态信息（用于查找相关库中的图片）
			String state = null;
			Object obj = request.getAttribute("picstate");
			if (obj != null) {
				state = obj.toString();
			}

			String stateParam = request.getParameter("picstate");
			if (StringUtils.isNotBlank(stateParam)) {
				state = stateParam;
			}

			// 统计库中的图片来自哪个库（针对统计库中图片系统）
			String piclib = request.getParameter("piclib");
			if (!StringUtils.isEmpty(piclib)) {
				int nPiclib = Integer.parseInt(piclib);
				switch (nPiclib) {
				case 1:
					//图片系统---待编库
					state = String.valueOf(PicStateConstants.ArchivePictureState.TO_EDIT.getValue());
					break;
				case 2:
					// 图片系统---资料库
					state = String.valueOf(PicStateConstants.ArchivePictureState.EDITED
									.getValue());
					break;
				case 3:
					//图片系统---成品库
					state = String.valueOf(PicStateConstants.ArchivePictureState.EDITED.getValue());
					sbHql.append(" and isReferredByProduct = '%1'");
					break;
				case 4:
					// 图片系统---发布库
					sbHql.append(" and isReferredByPublish = 1");
					break;
				case 5:
					// 图片系统---方正采编
					sbHql.append(" and isReferredByProduct = '%1?'");
					break;
				}
			}

			// 不给state默认状态。如果不能从parameter和attribute中获取state，则取出待编和已编的图片。
			int nState = PicStateConstants.ArchivePictureState.EDITED
					.getValue();
			String lib=request.getParameter("lib");
			if(lib==null||!lib.equals("cpk")){
				if (StringUtils.isNotBlank(state)) {
					try {
						nState = Integer.parseInt(state);
						sbHql.append(" and pictureState = " + nState);
					} catch (Exception e) {
					}
				} else {// 针对统计库（默认显示待编跟已编的图片）
					sbHql.append(" and ( pictureState = 0 or pictureState = 1 )");
				}
			}

			String site = request.getParameter("site");
			// 如果取不到站点，则取默认站点信息
			if (StringUtils.isEmpty(site)) {
				site = Config.DEFAULT_SITE;
			}
			try {
				int nSiteId = Integer.parseInt(site);
				sbHql.append(" and siteId = " + nSiteId);
			} catch (Exception e) {
			}

			// 删除标记 默认显示未删除的（针对资料库，其它库显示未删图片）
			String sdelFlag = request.getParameter("delflag");
			if (!StringUtils.isEmpty(sdelFlag)) {
				try {
					int ndelFlag = Integer.parseInt(sdelFlag);
					if (ndelFlag != -1) {
						sbHql.append(" and deleteFlag = " + ndelFlag);
					}
				} catch (Exception e) {
				}
			} else {
				sbHql.append(" and deleteFlag = 0");
			}

			// 已锁标记 默认显示未被锁的（针对资料库，其它库默认显示未锁图片）
			// 9-26:资料库显示所有图片，包含未锁，已锁的。
			String sIsLock = request.getParameter("islock");
			if (!StringUtils.isEmpty(sIsLock)) {
				try {
					int nIsLock = Integer.parseInt(sIsLock);
					if (nIsLock != -1) {
						sbHql.append(" and isLocked = " + nIsLock);
					}
				} catch (Exception e) {
				}
			}/*
			 * else{ sbHql.append(" and isLocked = :islock"); map.put("islock",
			 * 0); }
			 */

			// 上传用户（针对待编库）
			Object oUploader = request.getAttribute("uploader");
			if (oUploader != null && !oUploader.toString().equals("0")) {
				String uploader = oUploader.toString();
				if (StringUtils.isNotBlank(uploader)) {
					sbHql.append(" and uploader = " + uploader);
				}
			}

			// 图片来源（针对待编库）
			String picsource = request.getParameter("picsource");
			if (!StringUtils.isEmpty(picsource)) {
				try {
					int nPicSource = Integer.parseInt(picsource);
					if (nPicSource != -1) {
						sbHql.append(" and pictureSourceId = " + nPicSource);
					}
				} catch (Exception e) {
				}
			}

			String security = request.getParameter("security");
			//security为-1时，忽略密级
			if(security==null || !security.equals("-1")){
				int nSecurityType = 0;
				// 留资状态才能查询密级（针对资料库，其它库默认显示普通密级图片）
				if (nState == PicStateConstants.ArchivePictureState.HOLDING
						.getValue()
						&& !StringUtils.isEmpty(security)) {
					try {
						nSecurityType = Integer.parseInt(security);
					} catch (Exception e) {
					}
				}
				sbHql.append(" and securityType = " + nSecurityType);
			}

			// 历史图片/新闻图片（针对资料库、统计库） 默认显示新闻图片
			String history = request.getParameter("history");
			if (!StringUtils.isEmpty(history)) {
				try {
					int nHistory = Integer.parseInt(history);
					if (nHistory != -1) {
						sbHql.append(" and isHistoryPicture = " + nHistory);
					}
				} catch (Exception e) {
				}
			} else {
				// 默认取新闻图片
				sbHql.append(" and isHistoryPicture = 0");
			}

			// 成品库 isReferredByProduct producttype 以及成品库中图片的状态（选用、见报）
			String prodyctType = null;
			// 当选择显示成品库中的图片时会从attribute中获取参数
			Object oProductType = request.getAttribute("producttype");
			if (oProductType != null) {
				prodyctType = oProductType.toString();
			}
			// 从资料库中判断图片是否存在于成品库中的时候从parameter中获取参数
			String incpk = request.getParameter("incpk");
			if (StringUtils.isNotBlank(incpk)) {
				prodyctType = incpk;
			}
			String status = request.getParameter("fzcbStatus");
			if (StringUtils.isNotBlank(prodyctType)) {
				if ("-1".equals(prodyctType)) {// 针对资料库没选成品库中是否存在
					if ("1".equals(status)) {// 图片选用
						sbHql.append(" and isReferredByProduct = '%1?'");
					} else if ("2".equals(status)) {// 图片见报
						sbHql.append(" and isReferredByProduct = '%1??'");
					}
				} else if ("0".equals(prodyctType)) {// 成品库中不存在
					if ("1".equals(status)) {// 图片选用
						sbHql.append(" and isReferredByProduct = '%1?' and isreferredbyproduct != '%1'");
					} else if ("2".equals(status)) {// 图片见报
						sbHql.append(" and isReferredByProduct = '%1??' and isreferredbyproduct != '%1'");
					} else {
						sbHql.append(" and isReferredByProduct != '%1'");
					}
				} else if ("1".equals(prodyctType)) {// 成品库中存在
					if ("1".equals(status)) {// 成品库中的图片状态---选用
						sbHql.append(" and isReferredByProduct = '%11'");
					} else if ("2".equals(status)) {// 成品库中的图片状态---见报
						sbHql.append(" and isReferredByProduct = '%1?1'");
					} else {// 成品库中的所有图片
						sbHql.append(" and isReferredByProduct = '%1'");
					}
				}
			} else if (prodyctType == null) {// 统计库
				if ("1".equals(status)) {// 图片选用
					sbHql.append(" and isReferredByProduct = '%1?'");
				} else if ("2".equals(status)) {// 图片见报
					sbHql.append(" and isReferredByProduct = '%1??'");
				}
			}

			// 是否原图（针对统计库）
			String issrcpic = request.getParameter("issrcpic");
			if (!StringUtils.isEmpty(issrcpic)) {
				try {
					int nIssrcpic = Integer.parseInt(issrcpic);
					if (nIssrcpic != -1) {
						sbHql.append(" and isSourcePicture = " + nIssrcpic);
					}
				} catch (Exception e) {
				}
			}

			// 联合检索（针对待编库、资料库、成品库、统计库）
			String q = request.getParameter("qtext");
			String queryType = request.getParameter("queryType");
			//获取同组人物串
			Object phgrapherIds = request.getAttribute("PhgrapherIds");
			//待编库中检索摄影师选项时，只能查看当前组中摄影师的
			Object temp = request.getAttribute("dbk");
			if(("dbk").equals(temp)){
				String[] authorIds = null;
				if(!("").equals(phgrapherIds)){
					authorIds = ((String) phgrapherIds).split(";");
					sbHql.append(" and (");
					//遍历摄影师ID，拼接检索条件
					for(int i=0;i<authorIds.length;i++){
						if(i == 0){
							sbHql.append(" uploader = '"+authorIds[i]+"'");
						}else{
							sbHql.append(" or uploader = '"+authorIds[i]+"'");
						}
					}
					sbHql.append(" )");
				}else{
					sbHql.append(" and id = 1.11111");
				}
				if (StringUtils.isNotBlank(q)) {
					q = com.coffee.zw.util.StringUtils.stringFilter(q)
							.toLowerCase();
					q = SearchStringUtils.toTrsString(q);
					if (queryType.equals("0")) {
						sbHql.append(" and (");
						if (StringUtils.isNumeric(q)
								&& q.compareTo("9223372036854775807") <= 0
								&& q.length() <= 19) {
							long nPicId = Long.parseLong(q);
							sbHql.append(" id = " + nPicId + " or title = %" + q + "% or authorName = %" + q + "% or keywords = %" + q + "% or filename = %" + q + "% or sourcePictureId = %" + q + "% or sourcePictureName = %" + q + "% or memo = %" + q +"%");
							if ("0".equals(request.getParameter("uploader"))) {
								sbHql.append(" or uploader = %" + q + "%");
							}
						} else {
							// String[] aTitles= s.trim().split(" ");
							sbHql.append(" id = 1.11111 or title = %" + q + "% or authorName = %" + q + "% or keywords = %" + q + "% or filename = %" + q + "% or sourcePictureId = %" + q + "% or sourcePictureName = %" + q + "% or memo = %" + q +"%");
							if ("0".equals(request.getParameter("uploader"))) {
								sbHql.append(" or uploader = %" + q + "%");
							}
						}
						sbHql.append(" )");
					} else if (queryType.equals("1")) {
						if (StringUtils.isNumeric(q)
								&& q.compareTo("9223372036854775807") <= 0
								&& q.length() <= 19) {
							long nPicId = Long.parseLong(q);
							sbHql.append(" and id = " + nPicId);
						} else {
							sbHql.append(" and id = 1.11111");
						}
					} else if (queryType.equals("2")) {
						sbHql.append(" and title = %" + q + "%");
					} else if (queryType.equals("3")) {
						sbHql.append(" and authorName = %" + q + "%");
					} else if (queryType.equals("4")) {
						sbHql.append(" and keywords = %" + q + "%");
					} else if (queryType.equals("5")) {
						sbHql.append(" and filename = %" + q + "%");
					} else if (queryType.equals("6")) {
						sbHql.append(" and sourcePictureId = %" + q + "%");
					} else if (queryType.equals("7")) {
						sbHql.append(" and uploader = %" + q + "%");
					}else if(queryType.equals("8")){
						sbHql.append(" and sourcePictureName = %" + q +"%");
					}else if(queryType.equals("9")){
						sbHql.append(" and memo = %" + q +"%");
					}
				}
			}else{
				if (StringUtils.isNotBlank(q)) {
					q = com.coffee.zw.util.StringUtils.stringFilter(q)
							.toLowerCase();
					q = SearchStringUtils.toTrsString(q);
					if (queryType.equals("0")) {
						sbHql.append(" and (");
						if (StringUtils.isNumeric(q)
								&& q.compareTo("9223372036854775807") <= 0
								&& q.length() <= 19) {
							long nPicId = Long.parseLong(q);
							sbHql.append(" id = " + nPicId + " or title = %" + q + "% or authorName = %" + q + "% or keywords = %" + q + "% or filename = %" + q + "% or sourcePictureId = %" + q + "% or sourcePictureName = %" + q + "% or memo = %" + q + "%");
							if ("0".equals(request.getParameter("uploader"))) {
								sbHql.append(" or uploader = %" + q + "%");
							}
						} else {
							// String[] aTitles= s.trim().split(" ");
							sbHql.append(" id = 1.11111 or title = %" + q + "% or authorName = %" + q + "% or keywords = %" + q + "% or filename = %" + q + "% or sourcePictureId = %" + q + "% or sourcePictureName = %" + q + " or memo = %" + q + "%");
							if ("0".equals(request.getParameter("uploader"))) {
								sbHql.append(" or uploader = %" + q + "%");
							}
						}
						sbHql.append(" )");
					} else if (queryType.equals("1")) {
						if (StringUtils.isNumeric(q)
								&& q.compareTo("9223372036854775807") <= 0
								&& q.length() <= 19) {
							long nPicId = Long.parseLong(q);
							sbHql.append(" and id = " + nPicId);
						} else {
							sbHql.append(" and id = 1.11111");
						}
					} else if (queryType.equals("2")) {
						sbHql.append(" and title = %" + q + "%");
					} else if (queryType.equals("3")) {
						sbHql.append(" and authorName = %" + q + "%");
					} else if (queryType.equals("4")) {
						sbHql.append(" and keywords = %" + q + "%");
					} else if (queryType.equals("5")) {
						sbHql.append(" and filename = %" + q + "%");
					} else if (queryType.equals("6")) {
						sbHql.append(" and sourcePictureId = %" + q + "%");
					} else if (queryType.equals("7")) {
						sbHql.append(" and uploader = %" + q + "%");
					}else if(queryType.equals("8")){
						sbHql.append(" and sourcePictureName = %" + q + "%");
					}else if(queryType.equals("9")){
						sbHql.append(" and memo = %" + q + "%");
					}
				}
			}

			String uploaderValue = request.getParameter("uploader");
			String uploadTime = "";
			if("0".equals(uploaderValue)){
				uploadTime = request.getParameter("uploadTimeAll");
			}else if("1".equals(uploaderValue)){
				uploadTime = request.getParameter("uploadTime");
			}
			// 针对待编库我的上传图片时间（全部、当日、三天、一周、一月）
			if (StringUtils.isNotBlank(uploadTime)) {
				int index = Integer.parseInt(uploadTime);
				if (index != 0) {
					String nowTime = new SimpleDateFormat("yyyy-MM-dd")
							.format(new Date());
					Date now = DateUtils.parseDate(nowTime);
					switch (index) {
					case 1:// 当日图片
						String today = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(now);
						sbHql.append(" and createTime >= " + today);
						break;
					case 2:// 三天图片
						Date threeDays = org.apache.commons.lang.time.DateUtils
								.addDays(now, -3);
						String td = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(threeDays);
						sbHql.append(" and createTime >= " + td);
						break;
					case 3:// 一周图片
						Date oneWeek = org.apache.commons.lang.time.DateUtils
								.addWeeks(now, -1);
						String ow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(oneWeek);
						sbHql.append(" and createTime >= " + ow);
						break;
					case 4:// 一月图片
						Date oneMonth = org.apache.commons.lang.time.DateUtils
								.addMonths(now, -1);
						String om = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(oneMonth);
						sbHql.append(" and createTime >= " + om);
						break;
					}
				}
			}

			// 更多可选检索条件
			// 发布时间的检索条件（针对资料库、成品库、统计库）
			if (StringUtils.isNotBlank(request.getParameter("pDateBegin"))) {
				Date pDateBegin = DateUtils.parseDate(request
						.getParameter("pDateBegin"));
				String pdb = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(pDateBegin);
				if (pDateBegin != null) {
					sbHql.append(" and publishTime >= " + pdb);
				}
			}
			if (StringUtils.isNotBlank(request.getParameter("pDateEnd"))) {
				Date pDateEnd = DateUtils.parseDate(request
						.getParameter("pDateEnd"));
				// 对得到的日期加上一天
				pDateEnd = org.apache.commons.lang.time.DateUtils.addDays(
						pDateEnd, 1);
				String pde = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(pDateEnd);
				if (pDateEnd != null) {
					sbHql.append(" and publishTime < " + pde);
				}
			}
			
			// 上传时间的检索条件
			if (StringUtils.isNotBlank(request.getParameter("tDateBegin"))) {
				Date tDateBegin = DateUtils.parseDate(request
						.getParameter("tDateBegin"));
				String tdb = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(tDateBegin);
				if (tDateBegin != null) {
					sbHql.append(" and createTime >= " + tdb);
				}
			}
			if (StringUtils.isNotBlank(request.getParameter("tDateEnd"))) {
				Date tDateEnd = DateUtils.parseDate(request
						.getParameter("tDateEnd"));
				// 对得到的日期加上一天
				tDateEnd = org.apache.commons.lang.time.DateUtils.addDays(
						tDateEnd, 1);
				String tde = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(tDateEnd);
				if (tDateEnd != null) {
					sbHql.append(" and createTime < " + tde);
				}
			}

			// 侧栏分类树中的分类id
			String catid = request.getParameter("catid");
			if (!StringUtils.isEmpty(catid)) {
				sbHql.append(" and categoryInfo = %" + catid + "%");
			}

			// 所属分类的检索条件
			if (StringUtils.isNotBlank(request.getParameter("categoryInfo"))) {
				String[] categoryAll = request.getParameter("categoryInfo")
						.split(";;");
				if (categoryAll.length > 0
						&& StringUtils.isNotBlank(categoryAll[0])) {
					String[] categoryIds = categoryAll[0].split(";");
					int k = 0;
					sbHql.append(" and ( ");
					for (int i = 0; i < categoryIds.length; i++) {
						if (StringUtils.isNotBlank(categoryIds[i])
								&& StringUtils.isNumeric(categoryIds[i])) {
							if (k == 0) {
								sbHql.append("categoryInfo = %" + categoryIds[i] + "%");
								k++;
							} else {
								sbHql.append(" or categoryInfo = %" + categoryIds[i] + "%");
							}
						}
					}
					sbHql.append(" )");
				}
			}
			
			// 截取 and或者 or开头的字符串
			if (sbHql.length() > 0) {
				String subString = "";
				if (sbHql.indexOf(" and") == 0) {
					subString = sbHql.substring(4, sbHql.length());
				} else if (sbHql.indexOf(" or") == 0) {
					subString = sbHql.substring(3, sbHql.length());
				} else {
					subString = sbHql.toString();
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