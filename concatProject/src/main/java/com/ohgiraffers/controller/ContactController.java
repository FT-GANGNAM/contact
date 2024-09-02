package com.ohgiraffers.controller;

import com.ohgiraffers.function.Searcher;
import com.ohgiraffers.function.UserAccountManager;

public class ContactController
{
    UserAccountManager userAccountManager = new UserAccountManager();
    Searcher searcher = new Searcher();

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
}
