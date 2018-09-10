package com.test2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {

	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0;
	
	public synchronized void put(T t)
	{
		while(lists.size()==MAX)//为什么用while不是用if?
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lists.add(t);
		++count;
		this.notifyAll();//通知消费者线程进行消费
	}
	
	public synchronized T get()
	{
		T t = null;
		while(lists.size()==0)
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		t = lists.removeFirst();
		count--;
		this.notifyAll();//通知生产者进行生产
		return t;
	}
	
	public static void main(String[] args)
	{
		MyContainer1<String> c = new MyContainer1<>();
		//启动消费者线程
		for(int i=0; i<10; i++)
		{
			new Thread(()->{
				for(int j=0; j<5; j++)
				{
					System.out.println(c.get());
				}
			}, "c"+i).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//启动生产者线程
		for(int i=0; i<2; i++)
		{
			new Thread(()->{
				for(int j=0; j<25; j++)
				{
					c.put(Thread.currentThread().getName() + " " + j);
				}
			}, "p"+i).start();
		}
	}
}
