package telran.employees.perfomance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import telran.emplyees.dto.Employee;
import telran.emplyees.dto.GeneratorMode;

public class EmployeeGenerator {

	private String nameList[] = { "Yehudi Mannes", "Johan Spitzer", "Yoel Myer", "Adin Gollancz", "Cain Berlin",
			"Benzion Sarlin", "Avidan Weidenseld", "Adon Yoffey", "Moshe Mazar", "Hyman Grois", "Anat Meir",
			"Lior Herzl", "Ofra Berlin", "Ya'el Reiss", "Hodaya Yakobovitch", "Noga Pollack", "Noga Sharett",
			"Drorit Garbacz", "Eina Angel", "Nitzana Schapiro", "Lewi Krausz", "Yered Dayan", "Shimshon Weizmann",
			"Absolom Pirbright", "Adin Eshkol", "Lemuel Shulman", "Aitan Spiro", "Machum Stiebel", "Lev Blum",
			"Azriel Maxse", "Galina Yoffey", "Hannah Levenberg", "Orah Kahan", "Kalanit Gluckstein", "Roza Borach",
			"Shoshi Mankowitz", "Chedva Trachtenberg", "Avior Hirst", "Yonat Sharansky", "Nurit Sarasohn" };
	private List<LocalDate> birtdayList = LocalDate.of(1990, 01, 01).datesUntil(LocalDate.of(2010, 01, 01))
			.collect(Collectors.toList());
	private String departmentList[] = { "Development", "Operations", "Managment", "Sales", "Salaried", "Recrutment" };
	private int minSalary;
	private int maxSalary;
	private int employeeCount;
	private GeneratorMode mode;

	public EmployeeGenerator(int minSalary, int maxSalary, int employeeCount, GeneratorMode mode) {
		super();
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.employeeCount = employeeCount;
		this.mode = mode;
	}

	public ArrayList<Employee> generate() {
		ArrayList<Employee> randomEmployees = new ArrayList<Employee>(employeeCount);
		int nameLenth = nameList.length;
		int birtdayListLenth = birtdayList.size();

		for (int i = 0; i < employeeCount; i++) {
			Employee person = new Employee(i, nameList[getRandomInt(0, nameLenth)],
					birtdayList.get(getRandomInt(0, birtdayListLenth)), departmentList[getRandomInt(0, 3)],
					getRandomInt(minSalary, maxSalary));
			randomEmployees.add(person);
		}
		if (mode == GeneratorMode.DESC) {
			randomEmployees.sort((e1, e2) -> {
				Long e1Id = e1.getId();
				Long e2Id = e2.getId();
				return e1Id.compareTo(e2Id);
			});

		} else if (mode == GeneratorMode.RANDOM) {
			Collections.shuffle(randomEmployees);
		}

		return randomEmployees;
	}

	public int getRandomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
}
