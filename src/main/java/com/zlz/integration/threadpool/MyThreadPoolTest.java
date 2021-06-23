package com.zlz.integration.threadpool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolTest {

   public static Logger logger = LoggerFactory.getLogger(MyThreadPoolTest.class);

    private static RejectedExecutionHandler handler;
    /**
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     * parameters.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * @param threadFactory the factory to use when the executor
     *        creates a new thread
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code corePoolSize < 0}<br>
     *         {@code keepAliveTime < 0}<br>
     *         {@code maximumPoolSize <= 0}<br>
     *         {@code maximumPoolSize < corePoolSize}
     * @throws NullPointerException if {@code workQueue}
     *         or {@code threadFactory} or {@code handler} is null
     */
    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,5,1000, TimeUnit.MINUTES,new LinkedBlockingQueue<>(10),new CustomRejectedExecutionHandler());

    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            logger.info("Reject: {},ThreadPool State activeCount:{}, poolSize:{},queueSize:{}",
                    r,executor.getActiveCount(),executor.getPoolSize(),executor.getQueue().size());
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            MyThreadPoolTest.poolExecutor.submit(new MyThread(i));
        }

        for (int i = 3; i < 13 ; i++) {
            MyThreadPoolTest.poolExecutor.submit(new MyThread(i));
        }

        MyThreadPoolTest.poolExecutor.submit(new MyThread(13));
        MyThreadPoolTest.poolExecutor.submit(new MyThread(14));
        MyThreadPoolTest.poolExecutor.submit(new MyThread(15));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
