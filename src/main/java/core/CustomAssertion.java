package core;

import com.beust.jcommander.internal.Lists;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CustomAssertion {

    private static List<String> assert_messages = Lists.newArrayList();
    public static final ThreadLocal<WebDriver> drivers = new ThreadLocal();
    private WebDriver webDriver;

    public void getWebDriverAssert(ThreadLocal<WebDriver> driver) {
        drivers.set(driver.get());
    }


}
