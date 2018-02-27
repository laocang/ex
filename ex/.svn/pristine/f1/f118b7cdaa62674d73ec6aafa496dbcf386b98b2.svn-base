package lc.watchshop.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import lc.dinner.dao.IDinnerDao;
import lc.dinner.model.foodCategoryModel;
import lc.watchshop.dao.IOrderDao;

public class orderDao implements IOrderDao{

	public String addOrder(Map<String, String> map) {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		 	IOrderDao  dao = context.getBean(IOrderDao.class);
		 	String result = dao.addOrder(map).toString();
	        return result;
	}

}
