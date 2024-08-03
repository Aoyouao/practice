package com.aoyouao.advanced.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariTest {
    @Test
    public void testHardCodeHikari() throws Exception {
        /*
           硬编码：将连接池的配置信息与Java代码耦合在一起
           1.创建HikariDataSource连接池对象
           2.设置连接池的配置信息【必须|非必须】
           3.通过连接池获取连接对象
           4.回收连接
         */
        //1.创建HikariDataSource连接池对象
        HikariDataSource hikariDataSource = new HikariDataSource();

        //2.设置连接池的配置信息【必须|非必须】
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql:///hope");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("123456");

        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setMaximumPoolSize(20);

        //3.通过连接池获取连接对象
        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection);

        //4.回收连接
        connection.close();
    }

    @Test
    public void testResourcesHikari() throws Exception {
        //1.创建Properties集合，用于存储外部配置文件的key和value值
        Properties properties = new Properties();

        //2.读取外部配置文件，获取输入流，加载到Properties集合里
        InputStream inputStream = HikariTest.class.getClassLoader().getResourceAsStream("hikari.properties");
        properties.load(inputStream);

        //3.创建HikariConfig连接池配置对象，将Properties集合传进去
        HikariConfig hikariConfig = new HikariConfig(properties);

        //4.基于HikariConfig连接池配置对象，构造HikariDataSource
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        //5.获取连接
        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection);

        //6.回收连接
        connection.close();
    }
}
