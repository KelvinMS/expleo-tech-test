package test.po;

import core.ExtentManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ConvertionPage extends MasterPO {
    public ConvertionPage(ThreadLocal<WebDriver> driver) {
        super(driver);
    }


    @FindBy(id="midmarketFromCurrency")
    public WebElement dropContryFrom;

    @FindBy(id="midmarketToCurrency")
    public WebElement dropContryTo;

    @FindBy(css = "input[id='midmarketFromCurrency']")
    public WebElement tbxContryFrom;

    @FindBy(css = "input[id='midmarketToCurrency']")
    public WebElement tbxContryTo;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    public List<WebElement> btnConvert;

    @FindBy(xpath = "//a[contains(.,'View full chart')]")
    public WebElement btnViewFullChart;

    @FindBy(xpath = "//div[contains(@class,'unit-rates')]/p")
    public WebElement lblUnitRates;

    public ConvertionPage selectContryFrom(String contryMoney){
        dropContryFrom.click();
        tbxContryFrom.sendKeys(contryMoney, Keys.ENTER);
        return new ConvertionPage(driver);
    }

    public ConvertionPage selectContryTo(String contryTo){
        dropContryTo.click();
        tbxContryTo.sendKeys(contryTo, Keys.ENTER);
        return new ConvertionPage(driver);
    }

    public ConvertionPage convert(){
        wait.until(ExpectedConditions.elementToBeClickable(btnConvert.get(0))).click();
        ((JavascriptExecutor) driver.get()).executeScript(
                "arguments[0].scrollIntoView();", btnViewFullChart
        );
        getMoneyQuantityRate(driver.get().findElement(By.xpath("//div[contains(@class,'unit-rates')]/p")));
        return new ConvertionPage(driver);
    }

    public void getMoneyQuantityRate(WebElement lblUnitRates){
        ExtentManager.info("ConvertionRate: "+lblUnitRates.getText());
    }

}
