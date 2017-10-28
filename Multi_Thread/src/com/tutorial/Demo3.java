package com.tutorial;

public class Demo3 {
    public static void main(String [] args){
        Calculator a = new Calculator(10);
        Reporter b = new Reporter(10);
        Thread thread1 = new Thread(a);
        Thread thread2 = new Thread(b);
        thread1.start();
        thread2.start();
    }
}
class Calculator implements Runnable{
    private int n;
    public Calculator(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += i;
            System.out.println("This is number " + i + ". Sum is " + sum);
        }
    }
}
class Reporter implements Runnable{
    private int n;
    public Reporter(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for(int i = 1; i <= n; i++){
            try{
                Thread.sleep(600);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("This is my No. " + i + " call!");
        }
    }
}
