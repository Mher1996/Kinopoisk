package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyWebDriver {
    private static WebDriver myWebDriver;

    private static void setWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/newDriver/chromedriver.exe");
        myWebDriver =new ChromeDriver();
    }

    public static WebDriver getWebDriver() {
        if (myWebDriver ==null) {
            setWebDriver();
        }
        return myWebDriver;
    }
}
