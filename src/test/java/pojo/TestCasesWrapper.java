package pojo;

import java.util.List;

public class TestCasesWrapper {
	private List<TestCaseData> loginTests;
	private List<TestCaseData> dieticianTests;
	private List<TestCaseData> logoutTests;

	public List<TestCaseData> getLoginTests() {
		return loginTests;
	}

	public void setLoginTests(List<TestCaseData> loginTests) {
		this.loginTests = loginTests;
	}

	public List<TestCaseData> getDieticianTests() {
		return dieticianTests;
	}

	public void setDieticianTests(List<TestCaseData> dieticianTests) {
		this.dieticianTests = dieticianTests;
	}

	public List<TestCaseData> getLogoutTests() {
		return logoutTests;
	}

	public void setLogoutTests(List<TestCaseData> logoutTests) {
		this.logoutTests = logoutTests;
	}

}