package com.test5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedPoolTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService services = Executors.newCachedThreadPool();
		System.out.println(services);
		
		for(int i=0; i<2; i++)
		{
			services.execute(()->{
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println(services);
		TimeUnit.SECONDS.sleep(80);
		System.out.println(services);
	}
}
