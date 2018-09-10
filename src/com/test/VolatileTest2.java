package com.test;

import java.util.ArrayList;
import java.util.List;

/*
 * volatile不能保证多个线程共同修改running变量时带来的不一致问题。
 */
public class VolatileTest2 {

	volatile int count = 0;
	void m()
	{
		for(int i=0; i<10000; i++)
		{
			count++;
		}
	}
	public static void main(String[] args) {
		VolatileTest2 vt = new VolatileTest2();
		List<Thread> threads = new ArrayList<Thread>();
		for(int i=0; i<10; i++)
		{
			threads.add(new Thread(vt::m, "thread-"+i));
		}
		threads.forEach((o)->o.start());
		threads.forEach((o)->{
			try {
				o.join();//等待该线程终止
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//前面所有线程终止之后再执行主线程
		System.out.println(vt.count);
	}
}
