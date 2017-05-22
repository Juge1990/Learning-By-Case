/**
 * 
 */
package com.mrz.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 *
 */
public class IterableDemo  {


	public static void main(String[] agrs){
		IterableDemo demo = new IterableDemo();
		demo.Test();
		demo.TestForEach();
		demo.TestForEachWithLambda();
	}
	public void Test(){
		Log log = LogFactory.getLog(IterableDemo.class);
		List<String> list2 = new ArrayList<>();
		log.info(list2.iterator() == null);
		log.info(list2.iterator().hasNext());
		list2.addAll(new HashSet<String>());
		List<String> list = new ArrayList<>();
		list = Arrays.asList("A", "B", "C", "D");
		Iterator<String> iterator = list.iterator();
		Iterator<String> iterator2 = list.iterator();
		while(iterator.hasNext()){
			log.info(iterator.next());
			log.info(iterator2.hasNext());
		}
		log.info(iterator2.next());
	}
	public void TestForEach(){
		Log log = LogFactory.getLog(IterableDemo.class);
		List<String> list = Arrays.asList("A", "B", "C", "D");
		list.forEach(new Consumer<String>(){
			HashMap map;
			@Override
			public void accept(String t) {
				log.info(t.toLowerCase());
				
			}
		});
	}

	public void TestForEachWithLambda(){
		Log log = LogFactory.getLog(IterableDemo.class);
		List<String> list = Arrays.asList("A", "B", "C", "D");
		list.forEach(str-> log.info(str.toLowerCase()));
	}
	
	
}
