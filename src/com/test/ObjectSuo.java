package com.test;

import java.util.concurrent.TimeUnit;

public class ObjectSuo {

	Object o = new Object();
	
	void m()
	{
		synchronized(o)
		{
			while(true)
			{
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	public static void main(String[] args) {
		ObjectSuo suo = new ObjectSuo();
		//启动第一个线程
		new Thread(suo::m, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建第二个线程
		Thread t2 = new Thread(suo::m, "t2");
		suo.o = new Object();//锁对象发生改变，t2线程得以执行，佛则，t2线程永远得不到执行。
		t2.start();
	}
}
