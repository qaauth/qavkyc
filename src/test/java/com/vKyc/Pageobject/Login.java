package com.vKyc.Pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Login {

	WebDriver ldriver;
	WebDriverWait wait;
	JavascriptExecutor js;
	SoftAssert asert = new SoftAssert();

	public Login(WebDriver rdriver) {
		this.ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	//Login page
	@FindBy(xpath ="//input[@type='email']")
	@CacheLookup
	WebElement userName;
	
	@FindBy(xpath ="//input[@type='password']")
	@CacheLookup
	WebElement userPassword;
	
	@FindBy(xpath ="//*[@type='submit']")
	@CacheLookup
	WebElement logInBtn;
	
	@FindBy(xpath ="(//*[text()='Video KYC'])[2]")
	@CacheLookup
	WebElement verifyDashboardText;
	
	@FindBy(xpath ="//*[@id='dropdownMenuButton']")
	@CacheLookup
	WebElement userProfile;
	
	@FindBy(xpath ="//*[text()='Log out']")
	@CacheLookup
	WebElement logOutBtn;
	
	//Action method for login test
	public void loginVkyc(String username, String password) {
		wait = new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(userName));
		userName.sendKeys(username);
		userPassword.sendKeys(password);
		logInBtn.click();
	}
	//verify successful login
	public String verifyDashboardText() {
		wait = new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(verifyDashboardText));
		return verifyDashboardText.getText();
	}
	public void logoutUser() throws InterruptedException {  
		wait = new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(userProfile));
		userProfile.click();
		wait.until(ExpectedConditions.visibilityOf(logOutBtn));
		logOutBtn.click();
	}
}
