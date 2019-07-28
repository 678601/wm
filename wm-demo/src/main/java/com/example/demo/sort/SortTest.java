package com.example.demo.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description:  排序整理
 * @date:   2019年5月30日 下午4:42:12   
 * @version V1.0
 */
public class SortTest {
	
	private static final Logger log = LoggerFactory.getLogger(SortTest.class);
	/** 
	   * 排序--内部排序（只用内存 ）--插入排序--简单插入排序
	 * 						 			       希尔排序
	 * 						             选择排序--简单选择排序
	 *  								       堆排序
	 * 							   交换排序--冒泡排序
	 * 									       快速排序
	 * 	         				   归并排序
	 * 							   基数排序				
	 * 	        外部排序（内存外存都用）
	 */
	/*
	 * 1， 直接插入排序 （1）基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排
	 * 好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数 也是排好顺序的。如此反复循环，直到全部排好顺序。
	 */
	//@Test
	public void insertSort() {
		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,
				25, 53, 51 };
		int temp = 0;
		for (int i = 1; i < a.length; i++) {
			int j = i - 1;
			temp = a[i];
			for (; j >= 0 && temp < a[j]; j--) {
				a[j + 1] = a[j]; // 将大于temp的值整体后移一个单位
			}
			a[j + 1] = temp;
		}
		arrayToString(a);
	}
	/**
	 * 
	 * @Description: 数组显示工具类
	 * @param a
	 * @date:   2019年5月30日 下午4:52:13 
	 * @throws
	 */
	void arrayToString(int a[]){
		StringBuilder builder = new StringBuilder();
		String result="";
		for (int i = 0; i < a.length; i++) {
			builder.append(a[i]).append(",");
		}
		if (builder.length()>1) {
			result=builder.substring(0, builder.length());
		}
		log.info("result={}",result);
	}
	@Test
    public void testString() {
    	long startTime = System.currentTimeMillis();
    	for (int i = 0; i < 50000; i++) {
			String dd = i+"";
		}
    	long endTime = System.currentTimeMillis();
    	log.info("use = {}",endTime-startTime);
    	long startTime1 = System.currentTimeMillis();
    	for (int i = 0; i < 50000; i++) {
			String dd = String.valueOf(i);
		}
    	long endTime1 = System.currentTimeMillis();
    	log.info("use = {}",endTime1-startTime1);
    }
}
