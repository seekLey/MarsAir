package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.PageObj;

public class SearchSteps {

    String baseURL = "https://marsair.recruiting.thoughtworks.net/SujitAmbore";
    public WebDriver driver;
    public PageObj po;

    @Before
    public void open_browser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(baseURL);
        po = new PageObj(driver);

    }

    @Given("launch the browser")
    public void launch_the_browser() {
        System.out.println("########################   TEST STARTED   ##################################");
    }

    @Then("verify the page Title")
    public void verify_title() {
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        Assert.assertEquals("Mars Airlines: Home", actualTitle);
    }

    @Given("Verify the form fields on Home page")
    public void verify_FormFields() {
        String actualTextDPT = driver.findElement(By.xpath(".//*[contains(text(),'Departing')]")).getText();
        String expectedTextDPT = "Departing";

        // Check Departure text in the Search form
        Assert.assertEquals(actualTextDPT, expectedTextDPT);

        String actualTextRTN = driver.findElement(By.xpath(".//*[contains(text(),'Returning')]")).getText();
        String expectedTextRTN = "Returning";

        // Check Return text in the Search form
        Assert.assertEquals(actualTextRTN, expectedTextRTN);


        String actualTextProCode = driver.findElement(By.xpath(".//*[contains(text(),'Promotional Code')]")).getText();
        String expectedTextProCode = "Promotional Code";

        // Check Promo code text in the Search form
        Assert.assertEquals(actualTextProCode, expectedTextProCode);

    }

    @When("Select {string} and {string}")
    public void select_FormFields(String departure, String returning) {
        driver.findElement(By.xpath(".//*[@id='departing']")).sendKeys(departure);
        driver.findElement(By.xpath(".//*[@id='returning']")).sendKeys(returning);
    }

    @Then("click on search")
    public void click_search() {
        driver.findElement(By.xpath(".//*[@value='Search']")).isEnabled();
        driver.findElement(By.xpath(".//*[@value='Search']")).click();
    }

    @And("Verify the validation {string}")
    public void validation(String expectedMessage) {
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);

        expectedMessage = "Mars Airlines: Search Results";
        Assert.assertEquals(actualTitle,expectedMessage);

        String actualValidationMSG = driver.findElement(By.xpath(".//*[contains(text(), 'Sorry, there are no more seats available.')]")).getText();
        System.out.println(actualValidationMSG);
        String expectedValidationMSG = "Sorry, there are no more seats available.";
        Assert.assertEquals(actualValidationMSG,expectedValidationMSG);


    }


//    @After()
//    public void close_Browser() {
//        driver.close();
//        driver.quit();
//        System.out.println("########################   TEST ENDED   ##################################");
//    }
}