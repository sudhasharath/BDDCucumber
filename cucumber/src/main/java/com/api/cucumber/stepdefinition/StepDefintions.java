package com.api.cucumber.stepdefinition;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
 
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.util.List; 
import io.restassured.response.Response;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
public class StepDefintions {
	
	 private static final String USERNAME = "bhuvanesh";
	 private static final String PASSWORD = "Perigord1!";
	 private static final String BASE_URL = "https://glams-automation.glams.ie/api/app";
	 private static final String PARTNUMBER = "BLI-5902-01";
	 private static final String COMPONENTNAME = "V7700";
	 private static final String APIName = "Glams - Component Job Details";
	 private static String token;
	 private static RequestSpecification httpRequest;
	 private static Response response;
	 private static String jsonString;
	 private static String bookId;

	 private static String Strength = "";

	
	 @Given("I have List of Requests")
	 public void i_have_list_of_requests() {
		 RestAssured.baseURI = BASE_URL;
		 httpRequest = RestAssured.given();
		 String s = RestAssured.baseURI.toString();
	        System.out.println("URL :"+s);
	        httpRequest.auth().preemptive().basic(USERNAME,PASSWORD);
	 }
	 @Given("I pass the required headers")
	 public void i_pass_the_required_headers(DataTable table) {
		List<String> data = table.asList();

		Map<String,String> map = new LinkedHashMap<String,String>();
		 List<List<String>> cells = table.cells();
	
			
		for (int i=1;i<cells.size();i++) {
			map.put(cells.get(i).get(0), cells.get(i).get(1));
		}
		
		for(String key :map.keySet()) {
			System.out.println(key+" "+map.get(key));
		}

//		httpRequest .queryParam("name", APIName).multiPart("PartNumber", PARTNUMBER).multiPart("ComponentName", COMPONENTNAME);
	        
	        
	       
	       // 
	 }
	 @When("I post the request")
	 public void i_post_the_request() {
response = httpRequest.request(Method.POST, "/task");


		 String jsonString = response.asString();
		 token = JsonPath.from(jsonString).get("token");
	 }
	 @Then("Status Code should be {int}")
	 public void status_code_should_be(Integer int1) {
		 int statusCode = response.getStatusCode(); // Getting status code
	        Assert.assertEquals(statusCode, 200);
	        System.out.println("Status Code is :" + statusCode);
	 }
	 @Then("Response should display")
	 public void response_should_display(DataTable table){
		 String responseBody = response.getBody().asString();
	        System.out.println("responseBody is :" + responseBody);
	        Assert.assertEquals(responseBody.contains(PARTNUMBER), true);
	        System.out.println("PartNumber is :" + PARTNUMBER);
	        Assert.assertEquals(responseBody.contains(COMPONENTNAME), true);

	        System.out.println("Component Name is :" + COMPONENTNAME);
	        JsonPath jsonPath = new JsonPath(responseBody);
	        Strength = jsonPath.getString("Strength");
	       System.out.println("Strength is :" + Strength);
		  /* List<String> data = table.asList();
           System.out.println(data);
           JsonPath jsonPath = new JsonPath(responseBody);
           for(int i=0; i<data.size();i++) {
        	   
           switch(data.get(i)) {
           case "PartNumber":
        	   jsonPath.getString(PARTNUMBER);
           Assert.assertEquals(responseBody.contains("PartNumber"), true);
           }
           Assert.assertEquals(responseBody.contains(COMPONENTNAME), true);
           }*/
			/*Map<String,String> map = new LinkedHashMap<String,String>();
			 List<List<String>> cells = table.cells();
		
				
			for (int i=1;i<cells.size();i++) {
				map.put(cells.get(i).get(0), cells.get(i).get(1));
			}
			
			for(String key :map.keySet()) {
				System.out.println(key+" "+map.get(key));
			} */
	 }
}
