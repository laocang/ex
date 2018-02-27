package demo.service;

import java.util.List;
import java.util.Map;

import demo.dao.base.IRepository;
import demo.domain.model.temperatureModel;

public interface ITemperatureService{
	
	public int addTemperature(temperatureModel tem);
}
