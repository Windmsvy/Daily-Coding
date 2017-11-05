/**
 *  @author Windmsy
 *  @date Oct,31 2017
 *  Multi-Thread, Simulation the process of ordering and cooking
 *  User can input how many customer and chief
 *  Output : order / cooking information
 *  Using Synchronized and wait/notify
 *  Succeed in handling if there is no order available
 */

import java.util.Scanner;
public class BuyAndProd {
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	System.out.print("How many Food do you wanna sell ? ");
    	int foodnum = scan.nextInt();
        Food food = new Food(foodnum);
        System.out.print("How many customer do you have ? ");
        int num_cust = scan.nextInt();
        System.out.print("How many chief do you have ? ");
        int num_chief = scan.nextInt();
        for(int i = 1; i <= num_chief; i++){
            Thread tmp = new Thread(new Chief(food),"Producer-" + i);
            tmp.start();
        }
        for(int i = 1; i <= num_cust;i ++){
            Thread tmp = new Thread(new Customer(food),"Customer-" + i);
            tmp.start();
        }
    }
}
class Food{
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    private static final int MAX = 3;
    public final int upper;
    public Food(int n){
    	this.upper = n;
    }
    private int storage = 0;
    void purchase(int [] a) {
    	try {
        	synchronized (lock1) {
            	while(a[0] <= upper && storage <= 0) lock1.wait();
            } 
        	Thread.sleep(100);
        	synchronized (lock2){
        		if(a[0] <= upper){
	        		boolean jst = true;
	        		while(storage > 0 && a[0] <= upper &&jst){
		        		System.out.println(Thread.currentThread().getName() + " purchase the order " + a[0]++);
		        		jst = false;
		        		storage --;
		        		Thread.sleep(100);
		        		lock2.notifyAll();
	        		}
        		}
        	}
        }	
        catch (InterruptedException e){
        	e.printStackTrace();
        }
    }
    void produce(int [] a) {
    	try{
	        synchronized(lock2){
	            while(a[0] <= upper && storage >= MAX) lock2.wait();
	        }
	        Thread.sleep(100);
	        synchronized(lock1){
	        	if(a[0] <= upper){
		        	boolean jst = true;
		        	while(storage < MAX && a[0] <= upper && jst){
		            	System.out.println(Thread.currentThread().getName() + " produce the order " + a[0]++);
		            	jst = false;
		            	storage ++;
		            	Thread.sleep(100);
		            	lock1.notifyAll();
		        	}
	        	}
	    	}
	    }
    	catch(InterruptedException e){
    		e.printStackTrace();
    	}
	}
}

class Customer implements Runnable{
    private Food food;
    Customer(Food food) {
        this.food = food;
    }
    private static int [] a = {1};
    @Override
    public void run() {
        synchronized (this) {
        	while(a[0] <= food.upper){
				food.purchase(a);
			}
        }
    }
}

class Chief implements Runnable{
    private Food food;
    Chief(Food food) {
        this.food = food;
    }
    private static int [] a = {1};
    @Override
    public void run() {
        synchronized (this) {
        	while(a[0] <= food.upper){
				food.produce(a);
			}
        }
    }
}
