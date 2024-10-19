package application;

import java.sql.Date;

public class SalaryData {
	private int id;
	private String teacherID;
	private String name;
	private Double salaryPerDay;
	private int totalDays;
	private Double salaryPaid;
	private Date datePaid;

	public SalaryData(int id, String teacherID, String name, Double salaryPerDay, int totalDays,
			Double salaryPaid, Date datePaid) {
		this.id = id;
		this.teacherID = teacherID;
		this.name = name;
		this.salaryPerDay = salaryPerDay;
		this.totalDays = totalDays;
		this.salaryPaid = salaryPaid;
		this.datePaid = datePaid;
	}

	public int getId() {
		return id;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public String getName() {
		return name;
	}

	public Double getSalaryPerDay() {
		return salaryPerDay;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public Double getSalaryPaid() {
		return salaryPaid;
	}

	public Date getDatePaid() {
		return datePaid;
	}
}
