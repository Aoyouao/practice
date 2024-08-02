create table t_emp(
    emp_id int auto_increment comment '员工编号' primary key,
    emp_name varchar(100) not null comment '员工姓名',
    emp_salary double(10,5) not null comment '员工薪资',
    emp_age int not null comment '员工年龄'
);
insert into t_emp (emp_name, emp_salary, emp_age)
values('andy',777.77,32),
      ('大风哥',666.66,41),
      ('康师傅',111,23),
      ('Gavin',123,26),
      ('小鱼儿',123,28);

select emp_id,emp_name,emp_salary,emp_age from t_emp;

select emp_id,emp_name,emp_salary,emp_age from t_emp where emp_id = 1;

select emp_id,emp_name,emp_salary,emp_age from t_emp where emp_age >25;

insert into t_emp(emp_name, emp_salary, emp_age) values();

update t_emp set emp_salary = ? where emp_id = ?;

delete from t_emp where emp_id = ?;