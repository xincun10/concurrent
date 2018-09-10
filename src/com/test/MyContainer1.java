package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer1 {

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
		MyContainer1 mc = new MyContainer1();
		
		new Thread(()->{
			for(int i=0; i<10; i++)
			{
				mc.add(new Object());
				System.out.println("add" + i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "t1").start();
		
		new Thread(()->{
			while(true)
			{
				if(mc.size()==5)
				{
					break;
				}
			}
			System.out.println("t2结束");
		}, "t2").start();
	}
	
}
