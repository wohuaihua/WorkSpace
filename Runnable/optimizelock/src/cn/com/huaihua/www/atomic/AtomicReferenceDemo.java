package cn.com.huaihua.www.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ����cas ���������
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
									System.out.println("���С��20Ԫ����ֵ�ɹ���"+money.get());
									break;
								}else {
									System.out.println("������20Ԫ�������ֵ");
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
							System.out.println("����10Ԫ");
							if(money.compareAndSet(m, m-10)) {
								System.out.println("���ѳɹ������Ϊ��"+money.get());
								break;
							}
						}else {
							System.out.println("û���㹻�Ľ��");
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
