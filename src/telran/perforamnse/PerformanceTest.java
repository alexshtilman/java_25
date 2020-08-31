package telran.perforamnse;

public abstract class PerformanceTest {
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getnRuns() {
		return nRuns;
	}

	public void setnRuns(int nRuns) {
		this.nRuns = nRuns;
	}

	public PerformanceTest(String testName, int nRuns) {
		this.testName = testName;
		this.nRuns = nRuns;
	}

	private String testName;
	private int nRuns;

	protected abstract void runTest();

	public void run() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < nRuns; i++) {
			runTest();
		}
		long finishTime = System.currentTimeMillis();
		displayResults(startTime, finishTime);
	}

	private void displayResults(long startTime, long finishTime) {
		System.out.printf("Test: %s, complete after %d, count of runs - %d\n", testName, finishTime - startTime, nRuns);
	}
}
