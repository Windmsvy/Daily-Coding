package com.tutorial;

/**
 *  @author windmsvy
 *  Using implements runnable method to achieve selling tickets
 */

public class SellTicket {
    public static void main(String [] args) {
        window w1 = new window();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.start();
        t2.start();
        t3.start();
    }
}
class window implements Runnable{
    private int total = 100;
    @Override
    public void run() {
        while(true) {
            synchronized (this) {
                System.out.print(Thread.currentThread().getName());
                if (total > 0) {
                    System.out.println(" now selling No: " + total + " tickets.");
                    try {
                        Thread.sleep((int)(Math.random() * 1000));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    total--;
                } else {
                    System.out.println(" Sold out");
                    break;
                }
            }
        }
    }
}
