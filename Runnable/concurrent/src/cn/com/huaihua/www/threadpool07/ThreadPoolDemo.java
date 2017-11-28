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
		//ExecutorService es=Executors.newFixedThreadPool(5);//�̶���С���̳߳�
		ExecutorService es=Executors.newCachedThreadPool();//һ������ʵ��������е������̳߳�
		for(int i=0;i<10;i++) {
			es.submit(task);
		}
		es.shutdown();
	}
}
