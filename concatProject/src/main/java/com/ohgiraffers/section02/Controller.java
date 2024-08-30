package com.ohgiraffers.section02;

import com.ohgiraffers.section01.dto.GroupContactDTO;
import com.ohgiraffers.section02.findPhoneNumber;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Controller {

    private findPhoneNumber findphoneNumber = new findPhoneNumber("src/main/resources/mapper/contact-query.xml");

    public void findNumber(){
        Controller con = new Controller();


        Scanner scr = new Scanner(System.in);

        while(true) {

            System.out.println("1. 전체 목록 조회");
            System.out.println("2. 그룹으로 조회");
            System.out.println("목록을 선택해주세요 : ");
            int find = scr.nextInt();


            switch (find) {

                case 1 : con.test2(); break;
                case 2 : con.test();
                    break;
            }

        }





    }

    public void test(){
        List<GroupContactDTO> test = findphoneNumber.groupFindPhoneNumber1(getConnection());
        for (GroupContactDTO groupContactDTO : test) {
            System.out.println(groupContactDTO);
        }
    }

    public void test2(){
        List<GroupContactDTO> test2 = findphoneNumber.findPhoneNumbers1(getConnection());
        for (GroupContactDTO groupContactDTO : test2) {
            System.out.println(groupContactDTO);
        }
    }
}
