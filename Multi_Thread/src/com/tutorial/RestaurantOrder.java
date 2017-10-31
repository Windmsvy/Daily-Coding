/**
 *  @author Windmsy
 *  @date Oct,31 2017
 *  Multi-Thread, Simulation the process of ordering and cooking
 *  User can input how many customer and chief
 *  Output : order / cooking information
 *  Using Synchronized & wait/notify
 *  Succeed in handling if there is no order available
 */

package com.tutorial;
import java.util.Scanner;
public class RestaurantOrder {
    public static void main(String[] args) {
        Food food = new Food();
        System.out.print("How many customer do you have ? ");
        Scanner scan = new Scanner(System.in);
        int num_cust = scan.nextInt();
        System.out.print("How many chief do you have ? ");
        int num_chief = scan.nextInt();

        Thread[] customers = new Thread[num_cust];
        Thread[] chiefs = new Thread[num_chief];

        for(int j = 0; j < num_chief; j++){
            chiefs[j] = new Thread(new Chief(food));
            chiefs[j].setName("Chief " + (j + 1));
            chiefs[j].start();
        }
        for(int i = 0; i < num_cust;i ++){
            customers[i] = new Thread(new Customer(food));
            customers[i].setName("Customer " + (i + 1));
            customers[i].start();
        }
    }
}
class Food{
    private static final Object lock1 = new Object();
    private int order = 0;
    Food(){}
    void getOrder(int i) {
        synchronized (lock1) {
            if (i > order) {
                try {
                    lock1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " get the order " + i);
        }
    }
    void setOrder(int order) {
        synchronized (lock1) {
            this.order = order;
            System.out.println(Thread.currentThread().getName() + " finish food " + order);
            lock1.notify();
        }
    }
}

class Customer implements Runnable{
    private Food food;
    Customer(Food food) {
        this.food = food;
    }
    private static int i = 1;
    @Override
    public void run() {
        synchronized (this) {
            while (i <= 200) {
                try {
                    Thread.sleep((int) (Math.random() * 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i <= 200) food.getOrder(i ++);
            }
        }
    }
}

class Chief implements Runnable{
    private Food food;
    Chief(Food food) {
        this.food = food;
    }
    private static int i = 1;
    @Override
    public void run() {
        synchronized (this) {
            while (i <= 200) {
                try {
                    Thread.sleep((int)(Math.random() * 300));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i <= 200) food.setOrder(i ++);
            }
        }
    }
}
