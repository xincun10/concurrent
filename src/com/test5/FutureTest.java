package com.test5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		});//�൱��new Callable(){Integer call();}
		
		new Thread(task).start();
		System.out.println(task.get());//����,������ִ����֮��õ���������ֵ
		
		ExecutorService services = Executors.newFixedThreadPool(5);//�½��̳߳أ�������5���߳�
		Future<Integer> f = services.submit(()->{//��������һ������submit()�ķ���ֵΪ1
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		
		System.out.println(f.isDone());
		System.out.println(f.get());
		System.out.println(f.isDone());
	}
}
