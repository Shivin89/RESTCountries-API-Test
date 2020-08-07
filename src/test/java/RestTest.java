import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.get;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import groovy.json.JsonException;
import io.restassured.http.ContentType;


public class RestTest {
	
	@Test
	 public void getRequestFindCapital_name() throws JsonException {
		
		    given().
	        when()
	        .get("http://restcountries.eu/rest/v1/name/india") //Captures the (JSON) response of the API call
	        .then()	    
	        .assertThat()
	        .contentType(ContentType.JSON) //The response content type  equals json
	        .and()
	        .statusCode(HttpStatus.SC_OK) //The response status code is equal to 200.
	         .log().all();
	}
	
	@Test
	 public void getRequestvalidateinput_name_char() throws JsonException {
	  given().
	        when()
	        .get("http://restcountries.eu/rest/v1/name/aaa") //Captures the (JSON) response of the API call
	        .then()
	        .assertThat()
	        .contentType(ContentType.JSON) //The response content type  equals json
	        .and()
	        .statusCode(HttpStatus.SC_NOT_FOUND) //The response status code is equal to 404 Not found.
	        .log().all();
	}
	
	@Test
	 public void getRequestvalidateinput_name_number() throws JsonException {
	  given().
	        when()
	        .get("http://restcountries.eu/rest/v1/name/123") //Captures the (JSON) response of the API call
	        .then()
	        .assertThat()
	        .contentType(ContentType.JSON) //The response content type  equals json
	        .and()
	        .statusCode(HttpStatus.SC_NOT_FOUND) //The response status code is equal to 404 Not found.
	        .log().all();
	}
	
	@Test
	 public void getRequestFindCapital_code() throws JsonException {
		
		String expectedCapital ="Bogotá";
		    given().
	        when()
	        .get("https://restcountries.eu/rest/v2/alpha/co") //Captures the (JSON) response of the API call
	        .then()	    
	        .assertThat()
	        .contentType(ContentType.JSON) //The response content type  equals json
	        .and()
	        .statusCode(HttpStatus.SC_OK) //The response status code is equal to 200.	        
	        .body("capital",equalTo(expectedCapital) )
	         .log().all();
	}
	
	@Test
	 public void getRequestFindName_code() throws JsonException {
		
		String expectedCapital ="Colombia";
		    given().
	        when()
	        .get("https://restcountries.eu/rest/v2/alpha/col") //Captures the (JSON) response of the API call
	        .then()	    
	        .assertThat()
	        .contentType(ContentType.JSON) //The response content type  equals json
	        .and()
	        .statusCode(HttpStatus.SC_OK) //The response status code is equal to 200.	        
	        .body("name",equalTo(expectedCapital) )
	         .log().all();
	}
	
	@Test
	 public void getbadRequest_code() throws JsonException {
	  given().
	        when()
	        .get("https://restcountries.eu/rest/v2/alpha/i") //Captures the (JSON) response of the API call
	        .then()
	        .assertThat()
	        .contentType(ContentType.JSON) //The response content type  equals json
	        .and()
	        .statusCode(HttpStatus.SC_BAD_REQUEST) //The response status code is equal to 404 Not found.
	        .log().all();
	}
	
	@Test
	 public void getRequest_code_invalidNumber() throws JsonException {
	  given().
	        when()
	        .get("https://restcountries.eu/rest/v2/alpha/123") //Captures the (JSON) response of the API call
	        .then()
	        .assertThat()
	        .contentType(ContentType.JSON) //The response content type  equals json
	        .and()
	        .statusCode(HttpStatus.SC_NOT_FOUND) //The response status code is equal to 404 Not found.
	        .log().all();
	}
	
	@Test
	 public void getRequest_invalidinput() throws JsonException {
	  given().
	        when()
	        .get("https://restcountries.eu/rest/v2/alpha/abc123") //Captures the (JSON) response of the API call
	        .then()
	        .assertThat()
	        .contentType(ContentType.JSON) //The response content type  equals json
	        .and()
	        .statusCode(HttpStatus.SC_BAD_REQUEST) //The response status code is equal to 404 Not found.
	        .log().all();
	}
	
	
	@Test
	public void QueryParamter_Name()  {

	    given()
	            .queryParam("fullText","true")
	            .when()
	            .get("https://restcountries.eu/rest/v2/name/aruba")
	            .then()
	            .assertThat()
	            .contentType(ContentType.JSON)
	            .and()
	            .statusCode(HttpStatus.SC_OK)
	            .log().all();
	    
	}
	
	@Test
	public void getResponseTime() {

	    // Get the response time in seconds from a get request
	    String url = "http://restcountries.eu/rest/v1/name/Albania";
	    long timeInSeconds = get(url).timeIn(TimeUnit.SECONDS);

	    given()
	            .when()
	            .get(url)
	            .then().log().all()
	            .time(lessThan(timeInSeconds));
	}

	
}
