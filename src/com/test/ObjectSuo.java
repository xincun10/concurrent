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
		//������һ���߳�
		new Thread(suo::m, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�����ڶ����߳�
		Thread t2 = new Thread(suo::m, "t2");
		suo.o = new Object();//���������ı䣬t2�̵߳���ִ�У�����t2�߳���Զ�ò���ִ�С�
		t2.start();
	}
}
