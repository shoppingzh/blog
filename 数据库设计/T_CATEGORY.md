名称 | 类型 | 默认值 | 是否非空 | 说明
-- | -- | -- | -- |-- 
id | bigint | | 是 | 主键
name | varchar(256) | | 是 | 分类名称
pid | bigint | | 是 | 父分类
path | varchar | '' | 是 | ID路径
deleted | tinyint | 0 | 是 | 软删除
gmt_create | datetime | | 是 | 创建日期
gmt_modify | datetime | | 否 | 最后修改日期
