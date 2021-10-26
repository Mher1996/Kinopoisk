package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.util.UUID;

public class BaseTest {
    private WebDriverWait wait;

    public WebDriver getDriver() {
        return MyWebDriver.getWebDriver();
    }

    public WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), 30, 1000);
        }
        return wait;
    }

    public WebElement waihForElement(WebElement webElement) {
        return getWait().withMessage("Element cannot be located" + webElement).until(ExpectedConditions.visibilityOf(webElement));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult) {
        if (ITestResult.FAILURE == iTestResult.getStatus()) {
            Helper helper = new Helper();
            String path = "C:\\Users\\Dell\\Desktop\\HomeWork\\";
            String fileName = UUID.randomUUID() + ".jpg";
            helper.screenShot(getDriver(), path + fileName);
        }


    }


}
