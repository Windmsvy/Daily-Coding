class NewThread implements Runnable {  
   Thread t;  
   NewThread() {  
      // Second Thread 
      t = new Thread(this, "Demo Thread");  
      System.out.println("Child thread: " + t);    
      t.start();   
   }  
   // Entry for second thread  
   public void run() {  
      try {  
         for(int i = 5; i > 0; i--) {  
            System.out.println("Child Thread: " + i);  
            Thread.sleep(50);  
         }  
     } catch (InterruptedException e) {  
         System.out.println("Child interrupted.");  
     }  
     System.out.println("Exiting child thread.");  
   }  
}  
  
public class basic1 {  
   public static void main(String args[]) {  
      //创建一个新线程  
      new NewThread();   
      try {  
         for(int i = 5; i > 0; i--) {  
           System.out.println("Main Thread: " + i);  
           Thread.sleep(75);  
         }  
      } catch (InterruptedException e) {  
         System.out.println("Main thread interrupted.");  
      }  
      System.out.println("Main thread exiting.");  
   }  
}