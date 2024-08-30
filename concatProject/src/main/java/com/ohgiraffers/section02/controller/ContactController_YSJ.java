package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.ContactDAO_YSJ;
import com.ohgiraffers.section02.dto.ContactDTO_YSJ;

import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;



public class ContactController_YSJ {

    private ContactDAO_YSJ contactDAO = new ContactDAO_YSJ("src/main/resources/mapper/contact-query.xml");

public void insertcontact(){  //contact_name,phonenumber, email, address, birthday, groupnumber
    Scanner scr = new Scanner(System.in);
    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();


    System.out.println("연락처에 추가하고 싶은 이름을 입력 해주세요: ");
    contactDTO.contact_name(scr.nextLine());
    System.out.println("연락처에 추가할 전화번호를 입력 해주세요 ('-'포함) : ");
    contactDTO.phonenumber(scr.nextLine());
    System.out.println("연락처에 추가할 이메일을 입력 해주세요 : ");
    contactDTO.email(scr.nextLine());
    System.out.println("연락처에 추가할 주소를 입력 해주세요 : ");
    contactDTO.address(scr.nextLine());
    System.out.println("연락처에 추가할 생일을 입력 해주세요 : ");
    contactDTO.birthday(scr.nextLine());
    System.out.println("연락처에 추가할 그룹번호를 입력 해주세요 : ");
    contactDTO.groupnumber(scr.nextInt());

    int result = contactDAO.insertcontact(getConnection(), contactDTO);

    if(result > 0){
        System.out.println("연락처 등록 완료");
    }else{
        System.out.println("연락처 등록 실패");
    }


}

public void updatecontact() {
    Scanner scr = new Scanner(System.in);    // 바꿀 연락처 (b) 변수만 쓰면된다. 나중에 여유되면 나머지 필요없는 변수 삭제

    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();


    System.out.println("수정하고 싶은 연락처의 번호를 입력해주세요 : ");
    String a = scr.nextLine();

    System.out.println("연락처의 이름을 어떻게 바꾸시겠습니까? : ");
    contactDTO.contact_name(scr.nextLine());

    System.out.println("연락처의 번호를 어떻게 바꾸시겠습니까? :  ");
    contactDTO.phonenumber(scr.nextLine());

    System.out.println("연락처의 이메일을 어떻게 바꾸시겠습니까? : ");
    contactDTO.email(scr.nextLine());

    System.out.println("연락처의 주소를 어떻게 바꾸시겠습니까? ");
    contactDTO.address(scr.nextLine());

    System.out.println("연락처의 생일을 어떻게 바꾸시겠습니까? ");
    contactDTO.birthday(scr.nextLine());

    System.out.println("연락처의 그룹번호를 어떻게 바꾸시겠습니까? ");
    contactDTO.groupnumber(scr.nextInt());

    scr.nextLine();

    int result = contactDAO.updatecontact(getConnection(), contactDTO, a);

    }

}
