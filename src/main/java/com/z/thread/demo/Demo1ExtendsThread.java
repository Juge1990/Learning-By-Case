package com.z.thread.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class Demo1ExtendsThread {
	static Log log = LogFactory.getLog(Demo1ExtendsThread.class);
	Thread thread1;
	Thread thread2;
	boolean shouldDie = false;
	int num=0;
	
	
	public static void main(String[] agrs) {
		Demo1ExtendsThread demo = new Demo1ExtendsThread();
		demo.testDemo();
	}

	public void testDemo() {
		try {
			thread1 = new AddThread();
			thread2 = new ReduceThread();
			thread1.setName("producer");
			thread2.setName("consumer");

			thread1.start();
			thread2.start();
			while (num <= 10) {
				shouldDie = false;
				log.info("main thread, other thread should not die"+num);

				Thread.sleep(1000);
			}

			log.info("main thread, other thread should not die");
			shouldDie = true;
		} catch (InterruptedException e) {
			log.error(e.getStackTrace(), e);

		}
	}
	
	class AddThread extends Thread{
		@Override
		public void run() {
			while(!shouldDie){
				num = num+3;
				log.info(Thread.currentThread().getName()+ " +3");
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					log.error(e.getStackTrace(), e);
				}
			}
			log.info(Thread.currentThread().getName()+ " I completed.");

		}
	}
	
	class ReduceThread extends Thread {
		@Override
		public void run() {
			while (!shouldDie) {
				num = num - 1;
				log.info(Thread.currentThread().getName()+ " -1");

				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					log.error(e.getStackTrace(), e);
				}
			}
			log.info(Thread.currentThread().getName()+ " I completed.");

		}
	}
	

}
