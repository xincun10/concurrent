package com.test;

import java.util.ArrayList;
import java.util.List;

/*
 * volatile���ܱ�֤����̹߳�ͬ�޸�running����ʱ�����Ĳ�һ�����⡣
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
				o.join();//�ȴ����߳���ֹ
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//ǰ�������߳���ֹ֮����ִ�����߳�
		System.out.println(vt.count);
	}
}
