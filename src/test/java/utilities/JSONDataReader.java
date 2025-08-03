package utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
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
	   
	   public JSONObject readJsondata(String filepath, String data, int Index) throws IOException, ParseException {
			
			JSONParser jsonparser = new JSONParser();
			FileReader  reader = new FileReader(filepath);
			Object obj = jsonparser.parse(reader);
			JSONObject jsondata = (JSONObject)obj;
			JSONArray array = (JSONArray) jsondata.get(data);
			JSONObject jsonelement = (JSONObject)array.get(Index);
			return jsonelement;
			
		}
	   
	   public  JSONObject createRequestBodyFromJson(JSONObject jsonData, List<String> keys) {
		    JSONObject requestBody = new JSONObject();
		    for (String key : keys) {
		        requestBody.put(key, jsonData.get(key));
		    }
		    return requestBody;
		}
	   
	   public  String getStringField(JSONObject jsonObject, String key) {
	        return (String) jsonObject.get(key);
	    }
		

	  }

