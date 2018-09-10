package com.test5;

import java.util.concurrent.Executor;

public class ExecutorTest implements Executor{

	public static void main(String[] args) {
		new ExecutorTest().execute(()->System.out.println("hello executor"));
	}
	@Override
	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		command.run();
	}
}
