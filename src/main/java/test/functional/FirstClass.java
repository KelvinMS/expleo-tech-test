package test.functional;

import core.CustomAssertion;
import core.DriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.po.MasterPO;

public class FirstClass {


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

    @Parameters({"browser", "url"})
    @Test
    public void acessPage(@Optional ("chrome") String browser,
                          @Optional ("http://www.xe.com/currencyconverter/") String url) throws InterruptedException {
        setup(browser);
        driver.get().get(url);
        Thread.sleep(2000);

    }


    @AfterMethod
    private void tearDown() {
        driver.get().quit();
    }


}
