package com.trello.step_definitions;
import com.trello.payload.TrelloPayloads;
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
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class TrelloStepDefs {
    TrelloPayloads trello = new TrelloPayloads();
    RequestSpecification req, res;
    ResponseSpecification resSpec;
    Response response;
    @Given("user has authentication information to trello")
    public void user_has_authentication_information_to_trello() {
        req = new RequestSpecBuilder().setBaseUri(trello.url).addQueryParam("key", trello.key).addQueryParam("token", trello.token).addQueryParam("name", "ahmetBoard").addHeader("Content-Type", "application/json").build();
        res = given().spec(req);
    }

    @When("user creates new board")
    public void user_creates_new_board() {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
        response = res.when().post(trello.createBoardAddress).then().spec(resSpec).extract().response();
    }

    @Then("new board is created")
    public void new_board_is_created() {
        Assert.assertEquals(response.getStatusCode(),200);
    }

    RequestSpecification req1, res1, req2, res2;
    ResponseSpecification resSpec1, resSpec2;
    Response response1, response2;


    @Given("user has request information to create card")
    public void user_has_request_information_to_create_card() {
        req1 = new RequestSpecBuilder().setBaseUri(trello.url).addQueryParam("idList", "61be62fdf8bdc7339c7c9075").addQueryParam("key", trello.key).addQueryParam("token", trello.token).addQueryParam("name", "card1").addHeader("Content-Type", "application/json").build();
        res1 = given().spec(req1);

        req2 = new RequestSpecBuilder().setBaseUri(trello.url).addQueryParam("key", trello.key).addQueryParam("idList", "61be62fdf8bdc7339c7c9075").addQueryParam("token", trello.token).addQueryParam("name", "card2").addHeader("Content-Type", "application/json").build();
        res2 = given().spec(req2);
    }

    @When("user adds two cards")
    public void user_adds_two_cards() {
        resSpec1 = new ResponseSpecBuilder().expectStatusCode(200).build();
        response1 = res1.when().post("/1/cards").then().spec(resSpec1).extract().response();

        resSpec2 = new ResponseSpecBuilder().expectStatusCode(200).build();
        response2 = res2.when().post("/1/cards").then().spec(resSpec2).extract().response();

    }

    @Then("cards are added to board")
    public void cards_are_added_to_board() {
        Assert.assertEquals(response1.getStatusCode(),200);
        Assert.assertEquals(response2.getStatusCode(),200);
    }

    RequestSpecification req3, res3;
    ResponseSpecification resSpec3;
    Response response3;

    @Given("user has request information to update card")
    public void user_has_request_information_to_update_card() {
        req3 = new RequestSpecBuilder().setBaseUri(trello.url).addQueryParam("key", trello.key).addQueryParam("token", trello.token).addQueryParam("name", "card3").addHeader("Content-Type", "application/json").build();
        res3 = given().spec(req3);
    }

    @When("user sends update request tıo update a card")
    public void user_sends_update_request_tıo_update_a_card() {
        resSpec3 = new ResponseSpecBuilder().expectStatusCode(200).build();
        response3 = res3.when().put("/1/cards/61be646ec973db137b4a9159").then().spec(resSpec3).extract().response();
    }

    @Then("card is updated")
    public void card_is_updated() {
        Assert.assertEquals(response3.getStatusCode(),200);
    }

    RequestSpecification req4, res4, req5, res5;
    ResponseSpecification resSpec4, resSpec5;
    Response response4, response5;

    @Given("user has request information to delete card")
    public void user_has_request_information_to_delete_card() {
        req4 = new RequestSpecBuilder().setBaseUri(trello.url).addQueryParam("key", trello.key).addQueryParam("token", trello.token).addHeader("Content-Type", "application/json").build();
        res4 = given().spec(req4);

        req5 = new RequestSpecBuilder().setBaseUri(trello.url).addQueryParam("key", trello.key).addQueryParam("token", trello.token).addHeader("Content-Type", "application/json").build();
        res5 = given().spec(req5);
    }

    @When("user sends request to delete cards")
    public void user_sends_request_to_delete_cards() {
        resSpec4 = new ResponseSpecBuilder().expectStatusCode(200).build();
        response4 = res4.when().delete("/1/cards/61be634b173e215458ee267c").then().spec(resSpec4).extract().response();

        resSpec5 = new ResponseSpecBuilder().expectStatusCode(200).build();
        response5 = res5.when().delete("/1/cards/61be636777b0ad275c8537ce").then().spec(resSpec5).extract().response();
    }

    @Then("cards are deleted")
    public void cards_are_deleted() {
        Assert.assertEquals(response4.getStatusCode(),200);
        Assert.assertEquals(response5.getStatusCode(),200);
    }


    RequestSpecification req6, res6;
    ResponseSpecification resSpec6;
    Response response6;

    @Given("user has request information to delete the board")
    public void user_has_request_information_to_delete_the_board() {
        req6 = new RequestSpecBuilder().setBaseUri(trello.url).addQueryParam("key", trello.key).addQueryParam("token", trello.token).addHeader("Content-Type", "application/json").build();
        res6 = given().spec(req6);
    }

    @When("user sends delete request to delete the board")
    public void user_sends_delete_request_to_delete_the_board() {
        resSpec6 = new ResponseSpecBuilder().expectStatusCode(200).build();
        response6 = res6.when().delete("/1/cards/61be5f84b773181d5ceaf42f").then().spec(resSpec6).extract().response();
    }

    @Then("board is deleted")
    public void board_is_deleted() {
        Assert.assertEquals(response6.getStatusCode(),200);
    }
}
