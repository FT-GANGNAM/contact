package com.ohgiraffers.section02.dao;

import com.ohgiraffers.section01.dto.GroupDTO;
import com.ohgiraffers.section02.dto.ContactDTO_YSJ;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
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
            pstmt.setInt(6, contactDTO.getUser_code());

            result = pstmt.executeUpdate();

            System.out.println();
            System.out.println("* ੈ✩‧₊ 연락처가 추가되었습니다. * ੈ✩‧₊");

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
            pstmt.setString(7, a);
            pstmt.setString(2, contactDTO.getPhonenumber());
            pstmt.setString(3, contactDTO.getEmail());
            pstmt.setString(4, contactDTO.getAddress());
            pstmt.setString(5, contactDTO.getBirthday());
            pstmt.setInt(6, contactDTO.getUser_code());

            result = pstmt.executeUpdate();

            if (result == 1){
                System.out.println("* ੈ✩‧₊ 연락처 변경 성공 * ੈ✩‧₊");
            } else {
                System.out.println("* ੈ✩‧₊ 연락처 변경 실패 * ੈ✩‧₊");
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
                System.out.println("* ੈ✩‧₊ 연락처 제거에 성공하셨습니다. * ੈ✩‧₊");
            }else {
                System.out.println("* ੈ✩‧₊ 연락처 제거에 실패하셨습니다. * ੈ✩‧₊");
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

    public int insertGroup(Connection con, String group, int userCode) {

        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertGroup");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, group);
            pstmt.setInt(2, userCode);

            result = pstmt.executeUpdate();

            if(result == 1){
                System.out.println("* ੈ✩‧₊ 그룹 추가가 완료되었습니다.* ੈ✩‧₊");

            }else{

                System.out.println("* ੈ✩‧₊ 그룹 추가를 실패하였습니다. * ੈ✩‧₊");
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

                System.out.println("* ੈ✩‧₊ 그룹이 제거되었습니다. * ੈ✩‧₊");
            }else{
                System.out.println("* ੈ✩‧₊ 그룹 제거에 실패하였습니다. * ੈ✩‧₊");
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);

        }return result;

    }

    public List<ContactDTO_YSJ> getAllContacts(Connection con, int userCode)
    {
        List<ContactDTO_YSJ> userContacts = new ArrayList<ContactDTO_YSJ>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = prop.getProperty("printContactList");

        try
        {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userCode);

            rs = pstmt.executeQuery();
            while(rs.next())
            {
                ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();
                contactDTO.contact_name(rs.getString("contact_name"));
                contactDTO.phonenumber(rs.getString("phonenumber"));
                contactDTO.email(rs.getString("email"));
                contactDTO.address(rs.getString("address"));
                contactDTO.birthday(rs.getString("birthday"));
                contactDTO.userCode(userCode);

                userContacts.add(contactDTO);
            }
        }
        catch (SQLException e)
        {
            System.out.println("* ੈ✩‧₊ 연락처를 찾을 수 없습니다. * ੈ✩‧₊");
        }


        return userContacts;
    }

    public List<GroupDTO> getAllGroups(Connection con, int userCode)
    {
        List<GroupDTO> groups = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = prop.getProperty("printGroupList");

        try
        {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userCode);

            rs = pstmt.executeQuery();
            while(rs.next())
            {
                groups.add(new GroupDTO(rs.getInt("groupnumber"), rs.getString("groupname")));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return groups;
    }

    public void changeGroupNumberOfContact(Connection con, int groupNum, int userCode, String phoneNum)
    {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("changeGroupNumber");

        try
        {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, groupNum);
            pstmt.setInt(2, userCode);
            pstmt.setString(3, phoneNum);
            result = pstmt.executeUpdate();

            System.out.println("* ੈ✩‧₊ 그룹 설정에 성공했습니다. * ੈ✩‧₊");
        }
        catch (SQLException e)
        {
            System.out.println("* ੈ✩‧₊" + phoneNum + " 의 그룹 설정에 실패했습니다. * ੈ✩‧₊");
        }finally{
            close(con);
            close(pstmt);
        }
    }


    public int updatefordeletegroup(Connection con, ContactDTO_YSJ contactDTO){

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();


        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/contact-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("updatefordeletegroup"));

            pstmt.setString(1, contactDTO.getGroupname());

            result = pstmt.executeUpdate();

            if (result == 1){
                System.out.println("* ੈ✩‧₊ 그룹 삭제를 위한 초기화 성공 * ੈ✩‧₊");
            }else {
                System.out.println("* ੈ✩‧₊ 초기화 실패하셨습니다. 다시 시도해주세요. * ੈ✩‧₊");
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

}
