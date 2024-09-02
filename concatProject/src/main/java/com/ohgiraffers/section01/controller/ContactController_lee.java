package com.ohgiraffers.section01.controller;

import com.ohgiraffers.section01.dao.ContactDAO_lee;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class ContactController_lee {

    private ContactDAO_lee contactDAO = new ContactDAO_lee("src/main/resources/mapper/contact-query.xml");


    public void groupChoose(int userCode) {
        Scanner sc = new Scanner(System.in);
        int a = userCode;

        while(true) {
            System.out.println("1.연락처의 총 개수, 2.그룹별 연락처 개수, 3.최근 추가된 연락처, 0.초기 화면으로 돌아가기");
            int choice = sc.nextInt();

            switch(choice) {
                case 1: totalCount(a); break;
                case 2: groupByPhoneNumber(a); break;
                case 3: selectLastContact(a); break;
                case 0:
                    System.out.println("초기화면으로 돌아갑니다.");
                    return; // 메서드를 종료하며 루프를 빠져나감
                default:
                    System.out.println("유효하지 않은 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    public void totalCount(int a) {
        int result = contactDAO.totalCount(getConnection(), a);
        System.out.println("연락처의 총 개수는 " + result + "개 입니다 !");
    }

    public void groupByPhoneNumber(int a) {
        List<Map<String, Integer>> result = contactDAO.groupByPhoneNumber(getConnection(),a);
        for (Map<String, Integer> map : result) {
            System.out.println(map);
        }

    }

    public void selectLastContact(int a) {
        List result = contactDAO.selectLastContact(getConnection(),a);

        for (Object list : result) {
            System.out.println(list);
        }
    }

}
