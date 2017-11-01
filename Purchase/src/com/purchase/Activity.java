package com.purchase;
import java.util.*;

public class Activity {
    /**
     *  Main function for this project
     */
    private HashMap<Integer,Integer> itemPopular = new HashMap<>(); // store popular information
    private HashMap<Integer,Integer> itemRemain = new HashMap<>();  // store remain information
    private HashMap<Integer,ActInfo> itemInfo = new HashMap<>();    // store activity information
    private HashMap<Integer,Integer> itemactiveId = new HashMap<>();    // Pair for activityID and goodsID
    private int numberItem,numberCommand;
    private Scanner scan = null;
    private int loadcommand = 0;
    public void loadInformation(){
        scan = new Scanner (System.in);
        String [] info = scan.nextLine().split(" ");
        this.numberItem = Integer.parseInt(info[0]);
        this.numberCommand = Integer.parseInt(info[1]);
        for(int i = 0; i < numberItem; i++){
            String[] part = scan.nextLine().split(" ");
            int id = Integer.parseInt(part[0]);
            int popular = Integer.parseInt(part[1]);
            int remain = Integer.parseInt(part[2]);
            itemPopular.put(id,popular);
            itemRemain.put(id,remain);
        }
    }
    public boolean hasNextLine(){
        return loadcommand < numberCommand;
    }
    public String getRequest(){
        loadcommand++;
        return scan.nextLine();
    }

    /**
     *
     * @param request: Read next line, get operation demand
     *               choosing different function to achieve it
     *
     */
    public void operation(String request){
        String[] split = request.split(" ");
        int time = Integer.parseInt(split[0]);
        if(split[1].equals("add")){
            addActivity(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Integer.parseInt(split[4]),Integer.parseInt(split[5]));
        }
        else if(split[1].equals("buy")){
            buyGoods(Integer.parseInt(split[2]),Integer.parseInt(split[3]),time);
        }
        else if(split[1].equals("list")){
            getActivityList(time);
        }
    }

    /**
     *
     * @param startTime Start time of this activity
     * @param endTime   End time of this activity
     * @param goodsId   Goods involved in
     * @param limitQuantity Quantity
     */
    private void addActivity(int startTime,int endTime,int goodsId,int limitQuantity){
        if(limitQuantity > itemRemain.get(goodsId)){
            System.out.println(-1);
        }
        else{
            ActInfo tmp = new ActInfo(startTime,endTime,limitQuantity);
            itemInfo.put(goodsId,tmp);
            itemactiveId.put(tmp.getActivityId(),goodsId);
            System.out.println(tmp.getActivityId());
        }
    }

    /**
     *
     * @param activeId activity ID
     * @param quant Purchase amount
     * @param time  purchase time
     */
    private void buyGoods(int activeId,int quant,int time){
        int goodsId = itemactiveId.get(activeId);
        ActInfo tmp = itemInfo.get(goodsId);
        if(time >= tmp.getEndTime() || time < tmp.getStartTime() || quant > tmp.getQuant()){
            System.out.println(-1);
        }
        else{
            tmp.changeQuant(quant);
            tmp.setLastPurchase(time);
            itemInfo.put(goodsId,tmp);
            System.out.println(0);
        }
    }

    /**
     *
     * @param time time stamp for getting list
     */
    private void getActivityList(int time){
        TreeSet<Rank> helper = new TreeSet<>(Rank.cmp);
        for(Map.Entry<Integer,ActInfo> entry : itemInfo.entrySet()){
            int id = entry.getKey();
            ActInfo part = entry.getValue();
            if(time >= part.getEndTime()){
                continue;
            }
            else{
                Rank tmp = new Rank(itemPopular.get(id),part.getStartTime(),id,part.getActivityId(),part.getLastPurchase());
                if(time < part.getStartTime()) tmp.setStatus(2);
                else if(part.getQuant() == 0) tmp.setStatus(1);
                helper.add(tmp);
            }

        }
        StringBuilder str = new StringBuilder();
        for(Rank k : helper) str.append(k.getActivityID()).append(" ");
        if(str.length() > 0) str.deleteCharAt(str.length() - 1);
        System.out.println(str.toString());
    }
}
