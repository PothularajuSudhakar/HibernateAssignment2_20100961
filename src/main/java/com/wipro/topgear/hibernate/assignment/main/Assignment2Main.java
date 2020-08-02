package com.wipro.topgear.hibernate.assignment.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.topgear.hibernate.assignment.domain.Employee;
import com.wipro.topgear.hibernate.assignment.repository.EmployeeRepository;
import com.wipro.topgear.hibernate.assignment.repository.IEmployeeRepository;

public class Assignment2Main {

	public static void main(String[] args) throws ParseException {

		IEmployeeRepository empRepository = new EmployeeRepository();

		String sampleJoiningDate = "22/07/2019";
		Date joiningDate = new SimpleDateFormat("dd/MM/yyyy").parse(sampleJoiningDate);

		String dob = "22/07/1988";
		Date dobDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob);

		Employee employee = new Employee();
		employee.setEmpName("Sudhakar");
		employee.setEmpDesignation("Developer");
		employee.setEmpDOB(dobDate);
		employee.setEmpDOJ(joiningDate);
		employee.setEmpAge(31);
		employee.setEmpSalary(70000d);
		empRepository.save(employee);

		List<Employee> employeeList = new ArrayList<Employee>();

		int count = 50;

		Employee employeeTemp = null;
		for (int i = count; i > 0; i--) {
			employeeTemp = new Employee();
			employeeTemp.setEmpName("Sudhakar" + i);
			employeeTemp.setEmpDesignation("Developer");
			employeeTemp.setEmpDOB(dobDate);
			employeeTemp.setEmpDOJ(joiningDate);
			employeeTemp.setEmpAge(31);
			employeeTemp.setEmpSalary(70000d);
			employeeList.add(employeeTemp);
		}
		empRepository.saveEmployeeList(employeeList);

		Employee employee1 = new Employee();
		employee1.setEmpName("Raja");
		employee1.setEmpDesignation("QA");
		employee1.setEmpDOB(dobDate);
		employee1.setEmpDOJ(joiningDate);
		employee1.setEmpAge(31);
		employee1.setEmpSalary(40000d);
		empRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setEmpName("Sanjeev");
		employee2.setEmpDesignation("CRS");
		employee2.setEmpDOB(dobDate);
		employee2.setEmpDOJ(joiningDate);
		employee2.setEmpAge(31);
		employee2.setEmpSalary(5000d);
		empRepository.save(employee2);

		// Retrieve & display the employee objects whose salary is in between 10000 and
		// 40000
		List<Employee> empList = empRepository.getAllEmployeesBetweenGivenSalary(10000d, 40000d);
		empList.forEach(emp -> System.out.println(emp.toString()));

		// Retrieve & display the employee objects whose name is ending with letter “r /
		// R”
		List<Employee> empListForGivenLetter = empRepository.getAllEmployeesEndingWithGivenString("r");
		empListForGivenLetter.forEach(emp -> System.out.println(emp.toString()));

		// Retrieve & display the last 10 employee objects from the table
		List<Employee> last10EmpList = empRepository.getLast10Employees();
		last10EmpList.forEach(emp -> System.out.println(emp.toString()));

		// Display all the employee objects name and salary by retrieving them using
		// Scalar query.
		List<Object[]> nameAndSalaryOfEmployees = empRepository.getNameAndSalaryForEmployees();
		nameAndSalaryOfEmployees.forEach(emp -> System.out.println("Name: " + emp[0] + " Salary: " + emp[1]));

		// Display all the employee objects using Entity query
		List<Employee> allEmployeesList = empRepository.getAllEmployees();
		allEmployeesList.forEach(emp -> System.out.println(emp.toString()));

	}

}
