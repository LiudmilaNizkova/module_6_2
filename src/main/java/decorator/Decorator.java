package decorator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class Decorator implements WebDriver {

    protected WebDriver driver;

    public Decorator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String url) {
    	System.out.println("Open url - "+url.toString());
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }
    
    @Override
    public WebElement findElement(By by) {
        System.out.println("Looking for element with locator: " + by.toString());
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
    	System.out.println("quit from firefox");
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
    	System.out.println("launch manage ");
        return driver.manage();
    }

}
