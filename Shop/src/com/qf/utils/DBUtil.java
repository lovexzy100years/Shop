package com.qf.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil {

    //德鲁伊数据库连接池
    private static DruidDataSource pool;

    static{
        Properties p = new Properties();
        try {
            p.load(DBUtil.class.getClassLoader().getResourceAsStream("DBConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driverName = p.getProperty("driverName");
        String url = p.getProperty("url");
        String username = p.getProperty("username");
        String password = p.getProperty("password");
        int maxActive = Integer.parseInt(p.getProperty("maxActive"));

        //创建连接池
        pool = new DruidDataSource();

        //设置参数
        pool.setDriverClassName(driverName);
        pool.setUrl(url);
        pool.setUsername(username);
        pool.setPassword(password);
        pool.setMaxActive(maxActive);
    }

    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    /**
     * 获取连接对象
     * @return 返回连接对象
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = local.get();
        if(connection == null){
            connection = pool.getConnection();
            local.set(connection);
        }
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                if(connection.getAutoCommit()){
                    connection.close();
                    local.set(null);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //开启事务
    public static void startTransaction() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    //提交事务
    public static void commit() throws SQLException {
        Connection connection = local.get();
        if(connection != null){
            connection.commit();
            connection.close();
            local.set(null);
        }
    }

    //回滚事务
    public static void rollback() throws SQLException {
        Connection connection = local.get();
        if(connection != null){
            connection.rollback();
            connection.close();
            local.set(null);
        }
    }


    /**
     * 更新数据（添加、删除、修改）
     * @param sql sql命令
     * @param params 数据参数
     * @return 对行造成的影响
     */
    public static int commonUpdate(String sql,Object... params){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParams(statement,params);
            int num = statement.executeUpdate();
            return num;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
        return  -1;
    }

    /**
     * 主键回填（SQL做插入操作，返回int 的主键）
     * @param sql sql命令
     * @param params 数据参数
     * @return 返回int主键
     */
    public static int commonInsert(String sql,Object... params){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);//添加操作就返回int主键
            setParams(statement,params);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int primaryKey =  resultSet.getInt(1);
                return primaryKey;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
        return  -1;
    }

    public static <T> List<T> commonQuery(Class<T> clazz,String sql,Object... params){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParams(statement,params);
            resultSet = statement.executeQuery();

            //获取表信息对象
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取字段个数
            int columnCount = metaData.getColumnCount();

            List<T> list = new ArrayList<>();

            while(resultSet.next()){

                //创建实体类对象
                T t = clazz.newInstance();

                for (int i = 1; i <= columnCount; i++) {
                    //获取字段名
                    String fieldName = metaData.getColumnName(i);
                    //获取字段名对应的数据
                    Object value = resultSet.getObject(fieldName);
                    //通过反射设置对象的数据
                    setField(t,fieldName,value);
                }

                list.add(t);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,resultSet);
        }
        return null;
    }

    /**
     * 设置数据参数
     * @param statement
     * @param params
     * @throws SQLException
     */
    private static void setParams(PreparedStatement statement,Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i+1,params[i]);
        }
    }

    /**
     * 获取本类及其父类的属性对象
     * @param clazz class对象
     * @param name 属性名
     * @return 属性对象
     */
    private static Field getField(Class<?> clazz, String name){

        for(Class<?> c = clazz;c!=null;c=c.getSuperclass()){
            try {
                Field field = c.getDeclaredField(name);
                return field;
            } catch (NoSuchFieldException e) {
            } catch (SecurityException e) {
            }
        }
        return null;
    }

    /**
     * 设置目标对象里的属性
     * @param obj 目标对象
     * @param name 属性名
     * @param val 属性值
     */
    private static void setField(Object obj,String name,Object val){

        Field field = getField(obj.getClass(), name);
        if(field != null){
            field.setAccessible(true);
            try {
                field.set(obj, val);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getAllCount(String tableName){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select count(username) from  " + tableName;
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                int allCount = resultSet.getInt(1);
                return allCount;
            }
        } catch (SQLException e) {
        }
        return -1;
    }
}
