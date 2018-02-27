package demo.dao;

import java.util.List;
import java.util.Map;

import demo.dao.base.IRepository;
import demo.domain.model.recordModel;
import demo.domain.model.userModel;

public interface IRecordDao extends IRepository{

	
	public userModel login(String userName);
	
	public int updateLoginTime(String id);
	
	public int addRecord(recordModel record);
	
	public List<recordModel> getRecords(String userId);
}
