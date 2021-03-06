package cn.com.huaihua.www.threadpooldetail08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ExtThreadPool {
	public static class MyTask implements Runnable{
		
		public String name;

		public MyTask(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis()+" Thread ID :"+Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es=new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>()) {
			@Override
			protected void beforeExecute(Thread arg0, Runnable arg1) {
				System.out.println("准备执行"+((MyTask)arg1).name);
			}
			
			@Override
			protected void afterExecute(Runnable arg0, Throwable arg1) {
				System.out.println("执行完毕"+((MyTask)arg0).name);
			}
			
			@Override
			protected void terminated() {
				System.out.println("线程池退出");
			}
		};
		
		for(int i=0;i<5;i++) {
			MyTask task=new MyTask("TASK-GEYM-"+i);
			es.execute(task);
			Thread.sleep(10);
		}
		es.shutdown();
	}
}
