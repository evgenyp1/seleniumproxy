import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public enum Country {

        GERMANY("130.193.112.146:36923"),
        CANADA("24.226.146.46:33847");

        private final String ip;

        Country(String ip) {
            this.ip = ip;
        }
    }

    @Test
    public void seleniumTest() throws Exception {
        //Change this to the actual chromedriver location
        System.setProperty("webdriver.chrome.driver", "/Users/evgenyp/Downloads/chromedriver");

        Proxy proxy = new Proxy();

        for (Country country : Country.values()) {

            proxy.setHttpProxy(country.ip);
            proxy.setSslProxy(country.ip);

            //In case the proxy requires username/password, set it like this
            //proxy.setSocksUsername("user_name");
            //proxy.setSocksPassword("12345678");

            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("proxy", proxy);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");

            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            WebDriver driver = new ChromeDriver(capabilities);

            driver.get("https://mylocation.org/");

            TimeUnit.SECONDS.sleep(10);
            driver.close();
            driver.quit();
        }

    }

}