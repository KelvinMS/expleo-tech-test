package test.functional;

import core.CustomAssertion;
import core.DriverHelper;
import core.ExtentTestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import test.po.ConvertionPage;
import test.po.MasterPO;

@Listeners(ExtentTestListeners.class)
public class ConvertionTest {


    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private WebDriverWait wait;
    private CustomAssertion assertion;

    private void setup(String browser){
        DriverHelper driverHelper = new DriverHelper();
        driver.set(driverHelper.setupDriver(browser));
        assertion = new CustomAssertion();
        assertion.getWebDriverAssert(driver);
        wait = new MasterPO(driver).getWait();
    }

    @Parameters({"browser", "url","moneyFrom","moneyTo"})
    @Test(description = "make the conversion money From a contry to another")
    public void acessPage(@Optional ("firefox") String browser,
                          @Optional ("http://www.xe.com/currencyconverter/") String url,
                          @Optional ("cana") String moneyFrom,
                          @Optional ("Brazil") String moneyTo) {
        setup(browser);
        driver.get().get(url);
        ConvertionPage convertionPage = new ConvertionPage(driver).selectContryFrom(moneyFrom).selectContryTo(moneyTo).convert();
        assertion.assertEquals(wait.until(ExpectedConditions.visibilityOf(convertionPage.btnViewFullChart)).isDisplayed(),true,"Did the convertion moneyFrom:"+moneyFrom+" To:"+moneyTo+"");
        ;
    }

    @AfterMethod
    private void tearDown() {
        driver.get().quit();
    }
}
