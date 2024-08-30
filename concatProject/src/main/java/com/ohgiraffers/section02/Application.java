package com.ohgiraffers.section02;

import com.ohgiraffers.section01.controller.ContactController;

public class Application {
    public static void main(String[] args) {

        ContactController c = new ContactController();
        c.totalCount();


        c.groupByPhoneNumber();

    }//main
}//class
