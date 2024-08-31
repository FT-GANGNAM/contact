package com.ohgiraffers.section01.dao;

import com.ohgiraffers.section01.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class AccessUserDAO
{
    //DB랑 연결해주는 역할만
    private Properties prop = new Properties();

    public AccessUserDAO(String url)
    {
        try
        {
            prop.loadFromXML(new FileInputStream(url));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public UserDTO getUserInfo(Connection con, String id, String pwd)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = prop.getProperty("getUserInfo");

        UserDTO user = null;

        try
        {
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, pwd);
            rs = ps.executeQuery();

            while(rs.next())
            {
                user = new UserDTO(rs.getInt("user_code"), rs.getString("user_name"), rs.getString("id"), rs.getString("pwd"), rs.getString("prefer"));
                System.out.println(user + " - AccessUserDAO.getUserInfo");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            close(con);
            close(ps);
            close(rs);
        }

        return user;
    }

    public void signup(Connection con, String name, String id, String pwd)
    {
        PreparedStatement ps = null;
        int result = 0;
        String query = prop.getProperty("setNewUser");

        //얘는 쿼리에 INSERT INTO 해서 고객 정보값 넣어줘야 함
        //UserDTO 필요
        try
        {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, id);
            ps.setString(3, pwd);
            result = ps.executeUpdate();

            System.out.println(result == 1 ? "가입 완료" : "가입 실패");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
}
