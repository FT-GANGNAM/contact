package com.ohgiraffers.section02;


import com.ohgiraffers.section01.controller.ContactController;

public class Application {
    public static void main (String[] args){
        Controller controller = new Controller();
        controller.findNumber();
        ContactController contactController = new ContactController();
              //  c.totalCount();
        contactController.groupByPhoneNumber();

    //    c.groupByPhoneNumber();
        contactController.selectLastContact();

      //  c.selectLastContact();

    }//main
}//class



