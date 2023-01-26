package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.PageObj;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        po = new PageObj(driver);

    }

    @Given("launch the browser")
    public void launch_the_browser() {
        System.out.println("########################   TEST STARTED   ##################################");
    }

    @Then("verify the page Title")
    public void verify_title() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Mars Airlines: Home", actualTitle);
    }

    @Given("Verify the form fields on Home page")
    public void verify_FormFields() {
        String actualTextDPT = driver.findElement(By.xpath(".//*[contains(text(),'Departing')]")).getText();
        String expectedTextDPT = "Departing";

//         Check Departure textfield in the Search form
        Assert.assertEquals(actualTextDPT, expectedTextDPT);
        String actualTextRTN = driver.findElement(By.xpath(".//*[contains(text(),'Returning')]")).getText();
        String expectedTextRTN = "Returning";

//         Check Return textfield in the Search form
        Assert.assertEquals(actualTextRTN, expectedTextRTN);
        String actualTextProCode = driver.findElement(By.xpath(".//*[contains(text(),'Promotional Code')]")).getText();
        String expectedTextProCode = "Promotional Code";

//         Check Promo code textfield in the Search form
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

    @And("Verify the validation1 {string}")
    public void validationMSG1(String expectedMessage) {
        String actualTitle = driver.getTitle();

        String expectedTitle = "Mars Airlines: Search Results";
        Assert.assertEquals(actualTitle, expectedTitle);

        String actualValidationMSG = driver.findElement(By.xpath(".//*[contains(text(), 'Sorry, there are no more seats available.')]")).getText();
        Assert.assertEquals(actualValidationMSG, expectedMessage);
    }


    @And("Verify the validation2 {string}")
    public void validationMSG2(String expectedMessage) {
        String actualTitle = driver.getTitle();

        String expectedTitle = "Mars Airlines: Search Results";
        Assert.assertEquals(actualTitle, expectedTitle);

        String actualValidationMSG = driver.findElement(By.xpath(".//*[contains(text(), 'Unfortunately, this schedule is not possible. Please try again.')]")).getText();
        Assert.assertEquals(actualValidationMSG, expectedMessage);
    }

    @And("Verify the validation3 {string}")
    public void validationMSG3(String expectedMessage) {
        String actualTitle = driver.getTitle();

        String expectedTitle = "Mars Airlines: Search Results";
        Assert.assertEquals(actualTitle, expectedTitle);

        String successMSG1 = driver.findElement(By.xpath("//div[@id='content']/p[1]")).getText();
        String successMSG2 = driver.findElement(By.xpath("//div[@id='content']/p[2]")).getText();

        String actualValidationMSG = successMSG1 + successMSG2; // string concatenate
        Assert.assertEquals(actualValidationMSG, expectedMessage);
    }

    @Then("enter promotion {string}")
    public void enterPromoCode(String promoCode) {
        po.enterPromoCode(promoCode);
    }

    @And("Verify the promo code {string}")
    public void validatePromoCodeMessage(String expectedMessage) {
        String actualMessage = driver.findElement(By.xpath("//p[@class='promo_code']")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @And("verify the validation {string}")
    public void validateMessage(String expectedMessage) {
        String actualMessage = driver.findElement(By.xpath(".//*[contains(text(),'Unfortunately, this schedule is not possible. Please try again.')]")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);

        po.clickBackButton();
    }

    @Given("Verify {string} on the screen")
    public void verifyMessageOnHomePage(String expectedMessage) {
        String actualMessage = driver.findElement(By.xpath(".//*[contains(text(),'Book a ticket to the red planet now!')]")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);
    }


    @After()
    public void close_Browser() {
        driver.close();
        driver.quit();
        System.out.println("########################   TEST ENDED   ##################################");
    }
}