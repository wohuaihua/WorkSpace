package cn.com.huaihua.www.suspendAndResume04;

public class GoodSuspend {

	public static Object u=new Object();
	
	public static class ChangeObjectThread extends Thread{
		
		volatile boolean suspendme=false;
		
		public void suspendMe() {
			suspendme=true;
		}
		
		public void resumeMe() {
			suspendme=false;
			//Exception in thread "main" java.lang.IllegalMonitorStateException
			//synchronized (u)
			synchronized (this) {//t1
				notify();
			}
		}
		
		@Override
		public void run() {
			while(true) {
				//Exception in thread "main" java.lang.IllegalMonitorStateException
				//synchronized (u)
				synchronized (this) {//t1
					while(suspendme) {
						try {
							//System.out.println(Thread.currentThread().getId()+" wait");
							wait();//先获取对象的监视器，执行后在释放
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					synchronized (u) {
						System.out.println(Thread.currentThread().getId()+" in ChangeObjectThread");
					}
					Thread.yield();
				}
			}
		}
	}
	
	public static class ReadObjectThread extends Thread{
		
		@Override
		public void run() {
			while(true) {
				synchronized (u) {
					System.out.println(Thread.currentThread().getId()+" in ReadObjectThread");
				}
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ChangeObjectThread t1=new ChangeObjectThread();
		ReadObjectThread t2=new ReadObjectThread();
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t1.suspendMe();
		System.out.println("suspend t1 2 sec");
		Thread.sleep(2000);
		System.out.println("resume t1");
		t1.resumeMe();
	}
}
