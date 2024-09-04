package com.ohgiraffers.function;


import com.ohgiraffers.Prefer;
import com.ohgiraffers.section01.dao.UserPreferDAO;
import com.ohgiraffers.section01.dto.ContactDTO;
import com.ohgiraffers.section01.dto.UserDTO;
import com.ohgiraffers.section02.FindPhoneNumber;

import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class UserPrefer
{
    UserPreferDAO userPreferDAO = new UserPreferDAO("src/main/resources/mapper/contact-query.xml");
    Scanner sc = new Scanner(System.in);
    FindPhoneNumber findPhoneNumber = new FindPhoneNumber("src/main/resources/mapper/contact-query.xml");
    public void saveUserPrefer(UserDTO userDTO)
    {
        System.out.println("기준이 될 항목을 입력해주세요.");
        System.out.println("[ 이름 | 전화번호 | 이메일 | 주소 | 생일 ]");

        String preferValue = sc.nextLine();

        System.out.println("정렬 방법을 입력해주세요.");
        System.out.println("[ 오름차순 | 내림차순 ]");
        preferValue += " " + sc.nextLine();

        //tbl_user에서 prefer 가져와서 나눠
        

        // 이메일 오름차순으로 입력받으면 상수 email desc 반환하는이넘
        String change = Prefer.description(preferValue);

        //원하는 정렬방식으로 업데이트
        userPreferDAO.saveUserPrefer(getConnection(),change, userDTO.getUserCode());



        //해당 유저의 선호 정렬방식 가지고 오는 코드
       String prefer= userPreferDAO.saveUserPrefer1(getConnection(), userDTO.getUserCode());



        int userCode = userDTO.getUserCode();
        List<ContactDTO> test2 = findPhoneNumber.findsort(getConnection(), userCode, prefer);
        for (ContactDTO b : test2) {
            System.out.println(b);
        }



    }
}
