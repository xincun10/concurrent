package com.test2;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

//	volatile static Person p = new Person();
	static ThreadLocal<Person> tl = new ThreadLocal<Person>();
	public static void main(String[] args) {
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(tl.get());
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tl.set(new Person());
		}).start();
	}
}

class Person
{
	String name = "zhangsan";
}