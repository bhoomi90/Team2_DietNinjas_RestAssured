package pojo;

public class PatientTestData {

	private String testCaseId;
	private String endpoint;
	private String scenarioName;
	private String scenarioType;
	
	private PatientPojo patientDataInput;
	private VitalsPojo vitalsDataInput;
	private String file;

	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getScenarioName() {
		return scenarioName;
	}
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}
	public String getScenarioType() {
		return scenarioType;
	}
	public void setScenarioType(String scenarioType) {
		this.scenarioType = scenarioType;
	}
	public PatientPojo getPatientDataInput() {
		return patientDataInput;
	}
	public void setPatientDataInput(PatientPojo patientDataInput) {
		this.patientDataInput = patientDataInput;
	}
	public VitalsPojo getVitalsDataInput() {
		return vitalsDataInput;
	}
	public void setVitalsDataInput(VitalsPojo vitalsDataInput) {
		this.vitalsDataInput = vitalsDataInput;
	}
	
}
