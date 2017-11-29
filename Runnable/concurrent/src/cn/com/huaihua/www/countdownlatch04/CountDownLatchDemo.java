package cn.com.huaihua.www.countdownlatch04;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {

	public final static CountDownLatch end = new CountDownLatch(10);

	public final static CountDownLatchDemo demo = new CountDownLatchDemo();

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println("check complete");
			end.countDown();//通知CountDownLatch，一个任务已经完成了，到计时器可以减一了。
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			exec.execute(new CountDownLatchDemo());
		}
		end.await();// 只有10个任务都完成的时候才继续进行
		System.out.println("Fire");
		exec.shutdown();
	}
}
