insert into employee (id, workno, name, gender, age, idcard, entrydate) values (1,'1','莎莎','女',23,123456789123456789,'2005-01-01');

select * from employee;

insert into employee values (2,'2','大头','男',24,123456789987654321,'2005-01-01');

insert into employee values(3,'3','大迪','女',28,123987456321456789,'2004-01-01'),(4,'4','小胖','男',27,456987123654789123,'2006-01-01');

update employee set name = '马龙' where id = 4;

update employee set name = '小胖',gender = '女' where id = 4;

update employee set entrydate = '2008-01-01';

delete from employee where gender = '男';

delete from employee;
