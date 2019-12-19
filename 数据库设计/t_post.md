名称 | 类型 | 默认值 | 是否非空 | 说明
-- | -- | -- | -- |-- 
id | varchar | | 是 | 主键
user_id | varchar | | 是 | 用户id
title | varchar(1024) | "" | 是 | 文章标题
content | text | | 是 | 文章内容
draft | tinyint | 1 | 是 | 是否是草稿
deleted | tinyint | 0 | 是 | 是否软删除
gmt_create | datetime | | 是 | 创建时间
gmt_modify | datetime | | 否 | 最后修改时间