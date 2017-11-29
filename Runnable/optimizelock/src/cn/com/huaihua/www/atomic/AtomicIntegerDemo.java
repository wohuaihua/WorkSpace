package cn.com.huaihua.www.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
	
	static AtomicInteger i=new AtomicInteger();
	
	static CountDownLatch cd=new CountDownLatch(1000);
	
	public static class AddThread implements Runnable{

		@Override
		public void run() {
			for(int k=0;k<10000;k++) {
				i.incrementAndGet();
			}
			cd.countDown();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es=Executors.newFixedThreadPool(10);
		for(int i=0;i<1000;i++) {
			es.execute(new AddThread());
		}
		cd.await();
		System.out.println(i);
		es.shutdown();
	}
}
