package com.test3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller3 {

	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	static{
		for(int i=0; i<1000; i++)
		{
			tickets.add("票编号" + i);
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++)
		{
			new Thread(()->{
				while(true)
				{
					String s = tickets.poll();//poll()方法是原子性的
					if(s == null)
					{
						break;
					}
					else
					{
						System.out.println("销售了--" + s);
					}
				}
			}).start();
		}
	}
}
