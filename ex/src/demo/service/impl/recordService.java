package demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.IRecordDao;
import demo.dao.ITestDao;
import demo.domain.model.recordModel;
import demo.domain.model.userModel;
import demo.service.IRecordService;
import demo.service.ITestService;

@Service
public class recordService implements IRecordService{

	@Autowired
	private IRecordDao recordDao;
	

	public userModel login(String userName) {
		return recordDao.login(userName);
	}
	
	public int updateLoginTime(String id){
		return recordDao.updateLoginTime(id);
	}

	public int addRecord(String userId, String title, String content,
			String date) {
		recordModel record = new recordModel();
		record.setDate(date);
		record.setSubject(title);
		record.setUserid(userId);
		record.setContent(content);
		int result = recordDao.addRecord(record);
		return result;
	}

	public List<recordModel> getRecords(String userId) {
		return recordDao.getRecords(userId);
	}
}
