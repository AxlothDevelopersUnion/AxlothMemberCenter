//工具类——数据库连接工具
package org.axloth.util;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil
{
    private static String driverName; //驱动类
    private static String username;
    private static String password;
    private static String url; //数据库地址
    private static Connection conn=null; //数据库连接
    private static final String CONFIG_FILE="src/org/axloth/config/jdbc.properties";

    static
    { //初始化
        Properties properties=new Properties(); //配置文件的文件对象
        try
        {
            File config=new File(CONFIG_FILE);
            if(!config.exists())
            {
                throw new FileNotFoundException("缺少文件："+config.getAbsolutePath()+"，请重新创建该文件并配置内容！");
            }
            properties.load(new FileInputStream(config)); //加载配置文件
            driverName=properties.getProperty("driver_name"); //获取指定字段值
            username=properties.getProperty("username","");
            password=properties.getProperty("password","");
            url=properties.getProperty("url");
            if(driverName==null || url==null)
            {
                throw new ConfigurationException(config.getAbsolutePath()+"文件配置信息有误，请重新配置！");
            }

        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }catch(ConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    { //根据初始化配置信息获取对应的Connection对象
        try
        {
            if(conn==null || conn.isClosed())
            {
                Class.forName(driverName); //加载驱动类
                conn=DriverManager.getConnection(url,username,password); //获取数据库连接
            }
        }catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet resultSet)
    { //关闭ResultSet
        try
        {
            if(resultSet!=null)
            {
                resultSet.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt)
    { //关闭Statement
        try
        {
            if(stmt!=null)
            {
                stmt.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement pStmt)
    { //关闭PreparedStatement
        try
        {
            if(pStmt!=null)
            {
                pStmt.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt,PreparedStatement pStmt,ResultSet resultSet)
    { //关闭SQL语句
        close(stmt);
        close(pStmt);
        close(resultSet);
    }

    public static void closeConnection()
    { //关闭连接
        if(conn!=null)
        {
            try
            {
                conn.close();
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
