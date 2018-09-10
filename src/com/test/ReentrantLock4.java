package com.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * reentrantLock锁的使用
 */
public class ReentrantLock4 extends Thread{
	//参数为true表示为公平锁
	private static ReentrantLock lock = new ReentrantLock(true);

	public void run()
	{
		for(int i=0; i<100; i++)
		{
			lock.lock();
			try{
				System.out.println(Thread.currentThread().getName() + " 获得锁");
			}finally{
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		ReentrantLock4 rl = new ReentrantLock4();
		Thread t1 = new Thread(rl);
		Thread t2 = new Thread(rl);
		t1.start();
		t2.start();
	}
}
