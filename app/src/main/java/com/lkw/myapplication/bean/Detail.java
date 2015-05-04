package com.lkw.myapplication.bean;

import java.util.List;

/**
 * Created by LKW on 2015/5/1.
 */

///天假了住宿
public class Detail {
    private String name,summary,content,descUrl,supportMoney,targetMoney,supportCount,
            dayLeft,likeCount;

    public String getDescUrl() {
        return descUrl;
    }

    public void setDescUrl(String descUrl) {
        this.descUrl = descUrl;
    }

    public String getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(String supportMoney) {
        this.supportMoney = supportMoney;
    }

    public String getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(String targetMoney) {
        this.targetMoney = targetMoney;
    }

    public String getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(String supportCount) {
        this.supportCount = supportCount;
    }

    public String getDayLeft() {
        return dayLeft;
    }

    public void setDayLeft(String dayLeft) {
        this.dayLeft = dayLeft;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    private int progress;
    @Override
    public String
    toString() {
        return "Detail{" +
                "name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", imageUrlArray=" + imageUrlArray +
                '}';
    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private List<String>imageUrlArray;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImageUrlArray() {
        return imageUrlArray;
    }

    public void setImageUrlArray(List<String> imageUrlArray) {
        this.imageUrlArray = imageUrlArray;
    }


}
