package com.test;

import java.util.concurrent.TimeUnit;

/*
 * ������Ե��ø����ͬ������
 */
public class ChildSuo {

	synchronized void m()
	{
		System.out.println("m start");
		try
		{
			TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("m end");
	}
	
	public static void main(String[] args)
	{
		new Child().m();
	}
}

class Child extends ChildSuo{
	@Override
	synchronized void m() {
		System.out.println("child m start");
		super.m();
		System.out.println("chid m end");
	}
}
