package com.test5;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingPoolTest {

	public static void main(String[] args) throws IOException {
		//����CPU�ĺ�����������Ӧ��Ŀ���߳�
		ExecutorService services = Executors.newWorkStealingPool();
		//��ӡִ������CPU�ĺ�����
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		services.execute(new R(1000));
		services.execute(new R(2000));
		services.execute(new R(2000));
		services.execute(new R(2000));
		//4�������Ӹ�4���̣߳����������һ���Ƿ�������Ƚ������̣߳�����һ���߳�
		services.execute(new R(2000));
		//��������̳߳ز������Ǿ����̣߳�������������̵߳Ļ���û�취�����߳������
		System.in.read();
	}
	
	static class R implements Runnable
	{
		int time;
		R(int t)
		{
			this.time = t;
		}
		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(time + " " + Thread.currentThread().getName());
		}
		
	}
}
