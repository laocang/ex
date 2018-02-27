package demo.service;

import java.util.List;
import java.util.Map;

import demo.dao.base.IRepository;

public interface ITestService{
	
	public List<Map<String,Object>> test();
}
