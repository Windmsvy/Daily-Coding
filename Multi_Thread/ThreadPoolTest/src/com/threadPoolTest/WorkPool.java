package com.threadPoolTest;

import java.util.concurrent.*;

public class WorkPool {
    public static void main(String[] args) throws InterruptedException{
        RejectedExecutionHandler rejHandler = new RejectedExecutionHandlerAlter();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(4,15,10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),threadFactory,rejHandler);
        MyMonitorThread myMonitorThread = new MyMonitorThread(executorPool,3);
        Thread monitorThread = new Thread(myMonitorThread);
        monitorThread.start();
        for(int i = 0; i < 20; i++){
            Thread.sleep(150);
            executorPool.execute(new WorkerThread("cmd " + i));
        }
        Thread.sleep(5000);
        executorPool.shutdown();
        Thread.sleep(5000);
        myMonitorThread.shutdown();
    }

}
