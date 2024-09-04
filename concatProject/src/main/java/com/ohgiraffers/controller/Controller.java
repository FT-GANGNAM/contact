package com.ohgiraffers.controller;

import com.ohgiraffers.Prefer;
import com.ohgiraffers.dao.UserPreferDAO;
import com.ohgiraffers.dto.ContactDTO;
import com.ohgiraffers.dto.GroupContactDTO;
import com.ohgiraffers.dto.ContactDTO;
import com.ohgiraffers.dto.GroupContactDTO;
import com.ohgiraffers.function.FindPhoneNumber;

import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Controller {

    private FindPhoneNumber findphoneNumber = new FindPhoneNumber("src/main/resources/mapper/contact-query.xml");
    UserPreferDAO userPreferDAO = new UserPreferDAO("src/main/resources/mapper/contact-query.xml");

    public void findNumber(int userCode){
        Controller con = new Controller();


        Scanner scr = new Scanner(System.in);

        while(true) {

            System.out.println("1. 전체 목록 조회");
            System.out.println("2. 그룹으로 조회");
            System.out.println("0. 초기화면으로 돌아가기");
            System.out.println("목록을 선택해주세요 : ");
            try {
                int find = Integer.parseInt(scr.nextLine());


                switch (find) {

                    case 1:
                        con.test2(userCode);
                        break;
                    case 2:
                        con.test();
                        break;
                    case 0:
                        return;
                        default :
                        System.out.println("잘못된 숫자를 입력하셨습니다."); break;
                }
            } catch(NumberFormatException f){
                System.out.println();
                System.out.println("문자나 특수기호 말고 숫자로 입력하시오");
                System.out.println();
            }

        }





    }

    public void test(){
        List<GroupContactDTO> test = findphoneNumber.groupFindPhoneNumber1(getConnection());
        for (GroupContactDTO groupContactDTO : test) {
            System.out.println(groupContactDTO);

        }
    }

    public void test2(int userCode) {
        String prefer= userPreferDAO.saveUserPrefer1(getConnection(), userCode);

       List<ContactDTO> test1 = findphoneNumber.findPhoneNumbers1(getConnection(), userCode,prefer);
        Scanner scr = new Scanner(System.in);

        // 첫 번째 리스트 출력
        for (ContactDTO contactDTO : test1) {
            System.out.println(contactDTO);
        }


    }
    public void findGroup(){

        Scanner scr = new Scanner(System.in);
        Controller con = new Controller();

        while(true){
            System.out.println("1. 나의 그룹 조회");
            System.out.println("2. 그룹 추가");
            System.out.println("3. 그룹 제거");
            int choice = scr.nextInt();

            switch (choice){

                case 1 : break;
                case 2 : break;
                case 3 : break;

            }


        }

    }
}


//
//- **연락처 그룹화**
//        - 연락처를 그룹으로 분류할 수 있어야 한다.
//        - 사용자는 그룹을 추가하고, 삭제하고, 그룹에 연락처를 추가하거나 삭제할 수 있어야 한다.
