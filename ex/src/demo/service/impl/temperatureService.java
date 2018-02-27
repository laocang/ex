package demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.ITemperatureDao;
import demo.dao.ITestDao;
import demo.domain.model.temperatureModel;
import demo.service.ITemperatureService;
import demo.service.ITestService;

@Service
public class temperatureService implements ITemperatureService{

	@Autowired
	private ITemperatureDao temperatureDao;
	
	public int addTemperature(temperatureModel temperatureModel) {
		return temperatureDao.addTemperature(temperatureModel);
	}

}
