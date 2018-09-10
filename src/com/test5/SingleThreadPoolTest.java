package com.test5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolTest {

	public static void main(String[] args) {
		ExecutorService services = Executors.newSingleThreadExecutor();
		for(int i=0; i<5; i++)
		{
			final int j = i;
			services.execute(()->{
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
	}
}
