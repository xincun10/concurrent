package com.test3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller3 {

	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	static{
		for(int i=0; i<1000; i++)
		{
			tickets.add("Ʊ���" + i);
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++)
		{
			new Thread(()->{
				while(true)
				{
					String s = tickets.poll();//poll()������ԭ���Ե�
					if(s == null)
					{
						break;
					}
					else
					{
						System.out.println("������--" + s);
					}
				}
			}).start();
		}
	}
}
