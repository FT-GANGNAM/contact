package com.ohgiraffers.section02.dto;

public class ContactDTO_YSJ {

    private String contact_name;

    private String phonenumber;

    private String email;

    private String address;

    private String birthday;

    private int groupnumber;

    public ContactDTO_YSJ() {
    }

    public ContactDTO_YSJ(String contact_name, String phonenumber, String email, String address, String birthday, int groupnumber) {
        this.contact_name = contact_name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.groupnumber = groupnumber;
    }

    public ContactDTO_YSJ contact_name(String name){
        this.contact_name = name;
        return this;
    }


    public ContactDTO_YSJ phonenumber (String phonenumber){
        this.phonenumber = phonenumber;
        return this;
    }

    public ContactDTO_YSJ email (String email){
        this.email = email;
        return this;
    }

    public ContactDTO_YSJ address (String address){
        this.address = address;
        return this;
    }

    public ContactDTO_YSJ birthday (String birthday){
        this.birthday = birthday;
        return this;
    }

    public ContactDTO_YSJ groupnumber (int groupnumber){
        this.groupnumber = groupnumber;
        if(groupnumber > 0){
            System.out.println("성공하셨습니다!");
        } else {
            System.out.println("0보다 큰수를 입력해주세요!");
        }
        return this;
    }


    public String getContact_name() {
        return contact_name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getGroupnumber() {
        return groupnumber;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "contact_name='" + contact_name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", groupnumber=" + groupnumber +
                '}';
    }
}
