package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilsData {

	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			PrintStream logStream = new PrintStream(new File("logging"));
			req = new RequestSpecBuilder().setBaseUri(getBaseURI("baseURI")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(logStream))
					.addFilter(ResponseLoggingFilter.logResponseTo(logStream))
					.addHeader("Content-Type", "application/json").build();
			return req;
		}
		return req;

	}

	public static String getBaseURI(String key) throws IOException {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\resources\\generic.properties");
		properties.load(fileInputStream);
		return properties.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		
		String responseString = response.asString();
		JsonPath path = new JsonPath(responseString);		
		return path.get(key).toString();
	}

}
