package com.ohgiraffers.controller;

import com.ohgiraffers.function.UserAccountManager;

public class ContactController
{
    UserAccountManager userAccountManager = new UserAccountManager();

    public int login()
    {
        return userAccountManager.login();
    }

    public int signup()
    {
        return userAccountManager.signup();
    }
    public void searchContact()
    {
        System.out.println("연락처 검색!!!!");

    }
}
