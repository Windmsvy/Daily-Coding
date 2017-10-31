/**
 *  It is a simple example for deadlock
 *  Lock(a) contains Lock(b)
 *  Warning : DeadLock
 */

package com.tutorial;
public class DeadLockDemo {
    public static void main(String[] args){
        Player1 p1 = new Player1();
        Player2 p2 = new Player2();
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);

        t1.start();
        t2.start();
    }
}
class Player1 implements Runnable{
    @Override
    public void run() {
        synchronized (Player1.class){
            System.out.println("Player 1 finish the first step");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Player2.class){
                System.out.println("Player 1 finish the second step");
            }
        }
    }
}
class Player2 implements Runnable{
    @Override
    public void run() {
        synchronized (Player2.class){
            System.out.println("Player 2 finish the first step");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Player1.class){
                System.out.println("Player 2 finish the second step");
            }
        }
    }
}

