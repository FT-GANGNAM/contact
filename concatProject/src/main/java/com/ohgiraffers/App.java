package com.ohgiraffers;

import com.ohgiraffers.controller.ContactController;

import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ContactController controller = new ContactController();
        int userCode = 0; // 이걸로 로그인 후에 setUserCode 해서 넘겨주기

        while(true)
        {
            System.out.println();
            System.out.println("¯\\_( ͡° ͜ʖ ͡°)_/¯ 연락처 관리 프로그램 ¯\\_( ͡° ͜ʖ ͡°)_/ \n¯");
            System.out.println("1. 기존 아이디로 로그인");
            System.out.println("2. 신규 회원 가입");

            int choice = sc.nextInt();
            if(choice == 1)
            {
                System.out.println("로그인 하께여");
                //로그인하고 넘어가
            }
            else if(choice == 2)
            {
                System.out.println("회원가입 하께여");
                //회원가입 완료하고 추가해
            }
            else
            {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                continue;
            }

            System.out.println();
            System.out.println("* ੈ✩‧₊ 사용하실 서비스 번호를 입력해주세요 * ੈ✩‧₊");
            System.out.println("1. 연락처 관리");
            System.out.println("2. 그룹 관리");
            System.out.println("3. 연락처 검색");
            System.out.println("4. 목록 조회");
            System.out.println("5. 통계");
            System.out.println("6. 사용자 설정");
            System.out.println("9. 프로그램 종료");
            System.out.println("｡.ﾟ+:✿｡.ﾟ+:✿｡.ﾟ+:✿｡.ﾟ+:✿｡.ﾟ+:✿｡.ﾟ");
            choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                    System.out.println("할 거 고르셈");
                    System.out.println("추가");
                    System.out.println("수정");
                    System.out.println("삭제");
                    break;
                case 2:
                    System.out.println("할 거 고르셈");
                    System.out.println("추가");
                    System.out.println("수정");
                    System.out.println("그룹 내의 연락처 관리");
                    break;
                case 3:
                    System.out.println("연락처 검색하께요");
                    break;
                case 4:
                    System.out.println("모든 연락처 목록 조회하께여");
                    break;
                case 5:
                    System.out.println("사용자님의 연락처 통계입니다.");
                    break;
                case 6:
                    System.out.println("선호하는 정렬 순서를 설정해보세요");
                    break;
                case 9:
                    System.out.println("프로그램을 종료하겠긔");
                    return;
                default:
                    System.out.println("잘못된 값을 입력하셨습니다. 메인 화면으로 돌아갑니다.");
                    continue;
            }
        }

    }
}
