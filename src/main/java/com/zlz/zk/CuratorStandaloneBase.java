package com.zlz.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;

import java.util.concurrent.TimeUnit;

/**
 * https://curator.apache.org/curator-recipes/persistent-watcher.html
 * 可以仔细的查看一下
 * */
@Slf4j
public abstract  class CuratorStandaloneBase {

    private static final String CONNECT_STR = "127.0.0.1:2181";
    private static final int sessionTimeoutMs = 60*1000;
    private static final int connectionTimeoutMs = 5000;
    private static CuratorFramework curatorFramework;

    public static void main(String[] args) {
        init();
        try {
            createIfNeed("/test");
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 30);
        curatorFramework = CuratorFrameworkFactory.builder().connectString(getConnectStr())
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .canBeReadOnly(true)
                .build();
        curatorFramework.getConnectionStateListenable().addListener((client, newState) -> {
            if (newState == ConnectionState.CONNECTED) {
                log.info("连接成功！");
            }

        });
        log.info("连接中......");
        curatorFramework.start();
    }

    public static void createIfNeed(String path) throws Exception {
        curatorFramework.sync();
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat==null){
            String s = curatorFramework.create().forPath(path);
            log.info("path {} created! ",s);
        }
    }

    public static void createWatch(String path) throws Exception {
        //TODO 有可能再也做不了了
    }




    public static CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }
    protected static String getConnectStr(){
        return CONNECT_STR;
    }
}