package com.tutorial;

import java.util.concurrent.CountDownLatch;

public class CountDownTest {
    public static void main(String[] args){
        CountDownLatch endSingal = new CountDownLatch(5);

        for(int i = 1; i <= 10; i++){
            new Thread(new CountDownWorker(endSingal),"Worker-"+i).start();
        }
        System.out.println("Let's start");
        try {
            endSingal.await();
            new Thread(new CountDownMainWorker()).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
class CountDownWorker implements Runnable{
    private final CountDownLatch endSingal;
    public CountDownWorker(CountDownLatch endSingal){
        this.endSingal = endSingal;
    }
    @Override
    public void run() {
        try{
            Thread.sleep((int)(Math.random() * 2000));
            System.out.println(Thread.currentThread().getName() + " is Running");
            endSingal.countDown();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " continue working");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class CountDownMainWorker implements Runnable{
    @Override
    public void run() {
        System.out.println("Everything is ready, I will start working. ");
    }
}
