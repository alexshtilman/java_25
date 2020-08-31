package telran.employees.perfomance;

import telran.emplyees.dto.GeneratorMode;

public class EmployeePerformanceTestsApp {
	public static void main(String[] args) {
		int N_employees = 10000;
		int N_runs = 1000;
		int minSalary = 5600;
		int maxSalary = 55000;

		GetEmployeeTest testSordedAscGet = new GetEmployeeTest("get Sorted ASC", minSalary, maxSalary, N_employees,
				GeneratorMode.ASC, N_runs);
		GetEmployeeTest testSordedDecGet = new GetEmployeeTest("get Sorted DESC", minSalary, maxSalary, N_employees,
				GeneratorMode.DESC, N_runs);
		GetEmployeeTest testRandomGet = new GetEmployeeTest("get Unsorted", minSalary, maxSalary, N_employees,
				GeneratorMode.DESC, N_runs);

		AddEmployeeTest testSordedAscAdd = new AddEmployeeTest("add Sorted ASC", minSalary, maxSalary, N_employees,
				GeneratorMode.ASC, N_runs);
		AddEmployeeTest testSordedDecAdd = new AddEmployeeTest("add Sorted DESC", minSalary, maxSalary, N_employees,
				GeneratorMode.DESC, N_runs);
		AddEmployeeTest testRandomAdd = new AddEmployeeTest("add Unsorted", minSalary, maxSalary, N_employees,
				GeneratorMode.DESC, N_runs);

		RemoveEmployeeTest testSordedAscRemove = new RemoveEmployeeTest("remove Sorted ASC", minSalary, maxSalary,
				N_employees, GeneratorMode.ASC, N_runs);
		RemoveEmployeeTest testSordedDecRemove = new RemoveEmployeeTest("remove Sorted DESC", minSalary, maxSalary,
				N_employees, GeneratorMode.DESC, N_runs);
		RemoveEmployeeTest testRandomRemove = new RemoveEmployeeTest("remove Unsorted", minSalary, maxSalary,
				N_employees, GeneratorMode.DESC, N_runs);

		UpdateEmployeeTest testSordedAscUpdate = new UpdateEmployeeTest("update Sorted ASC", minSalary, maxSalary,
				N_employees, GeneratorMode.ASC, N_runs);
		UpdateEmployeeTest testSordedDecUpdate = new UpdateEmployeeTest("update Sorted DESC", minSalary, maxSalary,
				N_employees, GeneratorMode.DESC, N_runs);
		UpdateEmployeeTest testRandomUpdate = new UpdateEmployeeTest("update Unsorted", minSalary, maxSalary,
				N_employees, GeneratorMode.DESC, N_runs);

		System.out.println("GET");
		testSordedAscGet.run();
		testSordedDecGet.run();
		testRandomGet.run();
		System.out.println("ADD");
		testSordedAscAdd.run();
		testSordedDecAdd.run();
		testRandomAdd.run();
		System.out.println("REMOVE");
		testSordedAscRemove.run();
		testSordedDecRemove.run();
		testRandomRemove.run();
		System.out.println("UPDATE");
		testSordedAscUpdate.run();
		testSordedDecUpdate.run();
		testRandomUpdate.run();

	}
}
