package com.longxin.bdt.phone;

public enum CardType {

    CHINA_MOBILE("中国移动", 1),
    CHINA_UNICOM("中国联通", 2),
    CHINA_TELECOM("中国电信", 3),
    CHINA_TELECOM_VIRTUAL_OPERATOR("电信虚拟运营商", 4),
    CHINA_UNICOM_VIRTUAL_OPERATOR("联通虚拟运营商", 5),
    CHINA_MOBILE_VIRTUAL_OPERATOR("移动虚拟运营商", 6),
    UNKNOWN("未定义", 0);

    private String name;
    private int id;

    CardType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static CardType getFromID(int id) {
        switch (id) {
            case 1:
                return CHINA_MOBILE;
            case 2:
                return CHINA_UNICOM;
            case 3:
                return CHINA_TELECOM;
            case 4:
                return CHINA_TELECOM_VIRTUAL_OPERATOR;
            case 5:
                return CHINA_UNICOM_VIRTUAL_OPERATOR;
            case 6:
                return CHINA_MOBILE_VIRTUAL_OPERATOR;
            default:
                return UNKNOWN;
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
