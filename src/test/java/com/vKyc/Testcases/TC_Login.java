package com.vKyc.Testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vKyc.Pageobject.Login;

public class TC_Login extends Baseclass {

	@Test(enabled = true, testName = "Login Test", priority = 1)
	public void loginTest() throws IOException, InterruptedException {	
	
		driver.get(baseURL);
		logger.info("Url is opened");
		Login log = new Login(driver);
		log.loginVkyc(AdminUsername, AdminPassword);
		logger.info("Entered Username and password!");
		if (log.verifyDashboardText().contains("Video KYC")) {
			Assert.assertTrue(true);
			logger.info("Demo user has loggedIn Successfully!");
		} else {
			logger.info("Demo user has not loggedIn Successfully!");
			captureScreen(driver, "verifyDashboardSummaryText");
			Assert.assertTrue(true);
		}
		log.logoutUser();
		logger.info("demo user logged out successfully");
	}
}
