package com.ohgiraffers;

public enum Prefer {


    EMAIL_ASC("email ASC",1),

    EMAIL_DESC("email DESC",2),

    NAME_ASC("contact_name ASC",3),

    NAME_DESC("contact_name DESC",4),

    BIRTHDAY_ASC("birthday ASC",5),

    BIRTHDAY_DESC("birthday DESC",6);









    private final String description;
    private final int count;

    public static  String description(int a) {
            String des = null;
        for(Prefer p : Prefer.values()) {
            if(p.count== a){
                des = p.getDescription();


            }

        }
        return des;
    }

    public static  String description(String prefer) {
        String des = null;
        for(Prefer p : Prefer.values()) {
            if(p.getDescription().equals(prefer)){
                des = p.getDescription();


            }

        }
        return des;
    }

    Prefer(String description, int count) {     // 자동으로 상수의 속성(소괄호안의값)이 description으로 들어온다!
        this.description = description;
        this.count = count;// 즉, 인스턴스를 만들면 상수의 속성이 따라온다!
    }

    public String getDescription() {
        return description;
    }

}
