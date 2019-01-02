package com.longxin.bdt.phone;


public class Record {
    private String phonePrefix;
    private String province;
    private String city;
    private String TelecomsOperators;
    private String AreaCode;
    private String postCode;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelecomsOperators() {
        return TelecomsOperators;
    }

    public void setTelecomsOperators(String telecomsOperators) {
        TelecomsOperators = telecomsOperators;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(String phonePrefix) {
        this.phonePrefix = phonePrefix;
    }


    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",
                getPhonePrefix(),
                getProvince(),
                getCity(),
                getTelecomsOperators(),
                getPostCode(),
                getAreaCode()
        );
    }
}