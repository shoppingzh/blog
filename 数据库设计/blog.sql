drop database if exist blog;

create database blog default character set utf8;
use blog;

create table t_user(
    id varchar(256),
    username varchar(128) not null,
    password varchar(256) not null,
    nickname varchar(128),
    age tinyint,
    deleted tinyint not null default 0,
    gmt_create datetime,
    gmt_modify datetime,
	primary key(id)
);

insert into t_user (id, username, password, nickname) values ('0', 'zxp', '123', '管理员');

create table t_article(
	id varchar(256),
	user_id varchar(256),
	title varchar(1024) not null default '',
	summary varchar(1024) not null default '',
	draft tinyint not null default 1,
	deleted tinyint not null default 0,
	gmt_create datetime,
	gmt_modify datetime,
	primary key(id)
);

create table t_article_content(
	article_id varchar(256) not null,
	content text not null,
	primary key(article_id)
);

create table t_tag(
	id varchar(256),
	name varchar(256) not null,
	deleted tinyint not null default 0,
	gmt_create datetime,
	gmt_modify datetime,
	primary key(id)
);
