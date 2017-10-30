package com.tutorial;

/**
 *   Using extends thread method to achieve multi-thread, sychronized
 *   @author windmsvy
 */
public class SellTicketThread {
    public static void main(String [] args){
        sellThread s1 = new sellThread("Window 1");
        sellThread s2 = new sellThread("Window 2");
        sellThread s3 = new sellThread("Window 3");

        s1.start();
        s2.start();
        s3.start();
    }
}
class sellThread extends Thread{
    private static int total = 200;
    public sellThread(String name){
        super(name);
    }
    @Override
    public void run(){
        while (true) {
            synchronized (sellThread.class) {
                if(total > 0){
                    System.out.println(Thread.currentThread().getName() + " now output No. " + total-- + " ticket");
                    try {
                        Thread.sleep((int)(Math.random() * 300));
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Sold out");
                    break;
                }
            }
        }
    }
}
