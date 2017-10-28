package com.tutorial;
import java.util.*;

public class Demo1 {

    public static void main(String[] args) {
	// write your code here
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        cat1.start();
        cat2.start();
    }
}
class Cat extends Thread{
    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            try{
                Thread.sleep(500);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(this.getName());
            System.out.println(i);
        }
    }
}
