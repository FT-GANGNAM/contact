package com.ohgiraffers.section02;

import com.ohgiraffers.section02.findPhoneNumber;

import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Controller {

    private findPhoneNumber findphoneNumber = new findPhoneNumber("src/main/resources/mapper/contact-query.xml");

    public void findNumber(){
        List<String> result = findphoneNumber.findPhoneNumbers1(getConnection());
        System.out.println(result);

    }
}
