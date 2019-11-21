package com.qa.flipkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.flipkart.base.BasePage;

public class HomePage extends BasePage {

	@FindBy(xpath = "//input[@class='LM6RPg']")
	WebElement seachBox;

	@FindBy(xpath = "//span[@class='_1QZ6fC _3Lgyp8' and .='Electronics']")
	WebElement electronicsTab;

	@FindBy(xpath = "//span[.='TVs & Appliances']")
	WebElement appliancesTab;

	@FindBy(xpath = "//span[.='Home & Furniture']")
	WebElement homeFurnitureTab;

	@FindBy(xpath = "//span[.='Cart']")
	WebElement cartLink;

	@FindBy(xpath = "//button[@class='_2AkmmA _2Z-ax5 _1eFTEo']")
	WebElement shopNowLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();

	}

	public boolean clickOnCart() {

		cartLink.click();
		return shopNowLink.isDisplayed();
	}
}
