package cn.com.huaihua.www.threadpooldetail08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectThreadDemo {

	public static class Mytask implements Runnable{

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis()+" :Thread ID: "+Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Mytask task=new Mytask();
		ExecutorService es=new ThreadPoolExecutor(5, 5, 0L,
				TimeUnit.MILLISECONDS,
				new LinkedBlockingDeque<Runnable>(10),
				Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {
					//ÖØÐ´¾Ü¾ø²ßÂÔ
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println(r.toString()+" is discard");
					}
				});
		for(int i=0;i<Integer.MAX_VALUE;i++) {
			es.submit(task);
			Thread.sleep(10);
		}
	}
}
