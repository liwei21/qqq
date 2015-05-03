package com.lkw.myapplication.bean;

import java.util.List;

/**
 * Created by LKW on 2015/5/3.
 */
public class PingLun  {
    private String topicID,time,content,is_top,is_deal_owner,is_deal_supporter,
            likes,liked,imageUrlArray;
//    private List<String>imageUrlArray;
    private PingOwner owner;


    @Override
    public String toString() {
        return "PingLun{" +
                "topicID='" + topicID + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", is_top='" + is_top + '\'' +
                ", is_deal_owner='" + is_deal_owner + '\'' +
                ", is_deal_supporter='" + is_deal_supporter + '\'' +
                ", likes='" + likes + '\'' +
                ", liked='" + liked + '\'' +
                ", imageUrlArray='" + imageUrlArray + '\'' +
                ", owner=" + owner +
                '}';
    }

    public PingOwner getOwner() {
        return owner;
    }

    public void setOwner(PingOwner owner) {
        this.owner = owner;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIs_top() {
        return is_top;
    }

    public void setIs_top(String is_top) {
        this.is_top = is_top;
    }

    public String getIs_deal_owner() {
        return is_deal_owner;
    }

    public void setIs_deal_owner(String is_deal_owner) {
        this.is_deal_owner = is_deal_owner;
    }

    public String getIs_deal_supporter() {
        return is_deal_supporter;
    }

    public void setIs_deal_supporter(String is_deal_supporter) {
        this.is_deal_supporter = is_deal_supporter;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getImageUrlArray() {
        return imageUrlArray;
    }

    public void setImageUrlArray(String imageUrlArray) {
        this.imageUrlArray = imageUrlArray;
    }
}
