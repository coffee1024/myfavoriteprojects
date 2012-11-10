package com.coffee.quartz;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *@author  liuguangqiang
 *@date    2012-8-17 下午2:53:53
 *@version 1.0
 **/
public class ClearUploadPic extends ConnectDbBase {
	String selectsql="select picture_id,filename from archivepicture where upload_progress=0";
	String deletesql="delet from archivepicture where upload_progress=0";
	
	public void deletePics(){
	}
	
}
