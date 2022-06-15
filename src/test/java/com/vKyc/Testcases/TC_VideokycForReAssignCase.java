package com.vKyc.Testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vKyc.Pageobject.Login;
import com.vKyc.Pageobject.videoKyc;
import com.vKyc.Utilities.RandomMethod;

public class TC_VideokycForReAssignCase extends Baseclass{

	//Admin initiate/upload a case for Re-assign by Auditor|Assert based on alert
    @Test(enabled = true, testName = "Admin uploads a case for Re-Assign", priority =1)
    public void uploadVKycCaseForReAssignByAuditor() throws IOException, InterruptedException {
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
   	  Thread.sleep(3000);
   	  log.logoutUser();
   	  logger.info("Admin loggedout successfully");
   }
   //Agent verify and schedules the case | Assert based on case availability in Scheduled bucket
   @Test(enabled = true, testName = "verify and schedule case for Re-Assign", priority = 2, dependsOnMethods = {
	 "uploadVKycCaseForReAssignByAuditor"})
   public void scheduleCaseByAgentForReAssign() throws InterruptedException, IOException {
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
   //Candidate checks for scheduled V-kyc mail and submits all Kyc Related info thru available link 
   //| Assert based on link availability
   @Test(enabled = true, testName = "vkyc started by customer for Re-Assign", priority =3, dependsOnMethods = {
	 "scheduleCaseByAgentForReAssign"})
   public void vKycProcessByCustomerForReAssign() throws InterruptedException, IOException {
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
   	   videoKyc initiate= new videoKyc(driver);
   	   initiate.customerClickOnKycLink();
   	   logger.info("v-kyc link hitted by customer");
   	   //Pan submit using Browse to upload
   	   videoKyc submit= new videoKyc(driver);
   	   submit.submitPanCardUsingBrowseToUpload();
   	   logger.info("pan card submitted by customer");
   	   Thread.sleep(15000);
   	   //click on AdharDoc Box
   	   videoKyc click= new videoKyc(driver);
   	   click.clickOnAdharDocBox();
   	   logger.info("clicked on AdharDoc Box after pan Uplaod");
   	   // submit Aadhar using browse to upload(repeat till uploaded successfully)
   	   for(int i=0;i<=7;i++) {
   	      videoKyc ver= new videoKyc(driver);
   	        if(ver.verifyToProceedAdharUpload().contentEquals("Aadhaar Image")) {
   		       videoKyc submits= new videoKyc(driver);
   		    if(submits.submitAadharUsingBrowseToUpload()==true) {
   	    		logger.info("aadhar upload successfull");
   	    		break;
   	    	}
   		  }
   	   }
       //verify Aadhaar details verified successfully
       videoKyc verify= new videoKyc(driver);
       if(verify.verifyDocDetailsCheckedSuccessMsg().contains("Aadhar Card")) {
    		 Assert.assertTrue(true);
			 logger.info("Submitted Adhar details verified successfully");
	   }
	   else {
			 logger.info("Submitted Adhar details  NOT verified");
			 captureScreen(driver, "Submitted Adhar  NOT verified ");
			 Assert.assertTrue(false);
	   }
  	   //start f2f video call by customer
   	   videoKyc vky=new videoKyc(driver);
   	   vky.startF2FVideoKycByCandidate();
   	   logger.info("f2f video kyc started by Customer");
   }
   //Agent starts vkyc with candidate and after it is successful it marks the case as completed
   // |Assert based on new case status as Pending with Auditor
   @Test(enabled = true, testName = "vkyc started by agent for Re-Assign", priority =4, dependsOnMethods = {
    "vKycProcessByCustomerForReAssign"})
    public void vkycProcessByAgentForCaseReAssignAtAuditorEnd() throws InterruptedException, IOException {
    	  videoKyc obj= new videoKyc(driver);
    	  obj.startVKycByAgent();
    	  logger.info("agent started f2f video process");
     	  String Text=RandomMethod.randomString();
        logger.info("got random string text");
     	  String mobNum=RandomMethod.randomMobileNumer();
     	  logger.info("got random mob no.");
        String Amount=RandomMethod.randomApplicantId();
     	  logger.info("got random amount");
    	  obj.doc_AddressVerification(Text,mobNum,Amount);
    	  logger.info("address verification started by agent");
    	  // address verification success message
    	  videoKyc verify= new videoKyc(driver);
    	  if(verify.verifyAddressVerificationSuccessMsg().contains("Location details has been successfully verified")) {
     		  Assert.assertTrue(true);
 			  logger.info("Address verified successfully by agent");
 		  }
 		  else {
 			 logger.info("Address verification NOT successfull");
 			 captureScreen(driver, "Address verification NOT successfull ");
 			 Assert.assertTrue(false);
 		  }
    	  videoKyc pan= new videoKyc(driver);
    	  pan.doc_PanVerification();
    	  logger.info("pan verification started by agent");
    	  //pan verification success msg
    	  videoKyc ver= new videoKyc(driver);
    	  if(ver.panverificationSuccessMsg().contains("PAN card has been successfully verified")) {
     		  Assert.assertTrue(true);
 			  logger.info("pan verified successfully by agent");
 		  }
 		  else {
 			  logger.info("pan verification NOT successfull");
 			  captureScreen(driver, "pan verification NOT successfull ");
 			  Assert.assertTrue(false);
 		  }
    	  videoKyc adhar= new videoKyc(driver);
    	  adhar.doc_AadharVerification();
    	  logger.info("Aadhar verification started by agent");
    	  //Aadhar verification success msg
    	  videoKyc veri= new videoKyc(driver);
    	  if(veri.aadharverificationSuccessMsg().contains("AADHAAR card has been successfully verified")) {
     		  Assert.assertTrue(true);
 			  logger.info("Aadhar verified successfully by agent");
 		  }
 		  else {
 			  logger.info("Aadhar verification NOT successfull");
 			  captureScreen(driver, "Aadhar verification NOT successfull");
 			  Assert.assertTrue(false);
 		  }
    	  videoKyc take= new videoKyc(driver);
    	  take.takeCustomerPictureUsingScreenshot();
    	  logger.info("customer picture taken by agent");
    	  //verify all documents verified success msg
    	  videoKyc verif= new videoKyc(driver);
    	  if(verif.docVerificationSuccessMsg().contains("All Documents Verified.")) {
     		  Assert.assertTrue(true);
 			  logger.info("All documents verified successfully by agent");
 		  }
 		  else {
 			  logger.info("All documents verification NOT successfull");
 			  captureScreen(driver, "All documents verification NOT successfull");
 			  Assert.assertTrue(false);
 		  }
    	  videoKyc mark= new videoKyc(driver);
    	  mark.markCaseAsCompleteByAgent();
    	  logger.info("case marked as complete by Agent");
    	  //verify case status at Agent End
    	  videoKyc verifi= new videoKyc(driver);
    	  if(verifi.verifyCaseStatusAtAgentEnd().contains("Pending with Auditor")) {
     		  Assert.assertTrue(true);
 			  logger.info("Case Status verified successfully by agent");
 		  }
 		  else {
 			  logger.info("Case Status verification NOT successfull");
 			  captureScreen(driver, "Case Status verification NOT successfull");
 			  Assert.assertTrue(false);
 		  }
    	  Login log = new Login(driver);
    	  log.logoutUser();
    	  logger.info("Agent loggedout successfully");
  }
  //Auditor Re-assignes the case |Assert based on case visibility in Active-ReAssign bucket
  @Test(enabled = true, testName = "Auditor marks case ReAssigned And Verify", priority =5, dependsOnMethods ={
   "vkycProcessByAgentForCaseReAssignAtAuditorEnd"})
  public void vkycCaseReAssignByAuditor() throws InterruptedException, IOException {
     	  driver.get(baseURL);
 		  logger.info("v-Kyc url opened");
 		  Login log = new Login(driver);
 		  log.loginVkyc(AuditorUsername, AuditorPassword);
 		  logger.info("Auditor LoggedIn successfully!");
 		  videoKyc approve= new videoKyc(driver);
 		  String Text=RandomMethod.randomString();
 		  logger.info("got feedback text");
 		  approve.reAssignCaseByAuditor(Text);
 		  logger.info("case ReAssigned by Auditor");
 		  Thread.sleep(3000);
 		  log.logoutUser();
		  logger.info("Auditor logged out successfully");
		  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  driver.get(baseURL);
		  logger.info("v-Kyc url opened");
		  Login logg = new Login(driver);
		  logg.loginVkyc(AdminUsername, AdminPassword);
		  logger.info("Admin LoggedIn successfully!");
		  videoKyc verify= new videoKyc(driver);
		  if(verify.verifyCaseInReassignedBucket()==true) {
		       Assert.assertTrue(true);
			   logger.info("ReAssigned Case verified successfully in ReAssigned Bucket");
		  }
		  else {
			   logger.info("ReAssigned Case NOT verified in ReAssigned Bucket");
			   captureScreen(driver, "ReAssigned Case NOT verified in ReAssigned Bucket");
			   Assert.assertTrue(false);
		  }
		  Thread.sleep(2000);
		  logg.logoutUser();
		  logger.info("Admin logged out successfully");
 	 }
}
