package com.example.demo.dao;

import com.example.demo.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Override
    public String run(Integer id) {
        return "test";
    }

    @Override
    public List<User> selectAllUser() {
        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        User user;
        List<User> usersList = new ArrayList<User>();
        try {

            // 1、连接数据库
            conn = BaseDao.getConnection();
            //2、准备sql

            String sql = "SELECT `id`,`usercode`,`username`,`userpassword`,`birthday`,`phone`,`address` FROM `smbms_user`";
            pstm = conn.prepareStatement(sql);
            //3、执行SQL，获取结果集
            rs = pstm.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                String userCode = rs.getString(2);
                String userName = rs.getString(3);
                String userPassword = rs.getString(4);
                Date birthday = rs.getDate(5);
                long phone = rs.getLong(6);
                String address = rs.getString(7);
                user = new User(id,userCode,userName,userPassword,birthday,phone,address);
                usersList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{

            //4、关闭
            BaseDao.closeAll(conn, pstm, rs);

        }
        return usersList;

    }

}
