package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static base.MyWebDriver.getWebDriver;

public class Helper {
    public void scrollIntoView(WebElement element){
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView()", element);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void scrollIntoView(WebElement element, boolean alignToTop){
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView("+alignToTop+")", element);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ctrlClick(WebDriver driver, WebElement element){
        Actions actions=new Actions(driver);
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(element)
                .perform();
    }
    public Object[][] index(int bound) {
        Object[][] indexes = new Object[bound][1];
        for (int i = 0; i < bound; i++) {
            indexes[i][0] = i;

        }
        return indexes;
    }
    public void screenShot(WebDriver webDriver,String filePath){
        File screenShot=((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        File destination=new File(filePath);
        try {
            FileUtils.copyFile(screenShot,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void screenAshot(WebDriver driver,String path){
        Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(200))
                .takeScreenshot(driver);

        File file=new File(path);
        try {
            ImageIO.write(screenshot.getImage(),"jpg",file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateAlert(WebDriver driver, String alertText){
        ((JavascriptExecutor)driver).executeScript("alert("+alertText+")");
    }

}
