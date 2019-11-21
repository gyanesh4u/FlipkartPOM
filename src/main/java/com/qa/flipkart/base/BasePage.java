package com.qa.flipkart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.qa.flipkart.utility.WebEventListener;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public WebDriverEventListener eventListener;
	public static EventFiringWebDriver e_driver;

	public WebDriver init_driver(String browserName) {

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"E://Advanced Automation//FlipkartPOM//driver//chromedriver.exe");
			if (prop.getProperty("headless").equals("yes")) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			} else {
				driver = new ChromeDriver();
			}
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver");
			if (prop.getProperty("headless").equals("yes")) {
				FirefoxBinary fb = new FirefoxBinary();
				fb.addCommandLineOptions("--headless");
				FirefoxOptions fo = new FirefoxOptions();
				fo.setBinary(fb);
				driver = new FirefoxDriver(fo);
			} else {
				driver = new FirefoxDriver();
			}
		} else {
			System.out.println(browserName + ": is not correct or blank");
			try {
				throw new Exception("NO BROWSERFOUND EXCEPTION");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("pageloadtimeout")),
				TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		return driver;

	}

	public Properties init_properties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"E://Advanced Automation//FlipkartPOM//src//main//java//com//qa//flipkart//config//config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public String getScreenshot(WebDriver driver, String testMethodName) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + testMethodName + System.currentTimeMillis()
				+ ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}