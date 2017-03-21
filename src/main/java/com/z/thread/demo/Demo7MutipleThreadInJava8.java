package com.z.thread.demo;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Demo7MutipleThreadInJava8 {

	public Demo7MutipleThreadInJava8() {
		
	}
	
	public static void main(String[]agrs){
		long start = System.currentTimeMillis();

		Demo7MutipleThreadInJava8 demo = new Demo7MutipleThreadInJava8();
		demo.scan();
		long end = System.currentTimeMillis();

		System.out.println((end-start)/1000);
	}
	
	public void scan(){
		File rootFile = new File("C:/");
		File[] files  = rootFile.listFiles();
		List<File> fs = Arrays.asList(files);
		fs.stream().parallel().forEach(e -> handle(e));
	}
	
	public void handle(File file){
		if(file.isFile()){
			if(file.getName().contains("sys")){
				System.out.println(file.getAbsolutePath());
			}
		}else{
			File[] files = file.listFiles();
			if(files!=null){
				for(File f: files){
					handle(f);
				}
			}
		}
	}
}
