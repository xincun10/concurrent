package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer2 {

	//����volatile��ʹt2�õ�֪ͨ
	volatile List lists = new ArrayList();
	
	public void add(Object o)
	{
		lists.add(o);
	}
	
	public int size()
	{
		return lists.size();
	}
	
	public static void main(String[] args) {
		
		MyContainer2 mc = new MyContainer2();
		final Object lock = new Object();
		
		new Thread(()->{
			synchronized(lock)
			{
				if(mc.size()!=5)
				{
					System.out.println("t2������");
					try {
						lock.wait();//�ͷ���
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("t2������");
				}
			}
		}, "t2").start();
				
		new Thread(()->{
			System.out.println("t1������");
			synchronized(lock)
			{
				for(int i=0; i<10; i++)
				{
					mc.add(new Object());
					System.out.println("add" + i);
					if(mc.size()==5)
					{
						lock.notify();//����t1���̣������ͷ�����
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}			
		}, "t1").start();
		
	}
	
}