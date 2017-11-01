package com.purchase;
import java.util.Comparator;

/**
 *  class for list ranking
 */
public class Rank {
    private int popular,startTime,goodsID,lastPurchase,status,activityID;
    public Rank(int popular, int startTime, int goodsID,int activityID, int lastPurchase) {
        this.popular = popular;
        this.startTime = startTime;
        this.goodsID = goodsID;
        this.activityID = activityID;
        this.lastPurchase = lastPurchase;
        this.status = 0;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getActivityID(){
        return activityID;
    }

    public static Comparator<Rank> cmp = (o1, o2) -> {
        if(o1.status != o2.status) return o1.status - o2.status;
        else{
            /**
             * status = 2, not started
             * status = 1, sold out
             * status = 0, on sale
             */
            if(o1.status == 0){
                if(o1.popular != o2.popular) return o2.popular - o1.popular;
                return o1.goodsID - o2.goodsID;
            }
            else if(o1.status == 1){
                if(o1.lastPurchase != o2.lastPurchase) return o2.lastPurchase - o1.lastPurchase;
                else if(o1.popular != o2.popular) return o2.popular - o1.popular;
                return o1.goodsID - o2.goodsID;
            }
            else{
                if(o1.startTime != o2.startTime) return o1.startTime - o2.startTime;
                else if(o1.popular != o2.popular) return o1.popular - o2.popular;
                return o1.goodsID - o2.goodsID;
            }
        }
    };
}
