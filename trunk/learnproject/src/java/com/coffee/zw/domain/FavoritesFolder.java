package com.coffee.zw.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.coffee.zw.util.StringUtils;

/**
 * @Title 收藏夹实体类
 * @Author liuguangqiang
 * @crTime 2010-4-7 下午11:02:28
 * @Version 1.0
 */
@Entity
// @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "FAVORITESFOLDER")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
// @javax.persistence.SequenceGenerator(name = "SEQ_PICTURE", sequenceName =
// "SEQ_PICTURE")
// @FilterDef(name = "publish", parameters = @ParamDef(name = "pubLev", type =
// "integer"), defaultCondition = "entry_delete_flag<1 and entry_publish=1 and
// entry_pub_level<=:pubLev")
public class FavoritesFolder  extends TrsBean{
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PICTURE")
//	//@SequenceGenerator(name = "SEQ_PICTURE", sequenceName = "SEQ_PICTURE")
//	//重启tomcat后，如果使用默认的allocationSize，则为50，可能造成id不连续。需要则进行修改
//	@SequenceGenerator(name = "SEQ_PICTURE", sequenceName = "SEQ_PICTURE", allocationSize = 1)
	@GeneratedValue(generator = "paymentableGenerator")  
	@GenericGenerator(name = "paymentableGenerator", strategy = "sequence",parameters = { @Parameter(name = "sequence", value = "SEQ_FAVORITESFOLDER") })
	@Column(name = "ID")
	private Long id;

	@Column(name = "FOLDER_NAME")
	private String folderName;
	
	private String truckName;
	
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "PICTURE_NUM")
	private Integer pictureNum;

	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "SITE_ID")
	private Integer siteId = 1;

	public FavoritesFolder() {
		super();
	}

	public FavoritesFolder(Long id, String folderName, Long userId,
			String userName, Date createTime, Integer pictureNum,
			Integer status, Integer siteId) {
		super();
		this.id = id;
		this.folderName = folderName;
		this.userId = userId;
		this.userName = userName;
		this.createTime = createTime;
		this.pictureNum = pictureNum;
		this.status = status;
		this.siteId = siteId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPictureNum() {
		return pictureNum;
	}

	public void setPictureNum(Integer pictureNum) {
		this.pictureNum = pictureNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getTruckName() {
		String string=folderName;
			try {
				string=StringUtils.cutString(string, 15, "...");
			} catch (Exception e) {
				e.printStackTrace();
				string=folderName;
			}
		return string;
	}
}
