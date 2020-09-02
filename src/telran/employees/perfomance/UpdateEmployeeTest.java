
package telran.employees.perfomance;

import java.util.ArrayList;

import telran.employees.services.impl.EmployeeServiceImpl;
import telran.emplyees.dto.Employee;
import telran.emplyees.dto.GeneratorMode;
import telran.perforamnse.PerformanceTest;

public class UpdateEmployeeTest extends PerformanceTest {
	private EmployeeServiceImpl employees = new EmployeeServiceImpl();

	private ArrayList<Employee> randomEmpl = new ArrayList<Employee>();
	private ArrayList<Employee> randomEmplUpdate = new ArrayList<Employee>();

	public UpdateEmployeeTest(String testName, int minSalary, int MaxSalary, int count, GeneratorMode mode, int nRuns) {
		super(testName, nRuns);
		EmployeeGenerator generator = new EmployeeGenerator(minSalary, MaxSalary, count, mode);
		EmployeeGenerator generatorRandom = new EmployeeGenerator(minSalary, MaxSalary, count, GeneratorMode.RANDOM);
		randomEmpl.addAll(generator.generate());
		randomEmplUpdate.addAll(generatorRandom.generate());
		for (Employee empl : randomEmpl) {
			employees.addEmployee(empl);
		}
	}

	@Override
	protected void runTest() {
		for (Employee empl : randomEmplUpdate) {
			employees.updateEmployee(empl.getId(), empl);
		}
	}

}
