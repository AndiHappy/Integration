package com.zlz.ebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhailz
 *
 * @version 2018年8月15日 上午10:17:52
 */
public class LoadConditionPoolUtil {

	// 等待时间
	public static int waitTimeMils = 500000000;

	private static Logger log = LoggerFactory.getLogger(LoadConditionPoolUtil.class);

	protected static ExecutorService executePool = new ThreadPoolExecutor(24, 24, 60L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(5000));

	public static void submit(Runnable task) {
		executePool.execute(task);
	}
	
	
	
	/**
	 * 等待一定的时间，放弃
	 */
	public static boolean waitLoadDoc(LoadCondition condition,int waitTimeSeconds) {
		int waitTime = 0;
		try {
			// 乐观锁的用法
			for (;;) {
				if (condition.meetCondition() || waitTime > waitTimeSeconds*1000) {
					return condition.meetCondition();
				} else {
					int waitTimetmp = 50;
					Thread.sleep(waitTimetmp);
					waitTime = waitTime + waitTimetmp;
				}
			}
		} catch (InterruptedException e) {
			log.error("等待加载超时：{}", e);
			return false;
		}
	}

}
