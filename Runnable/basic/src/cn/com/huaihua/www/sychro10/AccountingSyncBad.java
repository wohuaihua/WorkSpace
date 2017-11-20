package cn.com.huaihua.www.sychro10;

public class AccountingSyncBad implements Runnable{
	
	static int i=0;
	
	//实例方法，相当于当前实例加锁 (静态方法，相当于当前类加锁)
	public /*static*/ synchronized void increase() {
		i++;
	}
	
	@Override
	public void run() {
		for(int j=0;j<1000000;j++) {
			increase();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		//不同实例
		Thread t1=new Thread(new AccountingSyncBad());
		Thread t2=new Thread(new AccountingSyncBad());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
	
}
