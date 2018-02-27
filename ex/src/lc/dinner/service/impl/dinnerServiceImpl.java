package lc.dinner.service.impl;

import java.util.List;

import lc.dinner.dao.IDinnerDao;
import lc.dinner.model.foodCategoryModel;
import lc.dinner.service.IDinnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dinnerServiceImpl")
public class dinnerServiceImpl implements IDinnerService{

	@Autowired
	private IDinnerDao dinnerDao;
	
	public int getCount() {
		return dinnerDao.getCount();
	}

	public List<foodCategoryModel> getFood() {
		return dinnerDao.getFood();
	}
	
}
