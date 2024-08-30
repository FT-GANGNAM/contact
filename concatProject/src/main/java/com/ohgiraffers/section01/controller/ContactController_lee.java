package com.ohgiraffers.section01.controller;

import com.ohgiraffers.section01.dao.ContactDAO_lee;

import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class ContactController_lee {

    private ContactDAO_lee contactDAO = new ContactDAO_lee("src/main/resources/mapper/contact-query.xml");

    public void totalCount(){
        int result = contactDAO.totalCount(getConnection());
        System.out.println("연락처의 총 개수는 "+result+"개 입니다 !");
    }

    public void groupByPhoneNumber(){
        List<Map<String, Integer>> result = contactDAO.groupByPhoneNumber(getConnection());
        for(Map<String, Integer> map : result){
            System.out.println(map);
        }

    }

//    public void selectLastContact(){
//        List result = contactDAO.selectLastContact(getConnection());
//        for(Object list : result){
//            System.out.println(list);
//
//        }
    }


