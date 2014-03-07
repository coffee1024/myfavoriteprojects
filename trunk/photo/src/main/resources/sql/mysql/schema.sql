drop table if exists p_task;
drop table if exists p_user;

create table p_task (
	id bigint auto_increment,
	title varchar(128) not null,
	description varchar(255),
	user_id bigint not null,
    primary key (id)
) engine=InnoDB;

create table p_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	nick_name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	score double not null,
	score double,
	score double,
	register_date timestamp not null default 0,
	primary key (id)
) engine=InnoDB;