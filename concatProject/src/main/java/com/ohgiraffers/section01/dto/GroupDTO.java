package com.ohgiraffers.section01.dto;

public class GroupDTO
{
    private int groupIndex;
    private String groupName;

    public GroupDTO()
    {

    }

    public GroupDTO(int groupIndex, String groupName)
    {
        this.groupIndex = groupIndex;
        this.groupName = groupName;
    }

    public int getGroupIndex()
    {
        return groupIndex;
    }

    public void setGroupIndex(int groupIndex)
    {
        this.groupIndex = groupIndex;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
}
