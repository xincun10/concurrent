package com.test3;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller1 {

	//Vector�ķ���������ͬ�������̰߳�ȫ��
	static Vector<String> tickets = new Vector<>();
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
				while(tickets.size()>0)
				{
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("������---" + tickets.remove(0));
				}
			}).start();
		}
	}
}
