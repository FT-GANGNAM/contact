package com.ohgiraffers.function;


import com.ohgiraffers.Prefer;
import com.ohgiraffers.dao.UserPreferDAO;
import com.ohgiraffers.dto.ContactDTO;
import com.ohgiraffers.dto.UserDTO;

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
        // 현재 유저가 사용하고 있는 정렬 방식 먼저 보여줘도 좋을 거 같음
        // 보여준 뒤에 변경 할 건지 안 할 건지 물어
        // 변경 안 할 거면 그냥 끝내

        System.out.println("기준이 될 항목을 입력해주세요.");
        System.out.println("[ 이름 | 전화번호 | 이메일 | 주소 | 생일 ]");

        String preferValue = sc.nextLine();

        System.out.println("정렬 방법을 입력해주세요.");
        System.out.println("[ 오름차순 | 내림차순 ]");
        preferValue += " " + sc.nextLine();

        //tbl_user에서 prefer 가져와서 나눠

        //원하는 정렬방식으로 업데이트
        userPreferDAO.saveUserPrefer(getConnection(),preferValue, userDTO.getUserCode());

       String prefer= userPreferDAO.saveUserPrefer1(getConnection(), userDTO.getUserCode());

        String sort = Prefer.description(prefer);

        int userCode = userDTO.getUserCode();


        List<ContactDTO> test2 = findPhoneNumber.findsort(getConnection(), userCode, sort);
        for (ContactDTO b : test2) {
            System.out.println(b);
        }



    }
}
