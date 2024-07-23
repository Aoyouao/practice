drop table employee;

create table emp(
    id int comment '编号',
    workno varchar(10) comment '工号',
    name varchar(10) comment '姓名',
    gender char(1) comment '性别',
    age int unsigned comment '年龄',
    idcad char(18) comment '身份证号',
    workaddress varchar(50) comment '工作地址',
    entrydate date comment '入职时间'
) comment '员工表';

insert into emp (id, workno, name, gender, age, idcad, workaddress, entrydate)
values (1,'1','大头儿子','男',4,'123456789987456321','重庆','2000-05-02'),
       (2,'2','小头爸爸','男',28,'123456489987456321','重庆','1989-05-02'),
       (3,'3','围裙妈妈','女',24,'123336789987456321','重庆','1995-05-02'),
       (4,'4','胡图图','男',4,'123455789987456321','上海','2004-05-02'),
       (5,'5','胡英俊','男',27,'12345678966745632x','上海','1988-05-02'),
       (6,'6','张小莉','女',27,'123336789987456321','上海','1990-05-02'),
       (7,'7','全全','男',4,'321456789987456321','北京','2008-05-02'),
       (8,'8','闵慧','女',29,'321356789987456321','北京','1995-05-02'),
       (9,'9','辛旗','男',30,'321996789987456321','深圳','1995-05-02'),
       (10,'10','李莲花','男',88,'123456999987456321','武汉','1000-05-02'),
       (11,'11','方多病','男',18,'123456555987456321','武汉','1025-05-02'),
       (12,'12','笛飞声','男',45,'123456444987456321','武汉','1000-05-02'),
       (13,'13','角丽瞧','女',42,'123456222987456321','武汉','1010-05-02'),
       (14,'14','石水','女',36,'123456781117456321','恩施','1015-05-02'),
       (15,'15','大头','男',24,'12345677778745632x','巴黎','2007-05-02'),
       (16,'16','莎莎','女',20,null,'巴黎','2007-05-02');

-- ----查询需求----

-- 基本查询222

-- 1.查询指定字段name,workno,age 返回
select name,workno,age from emp;


-- 2.查询所有字段返回
select id,workno,name,gender,age,idcad,workaddress,entrydate from emp;

select * from emp;

-- 3.查询所有员工的工作地址，起别名
select workaddress as '工作地址' from emp;

select workaddress '工作地址' from emp;

-- 4.查询公司员工的上班地址(不要重复)
select distinct workaddress from emp;

-- 条件查询
-- 1.查询年龄等于88的员工

select * from emp where age = 88;

-- 2.查询年龄小于20的员工信息

select * from emp where age < 20;

-- 3.查询年龄小于等于20的员工信息

select * from emp where age <= 20;

-- 4.查询没有身份证号的员工信息

select * from emp where idcad is null;

-- 5.查询年龄不等于88的员工信息

select * from emp where age != 88;

select * from emp where age <> 88;

-- 6.查询有身份证号的员工信息

select * from emp where idcad is not null;

-- 7.查询年龄在15（包含）到42（包含)之间的员工信息

select * from emp where age >= 15 && age <=42;

select * from emp where age >= 15 and age <=42;

select * from emp where age between 15 and 20;

-- 8.查询性别为女且年龄小于25岁的员工信息

select * from emp where gender = '女' and age <25;

-- 9.查询年龄等于18或24或36的员工信息

select * from emp where age = 18 or age = 24 or age = 36;

select * from emp where age in(18,24,36);

-- 10.查询姓名为两个字的员工信息

select * from emp where name like '__';

-- 11.查询身份证最后一位为x的员工信息

select * from emp where idcad like '%x';

select * from emp where idcad like '_________________x';

