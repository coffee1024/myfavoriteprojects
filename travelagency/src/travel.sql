DROP TABLE if exists adminuser;
CREATE TABLE adminuser (
  id int(12) NOT NULL auto_increment,
  user_name varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  phone varchar(20),
  group_id int not null,
  last_ip varchar(30),
  last_time timestamp,
  description varchar(2000),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE if exists customer;
CREATE TABLE customer (
  id int(12) NOT NULL auto_increment,
  user_name varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  phone varchar(20),
  group_id int not null,
  last_ip varchar(30),
  last_time timestamp,
  description varchar(2000),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE if exists customergroup;
CREATE TABLE customergroup (
  id  int(12) NOT NULL auto_increment,
  group_name varchar(20) NOT NULL,
  group_level int not null,
  description varchar(2000),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
delete from customergroup;

DROP TABLE if exists admingroup;
CREATE TABLE admingroup (
  id  int(12) NOT NULL auto_increment,
  group_name varchar(20) NOT NULL,
  group_level int not null,
  description varchar(2000),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE if exists travelagency;
CREATE TABLE travelagency (
  id  int(12) NOT NULL auto_increment,
  name varchar(20) NOT NULL,
  create_time timestamp,
  address varchar(2000),
  description varchar(2000),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE if exists route;
CREATE TABLE route (
  id  int(12) NOT NULL auto_increment,
  name varchar(20) NOT NULL,
  begintime timestamp,
  endtime timestamp,
  days int(5),
  keywords varchar(2000),
  description varchar(2000),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE if exists suggestwords;
CREATE TABLE suggestwords (
  id  int(20) NOT NULL auto_increment,
  searchnum int,
  pinyin varchar(100),
  words varchar(100),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE if exists searchwords;
CREATE TABLE searchwords (
  id  int(20) NOT NULL auto_increment,
  searchnum int,
  pinyin varchar(100),
  words varchar(100),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into suggestwords values (null,0,'butian','补填');
insert into route values(null,'本机双日游',null,null,5,'很好 一家 旅行','很好很强大的旅行社');
insert into adminuser values(null,'admin','94ff069bfaa1eaec','110',1,'',null,'系统超级管理员');
insert into travelagency values(null,'齐风旅行社',null,'北京市大屯路','很好的一家旅行社');
delete from admingroup;
insert into admingroup values(null,'超级管理员',0,'可以分配管理员、删除用户、进行系统备份等操作');
insert into admingroup values(null,'普通管理员',1,'可以进行管理用户相关的操作');
insert into admingroup values(null,'业务管理员',2,'可以进行的相关的业务操作');
insert into admingroup values(null,'普通员工',3,'可以进行基础信息的查看');
insert into admingroup values(null,'普通客户',3,'可以进行基础信息的查看');
insert into customergroup values(null,'普通用户',0,'可以享受基本的服务');
insert into customergroup values(null,'VIP用户',1,'可以享受更高的服务');
insert into customer values(null,'刘光强','root','110',2,'',null,'测试用户');
insert into customer values(null,'周强','root','110',2,'',null,'测试用户');
insert into customer values(null,'李岩','root','110',2,'',null,'测试用户');
insert into customer values(null,'陈伟','root','110',2,'',null,'测试用户');
insert into customer values(null,'邢超','root','110',2,'',null,'测试用户');
select words from suggestwords where pinyin = 'nihao' and words <> '你好' order by searchnum desc;
select words from suggestwords where pinyin like 'nihao%' order by searchnum desc;