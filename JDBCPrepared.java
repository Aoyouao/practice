package com.aoyouao.base;

import java.sql.*;
import java.util.Scanner;

public class JDBCPrepared {
    public static void main(String[] args) throws Exception{
        //1.注册驱动<可省略>

        //2.获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        //3.获取执行sql语句的对象
        PreparedStatement preparedStatement = connection.prepareStatement("select emp_id,emp_name,emp_salary,emp_age from t_emp where emp_name = ?");


        System.out.println("请输入员工姓名:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        //4.为？占位符赋值，并执行SQL语句，接收返回的结果集
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();

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
        preparedStatement.close();
        connection.close();
    }
}
