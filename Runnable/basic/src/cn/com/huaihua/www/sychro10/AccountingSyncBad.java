package cn.com.huaihua.www.sychro10;

public class AccountingSyncBad implements Runnable{
	
	static int i=0;
	
	//ʵ���������൱�ڵ�ǰʵ������ (��̬�������൱�ڵ�ǰ�����)
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
		//��ͬʵ��
		Thread t1=new Thread(new AccountingSyncBad());
		Thread t2=new Thread(new AccountingSyncBad());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
	
}
