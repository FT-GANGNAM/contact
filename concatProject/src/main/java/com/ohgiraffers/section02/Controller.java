package com.ohgiraffers.section02;

import com.ohgiraffers.Prefer;
import com.ohgiraffers.section01.dto.ContactDTO;
import com.ohgiraffers.section01.dto.GroupContactDTO;

import java.sql.Connection;
import java.util.*;
import java.util.prefs.Preferences;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Controller {

    private findPhoneNumber findphoneNumber = new findPhoneNumber("src/main/resources/mapper/contact-query.xml");

    public void findNumber(int userCode){
        Controller con = new Controller();


        Scanner scr = new Scanner(System.in);

        while(true) {

            System.out.println("1. 전체 목록 조회");
            System.out.println("2. 그룹으로 조회");
            System.out.println("0. 초기화면으로 돌아가기");
            System.out.println("목록을 선택해주세요 : ");
            int find = scr.nextInt();


            switch (find) {

                case 1 : con.test2(userCode); break;
                case 2 : con.test();
                    break;
                    case 0 : return;

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
        List<ContactDTO> test1 = findphoneNumber.findPhoneNumbers1(getConnection(), userCode);
        Scanner scr = new Scanner(System.in);

        // 첫 번째 리스트 출력
        for (ContactDTO contactDTO : test1) {
            System.out.println(contactDTO);
        }
            while (true) {
                System.out.println("어느 기준으로 정렬 하시겠습니까?");
                System.out.println("1.이메일 내림차순 2. 이메일 오름차순 3. 이름 내림차순 4.오름차순 5. 생일별 내림차순 6. 생일별 오름차순 0.뒤로가기");
                int count = scr.nextInt();
                if (count == 0) {
                    break;
                }
               String a= Prefer.description(count);
              List<ContactDTO> test2 = findphoneNumber.findsort(getConnection(),userCode,a);

                for (ContactDTO b : test2) {
                    System.out.println(b);


                }
                
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
