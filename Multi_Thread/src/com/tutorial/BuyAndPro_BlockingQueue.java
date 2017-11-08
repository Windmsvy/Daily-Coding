package com.tutorial;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BuyAndPro_BlockingQueue {
    public static void main(String [] args){
        BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(10);
        Producer2 producer = new Producer2(blockingQueue);
        Consumer2 consumer = new Consumer2(blockingQueue);
        for(int i = 1; i <= 7; i++){
            new Thread(producer,"Producer-" + i).start();
        }
        for(int i = 1; i <= 5; i++){
            new Thread(consumer,"Customer-" + i).start();
        }
        System.out.println("Customer and Producer both started");
    }
}
class Message{
    private String message;
    public Message(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
class Producer2 implements Runnable{
    private static int i = 0;
    private BlockingQueue<Message> blockingQueue;
    private static boolean end = true;
    public Producer2(BlockingQueue<Message> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (i < 100) {
                synchronized (this) {
                    int t = (++i);
                    if(t <= 100) {
                        Message msg = new Message("" + t);
                        blockingQueue.put(msg);
                        System.out.println("Production " + msg.getMessage() + " is already by " + Thread.currentThread().getName());
                    }
                }
                Thread.sleep((int)(Math.random() * 300));
            }
            Thread.sleep(200);
            synchronized (Message.class) {
                if(end) {
                    Message exit = new Message("exit");
                    blockingQueue.put(exit);
                    end = false;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
class Consumer2 implements Runnable{
    private BlockingQueue<Message> blockingQueue;
    private static boolean stopSign = true;
    public Consumer2(BlockingQueue<Message> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        try{
            while(stopSign) {
                synchronized (this) {
                    if(stopSign || blockingQueue.size() > 0){
                        Message msg = blockingQueue.take();
                        if ("exit".equals(msg.getMessage())) {
                            stopSign = false;
                            break;
                        } else {
                            System.out.println("Production " + msg.getMessage() + " is taken by " + Thread.currentThread().getName());
                        }
                    }
                }
                Thread.sleep((int) (Math.random() * 300));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}