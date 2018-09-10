package com.test;

import java.util.concurrent.TimeUnit;

/*
 * 可重入的锁
 */
public class SameSuo {

	public static void main(String[] args)
	{
		SameSuo s = new SameSuo();
		s.m1();
	}
	
	synchronized void m1()
	{
		System.out.println("m1 start");
		try
		{
			TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		m2();
	}
	
	synchronized void m2()
	{
		try{
			TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("m2");
	}
}