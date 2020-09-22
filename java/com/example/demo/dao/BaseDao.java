package com.example.demo.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDao {


    private static String driver;//驱动
    private static String url;//连接地址
    private static String user;//连接数据库用户名
    private static String password;//连接数据库密码


    static Connection conn;//连接对象
    PreparedStatement pstmt=null;


    static {
        //利用静态代码块，在类加载的时候执行
        init();
    }

    private static void init() {

        //创建Properties对象，加载database.properties配置文件信息
        Properties pro=new Properties();
        InputStream is= BaseDao.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            //加载database.properties配置文件
            pro.load(is);
//			driver=pro.getProperty("driver");
            driver=pro.getProperty("jdbc.driver");
            url=pro.getProperty("jdbc.url");
            user=pro.getProperty("jdbc.user");
            password=pro.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  连接数据库
     * @return 连接对象
     */
    public static Connection getConnection() {
        try {
            //加载驱动
            Class.forName(driver);
            conn=DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 关闭数据库
     * @param conn
     * @param pstmt
     * @param rs
     */
    public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs!=null) {
                rs.close();
            }
            if (pstmt!=null) {
                pstmt.close();
            }
            if (conn!=null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String sql,Object[] param) {
        int count=0;
        conn=this.getConnection();
        try {
            pstmt=conn.prepareStatement(sql);
            if (param!=null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject((i+1),param[i]);
                }
            }
            count=pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll(conn, pstmt, null);
        }
        return count;
    }

}

