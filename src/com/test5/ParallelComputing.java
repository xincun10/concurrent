package com.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelComputing {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		List<Integer> results = getPrime(1, 200000);
		long end = System.currentTimeMillis();
		System.out.println("单线程执行时间：" + (end - start));
		
		//CPU核心数
		final int cpuCoreNum = 4;
		//新建线程池，数目为CPU核心数
		ExecutorService services  = Executors.newFixedThreadPool(cpuCoreNum);		
		//执行多线程任务
		MyTask t1 = new MyTask(1, 80000);
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);
		//计算多线程任务的结果
		Future<List<Integer>> f1 = services.submit(t1);
		Future<List<Integer>> f2 = services.submit(t2);
		Future<List<Integer>> f3 = services.submit(t3);
		Future<List<Integer>> f4 = services.submit(t4);
		
		start = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		end = System.currentTimeMillis();
		System.out.println("多线程执行时间：" + (end - start));
	}
	
	//一个类继承Callable接口，返回值为List<Integer>
	static class MyTask implements Callable<List<Integer>>
	{
		int startPos, endPos;		
		MyTask(int s, int e)
		{
			this.startPos = s;
			this.endPos = e;
		}

		@Override
		public List<Integer> call() throws Exception {
			List<Integer> list = getPrime(startPos, endPos);
			return list;
		}		
	}

	//判断一个数是不是质数
	static boolean isPrime(int num)
	{
		for(int i=2; i<=num/2; i++)
		{
			if(num % i == 0)
			{
				return false;
			}
		}
		return true;
	}
	//返回质数的集合
	static List<Integer> getPrime(int start, int end)
	{
		List<Integer> results = new ArrayList<>();
		for(int i=start; i<=end; i++)
		{
			if(isPrime(i))
			{
				results.add(i);
			}
		}
		return results;
	}
}
