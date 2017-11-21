package cn.com.huaihua.www.cyclicbarrier05;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	
	public static class Solider implements Runnable{
		
		private String soldier;
		private final CyclicBarrier cyclic;
		
		public Solider(String soldier, CyclicBarrier cyclic) {
			this.soldier = soldier;
			this.cyclic = cyclic;
		}

		@Override
		public void run() {
			try {
				cyclic.await();
				dowork();
				cyclic.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}

		private void dowork() {
			try {
				Thread.sleep(1000);//���������Ϊ����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(soldier+":�������");
		}
		
	}
	
	public static class BarrierRun implements Runnable{
		
		boolean flag;
		int N;
		
		public BarrierRun(boolean flag, int n) {
			this.flag = flag;
			N = n;
		}

		@Override
		public void run() {
			if(flag) {
				System.out.println("����:[ʿ��"+N+"�����������]");
			}else {
				System.out.println("����:[ʿ��"+N+"�����������]");
				flag=true;
			}
		}
		
	}
	
	public static void main(String[] args) {
		final int N=10;
		Thread[] allSoldier=new Thread[N];
		boolean flag=false;
		CyclicBarrier cyclic=new CyclicBarrier(N, new BarrierRun(flag, N));
		System.out.println("���鼯�����");
		for(int i=0;i<N;i++) {
			System.out.println("ʿ��"+i+"����");
			allSoldier[i]=new Thread(new Solider("ʿ��"+i+"��", cyclic));
			allSoldier[i].start();
		}
		
	}
}
