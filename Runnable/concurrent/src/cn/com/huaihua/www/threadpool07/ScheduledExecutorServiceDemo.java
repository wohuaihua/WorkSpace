package cn.com.huaihua.www.threadpool07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorServiceDemo {
	
	public static void main(String[] args) {
		ScheduledExecutorService ses=Executors.newScheduledThreadPool(10);
		ses.scheduleAtFixedRate(new Runnable() {
		//ses.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					//Thread.sleep(1000);
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()/1000);
			}
		}, 0, 2, TimeUnit.SECONDS);
	}
}
