package com.ohgiraffers.section01.dto;

public class ContactDTO {

    private int contact_code;
    private String contact_name;
    private String phonenumber;
    private String email;
    private String address;
    private String birthday;
    private String groupnumber;
    private String groupName;

    public ContactDTO() {
    }

    public ContactDTO(int contact_code, String contact_name, String phonenumber, String email, String address, String birthday, String groupnumber) {
        this.contact_code = contact_code;
        this.contact_name = contact_name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.groupnumber = groupnumber;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public ContactDTO(String contact_name, String phonenumber, String birthday, String groupName)
    {
        this.contact_name = contact_name;
        this.phonenumber = phonenumber;
        this.birthday = birthday;
        this.groupName = groupName;
    }

    public ContactDTO(String contact_name, String phonenumber, String email, String address, String birthday, String groupName) {
        this.contact_name = contact_name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.groupName = groupName;
    }

    public int getContact_code() {
        return contact_code;
    }

    public void setContact_code(int contact_code) {
        this.contact_code = contact_code;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(String groupnumber) {
        this.groupnumber = groupnumber;
    }


    @Override
    public String toString() {
        return
                "연락처 코드 =" + contact_code +
                ", 이름 ='" + contact_name + '\'' +
                ", 전화번호 ='" + phonenumber + '\'' +
                ", 이메일 ='" + email + '\'' +
                ", 주소 ='" + address + '\'' +
                ", 생일 ='" + birthday + '\'' +
                ", 그룹 ='" + groupnumber + '\''
                ;
    }
}
