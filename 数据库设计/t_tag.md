名称 | 类型 | 默认值 | 是否非空 | 说明
-- | -- | -- | -- |-- 
id | bigint | | 是 | 主键
name | varchar(256) | | 是 | 标签名称
theme | varchar(64) | | | 主题色
deleted | tinyint | 0 | 是 | 是否软删除
gmt_create | datetime | | 是 | 创建时间
gmt_modify | datetime | | 是 | 最后修改时间

