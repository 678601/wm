package com.example.demo.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamTest1 {
	
	private static final Logger log  = LoggerFactory.getLogger(StreamTest1.class);
	
	
	/*
	 * 1、由Collection子类（List，Set）来创建流：
	 * java8扩展了Collection接口，提供了stream（返回顺序流）和parallelStream（返回并行流）两个方法。并行流我们后面在详细讨论，
	 * 示例代码
	 */
	public void test(){
	    List<String> list = Arrays.asList("a","b","c");
	    Stream stram = list.stream();
	    Stream parallelSteam = list.parallelStream();
	}
	
	public void test2(){
	    String[] strArr = new String[]{"a","b","c"};
	    Stream stram = Arrays.stream(strArr);
	    //还有许多重载形式的方法，可以返回带类型的Stream，例如：
	    IntStream stram2 = Arrays.stream(new int []{1,2,3,4,5});
	}
	
//	@Test
	public void test3() {
	    Stream s = Stream.of("1","2",3,new String[]{"a", "b", "c"});
	}
	
//	@Test
	public void test4() {
	    //Stream.iterate方法第一个方法表示开始值得，第二个参数需要提供一个一元操作函数，我们用lambda表达式传递给它
	    Stream stream1 = Stream.iterate(0, (x) -> x + 2);
	    stream1.forEach(System.out::println); //输出的是0,2,4,6,8,10....将会一直循环下去，永远不停息
	    //Stream.generate需要一个供给型函数接口
	    Stream stream2 = Stream.generate(() -> 1);
	    stream2.forEach(System.out::println); //输出无数个1，将会一直循环下去，永远不停息
	}
	
//	@Test
	public void test5(){
	    //我们从0开始获取前50个偶数
	    Stream stream1 = Stream.iterate(0, (x) -> x + 2).limit(50);
	    stream1.forEach(System.out::println);//输出0，2，4，6，8。。。。98
	}
	
	
}
