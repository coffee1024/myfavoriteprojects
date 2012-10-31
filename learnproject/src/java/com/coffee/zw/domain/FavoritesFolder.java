package com.coffee.zw.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "favoritesfolder")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
// @javax.persistence.SequenceGenerator(name = "SEQ_PICTURE", sequenceName =
// "SEQ_PICTURE")
// @FilterDef(name = "publish", parameters = @ParamDef(name = "pubLev", type =
// "integer"), defaultCondition = "entry_delete_flag<1 and entry_publish=1 and
// entry_pub_level<=:pubLev")
public class FavoritesFolder  extends BaseBean{
	// @GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PICTURE")
//	//@SequenceGenerator(name = "SEQ_PICTURE", sequenceName = "SEQ_PICTURE")
//	//重启tomcat后，如果使用默认的allocationSize，则为50，可能造成id不连续。需要则进行修改
//	@SequenceGenerator(name = "SEQ_PICTURE", sequenceName = "SEQ_PICTURE", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "paymentableGenerator",strategy = GenerationType.IDENTITY)  
	@GenericGenerator(name = "paymentableGenerator", strategy = "identity")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "FOLDER_NAME")
	private String folderName;

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
}
