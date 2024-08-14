package stepDefinitionsPack;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.UtilsData;
import apiData.Location;
import apiData.Place;

public class AddPlaceValidation extends UtilsData{
	
	RequestSpecification requestSpec;
	ResponseSpecification res;
	Response response;
	TestDataBuilder dataBuilder = new TestDataBuilder();
	public static String place_id;
	
	
	@Given("add playload for the map api with {string} {string} {string}")
	public void add_playload_for_the_map_api_with(String name, String address, String language) throws IOException {
		
		requestSpec = given().spec(requestSpecification()).body(dataBuilder.addPlacedata(name, address, language));
		res = new ResponseSpecBuilder().expectStatusCode(200).build();
	}
	@When("user perform {string} with {string} http method request")
	public void user_perform_the_post_http_method_request(String apiString, String httpMethod) {
	
		APIResources resource = APIResources.valueOf(apiString);
		if(httpMethod.equalsIgnoreCase("post")) {
			
			response = requestSpec.when().post(resource.getAPIResource()).then().extract().response();
		}else if(httpMethod.equalsIgnoreCase("get")) {
			
			response = requestSpec.when().get(resource.getAPIResource()).then().extract().response();
		}
		
		
	}
	@Then("the api call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
		
		
		assertEquals(Integer.valueOf(response.getStatusCode()), statusCode);
	}
	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String keyValue, String expectedValue) {
		
		String actualValue = getJsonPath(response ,keyValue);
		assertEquals(actualValue, expectedValue);
		
	}
	
	@Then("verify place_id maps using the {string} with {string}")
	public void verify_place_id_maps_using_the_with(String expectedName, String resource) throws IOException {
		
		place_id = getJsonPath(response, "place_id");
		requestSpec = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_perform_the_post_http_method_request(resource, "get");
		String actualName = getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
		
	}
	
	@Given("delete place")
	public void delete_place() throws IOException {

		requestSpec = given().spec(requestSpecification()).body(dataBuilder.getPlaceId(place_id));
	}
	
}
