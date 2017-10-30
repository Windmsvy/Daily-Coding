class NewThread extends Thread {  
   NewThread() {  
      //创建第二个新线程  
      super("Demo Thread");  
      System.out.println("Child thread: " + this);  
      //开始线程  
      start();   
   }  
   //第二个线程入口  
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
public class basic2 {  
   public static void main(String args[]) {   
      new NewThread();   
      try {  
         for(int i = 5; i > 0; i--) {  
            System.out.println("Main Thread: " + i);  
            Thread.sleep(100);  
         }  
      } catch (InterruptedException e) {  
         System.out.println("Main thread interrupted.");  
      }  
      System.out.println("Main thread exiting.");  
   }  
}