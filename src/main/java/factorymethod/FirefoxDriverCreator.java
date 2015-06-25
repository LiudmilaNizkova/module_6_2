package factorymethod;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxDriverCreator extends WebDriverCreator {

    @Override
    public WebDriver factoryMethod() {
        FirefoxBinary binary = new FirefoxBinary(new File("c:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
        FirefoxProfile profile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(binary, profile);
        return driver;
    }
}
