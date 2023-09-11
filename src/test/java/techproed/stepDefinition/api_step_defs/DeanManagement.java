package techproed.stepDefinition.api_step_defs;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import techproed.pojos.dean_management.DeanPostPojo;
import techproed.pojos.dean_management.ResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static techproed.base_urls.BaseUrl.spec;

public class DeanManagement {
    DeanPostPojo expectedData;
    Response response;
    ResponsePojo actualData;


    @Given("Dean eklemek icin Post request hazirligi yapilir")
    public void dean_eklemek_icin_post_request_hazirligi_yapilir() {
        // Set the URL
        spec.pathParams("first", "dean", "second", "save");
    }
    @Given("Gonderilecek dean bilgileri hazirlanir")
    public void gonderilecek_dean_bilgileri_hazirlanir() {
        // Set the expected data
        expectedData = new DeanPostPojo("1975-05-05","Istanbul","MALE","Ali","12345678Aa*","505-555-5555","578-67-9518","Can","alicancancan");
    }
    @When("Dean eklemek icin Post request gonderilir")
    public void dean_eklemek_icin_post_request_gonderilir() {
        // Send the request and get the response
        response = given(spec).body(expectedData).when().post("{first}/{second}");
        actualData = response.as(ResponsePojo.class);

    }
    @Then("Dean bilgileri dogrulanir")
    public void dean_bilgileri_dogrulanir() {
        // Do assertion
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getBirthDay(), actualData.getObject().getBirthDay());
        assertEquals(expectedData.getBirthPlace(), actualData.getObject().getBirthPlace());
        assertEquals(expectedData.getGender(), actualData.getObject().getGender());
        assertEquals(expectedData.getName(), actualData.getObject().getName());
        assertEquals(expectedData.getPhoneNumber(), actualData.getObject().getPhoneNumber());
        assertEquals(expectedData.getSsn(), actualData.getObject().getSsn());
        assertEquals(expectedData.getSurname(), actualData.getObject().getSurname());
        assertEquals(expectedData.getUsername(), actualData.getObject().getUsername());

    }
}










