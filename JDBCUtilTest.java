package com.aoyouao.senior;

import com.aoyouao.senior.dao.BankDao;
import com.aoyouao.senior.dao.EmployeeDao;
import com.aoyouao.senior.dao.impl.BankDaoImpl;
import com.aoyouao.senior.dao.impl.EmployeeDaoImpl;
import com.aoyouao.senior.pojo.Employee;
import com.aoyouao.senior.util.JDBCUtil;
import com.aoyouao.senior.util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCUtilTest {
    @Test
    public void testGetConnection(){
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);

        JDBCUtil.release(connection);
    }

    @Test
    public void testJDBCUtilV2(){
        /*Connection connection1 = JDBCUtil.getConnection();
        Connection connection2 = JDBCUtil.getConnection();
        Connection connection3 = JDBCUtil.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);*/

        Connection connection1 = JDBCUtilV2.getConnection();
        Connection connection2 = JDBCUtilV2.getConnection();
        Connection connection3 = JDBCUtilV2.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
    }

    @Test
    public void testEmployeeDao(){
        //1.创建Dao类实现对象
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        /*List<Employee> employees = employeeDao.selectAll();

        for(Employee employee:employees){
            System.out.println("employee="+employee);
        }*/

        /*Employee employee = employeeDao.selectByEmpId(1);
        System.out.println("employee="+employee);*/


        /*Employee employee = new Employee(6,"tom",300.65,38);
        int insert = employeeDao.insert(employee);
        System.out.println(insert);*/

       /* Employee employee = new Employee(6,"tom",656.65,38);
        int update = employeeDao.update(employee);
        System.out.println(update);*/

        int delete = employeeDao.delete(6);
        System.out.println(delete);
    }

    @Test
    public void testTransaction(){
        BankDao bankDao = new BankDaoImpl();
        Connection connection = null;

        try {
            //获取连接，将连接的事务提交改为手动提交
            connection = JDBCUtilV2.getConnection();
            connection.setAutoCommit(false);
            //操作减钱
            bankDao.subMoney(1,100);
            int i = 10/0;
            //操作加钱
            bankDao.addMoney(2,100);
            //前置的多次dao操作，没有异常，提交事务
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally{
            JDBCUtilV2.release();
        }
    }
}
