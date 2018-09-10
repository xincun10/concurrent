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
		strs.put("aaa");//�����ȴ�����������
//		strs.add("aaa");//������쳣����Ϊ���еĳ���Ϊ0
	}
}
