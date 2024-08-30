package com.ohgiraffers.section01.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class ContactDAO {

    private Properties prop = new Properties();

    public ContactDAO(String url) {
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int totalCount(Connection con){
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int result = 0;
        String query = prop.getProperty("countAllContact");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            if(rset.next()){
                result = rset.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return result;
    }

    public List<Map<String, Integer>> groupByPhoneNumber(Connection con){
            PreparedStatement pstmt = null;
            ResultSet rset = null;
            List<Map<String, Integer>> result = new ArrayList<>();
            String query = prop.getProperty("groupCount");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while(rset.next()){
                Map<String, Integer> map = new HashMap<>();
                map.put(rset.getString("groupname"), rset.getInt("count(*)"));
                result.add(map);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return result;

    }

    public List selectLastContact(Connection con){
        List result = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectLastContact");
                con.prepareStatement();
    }
}



