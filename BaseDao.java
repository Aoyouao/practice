package com.aoyouao.senior.dao;

import com.aoyouao.senior.util.JDBCUtilV2;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 将共性的数据库的操作代码封装在BaseDao里
 */
public class BaseDao {
    /**
     * 通用的增删改的方法
     * @param sql 调用者要执行的SQL语句
     * @param params SQL语句中的占位符要赋值的参数
     * @return 受影响的行数
     */
    public int executeUpdate(String sql,Object...params) throws Exception{
        //1.通过JDBCUtilV2获得数据库连接
        Connection connection = JDBCUtilV2.getConnection();

        //2.预编译SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //3.为占位符赋值，执行SQL，接收返回结果
        if(params != null && params.length > 0){
            for(int i = 0;i<params.length;i++){
                preparedStatement.setObject(i+1,params[i]);
            }
        }
        int row = preparedStatement.executeUpdate();

        //4.释放资源
        preparedStatement.close();
        if(connection.getAutoCommit()){
            JDBCUtilV2.release();
        }

        //5.处理结果
        return row;
    }

    /**
     * 通用的查询：多行多列，单行多列，单行单列
     *          多行多列：List<Employee>
     *          单行多列：Employee
     *          单行单列：封装的是一个结果 Double,Integer......
     * 封装过程：
     *     1.返回的类型：泛型：类型不确定，调用者知道，调用时，将此次查询的结果类型告知BaseDao就可以了
     *     2.返回的结果：通用，List  可以存储多个结果，也可以存储一个结果 get(0)
     *     3.结果的封装：反射，要求调用者告知BaseDao要封装的类对象 Class
     */
    public <T> List<T> executeQuery(Class<T> clazz,String sql,Object...params)throws Exception{
        //获取连接
        Connection connection = JDBCUtilV2.getConnection();

        //预编译SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        //设置占位符的值
        if(params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1,params[i]);
            }
        }

        //执行SQL，并接收返回的结果集
        ResultSet resultSet = preparedStatement.executeQuery();

        //获取结果集中的原数据对象
        //包含了 列的数量，每个列的名称
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<T> list = new ArrayList<>();
        //处理结果
        while(resultSet.next()){
            //循环一次，代表有一行数据，通过反射创建一个对象
            T t = clazz.newInstance();
            //循环遍历当前行的列，循环几次，看有多少列
            for(int i = 1;i <=columnCount;i++){
                //通过下标获取列的值
                Object value = resultSet.getObject(i);
                //获取到的列的value值，这个值就是t这个对象中的某一个属性
                //获取当前拿到的列的名字 = 对象的属性名
                String fieldName = metaData.getColumnLabel(i);
                Field field = clazz.getDeclaredField(fieldName);
                //突破封装的private
                field.setAccessible(true);
                field.set(t,value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()){
            JDBCUtilV2.release();
        }
        return list;
    }

    /**
     * 通用查询：在上面查询的集合结果中获取第一个结果。简化了单行单列的获取和单行多列的获取
     */
    public <T> T executeQueryBean(Class<T> clazz,String sql,Object...params)throws Exception{
        List<T> list = this.executeQuery(clazz, sql, params);
        if(list == null || list.size() == 0){
            return null;
        }
        return list.get(0);
    }
}
