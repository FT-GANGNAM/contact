package com.ohgiraffers.section01.dao;

import com.ohgiraffers.section01.dto.ContactDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class SearchDAO
{
    private Properties prop = new Properties();

    public SearchDAO(String url)
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

    public List<ContactDTO> searchName(Connection con, String searchVal, int userCode)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ContactDTO> contacts = new ArrayList<>();
        String query = prop.getProperty("searchByName");

        try
        {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + searchVal + "%");
            ps.setInt(2, userCode);

            rs = ps.executeQuery();

            while(rs.next())
            {
                ContactDTO contact = new ContactDTO(rs.getInt("contact_code"), rs.getString("contact_name"), rs.getString("phonenumber"), rs.getString("email"), rs.getString("address"), rs.getString("birthday"), rs.getString("groupnumber"));
                contacts.add(contact);
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

        return contacts;
    }


    public void searchPhoneNumber(Connection con)
    {
        //SELECT * FROM tbl_contact WHERE phoneNumber LIKE '%?%'
        String query = prop.getProperty("searchByPhoneNumber");

    }


    public void searchEmail(Connection con)
    {
        //SELECT * FROM tbl_contact WHERE email LIKE '%?%'
        String query = prop.getProperty("searchByEmail");
    }

    public void searchAddress(Connection con)
    {
        String query = prop.getProperty("searchByAddress");
        //SELECT * FROM tbl_contact WHERE address LIKE '%?%'
    }

}
