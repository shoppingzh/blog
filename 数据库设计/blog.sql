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
    gmt_modify datetime
);

insert into t_user (id, username, password, nickname) values ('0', 'zxp', '123', '管理员');
