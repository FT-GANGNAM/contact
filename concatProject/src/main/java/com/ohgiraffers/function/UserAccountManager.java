package com.ohgiraffers.function;

import com.ohgiraffers.section01.dao.AccessUserDAO;
import com.ohgiraffers.section01.dto.UserDTO;

import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class UserAccountManager
{
    AccessUserDAO accessUserDAO = new AccessUserDAO("src/main/resources/mapper/contact-query.xml");
    Scanner sc = new Scanner(System.in);

    public int login()
    {
        //회원 정보 tbl_user에서 가져올 거임
        //입력한 키랑 tbl_user.id랑 tbl_user.password 비교
        //해당 멤버 맞으면 primary key로 설정된 값 가져오기

        System.out.println("아이디를 입력해주세요: ");
        String id = sc.nextLine();
        System.out.println("비밀번호를 입력해주세요: ");
        String pwd = sc.nextLine();

        UserDTO user = accessUserDAO.getUserInfo(getConnection(), id, pwd);
        
        if(user != null)
        {
            System.out.println("***** 로그인 성공! *****");
            System.out.println(user.getUserName() + " 님, 환영합니다 (*ˊᵕˋ*)ﾉ");
        }
        else
        {
            return -1;
        }

        return user.getUserCode();
    }

    public int signup()
    {
        //tbl_user 쭉 훑어서 이름이랑 아이디 일치 안 하는 거 확인
        //가입시켜
        System.out.println("이름을 입력해주세요: ");
        String name = sc.nextLine();
        System.out.println("아이디를 입력해주세요: ");
        String id = sc.nextLine();
        System.out.println("비밀번호를 입력해주세요: ");
        String pwd = sc.nextLine();

        accessUserDAO.signup(getConnection(), name, id, pwd);
        UserDTO user = accessUserDAO.getUserInfo(getConnection(), id, pwd);

        System.out.println("***** 회원 가입 성공! *****");
        System.out.println(user.getUserName() + " 님, 환영합니다 (*ˊᵕˋ*)ﾉ");
        return user.getUserCode();
    }
}
