package cn.com.huaihua.www.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 无锁cas 引起的问题
 * @author 76494
 *
 */
public class AtomicReferenceDemo {
	
	static AtomicReference<Integer> money=new AtomicReference<Integer>();
	public static void main(String[] args) {
		money.set(19);
		for(int i=0;i<3;i++) {
			new Thread() {
				@Override
				public void run() {
					while(true) {
						while(true) {
							Integer m=money.get();
							if(m<20) {
								if(money.compareAndSet(m, m+20)) {
									System.out.println("余额小于20元，充值成功："+money.get());
									break;
								}else {
									System.out.println("余额大于20元，无需充值");
								}
							}
						}
					}
				}
			}.start();
		}
		
		new Thread() {
			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					while(true) {
						Integer m=money.get();
						if(m>10) {
							System.out.println("大于10元");
							if(money.compareAndSet(m, m-10)) {
								System.out.println("消费成功，余额为："+money.get());
								break;
							}
						}else {
							System.out.println("没有足够的金额");
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
	}
	
}
