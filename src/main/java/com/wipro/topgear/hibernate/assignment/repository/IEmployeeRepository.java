package com.wipro.topgear.hibernate.assignment.repository;

import java.util.List;

import com.wipro.topgear.hibernate.assignment.domain.Employee;

public interface IEmployeeRepository {

	Integer save(Employee employee);

	void update(Employee employee);

	void delete(int id);

	Employee getEmployeeById(int id);

	// g. Display all the employee objects using Entity query.
	List<Employee> getAllEmployees();

	List<Employee> getAllEmployeesBetweenGivenSalary(Double fromSalary, Double toSalary);

	List<Employee> getAllEmployeesEndingWithGivenString(String nameEndingLetter);

	List<Employee> getLast10Employees();

	List<Object[]> getNameAndSalaryForEmployees();

	void saveEmployeeList(List<Employee> employeeList);

}
