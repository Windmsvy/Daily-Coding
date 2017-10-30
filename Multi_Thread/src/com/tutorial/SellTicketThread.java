package com.tutorial;

public class SellTicketThread {
    //TODO : correct this method
    public static void main(String [] args){
        sellThread s1 = new sellThread();
        sellThread s2 = new sellThread();
        sellThread s3 = new sellThread();

        s1.start();
        s2.start();
        s3.start();
    }
}
class sellThread extends Thread{
    private static volatile int total = 200;
    @Override
    public void run(){
        while (true) {
            synchronized (this) {
                if(total > 0){
                    System.out.println(Thread.currentThread().getName() + " now output No. " + total-- + " ticket");
                    try {
                        Thread.sleep(300);
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
