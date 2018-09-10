package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyContainer3 {

	//添加volatile，使t2得到通知
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
		
		MyContainer3 mc = new MyContainer3();
		CountDownLatch latch = new CountDownLatch(1);
		
		new Thread(()->{
			if(mc.size()!=5)
			{
				System.out.println("t2启动！");
				try {
					//加上门闩
					latch.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t2结束！");
			}
		}, "t2").start();
				
		new Thread(()->{
			System.out.println("t1启动！");
			for(int i=0; i<10; i++)
			{
				mc.add(new Object());
				System.out.println("add" + i);
				if(mc.size()==5)
				{
					//放下门闩
					latch.countDown();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}, "t1").start();
		
	}
	
}
