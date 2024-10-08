-- -------多表查询-----------------
-- 准备数据
create table dept(
    id   int auto_increment comment 'ID' primary key,
    name varchar(50) not null comment '部门名称'
)comment '部门表';

create table emp(
    id  int auto_increment comment 'ID' primary key,
    name varchar(50) not null comment '姓名',
    age  int comment '年龄',
    job varchar(20) comment '职位',
    salary int comment '薪资',
    entrydate date comment '入职时间',
    managerid int comment '直属领导ID',
    dept_id int comment '部门ID'
)comment '员工表';

-- 添加外键
alter table emp add constraint fk_emp_dept_id foreign key (dept_id) references dept(id);

INSERT INTO dept (id, name) VALUES (1, '研发部'), (2, '市场部'),(3, '财务部'), (4, '销售部'), (5, '总经办'), (6, '人事部');
INSERT INTO emp (id, name, age, job,salary, entrydate, managerid, dept_id) VALUES
            (1, '金庸', 66, '总裁',20000, '2000-01-01', null,5),

            (2, '张无忌', 20, '项目经理',12500, '2005-12-05', 1,1),
            (3, '杨逍', 33, '开发', 8400,'2000-11-03', 2,1),
            (4, '韦一笑', 48, '开发',11000, '2002-02-05', 2,1),
            (5, '常遇春', 43, '开发',10500, '2004-09-07', 3,1),
            (6, '小昭', 19, '程序员鼓励师',6600, '2004-10-12', 2,1),

            (7, '灭绝', 60, '财务总监',8500, '2002-09-12', 1,3),
            (8, '周芷若', 19, '会计',48000, '2006-06-02', 7,3),
            (9, '丁敏君', 23, '出纳',5250, '2009-05-13', 7,3),

            (10, '赵敏', 20, '市场部总监',12500, '2004-10-12', 1,2),
            (11, '鹿杖客', 56, '职员',3750, '2006-10-03', 10,2),
            (12, '鹤笔翁', 19, '职员',3750, '2007-05-09', 10,2),
            (13, '方东白', 19, '职员',5500, '2009-02-12', 10,2),

            (14, '张三丰', 88, '销售总监',14000, '2004-10-12', 1,4),
            (15, '俞莲舟', 38, '销售',4600, '2004-10-12', 14,4),
            (16, '宋远桥', 40, '销售',4600, '2004-10-12', 14,4),
            (17, '陈友谅', 42, null,2000, '2011-10-12', 1,null);

-- 多表查询 笛卡尔积
select * from emp,dept;
select * from emp,dept where emp.dept_id = dept.id;


-- 内连接演示
-- 1.查询每一个员工的姓名，及关联的部门的名称(隐式内连接实现)
select e.name,d.name from emp e,dept d where e.dept_id = d.id;

-- 1.查询每一个员工的姓名，及关联的部门的名称(显式内连接实现) ---[inner] join...on...
select e.name,d.name from emp e inner join dept d on e.dept_id = d.id;


-- 外连接演示
-- 1.查询emp表的全部数据，和对应的部门信息(左外连接)
select e.*,d.name from emp e left outer join dept d on e.dept_id = d.id;

-- 2.查询dept表的全部数据，和对应的员工信息(右外连接)
select d.*,e.* from emp e right outer join dept d on e.dept_id = d.id;

select d.*,e.* from dept d left join emp e on e.dept_id = d.id;


-- 自连接演示 自连接可以是内连接也可以是外连接
-- 1.查询员工及其所属领导的名字
select a.name,b.name from emp a,emp b where a.managerid = b.id;

-- 2.查询所有员工emp及其领导的名字emp，如果员工没有领导，也需要查出来
select a.name '员工',b.name '领导' from emp a left join emp b on a.managerid = b.id;


-- 联合查询演示 union all,union
-- 1.将薪资低于5000的员工，和年龄大于50岁的员工全部查询出来

-- 不去重
select * from emp where salary < 5000
union all
select * from emp where age >50;

-- 去重
select * from emp where salary < 5000
union
select * from emp where age > 50;


-- -----------------------子查询-------------------------------
-- 标量子查询
-- 1.查询“销售部”的所有员工信息
select * from emp where dept_id = (select id from dept where name = '销售部');

-- 2.查询在方东白 入职之后的员工信息
select * from emp where entrydate > (select entrydate from emp where name = '方东白');


-- 列子查询
-- 1. 查询 "销售部" 和 "市场部" 的所有员工信息
select * from emp where dept_id in (select id from dept where name = '销售部' or name = '市场部');

-- 2. 查询比 财务部 所有人工资都高的员工信息
select * from emp where salary > all(select salary from emp where dept_id = (select id from dept where name = '财务部'));

-- 3. 查询比研发部其中任意一人工资高的员工信息
select * from emp where salary > any(select salary from emp where dept_id = (select id from dept where name = '研发部'));


-- 行子查询
-- 1. 查询与 "张无忌" 的薪资及直属领导相同的员工信息
select * from emp where (salary,managerid) = (select salary,managerid from emp where name = '张无忌');


-- 表子查询
-- 1. 查询与 "鹿杖客" , "宋远桥" 的职位和薪资相同的员工信息
select * from emp where (job,salary) in (select job,salary from emp where name = '鹿杖客' or name = '宋远桥');

-- 2. 查询入职日期是 "2006-01-01" 之后的员工信息 , 及其部门信息
-- a.入职日期是 "2006-01-01" 之后的员工信息
select * from emp where entrydate > '2006-01-01';
select e.*,d.* from (select * from emp where entrydate > '2006-01-01') e left join dept d on e.dept_id = d.id;