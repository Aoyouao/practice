package com.aoyouao.base;

import org.junit.Test;

import java.sql.*;
import java.util.Date;

public class JDBCOperation {
    @Test
    public void testQuerySingleRowAndCol() throws SQLException {
        //1.注册驱动
        //2.获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        //3.预编译SQL语句得到PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as count from t_emp");

        //4.执行SQL语句，获取结果
        ResultSet resultSet = preparedStatement.executeQuery();

        //5.处理结果
        while(resultSet.next()){
            int count = resultSet.getInt("count");
            System.out.println(count);
        }

        //6.释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testQuerySingleRow() throws SQLException {
        //1.注册驱动

        //2.获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        //3.预编译SQL语句得到PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement("select emp_id,emp_name,emp_salary,emp_age from t_emp where emp_id = ?");

        //4.为占位符赋值,执行SQL语句,并接收结果
        preparedStatement.setInt(1,5);
        ResultSet resultSet = preparedStatement.executeQuery();

        //5.处理结果
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

    @Test
    public void testQueryMoreRow() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("select emp_id,emp_name,emp_salary,emp_age from t_emp where emp_age > ?");

        preparedStatement.setInt(1,25);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testInsert() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("insert into t_emp(emp_name, emp_salary, emp_age) values(?,?,?)");

        preparedStatement.setString(1,"rose");
        preparedStatement.setDouble(2,345.67);
        preparedStatement.setInt(3,28);

        int result = preparedStatement.executeUpdate();

        if(result > 0){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("update t_emp set emp_salary = ? where emp_id = ?");

        preparedStatement.setDouble(1,888.88);
        preparedStatement.setInt(2,6);

        int result = preparedStatement.executeUpdate();

        if(result > 0){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("delete from t_emp where emp_id = ?");

        preparedStatement.setInt(1,6);

        int result = preparedStatement.executeUpdate();

        if(result > 0){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
        preparedStatement.close();
        connection.close();

    }
}
