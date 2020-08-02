package com.wipro.topgear.hibernate.assignment.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "Empcode")
	private Long empCode;

	@Column(name = "EmpName", nullable = false, length = 20)
	private String empName;

	@Column(name = "EmpDesignation", nullable = false, length = 4)
	private String empDesignation;

	@Column(name = "EmpDOB", nullable = false)
	private Date empDOB;

	@Column(name = "EmpDOJ", nullable = false)
	private Date empDOJ;

	@Column(name = "EmpAge", nullable = false, length = 2)
	private Integer empAge;

	@Column(name = "EmpSalary", nullable = false, precision = 8, scale = 2, columnDefinition = "DECIMAL(8,2)")
	private Double empSalary;

	public Long getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public Date getEmpDOB() {
		return empDOB;
	}

	public void setEmpDOB(Date empDOB) {
		this.empDOB = empDOB;
	}

	public Date getEmpDOJ() {
		return empDOJ;
	}

	public void setEmpDOJ(Date empDOJ) {
		this.empDOJ = empDOJ;
	}

	public Integer getEmpAge() {
		return empAge;
	}

	public void setEmpAge(Integer empAge) {
		this.empAge = empAge;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	@Override
	public String toString() {
		return "Employee [empCode=" + empCode + ", empName=" + empName + ", empDesignation=" + empDesignation
				+ ", empDOB=" + empDOB + ", empDOJ=" + empDOJ + ", empAge=" + empAge + ", empSalary=" + empSalary + "]";
	}

}
