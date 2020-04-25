drop database if exists blog;

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
	id bigint auto_increment,
	uid varchar(256),
	cid bigint,
	title varchar(1024) not null default '',
	summary varchar(1024) not null default '',
	draft tinyint not null default 1,
	deleted tinyint not null default 0,
	gmt_create datetime,
	gmt_modify datetime,
	primary key(id)
);

create table t_article_content(
	aid varchar(256) not null,
	content text not null,
	plain_content text,
	primary key(article_id)
);

create table t_tag(
	id bigint auto_increment,
	name varchar(256) not null,
	theme varchar(64),
	deleted tinyint not null default 0,
	gmt_create datetime,
	gmt_modify datetime,
	primary key(id)
);

insert into t_tag (id, name, theme, gmt_create) values (1, 'Vue', '#0f0', '2020-04-24 21:52:40');

create table t_article_tag(
	aid bigint,
	tid bigint,
	gmt_create datetime,
	primary key(aid, tid)
);

create table t_category(
	id bigint auto_increment,
	name varchar(256) not null unique,
	pid bigint,
	path varchar(256),
	deleted tinyint not null default 0,
	gmt_create datetime,
	gmt_modify datetime,
	primary key(id)
);

insert into t_category (id, name) values (1, '随笔');
