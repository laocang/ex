package demo.dao;

import java.util.List;
import java.util.Map;

import lc.dinner.model.userModel;

import demo.dao.base.IRepository;

public interface ITestDao extends IRepository{

	public List<Map<String,Object>> test();
	
	public List<userModel> getUser();
}
