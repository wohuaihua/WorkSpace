package cn.com.huaihua.www.reentrantlock01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 申请锁等待限时
 * @author 76494
 *
 */
public class TimeLock implements Runnable{
	
	public static ReentrantLock lock=new ReentrantLock();

	@Override
	public void run() {
		try {
			if(lock.tryLock(5, TimeUnit.SECONDS)) {
				Thread.sleep(6000);
			}else {
				System.out.println(Thread.currentThread().getId()+" Get lock is fail");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		TimeLock r1=new TimeLock();
		//多个线程走同一个方法
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r1);
		t1.start();
		t2.start();
	}
}
