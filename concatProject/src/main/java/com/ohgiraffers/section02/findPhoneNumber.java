package com.ohgiraffers.section02;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class findPhoneNumber {

    /*      - **연락처 목록 조회**
            - 저장된 모든 연락처를 목록 형태로 조회할 수 있어야 한다. //리스트
            !-- 목록은 이름, 전화번호, 생일, 그룹을 기준으로 정렬할 수 있다.
            - 연락처는 그룹별로 조회할 수 있는 기능을 제공한다.
     */

    private Properties prop = new Properties();

    public findPhoneNumber(String url) {

        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public List<String> findPhoneNumbers1(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List categoryList = null;


        try {
            String query = prop.getProperty("findPhoneNumber");

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            categoryList = new ArrayList<>();


            while (rset.next()) {

                categoryList.add(rset.getString("contact_name"));
                categoryList.add(rset.getString("phonenumber"));
                categoryList.add(rset.getString("birthday"));
                categoryList.add(rset.getString("groupname"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }


        return categoryList;

    }

    public List<String> groupFindPhoneNumber1(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List groupName = null;
        Scanner scr = new Scanner(System.in);


        System.out.println("그룹명을 입력해주세요");
        String groupName1 = scr.nextLine();


        try {
            String query = prop.getProperty("findGroup");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, "groupName1");
            rset = pstmt.executeQuery();
            groupName = new ArrayList<>();

            while (rset.next()) {



            System.out.println(groupName);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        return groupName;
    }

}












