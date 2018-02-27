package demo.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;



import demo.common.utils.DataSourceUtils;
import demo.dao.ITemperatureDao;
import demo.dao.ITestDao;
import demo.domain.base.MapperFactory;
import demo.domain.model.temperatureModel;

@Repository
public class temperatureDao implements ITemperatureDao{

	public List<Map<String,Object>> test() {
		ITestDao testDao = null;
		List<Map<String,Object>> users=null;
		try {
		    	
		    testDao = MapperFactory.createMapper(ITestDao.class,  DataSourceUtils.getDataSource());
		    users = testDao.test();
		 }catch(Exception e){
		    e.printStackTrace();
		 }
		 return users;
	}
	
	public int addTemperature(temperatureModel temmodel) {
		ITemperatureDao temperatureDao = null;
		List<temperatureModel> tem=null;
		int result = 0;
		try {
			SqlSession sqlSession = MapperFactory.getSqlSession(DataSourceUtils.getDataSource("ex"));
			temperatureDao = sqlSession.getMapper(ITemperatureDao.class);
			result = temperatureDao.addTemperature(temmodel);
		    sqlSession.commit();
		    sqlSession.close();
		 }catch(Exception e){
		    e.printStackTrace();
		 }
		 return result;
	}

	
	public int deleteEntities(String[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countEntities(Map<String, Object> parms) {
		// TODO Auto-generated method stub
		return 0;
	}
	
/*	int result = 0; 
	IPdaCommentinfoDao commentinfoDao = null;
    try {
    	SqlSession sqlSession = MapperFactory.getSqlSession(DataSourceUtils.getDataSource());
    	commentinfoDao = sqlSession.getMapper(IPdaCommentinfoDao.class);
        result = commentinfoDao.removePdaCommentinfo(id);
        sqlSession.commit();
          sqlSession.close();
    }catch(Exception e){
    	e.printStackTrace();
    }
    return result;*/
    
  

}
