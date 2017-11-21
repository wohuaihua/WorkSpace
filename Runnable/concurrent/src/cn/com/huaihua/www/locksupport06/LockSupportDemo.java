package cn.com.huaihua.www.locksupport06;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
	
	public static Object u=new Object();
	static ChangeObjectThead t1=new ChangeObjectThead("t1");
	static ChangeObjectThead t2=new ChangeObjectThead("t2");
			
	public static class ChangeObjectThead extends Thread{
		public ChangeObjectThead(String name) {
			super.setName(name);
		}
		@Override
		public void run() {
			synchronized (u) {
				System.out.println("in "+getName());
				LockSupport.park();//�����߳�,���û�ȡ��
				//LockSupport.park(this); ����һ���������󣬻������dump��
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		LockSupport.unpark(t1);
		LockSupport.unpark(t2);
		t1.join();
		t2.join();
	}
}
