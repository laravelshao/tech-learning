package com.laravelshao.learning.spring.aop;

/**
 * Created by shaoqinghua on 2017/12/18.
 */
public class CustomerServiceImpl implements ICustomerService {

	@Override
	public void save() {
		System.out.println("保存商品成功.....");
	}

	@Override
	public int find() {
		System.out.println("查询商品成功.....");
		return 100;
	}

}
