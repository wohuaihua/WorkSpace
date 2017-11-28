package cn.com.huaihua.www.threadpool07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
	
	public static class MyTask implements Runnable{

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis()+" "+Thread.currentThread().getId()+" arrived");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		MyTask task=new MyTask();
		//ExecutorService es=Executors.newFixedThreadPool(5);//固定大小的线程池
		ExecutorService es=Executors.newCachedThreadPool();//一个根据实际情况进行调整的线程池
		for(int i=0;i<10;i++) {
			es.submit(task);
		}
		es.shutdown();
	}
}
