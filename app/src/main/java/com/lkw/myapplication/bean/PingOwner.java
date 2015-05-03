package com.lkw.myapplication.bean;

/**
 * Created by LKW on 2015/5/3.
 */
public class PingOwner {
    private String userID,headerUrl,name,is_self,sex,province,city;

    @Override
    public String toString() {
        return "PingOwner{" +
                "userID='" + userID + '\'' +
                ", headerUrl='" + headerUrl + '\'' +
                ", name='" + name + '\'' +
                ", is_self='" + is_self + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_self() {
        return is_self;
    }

    public void setIs_self(String is_self) {
        this.is_self = is_self;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

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
}
