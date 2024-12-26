package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getDataFromJsonFile(String key) throws IOException, ParseException {
	JSONParser parser = new JSONParser();
	Object obj= parser.parse(new FileReader("./configAppdata/appCommondata.properties"));
	JSONObject map = (JSONObject) obj;
	return (String) map.get(key);
	}
}
