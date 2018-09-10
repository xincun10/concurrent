package com.test4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strs = new SynchronousQueue<>();
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		strs.put("aaa");//阻塞等待消费者消费
//		strs.add("aaa");//会产生异常，因为队列的长度为0
	}
}
