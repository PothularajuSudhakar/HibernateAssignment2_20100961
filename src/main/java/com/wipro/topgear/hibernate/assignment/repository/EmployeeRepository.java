package com.wipro.topgear.hibernate.assignment.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.wipro.topgear.hibernate.assignment.domain.Employee;
import com.wipro.topgear.hibernate.assignment.util.HibernateUtils;

public class EmployeeRepository implements IEmployeeRepository {

	public Integer save(Employee employee) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = null;
		Integer id = null;
		try {
			session = factory.openSession();
			session.beginTransaction();

			id = (Integer) session.save(employee);

			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return id;
	}

	public void update(Employee employee) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();

			session.saveOrUpdate(employee);

			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}

	}

	public void delete(int id) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();

			Employee Employee = (Employee) session.get(Employee.class, id);
			session.delete(Employee);

			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}

	}

	public Employee getEmployeeById(int id) {

		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = null;
		Employee employee = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			employee = (Employee) session.get(Employee.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return employee;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = null;
		List<Employee> employeeList = null;
		try {
			session = factory.openSession();
			session.beginTransaction();

			String sql = "SELECT * FROM EMPLOYEE";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Employee.class);
			employeeList = query.list();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return employeeList;
	}

	public List<Employee> getAllEmployeesBetweenGivenSalary(Double fromSalary, Double toSalary) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = null;
		List<Employee> employeeList = null;
		try {
			session = factory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(
					"from Employee emp where emp.empSalary between :fromSalary and :toSalary order by emp.empSalary");
			query.setParameter("fromSalary", fromSalary);
			query.setParameter("toSalary", toSalary);
			employeeList = query.list();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return employeeList;
	}

	public List<Employee> getAllEmployeesEndingWithGivenString(String nameEndingLetter) {

		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = null;
		List<Employee> employeeList = null;
		try {
			session = factory.openSession();
			session.beginTransaction();

			Criteria cr = session.createCriteria(Employee.class);
			cr.add(Restrictions.like("empName", nameEndingLetter + "%"));
			employeeList = cr.list();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return employeeList;
	}

	public List<Employee> getLast10Employees() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = null;
		List<Employee> employeeList = null;
		try {
			session = factory.openSession();
			session.beginTransaction();

			Criteria cr = session.createCriteria(Employee.class);
			cr.addOrder(Order.asc("empCode"));
			cr.setFirstResult(0);
			cr.setMaxResults(10);
			employeeList = cr.list();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return employeeList;
	}

	public List<Object[]> getNameAndSalaryForEmployees() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = null;
		List<Object[]> employeeList = null;
		try {
			session = factory.openSession();
			session.beginTransaction();

			SQLQuery query = session.createSQLQuery("select EmpName,EmpSalary from EMPLOYEE");
			query.addScalar("EmpName", StandardBasicTypes.STRING);
			query.addScalar("EmpSalary", StandardBasicTypes.DOUBLE);
			employeeList = query.list();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return employeeList;
	}

	public void saveEmployeeList(List<Employee> employeeList) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = null;

		try {
			int i = 0;
			session = factory.openSession();
			for (Employee employee : employeeList) {
				session.save(employee);
				if (i % 50 == 0) {
					session.flush();
					session.clear();
				}
				i++;
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
		}
		session.close();
	}

}
