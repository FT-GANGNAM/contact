package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.ContactDAO_YSJ;
import com.ohgiraffers.section02.dto.ContactDTO_YSJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ohgiraffers.common.JDBCTemplate.*;



public class ContactController_YSJ {

    private ContactDAO_YSJ contactDAO = new ContactDAO_YSJ("src/main/resources/mapper/contact-query.xml");

public void insertcontact(int userCode){  //contact_name,phonenumber, email, address, birthday, groupnumber
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

    contactDTO.userCode(userCode);


    int result = contactDAO.insertcontact(getConnection(), contactDTO);


}

public void updatecontact(int userCode) {
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

    contactDTO.userCode(userCode);

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

    public void insertGroup(int userCode){

        Scanner scr = new Scanner(System.in);
        ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();


        System.out.println("추가하실 그룹명을 입력해주세요 : ");
        contactDTO.groupname(scr.nextLine());

        int result = contactDAO.insertGroup(getConnection(), contactDTO);


    }



    public void deleteGroup(int userCode){

    Scanner scr = new Scanner(System.in);
    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();

        System.out.println("삭제할 그룹명을 입력해주세요");
        contactDTO.groupname(scr.nextLine());

        int result = contactDAO.deleteGroup(getConnection(), contactDTO);


    }

    public void updateGroup(int userCode)
    {
        Scanner scr = new Scanner(System.in);

        // 해당 유저코드가 가지고 있는 그룹명 출력
        System.out.println(contactDAO.getAllGroups(getConnection(), userCode));

        // 연락처 출력할 때
        System.out.println("연락처를 추가하고 싶은 그룹을 입력해주세요: ");
        String group = scr.nextLine();

        List<ContactDTO_YSJ> contacts = contactDAO.getAllContacts(getConnection(), userCode);

        List<String> phoneNumList = new ArrayList<>(); // 내가 입력 받을 휴대폰 번호

        while(true)
        {
            System.out.println("그룹에 담을 휴대폰 번호를 입력해주세요: ");
            String phoneNum = scr.nextLine();
            phoneNumList.add(phoneNum);

            System.out.println("더 추가하시겠습니까?");
            String answer = scr.nextLine();

            if(answer.equals("yes") || answer.equals("네") || answer.equals("예"))
                continue;
            else if(answer.equals("no") || answer.equals("아니요"))
                break;
            else
            {
                // 추후 디테일 수정
                System.out.println("잘못 입력했긔 니가 입력 잘못해서 끝낼 거임 - ContactController_YSJ.updateGroup");
                break;
            }
        }

        for(int i = 0 ; i < contacts.size() ; i++)
        {
            for(int j = 0 ; j < phoneNumList.size() ; j++)
            {
                if(contacts.get(i).getPhonenumber().equals(phoneNumList.get(j)))
                {
                    // contacts[i]의 groupnumber를 내가 선택한 그룹 이름의 넘버로 바꿔줄거예요
                }
            }
        }
        
        //끝

    }


    public void manageContact(int userCode)
    {
        System.out.println("1. 추가 2. 수정 3. 삭제");
        Scanner scr = new Scanner(System.in);
        String choice = scr.nextLine();
        switch (choice)
        {
            case "1":
            case "추가":
                insertcontact(userCode);
                break;
            case "2":
            case "수정":
                updatecontact(userCode);
                break;
            case "3":
            case "삭제":
                deletecontact();
                break;

            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    public void manageGroup(int userCode)
    {
        System.out.println("1. 추가 2. 삭제 3. 그룹 내에서 연락처 추가, 삭제");
        Scanner scr = new Scanner(System.in);
        String choice = scr.nextLine();

        switch (choice)
        {
            case "1":
            case "추가":
                insertGroup(userCode);
                break;
            case "2":
            case "삭제":
                deleteGroup(userCode);
                break;

            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }

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
