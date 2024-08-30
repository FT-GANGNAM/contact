package com.ohgiraffers.section02;

import com.ohgiraffers.section02.controller.ContactController_YSJ;

import java.util.Scanner;

public class Application_YSJ {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        ContactController_YSJ contactController = new ContactController_YSJ();

        while(true){
            System.out.println("사용할 기능을 선택해주세요!");
            System.out.println("1. 연락처 추가");
            System.out.println("9. 프로그램 종료");
            int choice = scr.nextInt();

            switch(choice){

                case 1 : contactController.insertcontact(); break;
                case 9 : return;
                default:
                    System.out.println("입력을 잘못하셨습니다. 다시 실행해주세요");
                    break;
            }

        }


    }


}
