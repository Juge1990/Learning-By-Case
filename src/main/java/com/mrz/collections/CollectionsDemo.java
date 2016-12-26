package com.mrz.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsDemo {

	public static void main(String[] agrs) {
		CollectionsDemo demo = new CollectionsDemo();
		List<Student> stus = Arrays.asList(new Student(10, "xiaoming"),
				new Student(11, "zhangsan"), new Student(9, "lisi"));
		stus.sort((Student left, Student right) -> Integer.compare(
				left.getAge(), right.getAge()));
		for(int i=0;i<stus.size(); i++){
			System.out.println(stus.get(i).getAge());
		}
		
		demo.sort(stus);
		for(int i=0;i<stus.size(); i++){
			System.out.println(stus.get(i).getAge());
		}
	}

	public void sort(List<Student> list) {
		Collections.sort(list, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				if (o1.getAge() <= o2.getAge()) {
					return 1;
				} else {
					return -1;
				}
			}

		});
	}
}
