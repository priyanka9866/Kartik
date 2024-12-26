package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFromPropertyFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\GenericUtilMavenProject\\src\\main\\resources\\FileUtility\\CommonTestdata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String keyValue = pObj.getProperty(key);
		return keyValue;
	}
}
