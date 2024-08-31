package com.ohgiraffers.controller;

import com.ohgiraffers.section01.dao.AccessUserDAO;

public class ContactController
{
    AccessUserDAO accessUserDAO = new AccessUserDAO("src/main/resources/mapper/contact-query.xml");

    public void login()
    {

    }

    public void searchContact()
    {
        System.out.println("연락처 검색!!!!");

    }
}
