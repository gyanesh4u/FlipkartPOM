package com.qa.flipkart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.flipkart.base.BasePage;
import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;

public class HomePageTest {
	public BasePage bp;
	public HomePage hp;
	public Properties prop;
	public LoginPage lp;
	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		bp = new BasePage();
		prop = bp.init_properties();
		String browser = prop.getProperty("browser");
		driver = bp.init_driver(browser);
		driver.get(prop.getProperty("url"));
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = hp.verifyHomePageTitle();
		System.out.println(title);
		Assert.assertEquals(title,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}

	@Test(priority=2)
	public void clickOnCartTest() throws Exception {
		 Thread.sleep(30000);
		boolean flag = hp.clickOnCart();
		Assert.assertTrue(flag);

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			bp.getScreenshot(driver, result.getName());
		}
		driver.quit();
	}
}
