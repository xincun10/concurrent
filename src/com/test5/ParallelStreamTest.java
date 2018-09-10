package com.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//计算所有的素数
public class ParallelStreamTest {

	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		Random r = new Random();
		for(int i=0; i<10000; i++)
		{
			nums.add(10000 + r.nextInt(10000));
		}
		
		long start = System.currentTimeMillis();
		nums.forEach(v->isPrime(v));
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		//使用parallel stream api
		start = System.currentTimeMillis();
		nums.parallelStream().forEach(ParallelStreamTest::isPrime);
		end = System.currentTimeMillis();
		System.out.println(end-start);
	}

	static boolean isPrime(int num) {
		for(int i=2; i<=num/2; i++)
		{
			if(num % i == 0)
			{
				return false;
			}
		}
		return true;
	}
}
