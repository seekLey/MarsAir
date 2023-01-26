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

    @FindBy(xpath = "//*[@id=\"content\"]/p[2]/a")
    @CacheLookup
    WebElement backButton;


    public void enterPromoCode(String code) {
        promoCode.clear();
        promoCode.sendKeys(code);
    }

    public void clickBackButton(){
        backButton.click();
    }


}