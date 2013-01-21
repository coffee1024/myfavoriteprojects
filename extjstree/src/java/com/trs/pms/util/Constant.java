package com.trs.pms.util;

public class Constant {
		
	public enum Right{
		
		/**
		 * 管理台权限
		 * 只要用户有系统管理台中任何一个项的操作权限，则表示该用户可以进入系统管理台页面
		 */
		manager(0L),
		/**
		 * 删除待编库栏目
		 */
		delPicFromDaibian(1L),
		/**
		 * 浏览待编库
		 */
		scanFromDaibian(2L),
		/**
		 * 从待编库选送图片至资料库
		 */
		sendPicToZiliao(3L),
		/**
		 * 留资
		 */
		LeaveCapital(4L),
		/**
		 * 编辑图片
		 */
		editPicture(5L),
		/**
		 * 彻底删除
		 */
		completeRemove(6L),
		/**
		 * 删除
		 */
		remove(7L),
		/**
		 * 下载大图
		 */
		loadBigPicture(8L),
		/**
		 * 下载原图
		 */
		loadOriginalPicture(9L),
		/**
		 * 引用图片到发布库
		 */
		quoteToFabu(10L),
		/**
		 * 采用至成品库
		 */
		useTochengpin(11L),
		/**
		 * 从成品库移除
		 */
		removePicFromchengpin(12L),
		/**
		 * 浏览成品库
		 */
		scanchengpin(13L),
		/**
		 * 发送下载通知
		 */
		messageForLoadPic(14L),
		/**
		 * 新建栏目（发布库权限）
		 */
		newChannel(15L),
		/**
		 * 发布栏目（发布库权限）
		 */
		publishChannel(16L),
		/**
		 * 修改栏目信息（发布库权限）
		 */
		editChannel(17L),
		/**
		 * 审核栏目（发布库权限）
		 */
		auditChannel(18L),
		/**
		 * 审核并发布栏目（发布库权限）
		 */
		auditAndPublishChannel(19L),
		/**
		 * 撤销已发布的栏目（发布库权限）
		 */
		revocationChannnel(20L),
		/**
		 * 移动栏目（发布库权限）
		 */
		moveChannel(21L),
		/**
		 * 删除栏目（发布库权限）
		 */
		deleteChannel(22L),
		/**
		 * 移动栏目下图片(单个栏目)
		 */
		moveChannelPicture(23L),
		/**
		 * 复制栏目内容(单个栏目)
		 */
		copyChannelContent(24L),
		/**
		 * 删除栏目内容(单个栏目)
		 */
		deleteChannelContent(25L),
		/**
		 * 新建栏目(单个栏目权限)
		 */
		newChannelSingle(26L),
		/**
		 * 封面图片管理
		 */
		coverPictureManage(27L),
		/**
		 * 用户管理
		 */
		userManage(28L),
		/**
		 * 角色管理
		 */	
		roleManage(29L),
		/**
		 * 权限管理
		 */
		rightManage(30L),
		/**
		 * 消息通知
		 */
		messageAndNotice(31L),
		/**
		 * 同步队列
		 */
		syncQueue(32L),
		/**
		 * 水印管理
		 */
		waterMarkManage(33L),
		/**
		 * 分类管理
		 */
		catagoryManage(34L),
		/**
		 * 栏目管理
		 */
		channnelManage(35L),
		/**
		 * 配置管理
		 */
		configManage(36L),
		/**
		 * 日志管理
		 */
		loggerManage(37L),
		/**
		 * 地区管理
		 */
		provinceManage(38L),
		/**
		 * 行业管理
		 */
		busniessManage(39L),
		/**
		 * 统计查询(统计库)
		 */
		statistics(40L),
		/**
		 * 浏览站点
		 */
		scanSite(41L),
		/**
		 * 增加站点
		 */
		newSite(42L),
		/**
		 * 修改站点
		 */
		editSite(43L),
		/**
		 * 发布站点
		 */
		publishSite(44L),
		/**
		 * 撤发站点
		 */
		revocationSite(45L),
		/**
		 * 删除站点
		 */
		deleteSite(46L),
		/**
		 * 公告管理
		 */
		noticeManage(47L),
		/**
		 * 站内信管理
		 */
		messageManage(48L),
		/**
		 * 用户图片请求管理
		 */
		picRequestManage(49L),
		/**
		 * 留言管理
		 */
		postcommentManage(50L),
		/**
		 * 邮件管理
		 */
		emailManage(51L),
		/**
		 * 日志类型管理
		 */
		loggerTypeManage(52L),
		/**
		 * 配置类型管理
		 */
		configTypeManage(53L),
		/**
		 * 选用图片请求
		 */
		sendPicRequest(54L),
		/**
		 * 撤销审核栏目(单个栏目)
		 * @return
		 */
		unAuditChannel(55L),
		/**
		 * 彻底删除栏目(单个栏目)
		 * @return
		 */
		eraseChannel(56L),
		/**
		 * 恢复栏目(单个栏目)
		 * @return
		 */
		recoverchannelSingle(57L),
		/**
		 * 复制图片
		 * @return
		 */
		copyPic(58L),
		/**
		 * 移动图片
		 * @return
		 */
		movePic(59L),
		/**
		 * 删除发布库图片
		 * @return
		 */
		deletePubPic(60L),
		/**
		 * 审核发布库图片
		 * @return
		 */
		checkPubPic(61L),
		/**
		 * 发布发布库图片
		 * @return
		 */
		publishPubPic(62L),
		/**
		 * 审核并发布发布库图片
		 * @return
		 */
		checkNPublishPic(63L),
		/**
		 * 撤销发布库图片
		 * @return
		 */
		unPublishPic(64L),
		/**
		 * 图片上版、见报管理
		 */
		picPaperManage(65L),
		/**
		 * 图片操作流程管理
		 */
		picOperationManage(66L),
		/**
		 * 资料库浏览
		 */
		scanFromZiLiao(67L),
		/**
		 * 发布库浏览
		 */
		scanFromFaBu(68L),

		/**
		 * 还原留资图片为一般图片
		 */
		rebackCapital(69L),
		/**
		 * 解锁图片
		 */
		unlocked(70L),
		/**
		 * 还原已删除的图片
		 */
		rebackDeletedPic(71L),
		/**
		 * 替换图片
		 */
		replacePic(72L),
		/**
		 * 图片上版
		 */
		picToPaper(73L),
		/**
		 * 上传图片
		 */
		uploadPic(74L),
		/**
		 * 发布库退稿图片
		 */
		rejectPic(75L),
		/**
		 * 查看已删图片
		 */
		lookDeletePic(76L),
		/**
		 * 查看机密图片
		 */
		lookSecretPic(77L),
		/**
		 * 查看绝密图片
		 */
		lookSecretestPic(78L),
		/**
		 * 查看待编库全部图片
		 */
		lookDBKAllPic(79L),
		/**
		 * 查看发布库图片状态
		 */
		viewFbkState(80L),
		/**
		 * 查看资料库图片状态
		 */
		viewZlkState(81L),
		/**
		 * 查看、导出图片流程操作记录
		 */
		viewPicOpe(82L),
		/**
		 * 摄影师
		 */
		photographer(83L),
		/**
		 * 查看摄影师
		 */
		viewPhotographer(84L),
		/**
		 * 发布栏目(单个栏目)
		 */
		publishChannelSingle(85L),
		/**
		 * 修改栏目信息(单个栏目)
		 */
		modifyChannelSingle(86L),
		/**
		 * 审核栏目(单个栏目)
		 */
		checkChannelSingle(87L),
		/**
		 * 审核并发布栏目(单个栏目)
		 */
		checkAndPublishChannelSingle(88L),
		/**
		 * 撤销发布栏目(单个栏目)
		 */
		unPublishChannelSingle(89L),
		/**
		 * 移动栏目(单个栏目)
		 */
		moveChannelSingle(90L),
		/**
		 * 删除栏目（单个栏目）
		 */
		deleteChannelSingle(91L),
		/**
		 * 撤销审核栏目（发布库权限）
		 */
		unCheckChannel(92L),
		/**
		 * 彻底删除栏目（发布库权限）
		 */
		dropChannel(93L),
		/**
		 * 恢复栏目（发布库权限）
		 */
		recoverChannel(94L),
		/**
		 * 退稿栏目下图片（栏目权限）
		 */
		modifyChannelPicture(95L),
		/**
		 * 撤销栏目下图片（栏目权限）
		 */
		drawbackChannelPicture(96L),
		/**
		 * 审核发布栏目图片（栏目权限）
		 */
		checkNPubChannelPicture(97L),
		/**
		 * 审核栏目下图片（栏目权限）
		 */
		checkChannelPicture(98L),
		/**
		 * 发布栏目下图片（栏目权限）
		 */
		pubChannelPicture(99L),
		/**
		 * 允许查看所有组图，没有该权限，则只能查看自己上传的图片
		 */
		scanAllPicture(100L),
		/**
		 * 允许删除任意图片或者组图，否则只能删除自己上传的
		 */
		deleteAnyPicture(101L),
		/**
		 * 允许检索任意的图片，如果没有该权限，只能检索自己上传的图片
		 */
		searchAnyPicture(102L),
		/**
		 * 查看所有数据分析，如果没有该权限，只能分析自己上传的图片和组图数据
		 */
		analysisAnyData(103L),
		/**
		 * WCM站点栏目管理权限
		 */
		wcmSiteChannelManage(104L),
		/**
		 * 发布记录管理权限
		 */
		publishRecordManage(105L),
		/**
		 * 发布到WCM权限
		 */
		pubToWCM(106L);
		
		private Long rightNumber=0L;

		public Long getValue() {
			return rightNumber;
		}

		private  Right(Long rightNumber) {
			this.rightNumber = rightNumber;
		}
	}
}
