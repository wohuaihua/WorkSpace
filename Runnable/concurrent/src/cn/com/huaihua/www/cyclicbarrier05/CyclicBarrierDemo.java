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
				Thread.sleep(1000);//随机数可能为负数
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(soldier+":任务完成");
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
				System.out.println("将军:[士兵"+N+"个，完成任务]");
			}else {
				System.out.println("将军:[士兵"+N+"个，集合完毕]");
				flag=true;
			}
		}
		
	}
	
	public static void main(String[] args) {
		final int N=10;
		Thread[] allSoldier=new Thread[N];
		boolean flag=false;
		CyclicBarrier cyclic=new CyclicBarrier(N, new BarrierRun(flag, N));
		System.out.println("队伍集合完毕");
		for(int i=0;i<N;i++) {
			System.out.println("士兵"+i+"报道");
			allSoldier[i]=new Thread(new Solider("士兵"+i+"个", cyclic));
			allSoldier[i].start();
		}
		
	}
}
