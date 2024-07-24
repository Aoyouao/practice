-- ----------函数演示----------
-- -----字符串函数-----------
-- concat
select concat('Hello',' Mysql');

-- lower
select lower('Hello');

-- upper
select upper('Hello');

-- lpad
select lpad('Hi',5,'-');

-- rpad
select rpad('Hi',5,'-');

-- trim
select trim('  Hello  Mysql  ');

-- substring
select substring('Hello Mysql',1,7);

-- 1.由于企业需求变更，企业员工的工号，统一为5位数，目前不足5位数的全部在前面补0
update emp set workno = lpad(workno,5,'0');


-- 数值函数
-- ceil
select ceil(-1.2);

-- floor
select floor(-1.9);

-- mod
select mod(7,4);

-- rand
select rand();

-- round
select round(2.345,2);

-- 通过数据库的函数，生成一个六位数的随机验证码
select lpad(round(rand()*1000000, 0), 6, '0');


-- 日期函数
-- curdate
select curdate();

-- curtime
select curtime();

-- now
select now();

-- year,month,day
select year(now());
select month(now());
select day(now());

-- date_add
select date_add(now(),interval 99 day);

-- datediff
select datediff('2024-07-24','2005-06-20');

-- 查询所有员工的入职天数，并根据入职天数倒序排序
select name,datediff(now(),entrydate) as '入职天数' from emp order by 入职天数 desc;


-- 流程控制函数
-- if
select if(1 = 0,'OK','ERROR');

-- ifnull
select ifnull(' ','DEfault');
select ifnull(null,'Default');

-- case when then else end
-- 查询emp表的员工姓名和工作地址（北京/上海--->一线城市，其他--->二线城市）
select
    name,
    case workaddress when '北京' then '一线城市' when '上海' then '一线城市' else '二线城市' end as '工作地址'
from emp;

-- 统计班级各个学员的成绩，展示的规则如下
-- >=82,优秀   >=60,及格    否则，不及格

create table score(
    id int comment 'ID',
    name varchar(20) comment '姓名',
    math int comment '数学',
    english int comment '英语',
    chinese int comment '语文'
) comment '学生成绩表';

insert into score(id, name, math, english, chinese)
values(1,'tom',67,88,95),
      (2,'rose',23,66,90),
      (3,'jack',56,98,76);

select
    id,
    name,
    case when math >= 85 then '优秀' when math >= 60 then '及格' else '不及格' end as '数学',
    case when english >= 85 then '优秀' when english >= 60 then '及格' else '不及格' end as '英语',
    case when chinese >= 85 then '优秀' when chinese >= 60 then '及格' else '不及格' end as '语文'
from score;