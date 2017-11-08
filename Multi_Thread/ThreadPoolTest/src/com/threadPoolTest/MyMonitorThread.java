package com.threadPoolTest;

import java.util.concurrent.ThreadPoolExecutor;

public class MyMonitorThread implements Runnable {

    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    public MyMonitorThread(ThreadPoolExecutor executor,int delay){
        this.executor = executor;
        this.seconds = delay;
    }

    public void shutdown(){
        this.run = false;
    }

    @Override
    public void run() {
        try{
            while(run){
                Thread.sleep(300);
                System.out.println(String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                                this.executor.getPoolSize(), this.executor.getCorePoolSize(), this.executor.getActiveCount(),
                                this.executor.getCompletedTaskCount(), this.executor.getTaskCount(), this.executor.isShutdown(), this.executor.isTerminated()));
            }
            Thread.sleep(seconds * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
