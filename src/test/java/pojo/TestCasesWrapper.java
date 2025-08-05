package pojo;

import java.util.List;

public class TestCasesWrapper {
    private List<TestCaseData> loginTests;
    private List<TestCaseData> dieticianTests;
    private List<TestCaseData> dieticianGetallTests;
    private List<TestCaseData> dieticianLoginTests;

   	private List<TestCaseData> dieticianGetIdTests;
    private List<TestCaseData> updateDieticianTests;
    private List<TestCaseData> deleteDieticianIdTests;
    
    private List<TestCaseData> getAllPatientsTests;
    
	private List<TestCaseData> logoutTests;

    private List<TestCaseData> logoutTests;

    private List<MorbidityTestData> morbidityTests;

    private List<PatientTestData> patientTests;

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

    public List<TestCaseData> getDieticianGetallTests() {
        return dieticianGetallTests;
    }

    public void setDieticianGetallTests(List<TestCaseData> dieticianGetallTests) {
        this.dieticianGetallTests = dieticianGetallTests;
    }

    public List<TestCaseData> getDieticianGetIdTests() {
        return dieticianGetIdTests;
    }

    public void setDieticianGetIdTests(List<TestCaseData> dieticianGetIdTests) {
        this.dieticianGetIdTests = dieticianGetIdTests;
    }

    public List<TestCaseData> getUpdateDieticianTests() {
        return updateDieticianTests;
    }

    public void setUpdateDieticianTests(List<TestCaseData> updateDieticianTests) {
        this.updateDieticianTests = updateDieticianTests;
    }

    public List<TestCaseData> getDeleteDieticianIdTests() {
        return deleteDieticianIdTests;
    }

    public void setDeleteDieticianIdTests(List<TestCaseData> deleteDieticianIdTests) {
        this.deleteDieticianIdTests = deleteDieticianIdTests;
    }

    public List<TestCaseData> getLogoutTests() {
        return logoutTests;
    }

    public void setLogoutTests(List<TestCaseData> logoutTests) {
        this.logoutTests = logoutTests;
    }

    public List<MorbidityTestData> getMorbidityTests() {
        return morbidityTests;
    }

    public void setMorbidityTests(List<MorbidityTestData> morbidityTests) {
        this.morbidityTests = morbidityTests;
    }

		
	 public List<TestCaseData> getDieticianLoginTests() {
			return dieticianLoginTests;
		}

		public void setDieticianLoginTests(List<TestCaseData> dieticianLoginTests) {
			this.dieticianLoginTests = dieticianLoginTests;
		}

		public List<TestCaseData> getGetAllPatientsTests() {
			return getAllPatientsTests;
		}

		public void setGetAllPatientsTests(List<TestCaseData> getAllPatientsTests) {
			this.getAllPatientsTests = getAllPatientsTests;
		}

    public List<PatientTestData> getPatientTests() {
        return patientTests;
    }

    public void setPatientTests(List<PatientTestData> patientTests) {
        this.patientTests = patientTests;
    }
}

