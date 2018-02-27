package demo.dao;

import java.util.List;
import java.util.Map;

import demo.dao.base.IRepository;
import demo.domain.model.temperatureModel;

public interface ITemperatureDao extends IRepository{

	public List<Map<String,Object>> test();
	
	public int addTemperature(temperatureModel tem);
}
