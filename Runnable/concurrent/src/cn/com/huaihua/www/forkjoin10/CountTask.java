package cn.com.huaihua.www.forkjoin10;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long>{
	
	private static final int THRESHOLD=10000;
	private long start;
	private long end;
	
	public CountTask(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long sum=0;
		boolean canCompute=(end-start)<THRESHOLD;
		if(canCompute) {
			for(long i=start;i<=end;i++) {
				sum+=i;
			}
			//System.out.println(sum);
		}else {
			long step=(start+end)/100;
			//System.out.println(step);
			ArrayList<CountTask> subTasks=new ArrayList<CountTask>();
			long pos=start;
			for(int i=0;i<100;i++) {
				long lastOne=pos+step;
				if(lastOne>end) {
					lastOne=end;
				}
				CountTask subTask=new CountTask(pos, lastOne);
				System.out.println(pos+" "+lastOne);
				pos+=step+1;
				subTasks.add(subTask);
				subTask.fork();
			}
			for(CountTask t:subTasks) {
				sum+=t.join();
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool=new ForkJoinPool();
		CountTask task=new CountTask(0, 200000L);
		ForkJoinTask<Long> result=forkJoinPool.submit(task);
		long res;
		try {
			res = result.get();
			System.out.println("sum="+res);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
