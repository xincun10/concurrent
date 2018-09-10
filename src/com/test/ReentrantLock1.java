package com.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * reentrantLock锁的使用
 */
public class ReentrantLock1 {

	Lock lock = new ReentrantLock();
	void m1()
	{
		try
		{
			lock.lock();//相当于synchronized(this)
			for(int i=0; i<5; i++)
			{
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}finally
		{
			lock.unlock();//手动释放锁
		}
	}
	
	void m2()
	{
		lock.lock();//与m1方法锁定同一把锁
		System.out.println("m2...");
		lock.unlock();
	}
	
	public static void main(String[] args) {
		ReentrantLock1 rl = new ReentrantLock1();
		new Thread(rl::m1, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(rl::m2, "t2").start();//t1执行完毕之后才能执行t2方法
	}
}
