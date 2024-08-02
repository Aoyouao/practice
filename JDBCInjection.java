package com.aoyouao.base;

import java.sql.*;
import java.util.Scanner;

//演示SQL注入攻击问题
public class JDBCInjection {
    public static void main(String[] args) throws Exception {
        //1.注册驱动<可省略>

        //2.获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        //3.获取执行sql语句的对象
        Statement statement = connection.createStatement();

        System.out.println("请输入员工姓名:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        //4.编写SQL语句，并执行，接收返回的结果集
        String sql = "select emp_id,emp_name,emp_salary,emp_age from t_emp where emp_name = '"+name+"'";
        ResultSet resultSet = statement.executeQuery(sql);

        //5.处理结果，遍历resultSet
        while(resultSet.next()){
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
        }

        //6.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
