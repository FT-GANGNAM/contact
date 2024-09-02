package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.ContactDAO_YSJ;
import com.ohgiraffers.section02.dto.ContactDTO_YSJ;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ohgiraffers.common.JDBCTemplate.*;



public class ContactController_YSJ {

    private ContactDAO_YSJ contactDAO = new ContactDAO_YSJ("src/main/resources/mapper/contact-query.xml");

public void insertcontact(){  //contact_name,phonenumber, email, address, birthday, groupnumber
    Scanner scr = new Scanner(System.in);
    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();


    System.out.println("연락처에 추가하고 싶은 이름을 입력 해주세요: ");
    contactDTO.contact_name(scr.nextLine());
    System.out.println("연락처에 추가할 전화번호를 입력 해주세요 ('-'포함) ex)010-1234-5678 : ");
    String phone = scr.nextLine();
    if (isValidPhoneNumber(phone)){
        contactDTO.phonenumber(phone);
    }else {
        System.out.println("입력하신 형식이 맞지 않습니다! ");
        return ;
    }
    System.out.println("연락처에 추가할 이메일을 입력 해주세요 ex)gangnam@gamil.com : ");
    String email = scr.nextLine();
    if (isValidEmail(email)){
        contactDTO.email(email);
    }else {
        System.out.println("입력하신 이메일 형식이 맞지 않습니다");
        return;
    }
    System.out.println("연락처에 추가할 주소를 입력 해주세요 : ");
    contactDTO.address(scr.nextLine());
    System.out.println("연락처에 추가할 생일을 입력 해주세요 ex)00월 00일, 01월 23일 : ");
    String birth = scr.nextLine();
    if(isValidBirthday(birth)){
        contactDTO.birthday(birth);
    }else {
        System.out.println("입력하신 형식이 맞지 않습니다!");
        return;
    }
    System.out.println("연락처에 추가할 그룹번호를 입력 해주세요 : ");
    contactDTO.groupnumber(scr.nextInt());
    System.out.println("연락처에 추가할 유저코드를 입력 해주세요 : ");
    contactDTO.user_code(scr.nextInt());

    int result = contactDAO.insertcontact(getConnection(), contactDTO);


}

public void updatecontact() {
    Scanner scr = new Scanner(System.in);    // 바꿀 연락처 (b) 변수만 쓰면된다. 나중에 여유되면 나머지 필요없는 변수 삭제

    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();


    System.out.println("수정하고 싶은 연락처의 번호를 입력해주세요 ex)010-1234-5678 : ");
    String a = scr.nextLine();

    System.out.println("연락처의 이름을 어떻게 바꾸시겠습니까? : ");
    contactDTO.contact_name(scr.nextLine());

    System.out.println("연락처의 번호를 어떻게 바꾸시겠습니까? ex)010-1234-5678 :  ");
        String phone = scr.nextLine();
    if (isValidPhoneNumber(phone)){
        contactDTO.phonenumber(phone);
    }else {
        System.out.println("입력하신 형식이 맞지 않습니다! ");
        return ;
    }

    System.out.println("연락처의 이메일을 어떻게 바꾸시겠습니까? ex)gangnam@gamil.com : ");
    String email = scr.nextLine();
    if (isValidEmail(email)){
        contactDTO.email(email);
    }else {
        System.out.println("입력하신 이메일 형식이 맞지 않습니다");
        return;
    }

    System.out.println("연락처의 주소를 어떻게 바꾸시겠습니까? ");
    contactDTO.address(scr.nextLine());

    System.out.println("연락처의 생일을 어떻게 바꾸시겠습니까? ex)00월 00일, 01월 23일 ");
    contactDTO.birthday(scr.nextLine());

    System.out.println("연락처의 그룹번호를 어떻게 바꾸시겠습니까? ");
    contactDTO.groupnumber(scr.nextInt());

    System.out.println("연락처의 유저코드를 어떻게 바꾸시겠습니까? ");
    contactDTO.user_code(scr.nextInt());

    scr.nextLine();

    int result = contactDAO.updatecontact(getConnection(), contactDTO, a);

    }

    public void deletecontact(){
    Scanner scr = new Scanner(System.in);
    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();

        System.out.println("제거할 연락처번호를 입력 해주세요 : ");
        contactDTO.phonenumber(scr.nextLine());

        int result = contactDAO.deletecontact(getConnection(), contactDTO);

    }

    public void insertGroup(){

        Scanner scr = new Scanner(System.in);
        ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();


        System.out.println("추가하실 그룹명을 입력해주세요 : ");
        contactDTO.groupname(scr.nextLine());

        int result = contactDAO.insertGroup(getConnection(), contactDTO);


    }



    public void deleteGroup(){

    Scanner scr = new Scanner(System.in);
    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();

        System.out.println("삭제할 그룹명을 입력해주세요");
        contactDTO.groupname(scr.nextLine());

        int result = contactDAO.deleteGroup(getConnection(), contactDTO);


    }






    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\d{3}-\\d{4}-\\d{4}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // 이메일 유효성 검사
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    // 생일 유효성 검사
    public static boolean isValidBirthday(String birthday) {
        String birthdayRegex = "^(0[1-9]|1[0-2])월 (0[1-9]|[12][0-9]|3[01])일$"; // ex) 08월 13일
        Pattern birthdayPattern = Pattern.compile(birthdayRegex);
        Matcher matcher = birthdayPattern.matcher(birthday);
        return matcher.matches();
    }





}
