package com.mrz.thread.threadPool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ForkJoinPoolDemo {
	static Log log = LogFactory.getLog(ForkJoinPoolDemo.class);
	public static void main(String []args) throws InterruptedException, ExecutionException{
		ForkJoinPoolDemo demo = new ForkJoinPoolDemo();
		demo.startTest();
		demo.startScan();
	}
	
	private void startTest() throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool(10);
		long start2 = System.currentTimeMillis();
		ForkJoinTask<Integer> task2 = pool.submit(new Calculator2(0, 1000000));
		log.info(task2.get());
		log.info(System.currentTimeMillis() - start2);
		log.info(pool.getActiveThreadCount());
		long start = System.currentTimeMillis();
		ForkJoinTask<Integer> task = pool.submit(new Calculator(0,1000000));
		log.info(task.get());
		log.info(System.currentTimeMillis() - start);
		

	}

	private void startScan(){
		new FilePrinter(new File("C:/"), "sys").run();
	}
	
	class FilePrinter implements Runnable{
		ForkJoinPool pool2 = new ForkJoinPool(10);
		File rootFile;
		String matcher;
		public FilePrinter(File root, String matcher){
			this.rootFile = root;
			this.matcher = matcher;
		}
		@Override
		public void run() {
			File[] files = rootFile.listFiles();
			if(files!=null){
				for(File file : files){
					if(file.isFile()){
						if(file.getName().contains(matcher)){
							log.info(file.getAbsoluteFile());
						}
					}else{
						pool2.submit(new FilePrinter(file, matcher));
					}
				}
			}
		}
		
		
	}
	
	class Calculator2 implements Callable{
		int start;
		int end;
		int threshold = 20;
		public Calculator2(int start, int end){
			this.start = start;
			this.end = end;
		}
		@Override
		public Integer call() throws Exception {
			Integer sum = 0;
			if(end-start<=threshold){
				for(int i = start; i<=end; i++){
					sum = sum + i;
				}
			}else{
				int mid = start+(end-start)/2;
				Callable<Integer> left = new Calculator2(start, mid);
				Callable<Integer> right = new Calculator2(mid+1, end);
				sum = left.call()+right.call();
			}
			return sum;
		}
		
	}
	
	class Calculator extends RecursiveTask<Integer>{
		int start;
		int end;
		int threshold = 20;
		int result = 0;
		List<ForkJoinTask<Integer>> tasks = new ArrayList<>();
		public Calculator(int start, int end){			
			this.start = start;
			this.end = end;
		}
		@Override
		protected Integer compute() {
			
			if(end-start<=threshold){
				for(int i = start; i<=end; i++){
					result = result + i;
				}
			}else{
				int mid = start+(end-start)/2;
				RecursiveTask<Integer>left = new Calculator(start, mid);
				RecursiveTask<Integer> right = new Calculator(mid+1, end);
				left.fork();
				right.fork();
				
				result = left.join()+right.join();
			}
			return result;
		}
		
	}

}