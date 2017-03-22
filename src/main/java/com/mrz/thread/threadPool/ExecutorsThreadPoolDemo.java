package com.mrz.thread.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExecutorsThreadPoolDemo {
	static Log log = LogFactory.getLog(ExecutorsThreadPoolDemo.class);

	public static void main(String []agrs){
		log.info("Main started");
		ExecutorsThreadPoolDemo demo = new ExecutorsThreadPoolDemo();
		demo.startRun();
		log.info("Main ended");
	}
	
	private void startRun(){
		List<Future<String>> futures = new ArrayList<>();
		ExecutorService service = Executors.newFixedThreadPool(4);
		for(int i = 0; i<5; i++){
			futures.add(service.submit(new Worker("A"+i)));
		}
		//������ý�����Ͳ�������
		for(Future<String> future: futures){
			try {
				log.info(future.get());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (ExecutionException e) {
				log.error(e.getStackTrace(), e);
			}
			
		}
	}
	
	 class Worker implements Callable<String> {
		String id;
		public Worker(String id){
			this.id = id;
		}
		@Override
		public String call() throws Exception {
			for(int i = 0; i<5 ; i++){
				log.info("I'm work: "+id);
			}
			return "Worker: "+ id+" done";
		}
		
	}
}
