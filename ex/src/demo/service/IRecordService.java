package demo.service;

import java.util.List;
import java.util.Map;

import demo.dao.base.IRepository;
import demo.domain.model.recordModel;
import demo.domain.model.userModel;

public interface IRecordService{
	
	
	public userModel login(String userName);
	
	public int updateLoginTime(String id);

	public int addRecord(String userId,String title,String content,String date);
	
	public List<recordModel> getRecords(String userId);
}
