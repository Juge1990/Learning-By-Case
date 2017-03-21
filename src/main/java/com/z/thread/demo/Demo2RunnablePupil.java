package com.z.thread.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Demo2RunnablePupil extends Student implements Runnable{
	static Log log = LogFactory.getLog(Demo2RunnablePupil.class);

	public Demo2RunnablePupil() {
		
	}
	@Override
	public void run() {
		try {
		for(int i=1;i<=5;i++){
			log.info(Thread.currentThread().getName() +" i can 1 fight "+i);
				Thread.sleep(1000);
			} 
		log.warn(Thread.currentThread().getName() +"Sheet, save me");
		Thread.sleep(1000);
		log.error(Thread.currentThread().getName() +"sheet, I am die");
		}catch (InterruptedException e) {
			log.error(e.getStackTrace(), e);
		}
	}
	
	public static void main(String agrs[]){
		log.info(Thread.currentThread().getName() +"Who Play LOL?");
		Demo2RunnablePupil pupil = new Demo2RunnablePupil();
		Thread pupilThread1 = new Thread(pupil);
		pupilThread1.setName("小学生");
		pupilThread1.start();
		try {
			for(int i=1;i<=5;i++){
				log.info(Thread.currentThread().getName() +" double kill ");
					Thread.sleep(600);
				} 
			}catch (InterruptedException e) {
				log.error(e.getStackTrace(), e);
			}
	}
}
