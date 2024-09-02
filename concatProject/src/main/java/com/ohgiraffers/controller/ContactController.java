package com.ohgiraffers.controller;

import com.ohgiraffers.function.Searcher;
import com.ohgiraffers.function.UserAccountManager;
import com.ohgiraffers.function.UserPrefer;

public class ContactController
{
    UserAccountManager userAccountManager = new UserAccountManager();
    Searcher searcher = new Searcher();
    UserPrefer userPrefer = new UserPrefer();

    public int login()
    {
        return userAccountManager.login();
    }

    public int signup()
    {
        return userAccountManager.signup();
    }

    public void searchContact(int userCode)
    {
        searcher.search(userCode);
    }

    public void saveUserPrefer(int userCode) {userPrefer.saveUserPrefer(userCode);}
}
