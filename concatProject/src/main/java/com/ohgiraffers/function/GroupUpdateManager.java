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
    List<GroupDTO> groups = null;
    Scanner scr = new Scanner(System.in);

    private int groupNum = 0;

    public void updateGroup(int userCode)
    {
        // 해당 유저코드가 가지고 있는 그룹명 출력
        groups = contactDAO.getAllGroups(getConnection(), userCode);
        System.out.println("*** 저장된 그룹 ***");
        for (GroupDTO groupDTO : groups)
        {
            System.out.println("- " + groupDTO.getGroupName());
        }

        System.out.println("편집하고 싶은 그룹을 입력해주세요: ");
        String selectedGroup = scr.nextLine();
        for (GroupDTO groupDTO : groups)
        {
            if(groupDTO.getGroupName().equals(selectedGroup))
            {
                groupNum = groupDTO.getGroupIndex();
                break;
            }
        }

        while(true)
        {
            //그룹 내 연락처 삭제할래 아니면 그룹에 연락처 추가할래
            System.out.println("***** 서비스를 선택하세요 *****");
            System.out.println("- 추가\n- 삭제");
            String choice = scr.nextLine();

            if(choice.equals("추가"))
            {
                addContactInGroup(userCode);
                break;
            }
            else if(choice.equals("삭제"))
            {
                deleteContactsInGroup();
                break;
            }
            else
            {
                System.out.println("잘못된 입력입니다.");
            }

        }
    }

    private void addContactInGroup(int userCode)
    {
        //그룹에 연락처 추가
        // 그룹 출력해줬으니까 연락처 출력해서 연락처를 추가하고 싶은

        List<ContactDTO_YSJ> contacts = contactDAO.getAllContacts(getConnection(), userCode); // 수정해라
        // 애초에 contacts에서 가져올 때 join tbl_group 해서 그룹 이름도 가져와서 넣어야 함
        for (ContactDTO_YSJ contactDTO_YSJ : contacts)
        {
            System.out.println(contactDTO_YSJ.getContactName() + " " + contactDTO_YSJ.getPhonenumber());
        }

        List<String> phoneNumList = new ArrayList<>(); // 내가 입력 받을 휴대폰 번호
        while(true)
        {
            System.out.println("그룹에 추가할 휴대폰 번호를 입력해주세요: ");
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

    private void deleteContactsInGroup()
    {
        //그룹 내에서 연락처 삭제
        // 그룹넘버와 유저코드 일치하는 연락처 전체 조회

        // 삭제할 연락처 선택 => 삭제
        // ex) 그룹넘버 1인 홍길동을 그룹에서 삭제하겟다고 하면 그룹넘버 null로 만들어주는 느낌~~  ^^
        // 해당 그룹 넘버에 저장된 유저 없을 경우 없다고 출력

        System.out.println("삭제하실 휴대폰 번호를 입력해주세요.");
        String phoneNum = scr.nextLine();



    }
}
