package com.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {

	static List<String> tickets = new ArrayList<>();
	static{
		for(int i=0; i<1000; i++)
		{
			tickets.add("Æ±±àºÅ" + i);
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++)
		{
			new Thread(()->{
				while(true)
				{
					synchronized(tickets){
						if(tickets.size()<=0)
						{
							break;
						}
						try {
							TimeUnit.SECONDS.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("ÏúÊÛÁË---" + tickets.remove(0));
					}	
				}
			}).start();
		}
	}
}
