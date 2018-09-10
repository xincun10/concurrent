package com.test3;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller1 {

	//Vector的方法都加了同步，是线程安全的
	static Vector<String> tickets = new Vector<>();
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
				while(tickets.size()>0)
				{
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("销售了---" + tickets.remove(0));
				}
			}).start();
		}
	}
}
