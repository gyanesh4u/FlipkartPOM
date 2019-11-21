package com.qa.flipkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.flipkart.base.BasePage;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//a[.='Login & Signup']")
	WebElement loginLink;

	@FindBy(xpath = "//input[@type='text' and @class='_2zrpKA _1dBPDZ']")
	WebElement loginTb;

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordTb;

	@FindBy(xpath = "//button[@class='_2AkmmA _1LctnI _7UHT_c']")
	WebElement loginBtn;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean verifyLoginPageLink() {
		return loginLink.isDisplayed();
	}

	public HomePage login(String un, String pw) {
		loginTb.sendKeys(un);
		passwordTb.sendKeys(pw);
		loginBtn.click();
		return new HomePage(driver);
	}
}
