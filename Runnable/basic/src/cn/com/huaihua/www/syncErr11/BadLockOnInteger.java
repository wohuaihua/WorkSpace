package cn.com.huaihua.www.syncErr11;

public class BadLockOnInteger implements Runnable{
	public static Integer i=0;
	
	static BadLockOnInteger instance=new BadLockOnInteger();

	@Override
	public void run() {
		for(int j=0;j<1000000;j++) {
			synchronized (i) {//错误的加锁
				i++;//i=Integer.valueOf(i.intValue()+1);
				//Integer 是一个不变对象，导致同步的不是一个对象
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1=new Thread(instance);
		Thread t2=new Thread(instance);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
	
}
