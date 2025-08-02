package pojo;

public class PatientTestData {

	private String testCaseId;
	private String endpoint;
	private PatientPojo patientDataInput;
	private VitalsPojo vitalsDataInput;
	

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
