package com.tutorial;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new MainTask());
        for(int i = 1; i <= 5; i++){
            new SubTask(cyclicBarrier,"Thread-"+i).start();
        }
    }
}
class MainTask implements Runnable{
    @Override
    public void run() {
        System.out.println("这是主要的任务!");
    }
}
class SubTask extends Thread{
    private CyclicBarrier cyclicBarrier;
    private String name;

    public SubTask(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("[并发任务" + name + "] 开始执行");
        try{
            Thread.sleep(5000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("[并发任务" + name + "] 执行完毕，通知执行器");
        try {
            cyclicBarrier.await();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}

