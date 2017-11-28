package cn.com.huaihua.www.reentrantlock01;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 76494
 *
 */
public class TryLock implements Runnable{
	
	public static ReentrantLock lock1=new ReentrantLock();
	public static ReentrantLock lock2=new ReentrantLock();
	
	int lock;

	public TryLock(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		if(lock==1) {
			while(true) {
				if(lock1.tryLock()) {//尝试获得锁，如果成功返回true,否则返回fasle,该方法不等待，立即返回
					try {
						try {
							Thread.sleep(500);
							//System.out.println("t1 sleep end");
						} catch (InterruptedException e) {
						}
						if(lock2.tryLock()) {
							try {
								System.out.println(Thread.currentThread().getId()+" :my job done");
								return;
							} finally {
								lock2.unlock();
								//System.out.println("t2 unclock 1");
							}
						}
					} finally {
						lock1.unlock();
						//System.out.println("t1 unclock 1");
					}
				}
			}
		}else {
			while(true) {
				if(lock2.tryLock()) {
					try {
						try {
							Thread.sleep(500);
							//System.out.println("t2 sleep end");
						} catch (InterruptedException e) {
						}
						if(lock1.tryLock()) {
							try {
								System.out.println(Thread.currentThread().getId()+" :my job done");
								return;
							} finally {
								lock1.unlock();
								//System.out.println("t1 unclock 2");
							}
						}
					} finally {
						lock2.unlock();
						//System.out.println("t2 unclock 2");
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		TryLock r1=new TryLock(1);
		TryLock r2=new TryLock(2);
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t1.start();
		t2.start();
	}
	
}
