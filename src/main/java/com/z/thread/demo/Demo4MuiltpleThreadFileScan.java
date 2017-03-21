package com.z.thread.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Demo4MuiltpleThreadFileScan {

	public Demo4MuiltpleThreadFileScan() {

	}
	
	public static void main(String agrs[]) throws InterruptedException{
		class Scanner extends Thread{
			File rootFile;
			public Scanner(File file){
				rootFile = file;
			}
			@Override
			public void run(){
				scanFile(rootFile);
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
		}
		long start = System.currentTimeMillis();
		String matcher = "sys";
		File file = new File("C:\\");
		File[] files = file.listFiles();
		List<Thread> threads = new ArrayList<>();
		for(File sfile : files){
			threads.add(new Scanner(sfile));
		}
		for(Thread t : threads){
			t.start();
		}
		for(Thread t: threads){
			t.join();
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000);
	}

}
