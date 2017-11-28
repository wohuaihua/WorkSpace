package cn.com.huaihua.www.test01;

public class AddSum {
	
	public static void main(String[] args) {
		long sum=0;
		for(int i=1;i<=200000;i++) {
			sum+=i;
			//System.out.println(sum);
		}
		System.out.println(sum);
	}
}
