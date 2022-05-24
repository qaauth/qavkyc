package com.vKyc.Testcases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vKyc.Pageobject.Login;
import com.vKyc.Pageobject.videoKyc;
import com.vKyc.Utilities.RandomMethod;

public class TC_RescheduleVKyc extends Baseclass{

	//Admin initiate/upload a case for Vkyc process for Reschedule with Same Agent|Assert based on alert
    @Test(enabled = true, testName = "Admin upload a case", priority = 1)
    public void uploadKycCaseByAdmin() throws IOException, InterruptedException {
   	    driver.get(baseURL);
        logger.info("v-Kyc url opened");
        Login log = new Login(driver);
	    log.loginVkyc(AdminUsername, AdminPassword);
	    logger.info("Admin LoggedIn successfully!");
       	videoKyc upload= new videoKyc(driver);
   	    String customerName=RandomMethod.randomStringCustomerName();
   	    logger.info("got customer/client name");
     	String Text=RandomMethod.randomString();
   	    logger.info("got random string text");
   	    String mobNum=RandomMethod.randomMobileNumer();
   	    logger.info("got random mob no.");
     	String Id=RandomMethod.randomApplicantId();
   	    logger.info("got random applicant id");
   	    upload.uploadCase(customerName,Text,mobNum,Id);
   	    logger.info("case uploaded/initiated by Admin");
     	videoKyc verify= new videoKyc(driver);
       //Assert based on Alert
   	    if(verify.verifyUploadSuccessText().contains("Case successfully uploaded.")) {
   		    Assert.assertTrue(true);
			logger.info("uploaded case verified successfully");
		}
		else {
			logger.info("uploaded case  NOT verified ");
			captureScreen(driver, "uploaded case NOT verified ");
			Assert.assertTrue(false);
		}
   	    log.logoutUser();
     	logger.info("Admin loggedout successfully");
   }
    
   //Agent verify case in Assigned bucket | Assert based on case availability in Assigned bucket
    @Test(enabled = true, testName = "verify case in AssignedBucket", priority = 2, dependsOnMethods = {
	"uploadVKycCaseByAdmin" })
    public void verifyCaseInAssignedBucket() throws InterruptedException, IOException {
    	 driver.get(baseURL);
		 logger.info("v-Kyc url opened");
		 Login log = new Login(driver);
		 log.loginVkyc(AgentUsername, AgentPassword);
		 logger.info("Agent LoggedIn successfully!");
		 videoKyc verify=new videoKyc(driver);
		 if(verify.verifyCaseInAssignedBucket()==true) {
			 Assert.assertTrue(true);
			 logger.info("Assigned case verified successfully ");
		 }
		 else {
			logger.info("Assigned case NOT verified ");
			captureScreen(driver, "Assigned case NOT verified ");
			Assert.assertTrue(false); 
		 }
		 Thread.sleep(200);
		 log.logoutUser();
		 logger.info("Agent loggedout successfully");
	}
   
   //Agent verify and schedules the case | Assert based on case availability in Scheduled bucket
   @Test(enabled = true, testName = "schedule & verify case", priority = 3, dependsOnMethods = {
	"verifyCaseInAssignedBucket" })
   public void scheduleCaseByAgent() throws InterruptedException, IOException {
   	    driver.get(baseURL);
		logger.info("v-Kyc url opened");
		Login log = new Login(driver);
		log.loginVkyc(AgentUsername, AgentPassword);
		logger.info("Agent LoggedIn successfully!");
		String scheduledTime = RandomMethod.getScheduleTime();
		logger.info("got Scheduled time for vkyc");
		videoKyc schedule= new videoKyc(driver);
		schedule.scheduleCalendarForKyc(scheduledTime);
		logger.info("entered scheduled Date and Time for VKyc");
		videoKyc verify= new videoKyc(driver);
   	    //Assert based on CASE Visibility in scheduled bucket on Agent end
		if(verify.verifyCaseInScheduledBucket()==true) {
   		    Assert.assertTrue(true);
			logger.info("Scheduled case verified successfully");
		}
		else {
			logger.info("Scheduled case NOT verified ");
			captureScreen(driver, "Scheduled case NOT verified ");
			Assert.assertTrue(false);
		}
   }
   //Customer checks for scheduled V-kyc mail and reschedules for another Time with same agent. 
   //|Assert based on success msg |Assert based on link availability|Assert based on case status as rescheduled
   @Test(enabled = true, testName = "customer reschedule kyc with same agent", priority = 4, dependsOnMethods = {
	"scheduleCaseByAgent" })
    public void reschedulekycByCustomer() throws InterruptedException, IOException {
    	videoKyc vrf= new videoKyc(driver);
    	//customer verify vkyc related link in mail
    	if(vrf.verifyVkycLinkReceivedByCustomer().contains("Click Here to Start")) {
    		Assert.assertTrue(true);
			logger.info("vkyc link received on candidate mail verified successfully");
		}
		else {
			logger.info("vkyc link received on candidate mail NOT verified");
			captureScreen(driver, "vkyc link received on candidate mail NOT verified ");
			Assert.assertTrue(false);
		}
    	videoKyc reschedule= new videoKyc(driver);
    	reschedule.customerRescheduleKycWithSameAgent();
    	logger.info("kyc rescheduled with same agent");
    	videoKyc verify= new videoKyc(driver);
    	//verify reschedule success msg
    	if(verify.verifyRescheduledSuccessMsg().contains("THANK YOU!")) {
    		Assert.assertTrue(true);
			logger.info("Reschedule success msg verified");
		}
		else {
			logger.info("Reschedule success msg NOT verified");
			captureScreen(driver, "Reschedule success msg NOT verified ");
			Assert.assertTrue(false);
		}
    	//verify rescheduled received link on mail
    	if(verify.verifyRescheduleKycLink().contains("just now")) {
    		Assert.assertTrue(true);
			logger.info("Reschedule kyc link received on mail verified");
		}
		else {
			logger.info("Reschedule kyc link received on mail NOT verified");
			captureScreen(driver, "Reschedule kyc link received on mail NOT verified ");
			Assert.assertTrue(false);
		}
    	//verify rescheduled status at Agent end
    	if(verify.verifyRescheduleStatusAtAgentEnd().contains("RESCHEDULED BY CUSTOMER")) {
    		Assert.assertTrue(true);
			logger.info("Reschedule STATUS verified at agent end");
		}
		else {
			logger.info("Reschedule STATUS NOT verified at Agent End");
			captureScreen(driver, "Reschedule STATUS NOT verified at Agent End");
			Assert.assertTrue(false);
		}
    	Login log = new Login(driver);
    	log.logoutUser();
    	logger.info("agent loggedout successfully");
    	Thread.sleep(1000);
    	
    }
    //Admin initiate/upload a case for reschedule with Different agent|Assert based on alert
    @Test(enabled = true, testName = "Admin uploads a case", priority = 5,dependsOnMethods = {
	"rescheduleVkycByCustomer" })
    public void uploadCaseByAdmin() throws IOException, InterruptedException {
//  	  driver.get(baseURL);
//        logger.info("v-Kyc url opened");
    	driver.navigate().refresh();
        Login log = new Login(driver);
	    log.loginVkyc(AdminUsername, AdminPassword);
	    logger.info("Admin LoggedIn successfully!");
      	videoKyc upload= new videoKyc(driver);
  	    String customerName=RandomMethod.randomStringCustomerName();
  	    logger.info("got customer/client name");
    	String Text=RandomMethod.randomString();
  	    logger.info("got random string text");
  	    String mobNum=RandomMethod.randomMobileNumer();
  	    logger.info("got random mob no.");
    	String Id=RandomMethod.randomApplicantId();
  	    logger.info("got random applicant id");
  	    upload.uploadCase(customerName,Text,mobNum,Id);
  	    logger.info("case uploaded/initiated by Admin");
    	videoKyc verify= new videoKyc(driver);
        //Assert based on Alert
  	    if(verify.verifyUploadSuccessText().contains("Case successfully uploaded.")) {
  		    Assert.assertTrue(true);
			logger.info("uploaded case verified successfully");
		}
		else {
			logger.info("uploaded case  NOT verified ");
			captureScreen(driver, "uploaded case NOT verified ");
			Assert.assertTrue(false);
		}
  	    log.logoutUser();
    	logger.info("Admin loggedout successfully");
   }
  
    //Agent verify and schedules the case | Assert based on case availability in Scheduled bucket
    @Test(enabled = true, testName = "verify and schedule case", priority = 6, dependsOnMethods = {
	 "uploadCaseByAdmin" })
    public void scheduleCaseAtAgent() throws InterruptedException, IOException {
  	    driver.get(baseURL);
		logger.info("v-Kyc url opened");
		Login log = new Login(driver);
		log.loginVkyc(AgentUsername, AgentPassword);
		logger.info("Agent LoggedIn successfully!");
		String scheduledTime = RandomMethod.getScheduleTime();
		logger.info("got Scheduled time for vkyc");
		videoKyc schedule= new videoKyc(driver);
		schedule.scheduleCalendarForKyc(scheduledTime);
		logger.info("entered scheduled Date and Time for VKyc");
		videoKyc verify= new videoKyc(driver);
  	    //Assert based on CASE Visibility in scheduled bucket on Agent end
		if(verify.verifyCaseInScheduledBucket()==true) {
  		    Assert.assertTrue(true);
			logger.info("Scheduled case verified successfully");
		}
		else {
			logger.info("Scheduled case NOT verified ");
			captureScreen(driver, "Scheduled case NOT verified ");
			Assert.assertTrue(false);
		}
    }
   //Customer checks for scheduled V-kyc mail and reschedules for another Time with Different agent. 
   //| Assert based on link availability |Assert based on success msg |Assert based on case status as rescheduled
   @Test(enabled = true, testName = "customer reschedule kyc with Different agent", priority = 7, dependsOnMethods = {
	"scheduleCaseAtAgent" })
   public void rescheduleKycByCustomer() throws InterruptedException, IOException {
   	    videoKyc vrf= new videoKyc(driver);
   	    //customer verify vkyc related link in mail
   	    if(vrf.verifyVkycLinkReceivedByCustomer().contains("Click Here to Start")) {
   		     Assert.assertTrue(true);
			 logger.info("vkyc link received on candidate mail verified successfully");
	    }
		else {
			 logger.info("vkyc link received on candidate mail NOT verified");
			 captureScreen(driver, "vkyc link received on candidate mail NOT verified ");
			 Assert.assertTrue(false);
		}
   	    videoKyc reschedule= new videoKyc(driver);
   	    String scheduledDate = RandomMethod.getPresentDate();
   	    logger.info("got scheduled Date for kyc");
   	    String scheduledTime = RandomMethod.getScheduleTime();
   	    logger.info("got scheduled Time for kyc");
   	    reschedule. customerRescheduleVkycWithDifferentAgent(scheduledDate, scheduledTime);
   	    logger.info("kyc rescheduled with different agent by customer");
   	    videoKyc verify= new videoKyc(driver);
 	    //verify reschedule success msg
 	    if(verify.verifyRescheduledSuccessMsg().contains("THANK YOU!")) {
 		     Assert.assertTrue(true);
			 logger.info("Reschedule success msg verified");
		 }
		 else {
			logger.info("Reschedule success msg NOT verified");
			captureScreen(driver, "Reschedule success msg NOT verified ");
			Assert.assertTrue(false);
		 }
 	     //verify rescheduled received link on mail
 	     if(verify.verifyRescheduleKycLink().contains("just now")) {
 		      Assert.assertTrue(true);
			  logger.info("Reschedule kyc link received on mail verified");
		 }
		 else {
			   logger.info("Reschedule kyc link received on mail NOT verified");
			   captureScreen(driver, "Reschedule kyc link received on mail NOT verified ");
			   Assert.assertTrue(false);
		 }
 	     //verify rescheduled status at Agent end
 	     if(verify.verifyRescheduleStatusAtAgentEnd().contains("RESCHEDULED BY CUSTOMER")) {
 		        Assert.assertTrue(true);
			    logger.info("Reschedule STATUS verified at agent end");
		  }
		  else {
			    logger.info("Reschedule STATUS NOT verified at Agent End");
			    captureScreen(driver, "Reschedule STATUS NOT verified at Agent End");
			    Assert.assertTrue(false);
		  }
     }
}
