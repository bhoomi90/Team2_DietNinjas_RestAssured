package utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	
	   public static List<Map<String, Object>> getLoginData(String filePath) throws IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        File file = new File(filePath);
	        return mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {});
	    }

}
