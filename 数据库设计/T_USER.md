名称 | 类型 | 默认值 | 是否非空 | 说明
-- | -- | -- | -- |-- 
id | varchar | | 是 |  主键，使用UUID等算法生成的唯一标识符，而不是自增的数值，因为这样很容易猜出来用户id
username | varchar | | 是 | 用户登录名
password | varchar | | 是 | 加密后的登录密码
nickname | varchar |  |  | 昵称
age | tinyint |||年龄
deleted | tinyint |0|是|是否已删除
gmt_create|datetime||是|创建时间
gmt_modify|datetime|||最后修改时间
