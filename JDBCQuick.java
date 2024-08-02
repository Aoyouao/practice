package com.aoyouao.base;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCQuick {
    public static void main(String[] args) throws Exception {

        //1.注册驱动<可以省略>
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //DriverManager.registerDriver(new Driver());

        //2.获取连接对象
        String url = "jdbc:mysql://localhost:3306/hope";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);

        //3.获取执行SQL语句的对象
        Statement statement = connection.createStatement();

        //4.编写SQL语句，并执行，接受返回的结果集
        String sql = "select emp_id,emp_name,emp_salary,emp_age from t_emp";
        ResultSet resultSet = statement.executeQuery(sql);

        //5.处理结果：遍历resultSet结果集
        while(resultSet.next()){
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
        }

        //6.释放资源（先开后关原则）
        resultSet.close();
        statement.close();
        connection.close();
    }
}
