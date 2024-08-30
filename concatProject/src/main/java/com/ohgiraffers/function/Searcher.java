package com.ohgiraffers.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Searcher
{
    //이름, 전화번호, 이메일, 주소 등을 기준으로 연락처를 검색할 수 있어야 한다.
    //검색 결과는 리스트 형태로 표시되며, 검색 조건에 따라 필터링할 수 있다.

    private Properties prop = new Properties();

    private List<Map<Integer,String>> searchedContact = new ArrayList<>(); // => 이게ㅐ 필요함? 리스트 형태로 표시할 이유가 없는데 지금
    //DTO 필요하지 않나 하고 toString 해서 표시 하던가 해야 될 거 같음

    public void searchName(Connection con)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = prop.getProperty("searchByName");

        try
        {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        //SELECT * FROM tbl_contact WHERE name LIKE '%?%'
        //SELECT * FROM tbl_contact WHERE phoneNumber LIKE '%?%' OR 주소, 이메일
        //처음에 뭘로 검색할건지 정해줘야 하나? 이름에서 검색하면 이거 번호로 검색하면 이거 ...

    }

    public void searchPhoneNumber(Connection con)
    {
        //SELECT * FROM tbl_contact WHERE phoneNumber LIKE '%?%'
        String query = prop.getProperty("searchByPhoneNumber");

    }


    public void searchEmail(Connection con)
    {
        //SELECT * FROM tbl_contact WHERE email LIKE '%?%'
    }

    public void searchAddress(Connection con)
    {
        String query = prop.getProperty("searchByAddress");
        //SELECT * FROM tbl_contact WHERE address LIKE '%?%' 이따 오탈자 수정
    }
}
