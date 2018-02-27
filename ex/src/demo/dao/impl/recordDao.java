package demo.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import demo.common.utils.DataSourceUtils;
import demo.dao.IRecordDao;
import demo.domain.base.MapperFactory;
import demo.domain.model.recordModel;
import demo.domain.model.userModel;

@Repository
public class recordDao implements IRecordDao{



	public int deleteEntities(String[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countEntities(Map<String, Object> parms) {
		// TODO Auto-generated method stub
		return 0;
	}

	public userModel login(String userName) {
			IRecordDao recordDao = null;
			userModel users=null;
			try {
				recordDao = MapperFactory.createMapper(IRecordDao.class,  DataSourceUtils.getDataSource("ex"));
			    users = recordDao.login(userName);
			 }catch(Exception e){
			    e.printStackTrace();
			 }
			 return users;
	}

	public int updateLoginTime(String id) {
		int result = 0; 
		IRecordDao recordDao = null;
        try {
        	SqlSession sqlSession = MapperFactory.getSqlSession(DataSourceUtils.getDataSource("ex"));
        	recordDao = sqlSession.getMapper(IRecordDao.class);
            result = recordDao.updateLoginTime(id);
            sqlSession.commit();
	        sqlSession.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
        return result;
	}

	public int addRecord(recordModel record) {
		int result = 0; 
		IRecordDao recordDao = null;
        try {
        	SqlSession sqlSession = MapperFactory.getSqlSession(DataSourceUtils.getDataSource("ex"));
        	recordDao = sqlSession.getMapper(IRecordDao.class);
            result = recordDao.addRecord(record);
            sqlSession.commit();
	        sqlSession.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
        return result;
	}

	public List<recordModel> getRecords(String userId) {
		IRecordDao recordDao = null;
		List<recordModel> records=null;
		try {
			recordDao = MapperFactory.createMapper(IRecordDao.class,  DataSourceUtils.getDataSource("ex"));
			records = recordDao.getRecords(userId);
		 }catch(Exception e){
		    e.printStackTrace();
		 }
		 return records;
	}
}
