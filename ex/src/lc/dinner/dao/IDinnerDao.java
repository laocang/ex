package lc.dinner.dao;

import java.util.List;

import lc.dinner.model.foodCategoryModel;
import lc.dinner.model.userModel;

public interface IDinnerDao {
	
	public int getCount();
	
	public List<foodCategoryModel>  getFood();
	
	public List<userModel> getUser();
}
