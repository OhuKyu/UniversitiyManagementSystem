package application;

import java.util.Date;

public class SubjectData {
	private int id;
	private String subjectCode;
	private String subject;
	private String course;
	private Date dateInsert;
	private Date dateUpdate;
	private Date dateDelete;
	private String status;

	public SubjectData(int id, String subjectCode, String subject, String course, Date dateInsert, Date dateUpdate,
			Date dateDelete, String status) {
		this.id = id;
		this.subjectCode = subjectCode;
		this.subject = subject;
		this.course = course;
		this.dateInsert = dateInsert;
		this.dateUpdate = dateUpdate;
		this.dateDelete = dateDelete;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public String getSubject() {
		return subject;
	}

	public String getCourse() {
		return course;
	}

	public Date getDateInsert() {
		return dateInsert;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public Date getDateDelete() {
		return dateDelete;
	}

	public String getStatus() {
		return status;
	}

}
