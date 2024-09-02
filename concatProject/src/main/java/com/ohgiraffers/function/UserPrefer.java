package com.ohgiraffers.function;


import com.ohgiraffers.section01.dao.UserPreferDAO;

import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class UserPrefer
{
    UserPreferDAO userPreferDAO = new UserPreferDAO("src/main/resources/mapper/contact-query.xml");
    Scanner sc = new Scanner(System.in);

    public void saveUserPrefer(int userCode)
    {
        System.out.println("기준이 될 항목을 입력해주세요.");
        System.out.println("[ 이름 | 전화번호 | 이메일 | 주소 | 생일 ]");

        String preferValue = sc.nextLine();

        System.out.println("정렬 방법을 입력해주세요.");
        System.out.println("[ 오름차순 | 내림차순 ]");
        preferValue += " " + sc.nextLine();

        //tbl_user에서 prefer 가져와서 나눠

        userPreferDAO.saveUserPrefer(getConnection(), preferValue, userCode);

    }
}
