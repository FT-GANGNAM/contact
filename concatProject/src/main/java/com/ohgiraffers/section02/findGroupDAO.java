package com.ohgiraffers.section02;

import com.ohgiraffers.section01.dto.GroupContactDTO;
import com.ohgiraffers.section01.dto.findGroupDTO;

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

public class findGroupDAO {

    private Properties prop = new Properties();

    public findGroupDAO(String url){

        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

//    public List<GroupContactDTO> findGroup(Connection con) {
//
//        PreparedStatement pstmt = null;
//        ResultSet rset = null;
//        List<GroupContactDTO> categoryList = null;
//
//
//
//        try {
//
//            String query = prop.getProperty("findGroup");
//
//            pstmt = con.prepareStatement(query);
//
//            rset = pstmt.executeQuery();
//
//            categoryList = new ArrayList<>();
//
//            while (rset.next()){
//
//                findGroupDTO groupDTO = new findGroupDTO(rset.getInt("Groupnumber"),rset.getString("Groupname"));
//                categoryList.add(groupDTO);
//                System.out.println();
//
//
//
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally{
//            close(rset);
//            close(pstmt);
//        }return categoryList;
//
//
//    }
}
