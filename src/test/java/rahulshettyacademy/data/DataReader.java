package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;


//import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	// This class is used to write one utility to scan the content inside the Json file and create HashMap out of it

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		// For File => import java.io.File; use this only
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		String jsonContent = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		// Convert Json file String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){	
		});
		return data;
	}
}
