package cn.com.huaihua.www.reentrantlock01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable{
	
	public static ReentrantLock lock=new ReentrantLock();
	public static Condition condition=lock.newCondition();
	
	@Override
	public void run() {
		try {
			lock.lock();//获取锁
			try {
				condition.await();//释放锁    类似wait
				System.out.println("Thread is going on");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			lock.unlock();//释放锁
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReenterLockCondition r1=new ReenterLockCondition();
		Thread t1=new Thread(r1);
		t1.start();
		Thread.sleep(1000);
		lock.lock();
		condition.signal();//首先尝试获取锁 		类似notify
		lock.unlock();
	}

}
