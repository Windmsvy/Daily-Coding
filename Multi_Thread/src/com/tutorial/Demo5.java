package com.tutorial;

public class Demo5 {
    public static void main(String [] args) {
        Myrunnable2 mr2 = new Myrunnable2();
        Thread t2 = new Thread(mr2);

        t2.setDaemon(true);
        t2.start();
        for (int i = 0; i < 20 ; i++) {
            System.out.println("Main -- " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Myrunnable2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
