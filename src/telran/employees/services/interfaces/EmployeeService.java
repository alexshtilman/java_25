package telran.employees.services.interfaces;

import telran.employees.dto.Employee;
import telran.employees.dto.ReturnCodes;

public interface EmployeeService {
	ReturnCodes addEmployee(Employee emp1);

	ReturnCodes removeEmployee(long id);

	Employee updateEmployee(long id, Employee newEmployee);

	Employee getEmployee(long id);

	Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo);

	Iterable<Employee> getEmployeesByDepartment(String department);

	Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo);
}
