package com.ohgiraffers.section01.dto;

public class findGroupDTO {


    private int groupnumber;
    private String groupName;

    public findGroupDTO() {
    }

    public findGroupDTO(int groupnumber, String groupName) {
        this.groupnumber = groupnumber;
        this.groupName = groupName;
    }

    public int getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(int groupnumber) {
        this.groupnumber = groupnumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "findGroupDTO{" +
                "groupnumber=" + groupnumber +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}

