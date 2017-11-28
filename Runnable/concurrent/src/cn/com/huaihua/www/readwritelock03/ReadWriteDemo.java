package cn.com.huaihua.www.readwritelock03;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁，实现读读之间真正的并行
 * @author 76494
 *
 */
public class ReadWriteDemo {
	
	private static Lock lock=new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	private static Lock readLock=readWriteLock.readLock();
	private static Lock writeLock=readWriteLock.readLock();
	
	private static int value;
	
	public Object handleRead(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);//模拟耗时操作
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	public void handleWrite(Lock lock,int index) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);//模拟耗时操作
			value=index;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ReadWriteDemo demo=new ReadWriteDemo();
		Runnable readRunnable=new Runnable() {
			
			@Override
			public void run() {
				try {
					demo.handleRead(readLock);
					//demo.handleRead(lock);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable writeRunnable=new Runnable() {
			
			@Override
			public void run() {
				try {
					demo.handleWrite(writeLock, new Random().nextInt());
					//demo.handleWrite(lock, new Random().nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		for(int i=0;i<18;i++) {
			new Thread(readRunnable).start();;
		}
		for(int i=0;i<20;i++) {
			new Thread(writeRunnable).start();;
		}
	}
}
