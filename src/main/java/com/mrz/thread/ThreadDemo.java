package com.mrz.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadDemo {
	static Log log = LogFactory.getLog(ThreadDemo.class);

	public static void main(String[] args) throws Exception {
		log.info("Main thread start");
		Thread thread1 = new Thread(new Runnable(){
			public void run() {
				log.info("Inner Thread: hello, I am Anonymous Inner Thread.");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					log.error(e.getStackTrace());
				    Thread.currentThread().interrupt();

				}
			}
		});
		thread1.start();
		log.info("Main thread end");

	}

}
