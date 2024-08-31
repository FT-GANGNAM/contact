package com.ohgiraffers.section01.dao;

import com.ohgiraffers.function.Login;
import com.ohgiraffers.function.Signup;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class AccessUserDAO
{
    private Properties prop = new Properties();

    Login login = new Login();
    Signup signup = new Signup();

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

    public int login(Connection con, String userId, String password)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = prop.getProperty("getAllUserInfo");

        int primaryKey = 0;
        //회원 정보 tbl_user에서 가져올 거임
        //입력한 키랑 tbl_user.id랑 tbl_user.password 비교
        //해당 멤버 맞으면 primary key로 설정된 값 가져오기

        try
        {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(rs.getString(1) + " 님 환영합니다 ദ്ദി₍ᵔ- ̫-ᵔ₎"); //유저 이름 불러와서 환영합니다 해놔
                //primary key 설정
            }
        }
        catch (SQLException e)
        {
            System.out.println("해당 회원 정보를 찾을 수 없습니다. ");
            throw new RuntimeException(e);
        }

        return primaryKey;
    }

    public void signup()
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = prop.getProperty("setNewUser");

        int primaryKey = 0;

        //얘는 쿼리에 INSERT INTO 해서 고객 정보값 넣어줘야 함
        //UserDTO 필요
    }
}
