package com.z.thread.demo;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo5MutiThreadWithThreadPool {

	public static void main(String agrs[]) throws InterruptedException, ExecutionException{
		class Scanner implements Runnable{
			File rootFile;
			public Scanner(File file){
				rootFile = file;
			}
			private  void scanFile(File file) {
				String matcher = "sys";
				if(file.isFile()){
					if(file.getName().contains(matcher)){
						System.out.println(file.getAbsolutePath());
						return;
					}
				}else{
					File[] files = file.listFiles();
					if(files ==null || files.length==0){
						return;
					}
					for(File subFile: files){
						scanFile(subFile);
					}
				}
				
			}

			@Override
			public void run() {
				scanFile(rootFile);
				
			}
		}
		
		long start = System.currentTimeMillis();
		String matcher = "sys";
		File file = new File("C:\\");
		ExecutorService service = Executors.newFixedThreadPool(20); 
		File[] files = file.listFiles();
		for(File sfile : files){
			Runnable t = new Scanner(sfile);
			service.submit(t);
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000);
		if(service.isTerminated())
		service.shutdown();
	}

}
