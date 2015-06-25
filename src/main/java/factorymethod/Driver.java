package factorymethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import singleton.WebDriverSingleton;

public class Driver {
	
	private static WebDriver driver;

	public static WebDriver get() {
		return driver;
	}
	
	public static void set(WebDriver driverInput) {
        driver = driverInput;
    }
	
	public static void init() {
        Properties properties = new Properties();
        FileInputStream propFile;
        try {
            propFile = new FileInputStream("test.properties");
            properties.load(propFile);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        @SuppressWarnings("unchecked")
        Enumeration<String> e = (Enumeration<String>) properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            System.setProperty(key, properties.getProperty(key));
            Reporter.log(key + " - " + properties.getProperty(key), 2, true);
        }
        WebDriver driverInput;
        switch (System.getProperty("test.browser")) {
        
            case "firefox":
            	WebDriverCreator creatorFirefox = new FirefoxDriverCreator();
            	driverInput = creatorFirefox.factoryMethod();
//            	driverInput = WebDriverSingleton.getWebDriverInstance();
            	Reporter.log("Browser is Firefox ", 2, true);
                break;
                
            case "chrome":
                WebDriverCreator creatorChrome = new ChromeDriverCreator();
                driverInput = creatorChrome.factoryMethod();
                Reporter.log("Browser is Chrome ", 2, true);
                break;
         
            default:
                throw new AssertionError("Unsupported browser: " + System.getProperty("test.browser"));
        }
        driverInput.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driverInput.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Driver.set(driverInput);
    }

    public static void tearDown() {
//    	Driver.get();
//    	WebDriverSingleton.closeWebBrowser();
        Driver.get().quit();
    }

}
