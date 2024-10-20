package application;

import java.sql.Date;

public class DataSubjectHandle {
	private Integer id;
	private String subjectCode;
	private String subject;
	private Date insertData;
	private String status;

	public DataSubjectHandle(Integer id, String subjectCode, String subject, Date insertData, String status) {
		this.id = id;
		this.subjectCode = subjectCode;
		this.subject = subject;
		this.insertData = insertData;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public String getSubject() {
		return subject;
	}

	public Date getInsertData() {
		return insertData;
	}

	public String getStatus() {
		return status;
	}
}
