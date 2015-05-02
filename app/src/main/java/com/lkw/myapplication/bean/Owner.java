package com.lkw.myapplication.bean;

/**
 * Created by LKW on 2015/5/2.
 */
public class Owner {
    private String headerUrl,name,intro,province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "headerUrl='" + headerUrl + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
