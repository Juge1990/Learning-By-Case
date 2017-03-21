package com.z.thread.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Demo6MutipleThreadWithForkJoinPool {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();

		ForkJoinPool pool = new ForkJoinPool();
		FileScanTask task = new FileScanTask(new File("C:/"));
		pool.submit(task);
		task.join();
		long end = System.currentTimeMillis();

		System.out.println((end-start)/1000);

	}

}

class FileScanTask extends RecursiveAction {
	File rootFile;

	public FileScanTask(File file) {
		super();
		this.rootFile = file;
	}

	@Override
	protected void compute() {
		File[] files = rootFile.listFiles();
		Collection<FileScanTask> tasks = new ArrayList<>();
		if (files == null) {
		} else {
			for (File file : files) {
				if (file.isFile()) {
					if (file.getName().contains("sys")) {
						System.out.println(file.getAbsolutePath());
					}
				} else {
					FileScanTask newTask = new FileScanTask(file);
					tasks.add(newTask);
				}
			}
			invokeAll(tasks);
		}
	}

}