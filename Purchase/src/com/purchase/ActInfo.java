package com.purchase;

/**
 * Store information of activity
 */
public class ActInfo {

    private static int order = -1; // Using static order for self-increasing
    private int startTime,endTime,Quant,lastPurchase,activityId;

    public ActInfo(int startTime, int endTime, int quant) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.Quant = quant;
        lastPurchase = 0;
        order ++;
        activityId = order;

    }
    public int getStartTime() {
        return startTime;
    }
    public int getEndTime() {
        return endTime;
    }
    public int getQuant() {
        return Quant;
    }
    public void changeQuant(int n){
        Quant -= n;
    }
    public void setLastPurchase(int lastPurchase) {
        this.lastPurchase = lastPurchase;
    }
    public int getLastPurchase(){
        return lastPurchase;
    }
    public int getActivityId(){
        return activityId;
    }
}
