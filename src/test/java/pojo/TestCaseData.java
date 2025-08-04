package pojo;

public class TestCaseData {
	 
	private String testCaseId;
	private String scenario;
	private String module;
	private String method;
	private String endpoints;
	private int expectedStatusCode;
	private String expectedStatusLineMsg;
	private String typeOfTesting;
	private loginData loginInputdata;
	private dieticianData dieticianInputdata;
	private dieticianData dieticianLoginInputdata;

	private loginData dieticianloginInputdata;
	private loginData patientloginInputdata;

	
	// Getters and Setters
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getEndpoints() {
		return endpoints;
	}
	public void setEndpoints(String endpoints) {
		this.endpoints = endpoints;
	}
	public int getExpectedStatusCode() {
		return expectedStatusCode;
	}
	public void setExpectedStatusCode(int expectedStatusCode) {
		this.expectedStatusCode = expectedStatusCode;
	}
	public String getExpectedStatusLineMsg() {
		return expectedStatusLineMsg;
	}
	public void setExpectedStatusLineMsg(String expectedStatusLineMsg) {
		this.expectedStatusLineMsg = expectedStatusLineMsg;
	}
	public String getTypeOfTesting() {
		return typeOfTesting;
	}
	public void setTypeOfTesting(String typeOfTesting) {
		this.typeOfTesting = typeOfTesting;
	}
	public loginData getLoginInputdata() {
		return loginInputdata;
	}
	public void setLoginInputdata(loginData loginInputdata) {
		this.loginInputdata = loginInputdata;
	}
	public dieticianData getDieticianInputdata() {
		return dieticianInputdata;
	}
	public void setDieticianInputdata(dieticianData dieticianInputdata) {
		this.dieticianInputdata = dieticianInputdata;
	}

	public loginData getDieticianloginInputdata() {
		return dieticianloginInputdata;
	}
	public void setDieticianloginInputdata(loginData dieticianloginInputdata) {
		this.dieticianloginInputdata = dieticianloginInputdata;
	}
	public loginData getPatientloginInputdata() {
		return patientloginInputdata;
	}
	public void setPatientloginInputdata(loginData patientloginInputdata) {
		this.patientloginInputdata = patientloginInputdata;
	}
	public dieticianData getDieticianLoginInputdata() {
		return dieticianLoginInputdata;
	}
	public void setDieticianLoginInputdata(dieticianData dieticianLoginInputdata) {
		this.dieticianLoginInputdata = dieticianLoginInputdata;
	}

}

