package cn.com.huaihua.www.suspendAndResume04;

public class BadSuspend {
	
	public static Object u=new Object();
	
	static ChangeObjectThread t1=new ChangeObjectThread("t1");
	
	static ChangeObjectThread t2=new ChangeObjectThread("t2");
	
	public static class ChangeObjectThread extends Thread{
		
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		@Override
		public void run() {
			synchronized (u) {
				System.out.println("in "+getName()+" "+System.currentTimeMillis());
				Thread.currentThread().suspend();
			}
			System.out.println("out "+getName());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		System.out.println(System.currentTimeMillis());
		t1.resume();
		System.out.println(System.currentTimeMillis());
		t2.resume();
		t1.join();
		t2.join();
	}
}
