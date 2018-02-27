package demo.dao.impl;

import java.util.List;
import java.util.Map;

import lc.dinner.dao.IDinnerDao;
import lc.dinner.model.userModel;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;


import demo.common.utils.DataSourceUtils;
import demo.dao.ITestDao;
import demo.domain.base.MapperFactory;

@Repository
public class testDao implements ITestDao{

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
    
	public  List<userModel> getUser(){
		ITestDao testDao = null;
		List<userModel> users=null;
		try {
		    	
		    testDao = MapperFactory.createMapper(ITestDao.class,  DataSourceUtils.getDataSource("ex"));
		    users = testDao.getUser();
		 }catch(Exception e){
		    e.printStackTrace();
		 }
		 return users;
	}
}
