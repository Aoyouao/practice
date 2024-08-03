package com.aoyouao.advanced;

import com.aoyouao.advanced.pojo.Employee;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAdvanced {
    @Test
    public void testORM() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("select emp_id,emp_name,emp_salary,emp_age from t_emp where emp_id = ?");

        preparedStatement.setInt(1,1);
        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;

        while(resultSet.next()){
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
        }
        System.out.println(employee);

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testQRMList() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("select emp_id,emp_name,emp_salary,emp_age from t_emp");

        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;
        List<Employee> employeeList = new ArrayList<>();

        while(resultSet.next()){
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
            //将每次循环封装的一行数据的对象存储在集合里
            employeeList.add(employee);
        }
        //处理结果，遍历集合
        for (Employee emp:employeeList){
            System.out.println(emp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testReturnPK() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        //预编译SQL语句，告知preparedStatement，返回新增数据的主键列的值
        String sql = "insert into t_emp(emp_name, emp_salary, emp_age) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

        //创建对象，将对象的属性值填充在？占位符上(ORM)
        Employee employee = new Employee(null,"jack",123.45,29);
        preparedStatement.setString(1, employee.getEmpName());
        preparedStatement.setDouble(2,employee.getEmpSalary());
        preparedStatement.setInt(3,employee.getEmpAge());

        //执行SQL，并获取返回的结果
        int result = preparedStatement.executeUpdate();
        ResultSet resultSet = null;
        //处理结果
        if(result > 0){
            System.out.println("成功");

            //获取当前新增数据的主键值，回显到Java中employee对象的empId属性上
            //返回的主键值，是一个单行单列的结果存储在resultSet中
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int empId = resultSet.getInt(1);
                employee.setEmpId(empId);
            }
            System.out.println(employee);
        }else{
            System.out.println("失败");
        }

        if(resultSet != null){
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testMoreInsert() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope", "root", "123456");

        String sql = "insert into t_emp(emp_name, emp_salary, emp_age) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        long start = System.currentTimeMillis();

        for(int i = 0;i < 10000;i++){
            preparedStatement.setString(1,"marry"+i);
            preparedStatement.setDouble(2,100.0+i);
            preparedStatement.setInt(3,20+i);

            preparedStatement.executeUpdate();
        }

        long end = System.currentTimeMillis();
        System.out.println("消耗时间" + (end - start));

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testBatch() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///hope?rewriteBatchedStatements=true", "root", "123456");

        /*
            1.必须在连接数据库的URL后面追加?rewriteBatchedStatements=true，允许批量操作
            2.新增SQL必须用values，且语句最后不要追加;结束
            3.调用addBatch()方法，将SQL语句进行批量添加操作
            4.统一执行批量操作，调用executeBatch()
         */
        String sql = "insert into t_emp(emp_name, emp_salary, emp_age) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        long start = System.currentTimeMillis();

        for(int i = 0;i < 10000;i++){
            preparedStatement.setString(1,"marry"+i);
            preparedStatement.setDouble(2,100.0+i);
            preparedStatement.setInt(3,20+i);

            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        long end = System.currentTimeMillis();
        System.out.println("消耗时间" + (end - start));

        preparedStatement.close();
        connection.close();
    }

}
