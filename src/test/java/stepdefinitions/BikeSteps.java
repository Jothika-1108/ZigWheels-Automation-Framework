package stepdefinitions;

import factory.BaseClass;
import pageObjects.HomePage;
import pageObjects.UpcomingBikesPage;
import pageObjects.UsedCarsPage;
import pageObjects.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BikeSteps {

    HomePage home;
    UpcomingBikesPage bikesPage;
    UsedCarsPage carsPage;
    LoginPage loginPage;

    // ================== COMMON ==================

    @Given("user navigates to ZigWheels website")
    public void user_navigates_to_zigwheels_website() {
        System.out.println("User is on ZigWheels homepage");
    }

    // ================== SCENARIO 1 ==================

    @When("user clicks on upcoming bikes")
    public void user_clicks_on_upcoming_bikes() {
        home = new HomePage(BaseClass.driver);
        home.clickUpcomingBikes();
    }

    @When("user selects Honda manufacturer")
    public void user_selects_honda_manufacturer() {
        bikesPage = new UpcomingBikesPage(BaseClass.driver);
        bikesPage.selectHonda();
    }

    @Then("user should get list of bikes under {int} lakh")
    public void user_should_get_list_of_bikes_under_lakh(Integer price) {
        bikesPage.filterAndDisplayBikes(price);
    }

    // ================== SCENARIO 2 ==================

    @When("user navigates to used cars in Chennai")
    public void user_navigates_to_used_cars_in_chennai() {
        carsPage = new UsedCarsPage(BaseClass.driver);
        carsPage.navigateToChennaiUsedCars();
    }

    @Then("user should extract all popular car models")
    public void user_should_extract_all_popular_car_models() {
        carsPage.getPopularModels();
    }

    // ================== SCENARIO 3 ==================

    @When("user clicks on login")
    public void user_clicks_on_login() {
        loginPage = new LoginPage(BaseClass.driver);
        loginPage.clickLoginIcon();
    }

    @When("user tries to login with email {string}")
    public void user_tries_to_login_with_email(String email) {
        loginPage.clickGoogle();
        loginPage.switchToGoogleWindow();
        loginPage.enterEmail(email);
        loginPage.clickNext();
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        String error = loginPage.getErrorMessage();
        System.out.println(error);
    }
}