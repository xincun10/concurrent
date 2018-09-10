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
		});//相当于new Callable(){Integer call();}
		
		new Thread(task).start();
		System.out.println(task.get());//阻塞,等任务执行完之后得到整数返回值
		
		ExecutorService services = Executors.newFixedThreadPool(5);//新建线程池，里面有5个线程
		Future<Integer> f = services.submit(()->{//往里面扔一个任务，submit()的返回值为1
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		
		System.out.println(f.isDone());
		System.out.println(f.get());
		System.out.println(f.isDone());
	}
}
