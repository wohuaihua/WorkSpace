package cn.com.huaihua.www.reentrantlock01;

import java.util.concurrent.locks.ReentrantLock;
/**
 * 重入锁的中断功能
 * @author 76494
 *
 */
public class IntLock implements Runnable{
	
	public static ReentrantLock lock1=new ReentrantLock();
	public static ReentrantLock lock2=new ReentrantLock();
	
	int lock;

	public IntLock(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
			try {
				if(lock==1) {
					lock1.lockInterruptibly();//如果当前线程未被中断，则获取锁。
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
					lock2.lockInterruptibly();
				}else {
					lock2.lockInterruptibly();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
					lock1.lockInterruptibly();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				if(lock1.isHeldByCurrentThread()) {//查询当前线程是否保持此锁
					lock1.unlock();
				}
				if(lock2.isHeldByCurrentThread()) {
					lock2.unlock();
				}
				System.out.println(Thread.currentThread().getId()+" 线程退出");
			}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntLock r1=new IntLock(1);
		IntLock r2=new IntLock(2);
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t2.interrupt();//中断响应
	}
	
}
