package com.mrz.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//http://www.jb51.net/article/81232.htm
public class ThreadDemo {
	static Log log = LogFactory.getLog(ThreadDemo.class);

	public static void main(String[] agrs) {
		ThreadDemo demo = new ThreadDemo();
		demo.test();
	}

	public void testRunnable() {
		int i = 0;

		// 方法二：实现Runnable
		for (i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 5) {
				Runnable runnable = new ThreadImplementsRunnable();
				new Thread(runnable).start();
				new Thread(runnable).start();
			}
		}
	}

	public void testCallable() {
		int i = 0;

		// 方法三：实现Callable接口
		Callable<Integer> callable = new ThreadImplementsCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		for (i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 5) {
				new Thread(futureTask).start();
				new Thread(futureTask).start();
			}
		}
		try {
			System.out.println("futureTask ruturn: " + futureTask.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test() {
		// 方法一：继承Thread
		int i = 0;
		for (; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 5) {
				ThreadExtendsThread threadExtendsThread = new ThreadExtendsThread();
				threadExtendsThread.start();
			}
		}

	}

	class ThreadExtendsThread extends Thread {
		private int i;

		@Override
		public void run() {
			for (; i < 10; i++) {
				System.out.println(getName() + " " + i);
			}
		}
	}

	class ThreadImplementsRunnable implements Runnable {
		private int i;

		@Override
		public void run() {
			for (; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
		}
	}

	class ThreadImplementsCallable implements Callable<Integer> {
		private int i;

		@Override
		public Integer call() throws Exception {
			for (; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
			return i;
		}
	}
}
