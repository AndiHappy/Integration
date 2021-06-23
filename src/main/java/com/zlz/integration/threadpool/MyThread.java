package com.zlz.integration.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

public class MyThread extends Thread {
    int falg;

    public MyThread(int falg) {
        this.falg=falg;
        this.setName("name:" + falg);
    }

    @lombok.SneakyThrows
    @Override
    public void run() {
        while (true){
            System.out.print(this.falg + " ");
            Thread.sleep(100);
        }

    }
}
