package com.test5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService services = Executors.newFixedThreadPool(5);
		for(int i=0; i<6; i++)
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
		
		services.shutdown();
		System.out.println(services.isTerminated());
		System.out.println(services.isShutdown());
		System.out.println(services);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(services.isTerminated());
		System.out.println(services.isShutdown());
		System.out.println(services);
	}
	
}
