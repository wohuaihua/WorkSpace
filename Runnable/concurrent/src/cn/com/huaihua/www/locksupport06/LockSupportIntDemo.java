package cn.com.huaihua.www.locksupport06;

import java.util.concurrent.locks.LockSupport;


public class LockSupportIntDemo {
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
				LockSupport.park();
				if(Thread.interrupted()) {
					System.out.println(getName()+" 被中断了");
				}
				System.out.println(getName()+" 执行完成");
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		t1.interrupt();
		LockSupport.unpark(t2);
	}
}
