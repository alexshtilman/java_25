package telran.employees.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

import telran.employees.dto.Employee;
import telran.employees.dto.ReturnCodes;
import telran.employees.services.interfaces.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	HashMap<Long, Employee> employees = new HashMap<>();
	TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
	TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
	HashMap<String, List<Employee>> employeesDepartment = new HashMap<>();

	@Override
	public ReturnCodes addEmployee(Employee empl) {

		Employee res = employees.putIfAbsent(empl.getId(), empl);
		if (res != null)
			return ReturnCodes.EMPLOYEE_ALREADY_EXISTS;
		addEmployeesSalary(empl);
		addEmployeesAge(empl);
		addEmployeesDepartment(empl);
		return ReturnCodes.OK;
	}

	@Override
	public ReturnCodes removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if (empl == null) {
			return ReturnCodes.EMPLOYEE_NOT_FOUND;
		}
		removeEmployeeAge(empl);
		removeEmployeeSalary(empl);
		removeEmployeeDepartment(empl);
		return ReturnCodes.OK;
	}

	@Override
	public Employee updateEmployee(long id, Employee newEmployee) {
		if (removeEmployee(id) == ReturnCodes.OK) {
			addEmployee(newEmployee);
			return newEmployee;
		}
		return null;
	}

	@Override
	public Employee getEmployee(long id) {
		return employees.get(id);
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		NavigableMap<Integer, List<Employee>> employeesSubMap = employeesAge.subMap(getBirthYear(ageTo), true,
				getBirthYear(ageFrom), true);
		return toCollectionEmployees(employeesSubMap.values());
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) {
		return employeesDepartment.getOrDefault(department, new LinkedList<>());
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		SortedMap<Integer, List<Employee>> employeesSubMap = employeesSalary.subMap(salaryFrom, salaryTo);
		return toCollectionEmployees(employeesSubMap.values());
	}

	private void addEmployeesDepartment(Employee empl) {
		String department = empl.getDepartment();
		List<Employee> employeesList = employeesDepartment.computeIfAbsent(department, d -> new ArrayList<>());
		employeesList.add(empl);
	}

	private void addEmployeesAge(Employee empl) {
		Integer birtYear = empl.getBirtDay().getYear();
		List<Employee> employeesList = employeesAge.computeIfAbsent(birtYear, a -> new ArrayList<>());
		employeesList.add(empl);
	}

	private void addEmployeesSalary(Employee empl) {
		Integer salary = empl.getSalary();
		List<Employee> employeesList = employeesSalary.computeIfAbsent(salary, s -> new ArrayList<>());
		employeesList.add(empl);
	}

	private void removeEmployeeDepartment(Employee empl) {
		String department = empl.getDepartment();
		List<Employee> employeesList = employeesDepartment.get(department);
		if (employeesList.size() > 1) {
			employeesList.remove(empl);
		} else {
			employeesDepartment.remove(department);
		}
	}

	private void removeEmployeeSalary(Employee empl) {
		Integer salary = empl.getSalary();
		List<Employee> employeesList = employeesSalary.get(salary);
		if (employeesList.size() > 1) {
			employeesList.remove(empl);
		} else {
			employeesSalary.remove(salary);
		}
	}

	private void removeEmployeeAge(Employee empl) {
		Integer birtYear = empl.getBirtDay().getYear();
		List<Employee> employeesList = employeesAge.get(birtYear);
		if (employeesList.size() > 1) {
			employeesList.remove(empl);
		} else {
			employeesAge.remove(birtYear);
		}
	}

	private Iterable<Employee> toCollectionEmployees(Collection<List<Employee>> values) {
		ArrayList<Employee> emplValues = new ArrayList<Employee>();
		values.forEach(emplValues::addAll);
		return emplValues;
	}

	private Integer getBirthYear(int age) {
		return LocalDate.now().getYear() - age;
	}

	public long size() {
		return employees.size();
	}
}
