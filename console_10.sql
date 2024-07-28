-- 查询建表语句   默认存储引擎：InnoDB
show create table account;

-- 查询当前数据库支持的存储引擎
show engines;

-- 创建表 my_myisam,并指定MyISAM引擎对象
create table my_myisam(
    id int,
    name varchar(10)
) engine = MyISAM;

-- 创建表 my_memory,并指定Memory引擎对象
create table my_memory(
    id int,
    name varchar(10)
) engine = Memory;