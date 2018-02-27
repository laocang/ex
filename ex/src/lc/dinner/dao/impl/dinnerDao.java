package lc.dinner.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import lc.dinner.dao.IDinnerDao;
import lc.dinner.model.foodCategoryModel;
import lc.dinner.model.userModel;

@Repository
public class dinnerDao implements IDinnerDao{
	
	public int getCount() {
		 	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		 	IDinnerDao  dao = context.getBean(IDinnerDao.class);
	        int iCount = dao.getCount();
	        return iCount;
	}

	public List<foodCategoryModel> getFood() {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		 	IDinnerDao  dao = context.getBean(IDinnerDao.class);
		 	List<foodCategoryModel> fcm = dao.getFood();
	        return fcm;
	}

	public List<userModel> getUser() {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		 IDinnerDao  dao = context.getBean(IDinnerDao.class);
		 List<userModel> user = dao.getUser();
		 return user;
	}
	
}
