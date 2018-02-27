package demo.domain.model;

import java.util.List;

public class recordModel {

	private String id;
	private String createtime;
	private String updatetime;
	private String subject;
	private String date;
	private String userid;
	private String content;

	private List<record_MxModel> record_mx;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<record_MxModel> getRecord_mx() {
		return record_mx;
	}

	public void setRecord_mx(List<record_MxModel> record_mx) {
		this.record_mx = record_mx;
	}

}
