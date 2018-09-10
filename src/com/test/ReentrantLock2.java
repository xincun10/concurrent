package com.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * reentrantLock����ʹ��
 */
public class ReentrantLock2 {

	Lock lock = new ReentrantLock();
	void m1()
	{
		try
		{
			lock.lock();//�൱��synchronized(this)
			for(int i=0; i<10; i++)
			{
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}finally
		{
			lock.unlock();//�ֶ��ͷ���
		}
	}
	
	void m2()
	{
		boolean locked = false;
		try {
			locked = lock.tryLock(5, TimeUnit.SECONDS);//��5s�ڳ�������
			System.out.println("m2..." + locked);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(locked) lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ReentrantLock2 rl = new ReentrantLock2();
		new Thread(rl::m1, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(rl::m2, "t2").start();//t1ִ�����֮�����ִ��t2����
	}
}
