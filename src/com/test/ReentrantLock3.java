package com.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * reentrantLock锁的使用
 */
public class ReentrantLock3 {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Thread t1 = new Thread(()->{
			try
			{
				lock.lock();
				System.out.println("t1 start");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				System.out.println("t1 end");
			} catch(InterruptedException e)
			{
				System.out.println("interrupted!");
			} finally
			{
				lock.unlock();
			}			
		});
		t1.start();
		
		Thread t2 = new Thread(()->{
			boolean locked = false;
			try
			{
				lock.lockInterruptibly();//在等待锁的过程中可以对interrupt()方法做出响应
				locked = true;
				System.out.println("t2 start");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("t2 end");
			} catch(InterruptedException e)
			{
				System.out.println("interrupted!");
			}finally{
				if(locked) lock.unlock();
			}
		});
		t2.start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.interrupt();//打断线程2的等待
	}
}
