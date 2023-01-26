package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObj {
    public WebDriver driver;

    public PageObj(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//*[@id='promotional_code']")
    @CacheLookup
    WebElement promoCode;


    public void enterPromoCode(String area) {
        promoCode.clear();
        promoCode.sendKeys(area);
    }

}