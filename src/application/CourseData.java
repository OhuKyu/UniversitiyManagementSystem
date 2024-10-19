package application;

import java.util.Date;

public class CourseData {
	private int id;
	private String course;
	private String department;
	private Double price;
	private Date dateInsert;
	private Date dateUpdate;
	private Date dateDelete;
	private String status;

	public CourseData(int id, String course, String department, Double price, Date dateInsert, Date dateUpdate,
			Date dateDelete, String status) {
		this.id = id;
		this.course = course;
		this.department = department;
		this.price = price;
		this.dateInsert = dateInsert;
		this.dateUpdate = dateUpdate;
		this.dateDelete = dateDelete;
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public String getCourse() {
		return course;
	}

	public String getDepartment() {
		return department;
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
