package telran.employees.perfomance;

import java.util.ArrayList;

import telran.emplyees.dto.Employee;
import telran.emplyees.dto.GeneratorMode;
import telran.emplyees.services.impl.EmployeeServiceImpl;
import telran.perforamnse.PerformanceTest;

public class RemoveEmployeeTest extends PerformanceTest {
	private EmployeeServiceImpl employees = new EmployeeServiceImpl();

	private ArrayList<Employee> randomEmpl = new ArrayList<Employee>();

	public RemoveEmployeeTest(String testName, int minSalary, int MaxSalary, int count, GeneratorMode mode, int nRuns) {
		super(testName, nRuns);
		EmployeeGenerator generator = new EmployeeGenerator(minSalary, MaxSalary, count, mode);
		randomEmpl.addAll(generator.generate());
		for (Employee empl : randomEmpl) {
			employees.addEmployee(empl);
		}
	}

	@Override
	protected void runTest() {
		for (Employee empl : randomEmpl) {
			employees.removeEmployee(empl.getId());
		}
	}

}
