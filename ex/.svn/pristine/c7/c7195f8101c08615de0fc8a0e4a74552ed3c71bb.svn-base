package demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.ITestDao;
import demo.service.ITestService;

@Service
public class testService implements ITestService{

	@Autowired
	private ITestDao testDao;
	
	public List<Map<String, Object>> test() {
		return testDao.test();
	}

}
