package com.aoyouao.advanced.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {
    @Test
    public void testHardCodeDruid() throws SQLException {
        /*
           硬编码：将连接池的配置信息和Java代码耦合在一起
           1.创建DruidDataSource连接池对象
           2.设置连接池的配置信息【必须|非必须】
           3.通过连接池获取连接对象
           4.回收连接【不是释放连接，而是将连接归还给连接池，给其他线程进行复用】
         */
        //1.创建DruidDataSource连接池对象
        DruidDataSource druidDataSource = new DruidDataSource();

        //2.设置连接池的配置信息【必须|非必须】
        //2.1必须
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql:///hope");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        //2.2非必须
        druidDataSource.setInitialSize(10);
        druidDataSource.setMaxActive(20);

        //3.通过连接池获取连接对象
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);

        //基于connection进行CRUD

        //4.释放连接
        connection.close();
    }

    @Test
    public void testResourcesDruid() throws Exception {
        //1.创建Properties集合，用于存储外部配置文件的key和value值
        Properties properties = new Properties();

        //2.读取外部配置文件，获取输入流，加载到Properties集合里
        InputStream inputStream = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
        properties.load(inputStream);

        //3.基于Properties集合构造DruidDataSource连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        //4.通过连接池获取连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //5.开发CRUD

        //6.回收连接
        connection.close();
    }
}
