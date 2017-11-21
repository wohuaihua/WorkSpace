package cn.com.huaihua.www.reentrantlock01;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable{
	
	public static ReentrantLock lock=new ReentrantLock(true);//公平锁

	@Override
	public void run() {
		while(true) {
			lock.lock();
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+" 获得锁");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		FairLock r1=new FairLock();
		Thread t1=new Thread(r1, "Tread-t1");
		Thread t2=new Thread(r1,"Thread-t2");
		t1.start();
		t2.start();
	}
	
}
