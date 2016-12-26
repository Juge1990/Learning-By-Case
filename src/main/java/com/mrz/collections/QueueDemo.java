package com.mrz.collections;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QueueDemo {
	static Log log = LogFactory.getLog(QueueDemo.class);
	
	public static void main(String []agrs){
		QueueDemo demo = new QueueDemo();
		demo.testAddQueue();
		demo.testOfferQueue();
	}

	//add() will throw exception directly when queue is full
	public void testAddQueue(){
		Queue<String> queue = new LinkedBlockingQueue<String>(2);
		queue.add("test1");
		queue.add("test2");
		//add() will throw exception directly when queue is full
		queue.add("test3");
	}
	
	//Offer() will return false if add element fail, but won't throw exception
	public void testOfferQueue(){
		Queue<String> queue = new LinkedBlockingQueue<String>(2);
		boolean result1 = queue.offer("test1");
		boolean result2 = queue.offer("test2");
		boolean result3 = queue.offer("test3");
		log.info(result1);
		log.info(result2);
		log.info(result3);

	}
}
