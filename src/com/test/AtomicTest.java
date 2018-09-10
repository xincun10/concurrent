package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Aromic类
 */
public class AtomicTest {

	AtomicInteger count = new AtomicInteger(0);
	void m()
	{
		for(int i=0; i<10000; i++)
		{
			count.incrementAndGet();//代替count++,具备原子性
		}
	}
	
	public static void main(String[] args)
	{
		AtomicTest at = new AtomicTest();
		List<Thread> threads = new ArrayList<Thread>();
		for(int i=0; i<10; i++)
		{
			threads.add(new Thread(at::m, "thread-" + i));
		}
		
		threads.forEach((o)->o.start());
		threads.forEach((o)->{
			try {
				o.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		System.out.println(at.count);
	}
}
