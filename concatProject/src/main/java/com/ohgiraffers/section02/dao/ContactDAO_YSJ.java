package com.ohgiraffers.section02.dao;

import com.ohgiraffers.section02.dto.ContactDTO_YSJ;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class ContactDAO_YSJ {

    private Properties prop = new Properties();

    public ContactDAO_YSJ(String url){
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertcontact(Connection con, ContactDTO_YSJ contactDTO){

        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertcontact");


        try {

            pstmt = con.prepareStatement(query); // contact_name,phonenumber, email, address, birthday, groupnumber
            pstmt.setString(1, contactDTO.getContactName());
            pstmt.setString(2, contactDTO.getPhonenumber());
            pstmt.setString(3, contactDTO.getEmail());
            pstmt.setString(4, contactDTO.getAddress());
            pstmt.setString(5, contactDTO.getBirthday());
            pstmt.setInt(6, contactDTO.getGroupnumber());
            pstmt.setInt(7, contactDTO.getUser_code());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("잘못된 값을 입력하셨습니다.");
        } finally {
            close(con);
            close(pstmt);
        }
        return result;
    }

    public int updatecontact(Connection con, ContactDTO_YSJ contactDTO, String a){

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/contact-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("updatecontact"));


            pstmt.setString(1,contactDTO.getContactName());
            pstmt.setString(8, a);
            pstmt.setString(2, contactDTO.getPhonenumber());
            pstmt.setString(3, contactDTO.getEmail());
            pstmt.setString(4, contactDTO.getAddress());
            pstmt.setString(5, contactDTO.getBirthday());
            pstmt.setInt(6, contactDTO.getGroupnumber());
            pstmt.setInt(7, contactDTO.getUser_code());

            result = pstmt.executeUpdate();

            if (result == 1){
                System.out.println("연락처 변경 성공");
            } else {
                System.out.println("연락처 변경 실패");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            close(con);
            close(pstmt);

        }
        return result;
    }

    public int deletecontact(Connection con, ContactDTO_YSJ contactDTO){

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/contact-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("deletecontact"));
            pstmt.setString(1, contactDTO.getPhonenumber());

            result = pstmt.executeUpdate();

            if (result == 1){
                System.out.println("연락처 제거에 성공하셨습니다.");
            }else {
                System.out.println("연락처 제거에 실패하셨습니다.");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }
    return result;
    }

    public int insertGroup(Connection con, ContactDTO_YSJ contactDTO) {

        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertGroup");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, contactDTO.getGroupname());

            result = pstmt.executeUpdate();

            if(result == 1){
                System.out.println("그룹 추가가 완료되었습니다.");

            }else{

                System.out.println("그룹 추가를 실패하였습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;

    }
    public int deleteGroup(Connection con, ContactDTO_YSJ contactDTO){
        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("deleteGroup");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, contactDTO.getGroupname());

            result = pstmt.executeUpdate();

            if(result == 1){

                System.out.println("그룹이 제거되었습니다.");
            }else{
                System.out.println("그룹 제거에 실패하였습니다.");
            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);

        }return result;

    }

}
