package com.laravelshao.learning.spring.aop;

/**
 * Created by shaoqinghua on 2017/12/18.
 */
public class ProductService {
	public void save() {
//		int a=1/0;
		System.out.println("商品保存成功.....");
	}
	
	public int find() {
		System.out.println("商品查询成功.....");
		return 23;
	}
}
