package com.coffee.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.iterators.ArrayListIterator;

/**
 * @Title 图片状态枚举变量
 * @CrTime 2010-4-14 上午10:32:53
 * @Version 1.0
 */
public class PicStateConstants {
	/**
	 * 资料库图片状态--0待编;1已编;10已审;20发布;30留资;99删除
	 */
	public enum ArchivePictureState {
		TO_EDIT(0), EDITED(1), CHECKED(10), PUBLISHED(20), HOLDING(30), DELETED(
				99);

		private int type;

		ArchivePictureState(int type) {
			this.type = type;
		}

		public int getValue() {
			return this.type;
		}

		public static ArchivePictureState getPictureSecurityType(int type) {
			for (ArchivePictureState aps : ArchivePictureState.values()) {
				if (aps.getValue() == type) {
					return aps;
				}
			}
			return null;
		}
	}

	/**
	 * @title 保存栏目状态,不值当多写出一个类,权且安居于此
	 * @crTime 2010-4-29
	 */
	public enum PublishChannelState {
		INIT(-1),EDITING(1), WAITINGCHECK(10), CHECKED(11),CHECKEDFAIL(12),TOPUBLISH(19), PUBLISHED(20),PUBLISHFAIL(21), TOUNPUBLISH(22),UNPUBLISHED(23),UNPUBLISHFAIL(24);
		private int type;

		PublishChannelState(int type) {
			this.type = type;
		}

		public int getValue() {
			return this.type;
		}

		public static PublishChannelState getPictureSecurityType(int type) {
			for (PublishChannelState aps : PublishChannelState.values()) {
				if (aps.getValue() == type) {
					return aps;
				}
			}
			return null;
		}
	}

	/**
	 * @title 发布库图片状态--0待编;1编辑;2送审;10已审;
	 * @crTime 2010-4-23
	 */
	public enum PublishPictureState {
		WAITINGCHECK(0),CHECKEDFAIL(1),EDITING(2), CHECKED(10),TOPUBLISH(11),PUBLISHFAIL(12),
		UNPUBLISHED(13), PUBLISHED(20),TOUNPUBLISH(21),UNPUBLISHFAIL(22),REJECTED(30);
		private int type;

		PublishPictureState(int type) {
			this.type = type;
		}

		public int getValue() {
			return this.type;
		}

		public static PublishPictureState getPictureSecurityType(int type) {
			for (PublishPictureState aps : PublishPictureState.values()) {
				if (aps.getValue() == type) {
					return aps;
				}
			}
			return null;
		}

	}
	/**
	 * 留资/密级类型--0普通;1秘密;2机密;3绝密
	 */
	public enum SecurityType {
		NORMAL(0), SECRET(1), CLASSIFIED(2), CONFIDENTIAL(3);

		private int type;

		SecurityType(int type) {
			this.type = type;
		}

		public int getValue() {
			return this.type;
		}

		public static SecurityType getPictureSecurityType(int type) {
			for (SecurityType pst : SecurityType.values()) {
				if (pst.getValue() == type) {
					return pst;
				}
			}
			return null;
		}
	}
	/**
	 * 色别----Color、BlackAndWhite、Red
	 */
	public enum ColorType {
		Color("Color"), BlackAndWhite("BlackAndWhite"), Red("Red");

		private String colorType;

		ColorType(String colorType) {
			this.colorType = colorType;
		}

		public String getValue() {
			return this.colorType;
		}
	}
	/**
	 * 图片取向模式----Landscape横图/Portrait竖图/Square方图
	 */
	public enum Orientation {
		Landscape("Landscape"), Portrait("Portrait"), Square("Square");

		private String orientation;

		Orientation(String orientation) {
			this.orientation = orientation;
		}

		public String getValue() {
			return this.orientation;
		}
	}
	
	
	/**
	 * 状态允许更改的列表
	 */
	public static List<String> pubPicStateList = new ArrayList<String>();
	

	static
	{
		pubPicStateList.add(PicStateConstants.PublishPictureState.WAITINGCHECK.getValue() +","+PicStateConstants.PublishPictureState.CHECKED.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.EDITING.getValue()+","+PicStateConstants.PublishPictureState.WAITINGCHECK.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.WAITINGCHECK.getValue()+","+PicStateConstants.PublishPictureState.CHECKED.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.WAITINGCHECK.getValue()+","+PicStateConstants.PublishPictureState.CHECKEDFAIL.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.CHECKEDFAIL.getValue()+","+PicStateConstants.PublishPictureState.EDITING.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.CHECKEDFAIL.getValue()+","+PicStateConstants.PublishPictureState.CHECKED.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.CHECKED.getValue()+","+PicStateConstants.PublishPictureState.TOPUBLISH.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.CHECKED.getValue()+","+PicStateConstants.PublishPictureState.CHECKEDFAIL.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.TOPUBLISH.getValue()+","+PicStateConstants.PublishPictureState.PUBLISHED.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.TOPUBLISH.getValue()+","+PicStateConstants.PublishPictureState.PUBLISHFAIL.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.PUBLISHFAIL.getValue()+","+PicStateConstants.PublishPictureState.TOPUBLISH.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.PUBLISHFAIL.getValue()+","+PicStateConstants.PublishPictureState.EDITING.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.PUBLISHED.getValue()+","+PicStateConstants.PublishPictureState.TOUNPUBLISH.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.TOUNPUBLISH.getValue()+","+PicStateConstants.PublishPictureState.UNPUBLISHED.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.TOUNPUBLISH.getValue()+","+PicStateConstants.PublishPictureState.UNPUBLISHFAIL.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.UNPUBLISHFAIL.getValue()+","+PicStateConstants.PublishPictureState.TOUNPUBLISH.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.UNPUBLISHED.getValue()+","+PicStateConstants.PublishPictureState.TOPUBLISH.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.CHECKED.getValue()+","+PicStateConstants.PublishPictureState.REJECTED.getValue());
		pubPicStateList.add(PicStateConstants.PublishPictureState.REJECTED.getValue()+","+PicStateConstants.PublishPictureState.CHECKED.getValue());
	}
	
	/**
	 * 栏目状态转换列表
	 */
	public static List<String> channelStateList = new ArrayList<String>();
	
	static
	{
		channelStateList.add(PicStateConstants.PublishChannelState.EDITING.getValue()+","+PicStateConstants.PublishChannelState.WAITINGCHECK.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.WAITINGCHECK.getValue()+","+PicStateConstants.PublishChannelState.CHECKED.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.WAITINGCHECK.getValue()+","+PicStateConstants.PublishChannelState.CHECKEDFAIL.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.CHECKEDFAIL.getValue()+","+PicStateConstants.PublishChannelState.EDITING.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.CHECKEDFAIL.getValue()+","+PicStateConstants.PublishChannelState.CHECKED.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.CHECKED.getValue()+","+PicStateConstants.PublishChannelState.TOPUBLISH.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.CHECKED.getValue()+","+PicStateConstants.PublishChannelState.CHECKEDFAIL.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.TOPUBLISH.getValue()+","+PicStateConstants.PublishChannelState.PUBLISHED.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.TOPUBLISH.getValue()+","+PicStateConstants.PublishChannelState.PUBLISHFAIL.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.PUBLISHFAIL.getValue()+","+PicStateConstants.PublishChannelState.TOPUBLISH.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.PUBLISHFAIL.getValue()+","+PicStateConstants.PublishChannelState.EDITING.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.PUBLISHED.getValue()+","+PicStateConstants.PublishChannelState.TOUNPUBLISH.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.TOUNPUBLISH.getValue()+","+PicStateConstants.PublishChannelState.UNPUBLISHED.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.TOUNPUBLISH.getValue()+","+PicStateConstants.PublishChannelState.UNPUBLISHFAIL.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.UNPUBLISHFAIL.getValue()+","+PicStateConstants.PublishChannelState.TOUNPUBLISH.getValue());
		channelStateList.add(PicStateConstants.PublishChannelState.UNPUBLISHED.getValue()+","+PicStateConstants.PublishChannelState.TOPUBLISH.getValue());
	}
}
