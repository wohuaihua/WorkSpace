package cn.com.huaihua.www.findstack09;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable{
	int a,b;
	
	public DivTask(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		double re=a/b;
		System.out.println(re);
	}
	
	public static void main(String[] args) {
		/*ThreadPoolExecutor pools=new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, 
				TimeUnit.MILLISECONDS, 
				new SynchronousQueue<Runnable>());
		*/
		TraceThreadPoolExecutor pools=new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, 
				TimeUnit.MILLISECONDS, 
				new SynchronousQueue<Runnable>());
		for(int i=0;i<5;i++) {
			pools.submit(new DivTask(100, i));//没有异常堆栈
			//pools.execute(new DivTask(100, i));//不知哪里提交的
		}
	}
}
