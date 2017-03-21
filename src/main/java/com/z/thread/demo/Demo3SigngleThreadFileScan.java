package com.z.thread.demo;

import java.io.File;


public class Demo3SigngleThreadFileScan {


	public Demo3SigngleThreadFileScan() {
		
	}
	
	
	private void scan(File rootFile, String matcher){
		if(!rootFile.exists()){
			return;
		}
		if(rootFile.isFile()){
			if(rootFile.getName().contains(matcher)){
				System.out.print(rootFile.getAbsolutePath()+"\n");
			}
		}else{
			
			File[] files = rootFile.listFiles();
			if(files != null){
				for(File file: files){
					scan(file, matcher);
				}
			}
			
		}
	}
	
	public static void main(String []agrs){
		long start = System.currentTimeMillis();
		Demo3SigngleThreadFileScan demo =  new Demo3SigngleThreadFileScan();
		demo.scan(new File("C:/"), "sys");
		long end = System.currentTimeMillis();
		System.out.print("Cost "+ (end-start)/1000);
	}
}
