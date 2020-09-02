package telran.employees.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.services.impl.EmployeeServiceImpl;
import telran.emplyees.dto.Employee;
import telran.emplyees.dto.ReturnCodes;

class EmployeesServiceTest {
	EmployeeServiceImpl employees = new EmployeeServiceImpl();

	public int getRandomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	public static int size(Iterable data) {

		if (data instanceof Collection) {
			return ((Collection<?>) data).size();
		}
		int counter = 0;
		for (Object i : data) {
			counter++;
		}
		return counter;
	}

	@BeforeEach
	void setUp() throws Exception {
		// there is no requirement to id, it could be negative
		long idList[] = { 1, 0, 2, 4, 3 };
		// there is no requirement to name, it could contain any letters and syblos
		String nameList[] = { "Alex Shtilman", "Mark Akimenko", "Olesia Petrenko", "Alla Klumel Shahrit",
				"Zhanna Burd" };
		// birt day can be lower than 1890 person can be Jeremy Beck or Jesus crist
		LocalDate birtdayList[] = { LocalDate.of(1987, 2, 20), LocalDate.of(1200, 1, 1), LocalDate.of(1984, 1, 5),
				LocalDate.of(1984, 1, 3), LocalDate.of(1983, 5, 30) };
		String departmentList[] = { "Development", "Operation", "Managment" };
		int salaryList[] = { 12000, 15000, 8000, 25000 };
		for (long id : idList) {
			Employee person = new Employee((int) id, nameList[(int) id], birtdayList[(int) id],
					departmentList[getRandomInt(0, 3)], salaryList[getRandomInt(0, 4)]);
			employees.addEmployee(person);
		}
	}

	@Test
	void testAddEmployee() {
		Employee newPerson = new Employee(5, "Boris", LocalDate.of(1999, 1, 1), "Development", 8000);
		assertEquals(ReturnCodes.OK, employees.addEmployee(newPerson));
		assertEquals(ReturnCodes.EMPLOYEE_ALREADY_EXISTS, employees.addEmployee(newPerson));
	}

	@Test
	void testRemoveEmployee() {
		assertEquals(ReturnCodes.OK, employees.removeEmployee(0));
		assertEquals(ReturnCodes.OK, employees.removeEmployee(4));
		assertEquals(ReturnCodes.EMPLOYEE_NOT_FOUND, employees.removeEmployee(0));
		assertEquals(ReturnCodes.EMPLOYEE_NOT_FOUND, employees.removeEmployee(5));
	}

	@Test
	void testUpdateEmployee() {
		Employee updatePerson = new Employee(0, "Alex Shtilman", LocalDate.of(1987, 2, 20), "New department", 55000);
		employees.updateEmployee(0, updatePerson);
		assertEquals(55000, employees.getEmployee(0).getSalary());
		Iterable<Employee> newDepartment = employees.getEmployeesByDepartment("New department");
		assertEquals(1, size(newDepartment));
		Employee incognito = new Employee(99, "Incognito", LocalDate.of(1987, 2, 20), "NotLisned", 550000);
		employees.updateEmployee(99, incognito);
		assertEquals(null, employees.getEmployee(99));
	}

	@Test
	void testGetEmployee() {
		Employee developer = employees.getEmployee(0);
		Employee manager = employees.getEmployee(4);
		Employee stranger = employees.getEmployee(42);
		assertEquals(LocalDate.of(1987, 2, 20), developer.getBirtDay());
		assertEquals("Zhanna Burd", manager.getName());
		assertEquals(null, stranger);
	}

	@Test
	void testGetEmployeesByAge() {
		Iterable<Employee> managers = employees.getEmployeesByAge(36, 36);
		assertEquals(2, size(managers));
		for (Employee empl : managers) {
			assertEquals(36, LocalDate.now().getYear() - empl.getBirtDay().getYear());
		}
		Iterable<Employee> retirees = employees.getEmployeesByAge(67, 110);
		assertEquals(new ArrayList<>(0), retirees);
	}

	@Test
	void testGetEmployeesByDepartment() {
		Iterable<Employee> managers = employees.getEmployeesByDepartment("Managment");
		for (Employee empl : managers) {
			assertEquals("Managment", empl.getDepartment());
		}
		Iterable<Employee> notInList = employees.getEmployeesByDepartment("not in list");
		assertEquals(new ArrayList<>(0), notInList);
	}

	@Test
	void testGetEmployeesBySalary() {

		Iterable<Employee> notInList = employees.getEmployeesBySalary(7000, 7500);
		assertEquals(new ArrayList<>(0), notInList);
		Iterable<Employee> all = employees.getEmployeesBySalary(8000, 25000);

		for (Employee empl : all) {

			assertTrue(empl.getSalary() >= 8000 && empl.getSalary() <= 25000);
		}

	}

}
