package com.tutorial;

public class Demo2 {
    public static void main(String [] args){
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Thread t1 = new Thread(dog1);
        Thread t2 = new Thread(dog2);
        t1.start();
        t2.start();
        /*
        dog1.run();
        dog2.run();
        */
    }
}
class Dog implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            try{
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Helloworld " +  i);

        }
    }
}
