package com.test5;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingPoolTest {

	public static void main(String[] args) throws IOException {
		//根据CPU的核心数启动对应数目的线程
		ExecutorService services = Executors.newWorkStealingPool();
		//打印执行任务即CPU的核心数
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		services.execute(new R(1000));
		services.execute(new R(2000));
		services.execute(new R(2000));
		services.execute(new R(2000));
		//4个任务扔给4个线程，第五个任务一定是分配给最先结束的线程，即第一个线程
		services.execute(new R(2000));
		//由于这个线程池产生的是精灵线程，如果不阻塞主线程的话是没办法看到线程输出的
		System.in.read();
	}
	
	static class R implements Runnable
	{
		int time;
		R(int t)
		{
			this.time = t;
		}
		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(time + " " + Thread.currentThread().getName());
		}
		
	}
}
