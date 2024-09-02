package com.ohgiraffers.section01.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class UserPreferDAO
{
    private Properties prop = new Properties();

    public UserPreferDAO(String url)
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

    public void saveUserPrefer(Connection con, String preferVal, String preferSort)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = null;

        query = prop.getProperty("savePrefer" + preferVal + " " + preferSort);


    }
}
