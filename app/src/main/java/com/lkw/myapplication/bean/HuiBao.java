package com.lkw.myapplication.bean;

import java.util.List;

/**
 * Created by LKW on 2015/5/2.
 */
public class HuiBao {
    private String itemID,supportAmount,supportCount,limit,
            repay,time,deliveryFee,can_be_support,isNewbie,money,
            update_time,originalPrice,itemType;
    private List<String>imageUrls;

    @Override
    public String toString() {
        return "HuiBao{" +
                "itemID='" + itemID + '\'' +
                ", supportAmount='" + supportAmount + '\'' +
                ", supportCount='" + supportCount + '\'' +
                ", limit='" + limit + '\'' +
                ", repay='" + repay + '\'' +
                ", time='" + time + '\'' +
                ", deliveryFee='" + deliveryFee + '\'' +
                ", can_be_support='" + can_be_support + '\'' +
                ", isNewbie='" + isNewbie + '\'' +
                ", money='" + money + '\'' +
                ", update_time='" + update_time + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", itemType='" + itemType + '\'' +
                ", imageUrls=" + imageUrls +
                '}';
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getSupportAmount() {
        return supportAmount;
    }

    public void setSupportAmount(String supportAmount) {
        this.supportAmount = supportAmount;
    }

    public String getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(String supportCount) {
        this.supportCount = supportCount;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getRepay() {
        return repay;
    }

    public void setRepay(String repay) {
        this.repay = repay;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getCan_be_support() {
        return can_be_support;
    }

    public void setCan_be_support(String can_be_support) {
        this.can_be_support = can_be_support;
    }

    public String getIsNewbie() {
        return isNewbie;
    }

    public void setIsNewbie(String isNewbie) {
        this.isNewbie = isNewbie;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
