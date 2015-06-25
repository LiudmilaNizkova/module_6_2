package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.MainPage;
import decorator.Decorator;
import factorymethod.Driver;

public class TestFactoryMethodDecorator {
		
	private final String UserLogin = "ludik.ludik2015";
	private final String UserPsw = "Qwerty111";
	private final String Subject = "test module 5";
	private final String Body = "Hello!\n\nThis is test of Module 5.\n\nBye";
	private final String LoggedUser  = "ludik.ludik2015@yandex.ru";
	private WebDriver driver;

	@BeforeMethod
    public void init(){
        Driver.init();
    }
	
	
	@Test
	public void testYandex(){
		driver = Driver.get();
		driver = new Decorator(driver);
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.loginToMail(UserLogin, UserPsw);
		HomePage homePage = new HomePage(driver);
		homePage.getLoggedUserName();
		Assert.assertTrue(homePage.getLoggedUserName().equals(LoggedUser), "Verification Failed: You are not logged under user "+UserLogin);
		homePage.writeNewEmail(Subject, Body);
		homePage.openDraftFolder();
		homePage.saveEmailToDraft();
		Assert.assertTrue(homePage.getTitleOfEmail().equals(Subject), "Verification Failed: No email in DRAFT folder with subject= "+Subject);
		homePage.sendNewEmail();
		homePage.openSentFolder();
		homePage.openDraftFolder();
		Assert.assertFalse(homePage.getTitleOfEmail().equals(Subject), "Verification Failed: Email is still in DRAFT folder with subject= "+Subject);
		homePage.openSentFolder();
		Assert.assertTrue(homePage.getTitleEmailInSentFolder().equals(Subject), "Verification Failed: No email in SENT folder with subject= "+Subject);
		homePage.quitFromMailBox();
	}
	
	@AfterMethod
    public void cleanup() {
//        Driver.tearDown();
    }

}
