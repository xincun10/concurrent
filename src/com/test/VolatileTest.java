package com.test;

import java.util.concurrent.TimeUnit;

/*
 * volatile¹Ø¼ü×Ö
 */
public class VolatileTest {
	/*volatile*/ boolean running = true;
	void m()
	{
		System.out.println("m start");
		while(running)
		{
			
		}
		System.out.println("m end!");
	}
	
	public static void main(String[] args) {
		VolatileTest vt = new VolatileTest();
		new Thread(vt::m, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vt.running = false;
	}
}
