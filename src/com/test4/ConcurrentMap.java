package com.test4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class ConcurrentMap {

	public static void main(String[] args) {
//		Map<String, String> map = new ConcurrentHashMap<>();
		Map<String, String> map = new ConcurrentSkipListMap<>();
		
//		Map<String, String> map = new Hashtable<>();
//		Map<String, String> map = new HashMap<>();//Collections.synchronizedXXX
		Random r = new Random();
		Thread[] ths = new Thread[100];
		CountDownLatch latch = new CountDownLatch(ths.length);
		long start = System.currentTimeMillis();
		for(int i=0; i<ths.length; i++)
		{
			ths[i] = new Thread(()->{
				for(int j=0; j<10000; j++)
				{
					map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
				}
				latch.countDown();
			});
		}
		
		Arrays.asList(ths).forEach(t->t.start());
		//等待所有线程执行结束
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
