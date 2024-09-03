package com.ohgiraffers.function;

import com.ohgiraffers.section01.dto.GroupDTO;
import com.ohgiraffers.section02.dao.ContactDAO_YSJ;
import com.ohgiraffers.section02.dto.ContactDTO_YSJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class GroupUpdateManager
{
    ContactDAO_YSJ contactDAO = new ContactDAO_YSJ("src/main/resources/mapper/contact-query.xml");
    public void updateGroup(int userCode)
    {
        Scanner scr = new Scanner(System.in);

        // 해당 유저코드가 가지고 있는 그룹명 출력
        List<GroupDTO> groups = contactDAO.getAllGroups(getConnection(), userCode);
        System.out.println("*** 연락처에 있는 그룹입니다 ***");
        for (GroupDTO groupDTO : groups)
        {
            System.out.println("- " + groupDTO.getGroupName());
        }

        // 연락처 출력할 때
        System.out.println("연락처를 추가하고 싶은 그룹을 입력해주세요: ");
        String selectedGroup = scr.nextLine();
        int groupNum = 0;
        for (GroupDTO groupDTO : groups)
        {
            if(groupDTO.getGroupName().equals(selectedGroup))
            {
                groupNum = groupDTO.getGroupIndex();
                break;
            }
        }

        List<ContactDTO_YSJ> contacts = contactDAO.getAllContacts(getConnection(), userCode); // 수정해라
        System.out.println(contacts);

        List<String> phoneNumList = new ArrayList<>(); // 내가 입력 받을 휴대폰 번호

        while(true)
        {
            System.out.println("그룹에 담을 휴대폰 번호를 입력해주세요: ");
            String phoneNum = scr.nextLine();

            System.out.println("더 추가하시겠습니까?");
            String answer = scr.nextLine();

            if(answer.equals("yes") || answer.equals("네") || answer.equals("예"))
            {
                phoneNumList.add(phoneNum);
                continue;
            }
            else if(answer.equals("no") || answer.equals("아니요") || answer.equals("아니"))
            {
                phoneNumList.add(phoneNum);
                break;
            }
            else
            {
                System.out.println("잘못된 입력입니다. [ " + phoneNum + " 그룹 추가 취소 ] ");
                break;
            }
        }

        for(int i = 0 ; i < contacts.size() ; i++)
        {
            for(int j = 0 ; j < phoneNumList.size() ; j++)
            {
                if(contacts.get(i).getPhonenumber().equals(phoneNumList.get(j)))
                {
                    // contacts[i]의 groupnumber를 내가 선택한 그룹 이름의 넘버로 바꿔줄거예요
                    contactDAO.changeGroupNumberOfContact(getConnection(), groupNum, userCode, phoneNumList.get(j));
                }
            }
        }
    }
}
