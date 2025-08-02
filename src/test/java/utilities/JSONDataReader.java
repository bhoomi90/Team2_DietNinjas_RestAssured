package utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.TestCaseData;
import pojo.TestCasesWrapper;

public class JSONDataReader {

	    public static TestCasesWrapper readAllModules(String filePath) {
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            return mapper.readValue(new File(filePath), TestCasesWrapper.class);
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to load JSON test data");
	        }
	    }

	    public static TestCaseData getTestCaseById(List<TestCaseData> testCases, String testCaseId) {
	        return testCases.stream()
	                .filter(data -> testCaseId.equals(data.getTestCaseId()))
	                .findFirst()
	                .orElseThrow(() -> new RuntimeException("Test case not found: " + testCaseId));
	    }

		
	}


