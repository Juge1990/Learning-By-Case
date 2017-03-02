package com.mrz.java8;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mrz.collections.Student;
/*
 * http://www.jb51.net/article/48304.htm
 */
public class Java8NewFutureDemo {
	static Log log = LogFactory.getLog(Java8NewFutureDemo.class);

	public static void main(String[] agrs) {
		long start = System.currentTimeMillis();
		// printName(new File("C:/"), "sys");
		log.info(System.currentTimeMillis() - start);
		// testSort();
		// testMethedInterface();
//		testPredicate();
		testOptional();
	}

	private static void testPredicate() {
		Predicate<String> notNull = str -> str != null && str.length() > 0;
		Predicate<String> isUpperCase = str -> {
			char[] chars = str.toCharArray();
			boolean result = false;
			for (char c : chars) {
				if (c >= 'A' && c <= 'Z') {
					result = true;
				} else {
					result = false;
				}
			}
			return result;
		};
		String test1 = "test1";
		String test2 = "";
		String test3 = null;
		log.info(notNull.test(test1));
		log.info(notNull.test(test2));
		log.info(notNull.test(test3));
		// predicate1.and(predicate2) 返回的是另一个Predicate 方法引用，
		// 其结果是predicate1.test(t) && predicate2.test(t)
		log.info(notNull.and(isUpperCase).test("ABC"));
		log.info(notNull.and(isUpperCase).test("ABc"));
		log.info(notNull.and(isUpperCase).test(null));
		// 抛异常,应该先notNull check
		log.info(isUpperCase.and(notNull).test(null));

	}

	static void testFunction() {
	}
	static void testOptional(){
		String strNull0 = null;
		System.out.println( strNull0.contains( "something" ) );

	}
	static void testSupplier() {
		// Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
		Supplier<Student> personSupplier = Student::new;
		personSupplier.get();
	}

	static void testSort() {
		List<String> list = Arrays.asList("test4", "test1", "test2", "test3");
		list.sort((left, right) -> left.compareTo(right));
		list.stream().forEach(s -> log.info(s));
	}

	static void printName(File file, String matcher) {
		if (file == null) {
			return;
		}
		if (file.isFile()) {
			if (file.getName().contains(matcher)) {
				log.info(Thread.currentThread().getName());
				log.info(file.getAbsolutePath());
			}
		} else {
			File[] files = file.listFiles();
			if (files != null) {
				List<File> fs = Arrays.asList(files);
				fs.stream().parallel().forEach(f -> printName(f, matcher));
			}
		}
	}

	static public void testMethedInterface() {
		Formula sq = (c) -> c * 2;
		log.info(sq.calculate(10));
		log.info(sq.sqrt(10));
	}

	interface Formula {
		double calculate(int a);

		default double sqrt(int a) {
			return Math.sqrt(a);
		}
	}
}
