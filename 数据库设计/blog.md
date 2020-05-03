# 数据库表设计



## t_tag
名称 | 类型 | 默认值 | 非空 | 说明
-- | -- | -- | -- |-- 
id | bigint | | 是 | 主键
name | varchar(256) | | 是 | 标签名称
theme | varchar(64) | | | 主题色
deleted | tinyint | 0 | 是 | 是否软删除
gmt_create | datetime | | 是 | 创建时间
gmt_modify | datetime | | 是 | 最后修改时间



## t_category
名称 | 类型 | 默认值 | 非空 | 说明
-- | -- | -- | -- |-- 
id | bigint | | 是 | 主键
name | varchar(256) | | 是 | 分类名称
pid | bigint | | 是 | 父分类
path | varchar | '' | 是 | ID路径
deleted | tinyint | 0 | 是 | 软删除
gmt_create | datetime | | 是 | 创建日期
gmt_modify | datetime | | 否 | 最后修改日期



## t_article
名称 | 类型 | 默认值 | 非空 | 说明
-- | -- | -- | -- |-- 
id | varchar | | 是 | 主键
uid | varchar | | 是 | 用户id
cid | bigint | | 否 | 所属分类（可以悬空）
title | varchar(1024) | "" | 是 | 文章标题
summary | text | | 是 | 文章概要（文章的前X个字） 
draft | tinyint | 1 | 是 | 是否是草稿
deleted | tinyint | 0 | 是 | 是否软删除
gmt_create | datetime | | 是 | 创建时间
gmt_modify | datetime | | 否 | 最后修改时间



## t_article_content
名称 | 类型 | 默认值 | 非空 | 说明
-- | -- | -- | -- |-- 
aid | varchar | | 是 | 文章id
content | text | | 是 | 富文本内容
plain_content | text | | 是 | 纯文本内容



## t_article_tag
名称 | 类型 | 默认值 | 非空 | 说明
-- | -- | -- | -- |-- 
aid | bigint | | 是 | 文章ID
tid | bigint | | 是 | 标签ID
gmt_create | datetime | | 是 | 创建时间



## t_user
名称 | 类型 | 默认值 | 非空 | 说明
-- | -- | -- | -- |-- 
id | varchar | | 是 |  主键，使用UUID等算法生成的唯一标识符，而不是自增的数值，因为这样很容易猜出来用户id
username | varchar | | 是 | 用户登录名
password | varchar | | 是 | 加密后的登录密码
nickname | varchar |  |  | 昵称
age | tinyint |||年龄
deleted | tinyint |0|是|是否已删除
gmt_create|datetime||是|创建时间
gmt_modify|datetime|||最后修改时间

