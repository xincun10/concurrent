package com.test4;

import java.util.concurrent.LinkedTransferQueue;

public class TransferQueueTest {

	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
//		new Thread(()->{
//			try {
//				System.out.println(strs.take());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}).start();
		strs.transfer("aaa");
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}
}
