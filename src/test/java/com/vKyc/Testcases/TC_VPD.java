package com.vKyc.Testcases;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.vKyc.Pageobject.Login;
import com.vKyc.Pageobject.VPD;
import com.vKyc.Utilities.RandomMethod;


public class TC_VPD extends Baseclass{

	String filename;
	String pdfContent;
	String Videofilename;
	
	//Admin uploads a Vkyc Case | Assert based on alert
    @Test(enabled = true, testName = "Admin upload a case", priority = 1)
    public void uploadVpdCaseByAdmin() throws IOException, InterruptedException {
    	driver.get(baseURL);
		logger.info("VPD url opened");
		Login log = new Login(driver);
		log.loginVkyc(AdminUsername, AdminPassword);
		logger.info("Admin LoggedIn successfully!");
    	VPD upload= new VPD(driver);
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
    	VPD verify= new VPD(driver);
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
    //Auditor verify case in Assigned bucket | Assert based on case availability in Assigned bucket
    @Test(enabled = true, testName = "verify case in AssignedBucket", priority = 2, dependsOnMethods = {
	"uploadVpdCaseByAdmin"})
    public void verifyCaseInAssignedBucket() throws InterruptedException, IOException {
    	 driver.get(baseURL);
		 logger.info("VPD url opened");
		 Login log = new Login(driver);
		 log.loginVkyc(AuditorUsername, AuditorPassword);
		 logger.info("Auditor LoggedIn successfully!");
		 VPD verify=new VPD(driver);
		 if(verify.verifyCaseInAssignedBucket()==true) {
			 Assert.assertTrue(true);
			 logger.info("Assigned case verified successfully ");
		 }
		 else {
			logger.info("Assigned case NOT verified ");
			captureScreen(driver, "Assigned case NOT verified ");
			Assert.assertTrue(false); 
		 }
		 Thread.sleep(2000);
		 log.logoutUser();
		 logger.info("Auditor loggedout successfully");
	}
    // Auditor schedules case|Assert based on case availability in scheduled bucket
    @Test(enabled = true, testName = "schedule case & verify", priority = 3, dependsOnMethods ={
	"verifyCaseInAssignedBucket"})
    public void scheduleCaseByAuditor() throws InterruptedException, IOException {
    	driver.get(baseURL);
		logger.info("VPD url opened");
		Login log = new Login(driver);
		log.loginVkyc(AuditorUsername, AuditorPassword);
		logger.info("Auditor LoggedIn successfully!");
		String scheduledTime = RandomMethod.getScheduleTime();
		logger.info("got Scheduled time for vkyc");
		VPD schedule= new VPD(driver);
		schedule.scheduleCalendarForVPdByAuditor(scheduledTime);
		logger.info("entered scheduled Date and Time for VKyc");
		VPD verify= new VPD(driver);
    	//Assert based on CASE Visibility in scheduled bucket on Auditor end
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
   //Customer checks for scheduled VPD mail and hit on received link| Assert based on link availability|Assert based on 
   @Test(enabled = true, testName = "verify VPD link Received By Customer and hit on received link", priority = 4, dependsOnMethods = {
	"scheduleCaseByAuditor" })
    public void verifyVPDlinkReceivedByCustomer() throws InterruptedException, IOException {
    	VPD vrf= new VPD(driver);
    	//customer verify vkyc related link in mail
    	if(vrf.verifyVPDlinkReceivedByCustomer().contains("Click Here to Start")) {
    		Assert.assertTrue(true);
			logger.info("vpd link received on candidate mail verified successfully");
		}
		else {
			logger.info("vpd link received on candidate mail NOT verified");
			captureScreen(driver, "vpd link received on candidate mail NOT verified ");
			Assert.assertTrue(false);
		}
    	VPD click= new VPD(driver);
    	click.customerClickOnKycLink();
    	logger.info("vpd link hit by customer & compliance checked on page");
    }
   //Customer submits all required documents thru available link and starts vpt call |assert based on doc uploaded 
   @Test(enabled = true, testName = "Customer submits documents and starts call", priority = 5, dependsOnMethods = {
	"verifyVPDlinkReceivedByCustomer" })
   public void submitDocumentsAndStartVptByCustomer() throws InterruptedException, IOException {
	   VPD submit= new VPD(driver);
	   submit.submitDocuments();
	   logger.info("customer submits documents");
	   VPD verify= new VPD(driver);
	   if(verify.verifyAllDocUpload().contains("VIDEO PD INSTRUCTIONS")) {
   		    Assert.assertTrue(true);
			logger.info("All Documents submission verified successfully by customer");
		}
		else {
			logger.info("All Documents submission by customer NOT verified");
			captureScreen(driver, "All Documents submission by customer NOT verified ");
			Assert.assertTrue(false);
		}
	   //verify vpd call started by customer
	   VPD start= new VPD(driver);
	   if(start.startVpdCallByCustomerAndVerify().contains("Waiting for agent to start the call")) {
  		    Assert.assertTrue(true);
			logger.info("vpd call started by customer verified successfully");
		}
		else {
			logger.info("vpd call started by customer NOT verified");
			captureScreen(driver, "vpd call started by customer NOT verified ");
			Assert.assertTrue(false);
		}
    }
   
    //Auditor starts vpt with candidate and after it is succesfull it marks the case as completed | Assert based on new case status as completed
    @Test(enabled = true, testName = "Auditor starts vpd call and completes case", priority = 6, dependsOnMethods = {
	"submitDocumentsAndStartVptByCustomer" })
    public void completeCaseByAuditor() throws InterruptedException, IOException {
	    VPD start= new VPD(driver);
	    start.startVPDbyAuditor();
	    logger.info("vpd call started by auditor");
   	    String Text=RandomMethod.randomString();
        logger.info("got random string text");
   	    String mobNum=RandomMethod.randomMobileNumer();
   	    logger.info("got random mob no.");
        String Amount=RandomMethod.randomApplicantId();
   	    logger.info("got random amount");
   	    String pdDate = RandomMethod.getPresentDate();
	    logger.info("got pd conducted date/present date");
	    VPD cst= new VPD(driver);
	    cst.customerVerificationByAuditor(Text,mobNum,Amount,pdDate);
	    logger.info("customer verification/questionnaire done by auditor");
	    VPD verify= new VPD(driver);
	    //verify details & documents submission success msg
	    if(verify.verifyDetailsAndDocSubmitSuccessMsg().contains("All steps completed.")) {
   		    Assert.assertTrue(true);
			logger.info("All Details & documents submission verified successfully by auditor");
	    }
	    else {
			logger.info("All Details & documents submission NOT verified");
			captureScreen(driver, "All Details & documents submission NOT verified ");
			Assert.assertTrue(false);
	    }
	    VPD mark= new VPD(driver);
	    mark.markCaseAsComplete();
	    logger.info("case marked as complete by Auditor");
	    VPD ver= new VPD(driver);
	    //verify case in Completed Bucket
	    if(ver.verifyCompletedCase()==true) {
   		    Assert.assertTrue(true);
			logger.info("completed case verified in Bucket successfully");
	    }
	    else {
			logger.info("completed case NOT verified");
			captureScreen(driver, "completed case NOT verified ");
			Assert.assertTrue(false);
	    }
	    Thread.sleep(2000);
	    Login log = new Login(driver);
	    log.logoutUser();
	    logger.info("Auditor logged out successfully");
	   
    }
    //Auditor finally marks case as Approved |Assert based on new case status as Approved
    @Test(enabled = true, testName = "mark case as Approved by Auditor", priority = 7,dependsOnMethods = {
	"completeCaseByAuditor" })
    public void markCaseAsApprovedByAuditor() throws InterruptedException, IOException{
    	driver.get(baseURL);
		logger.info("VPD url opened");
		Login log = new Login(driver);
		log.loginVkyc(AuditorUsername, AuditorPassword);
		logger.info("Auditor LoggedIn successfully!");
    	VPD approve= new VPD(driver);
		approve.approveCaseByAuditor();
		logger.info("case approved by auditor");
		VPD verify= new VPD(driver);
		//verify approved case status by auditor
		if(verify.verifyCaseApprovedStatus().contains("Approved")) {
    		Assert.assertTrue(true);
			logger.info("Approved Case Status verified successfully by auditor");
		}
		else {
			logger.info("Approved Case Status NOT verified");
			captureScreen(driver, "Approved Case Status NOT verified");
			Assert.assertTrue(false);
		}
		Thread.sleep(2000);
		log.logoutUser();
		logger.info("Auditor loggedout successfully");
     }
     //Auditor download and verify Report |Assert based on number of files count
     @Test(enabled = true, testName = "download and verify Report", priority = 8, dependsOnMethods = {
	 "markCaseAsApprovedByAuditor" })
     public void downloadAndVerifyReport() throws InterruptedException, IOException{
    	 driver.get(baseURL);
		 logger.info("VPD url opened");
		 Login log = new Login(driver);
		 log.loginVkyc(AuditorUsername, AuditorPassword);
		 logger.info("Auditor LoggedIn successfully!");
    	 File folder = new File("/home/shashi.ranjan/Downloads/VpdReport");
		 File fListInitial[] = folder.listFiles();
		 int initialNumberOfPDFs = fListInitial.length;
		 VPD click= new VPD(driver);
		 click.clickOnGeneratedReport();
		 logger.info("clicked on Generated report download icon");
		 File fListFinal[] = folder.listFiles();
		 //verify report generated 
		 if(initialNumberOfPDFs + 1== fListFinal.length) {
		    Assert.assertTrue(true);
		 	logger.info("report generated verified successfully");
		 }
		 else {
		 	logger.info("report generated NOT verified");
		 	captureScreen(driver, "report generated NOT verified");
		 	Assert.assertTrue(false);
		 }
		 Thread.sleep(5000);
		 log.logoutUser();
		 logger.info("Auditor logged out successfully");
      }
      //Assert based on customer name on REPORT Generated pdf
      @Test(enabled = true, testName = "verify customer name on REPORT Generated pdf", priority = 9, dependsOnMethods = {
 	 "downloadAndVerifyReport" })
      public void verifyCustomerName() throws IOException {
	      File folder = new File("/home/shashi.ranjan/Downloads/VpdReport");
	      File[] listOfFiles = folder.listFiles();
          for (int i = 0; i < listOfFiles.length; i++) {
	         if (listOfFiles[i].isFile()) {
	           filename= listOfFiles[i].getName();
	          }
	         // listOfFiles[i].deleteOnExit();
	       }
           File file = new File("/home/shashi.ranjan/Downloads/VpdReport/"+filename+"");
		   FileInputStream fis =new FileInputStream(file);
		   BufferedInputStream fileParse = new BufferedInputStream(fis);
		   PDDocument document = null;
		   document = PDDocument.load(fileParse);
	       pdfContent = new PDFTextStripper().getText(document);
		   if(pdfContent.contains("Shashi")) {
			   Assert.assertTrue(true);
			   logger.info("Customer name on Report verified successfully");
		    }
		    else {
			   logger.info("Customer name on Report NOT verified");
			   Assert.assertTrue(false);
		     }
	    }
        //Assert based on call start date on REPORT Generated page
        @Test(enabled = true, testName = "verify call start date", priority = 10)
        public void verifyCallStartDate() throws IOException {
    	     String scheduledDate = RandomMethod.getPresentDate();
    	     logger.info("got call start date");
    	     if(pdfContent.contains(scheduledDate)) {
			    Assert.assertTrue(true);
			    logger.info("call start date on Report verified successfully");
		      }
		      else {
			     logger.info("call start date on Report NOT verified");
			     Assert.assertTrue(false);
		   }
        }
        //Assert based on verification step2 :Geo-tagging address fetch on REPORT Generated page
        @Test(enabled = true, testName = "verify geoTag Address", priority = 11)
        public void verifyGeoTagAddress() throws IOException {
    	    String data = new String(Files.readAllBytes(Paths.get("geoTagAddress.txt")));
		    String geoTagAddress[] = data.split(" ");
		    logger.info("got GeoTag Address ");
		    if(pdfContent.contains(geoTagAddress[0])) {
			   Assert.assertTrue(true);
			   logger.info("Geo Tag Address on Report verified successfully");
		     }
		     else {
			    logger.info("Geo Tag Address on Report NOT verified");
			    Assert.assertTrue(false);
		     }
        }
       
       //Check at Auditor End VPD Recording is available or not |Assert based on available download
        @Test(enabled = true, testName = "download and verify recorded kyc video", priority = 12)
        public void downloadAndVerifyRecordedVideo() throws IOException, InterruptedException {
       	    driver.get(baseURL);
   		    logger.info("VPD url opened");
   		    Login log = new Login(driver);
   		    log.loginVkyc(AuditorUsername, AuditorPassword);
   		    logger.info("Auditor LoggedIn successfully!");
   		    VPD verify= new VPD(driver);
		    //verify recorded VPD status code
		    if(verify.getDownloadVideoUrlStatusCode()==200) {
  		         Assert.assertTrue(true);
  		 	     logger.info("downloaded recorded VPD verified successfully");
  		     }
  		     else {
  		 	     logger.info("downloaded recorded VPD NOT verified");
  		 	     captureScreen(driver, "approved audit status on Report NOT verified");
  		 	     Assert.assertTrue(false);
  		      }
 
        }
       //finally check the case availablity in completed-Approved Bucket at Admin end|Assert based on case availablity in concerned bucket
        @Test(enabled = true, testName = "finally check case availablity in completed-Approved Bucket At Admin End", priority =13)
        public void checkCaseAvailabilityinApprovedBucket() throws InterruptedException, IOException {
        	 Set<String> allWindows = driver.getWindowHandles();
   		     ArrayList<String> tabs = new ArrayList<String>(allWindows);
   		     driver.switchTo().window(tabs.get(0));
        	 driver.get(baseURL);
   		     logger.info("VPD url opened");
   		     Login log = new Login(driver);
   		     log.loginVkyc(AdminUsername, AdminPassword);
   		     logger.info("Admin LoggedIn successfully!");
   		     VPD verify= new VPD(driver);
   		     if(verify.verifyCaseInApprovedBucket()==true) {
       		     Assert.assertTrue(true);
   			     logger.info("Approved Case verified successfully in Approved Bucket");
   		     }
   		     else {
   			     logger.info("Approved Case NOT verified in Approved Bucket");
   			     captureScreen(driver, "Approved Case NOT verified in Approved Bucket");
   			     Assert.assertTrue(false);
   		     }
   		     Thread.sleep(2000);
   		     log.logoutUser();
   		     logger.info("Admin logged out successfully");
         }
        //Admin uploads the cases using “upload cases” option via Excel |Assert based on Alert 
        @Test(enabled =false, testName = "upload case using excel", priority = 14)
        public void uploadCaseUsingExcel() throws InterruptedException, IOException {
       	    driver.get(baseURL);
   		    logger.info("VPD url opened");
   		    Login log = new Login(driver);
   		    log.loginVkyc(AdminUsername, AdminPassword);
   		    logger.info("Admin LoggedIn successfully!");
   		    VPD upload= new VPD(driver);
   		    upload.uploadCaseUsingExcel();
   		    logger.info("case uploaded using excel");
   		    VPD verify= new VPD(driver);
   		    if(verify.verifyCaseUploadSuccessMsg().contains("CASES UPLOADED SUCCESSFULLY")) {
  		       Assert.assertTrue(true);
  		 	   logger.info("Case Upload using Excel Success Msg verified successfully");
  		    }
  		    else {
  		 	   logger.info("Case Upload using Excel Success Msg NOT verified");
  		 	   captureScreen(driver, "Case Upload using Excel Success Msg NOT verified");
  		 	   Assert.assertTrue(false);
  		    }
   		    Thread.sleep(7000);
   		    log.logoutUser();
   		    logger.info("Admin loggedOut Successfully");
        }
        //Check at  Auditor End whether Uploaded case is available or not
        @Test(enabled =false, testName="verify Uploaded case thru excel at Agent End",priority = 15,dependsOnMethods = {
	     "uploadCaseUsingExcel"})
        public void verifyCaseUploadAtAuditorEnd() throws IOException, InterruptedException {
       	     driver.get(baseURL);
    		 logger.info("VPD url opened");
    		 Login log = new Login(driver);
    		 log.loginVkyc(AuditorUsername, AuditorPassword);
    		 logger.info("Auitor LoggedIn successfully!");
    		 VPD verify= new VPD(driver);
   		     if(verify.verifyCaseUploadedUsingExcelAtAuditorEnd()==true) {
  		         Assert.assertTrue(true);
  		 	     logger.info("Case Upload using Excel verified successfully  at Agent End");
  		     }
  		     else {
  		 	     logger.info("Case Upload using Excel NOT verified  at Agent End");
  		 	     captureScreen(driver, "Case Upload using Excel NOT verified  at Agent End");
  		 	     Assert.assertTrue(false);
  		     }
   		     Thread.sleep(2000);
   		     log.logoutUser();
   		     logger.info("Auditor loggedOut successfully");
    	 }
        //Admin uploads a VPD Case for reject | Assert based on alert
        @Test(enabled = true, testName = "Admin upload a case for Reject", priority = 16)
        public void uploadVpdCaseByAdminForRejectingByAuditor() throws IOException, InterruptedException {
       	  driver.get(baseURL);
   		  logger.info("VPD url opened");
   		  Login log = new Login(driver);
   		  log.loginVkyc(AdminUsername, AdminPassword);
   		  logger.info("Admin LoggedIn successfully!");
       	  VPD upload= new VPD(driver);
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
       	  VPD verify= new VPD(driver);
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
       	  Thread.sleep(2000);
       	  log.logoutUser();
       	  logger.info("Admin loggedout successfully");
        }
        // Auditor checks case in assigned bucket and schedules case|Assert based on case availability in scheduled bucket
        @Test(enabled = true, testName = "SCHEDULE and Verify case by Auditor for Reject", priority = 17, dependsOnMethods = {
    	"uploadVpdCaseByAdminForRejectingByAuditor"})
        public void scheduleCaseByAuditorForRejectByAuditor() throws InterruptedException, IOException {
        	driver.get(baseURL);
    		logger.info("VPD url opened");
    		Login log = new Login(driver);
    		log.loginVkyc(AuditorUsername, AuditorPassword);
    		logger.info("Auditor LoggedIn successfully!");
    		String scheduledTime = RandomMethod.getScheduleTime();
    		logger.info("got Scheduled time for VPD");
    		VPD schedule= new VPD(driver);
    		schedule.scheduleCalendarForVPdByAuditor(scheduledTime);
    		logger.info("entered scheduled Date and Time for VPD");
    		VPD verify= new VPD(driver);
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
       //Candidate checks for scheduled VPD mail and submits all Kyc Related info thru available link | Assert based on link availability
       @Test(enabled = true, testName = "VPD started by customer for Rejecting case", priority =18, dependsOnMethods = {
    	"scheduleCaseByAuditorForRejectByAuditor" })
        public void vpdProcessByCustomerForRejectByAuditor() throws InterruptedException, IOException {
        	VPD vrf= new VPD(driver);
        	//customer verify VPD related link in mail
        	if(vrf.verifyVPDlinkReceivedByCustomer().contains("Click Here to Start")) {
        		Assert.assertTrue(true);
    			logger.info("VPD link received on candidate mail verified successfully");
    		}
    		else {
    			logger.info("VPD link received on candidate mail NOT verified");
    			captureScreen(driver, "VPD link received on candidate mail NOT verified ");
    			Assert.assertTrue(false);
    		}
        	//ClearBrowserCache();
      	   // logger.info("browser cache cleared");
        	VPD initiate= new VPD(driver);
        	initiate.customerClickOnKycLink();
        	logger.info("VPD link hit by customer & compliance checked on page");
        }
       //Customer submits all required documents thru available link and starts vpt call |assert based on doc uploaded 
       @Test(enabled =true,testName ="Customer submits documents and starts call for Rejecting Case",priority =19, dependsOnMethods = {
    	"vpdProcessByCustomerForRejectByAuditor" })
       public void submitDocumentsAndStartVptByCustomerForRejectByAuditor() throws InterruptedException, IOException {
    	   VPD submit= new VPD(driver);
    	   submit.submitDocuments();
    	   logger.info("customer submits documents");
    	   VPD verify= new VPD(driver);
    	   if(verify.verifyAllDocUpload().contains("VIDEO PD INSTRUCTIONS")) {
       		    Assert.assertTrue(true);
    			logger.info("All Documents submission verified successfully by customer");
    		}
    		else {
    			logger.info("All Documents submission by customer NOT verified");
    			captureScreen(driver, "All Documents submission by customer NOT verified ");
    			Assert.assertTrue(false);
    		}
    	   //verify vpd call started by customer
    	   VPD start= new VPD(driver);
    	   if(start.startVpdCallByCustomerAndVerify().contains("Waiting for agent to start the call")) {
      		    Assert.assertTrue(true);
    			logger.info("vpd call started by customer verified successfully");
    		}
    		else {
    			logger.info("vpd call started by customer NOT verified");
    			captureScreen(driver, "vpd call started by customer NOT verified ");
    			Assert.assertTrue(false);
    		}
        }
       
        //Auditor starts VPD with candidate and after it is succesfull it marks the case as completed | Assert based on new case status as completed
        @Test(enabled = true, testName ="Auditor starts vpd call and completes case For Rejecting Case",priority =20, dependsOnMethods = {
    	"submitDocumentsAndStartVptByCustomerForRejectByAuditor" })
        public void completeCaseByAuditorForRejectByAuditor() throws InterruptedException, IOException {
    	   VPD start= new VPD(driver);
    	   start.startVPDbyAuditor();
    	   logger.info("vpd call started by auditor");
       	   String Text=RandomMethod.randomString();
           logger.info("got random string text");
       	   String mobNum=RandomMethod.randomMobileNumer();
       	   logger.info("got random mob no.");
           String Amount=RandomMethod.randomApplicantId();
       	   logger.info("got random amount");
       	   String pdDate = RandomMethod.getPresentDate();
    	   logger.info("got pd conducted date/present date");
    	   VPD cst= new VPD(driver);
    	   cst.customerVerificationByAuditor(Text,mobNum,Amount,pdDate);
    	   logger.info("customer verification/questionnaire done by auditor");
    	   VPD verify= new VPD(driver);
    	   //verify details & documents submission success msg
    	   if(verify.verifyDetailsAndDocSubmitSuccessMsg().contains("All steps completed.")) {
       		    Assert.assertTrue(true);
    			logger.info("All Details & documents submission verified successfully by auditor");
    	   }
    	   else {
    			logger.info("All Details & documents submission NOT verified");
    			captureScreen(driver, "All Details & documents submission NOT verified ");
    			Assert.assertTrue(false);
    	   }
    	   VPD mark= new VPD(driver);
    	   mark.markCaseAsComplete();
    	   logger.info("case marked as complete by Auditor");
    	   VPD ver= new VPD(driver);
    	   //verify case in Completed Bucket
    	   if(ver.verifyCompletedCase()==true) {
       		    Assert.assertTrue(true);
    			logger.info("Case marked as completed verified successfully by auditor");
    	   }
    	   else {
    			logger.info("Case marked as completed NOT verified");
    			captureScreen(driver, "Case marked as completed NOT verified ");
    			Assert.assertTrue(false);
    	   }
    	   Thread.sleep(3000);
    	   Login log = new Login(driver);
    	   log.logoutUser();
    	   logger.info("Auditor logged out successfully");
      }
      //Auditor marks case as Rejected |Assert based on case status as Rejected
      @Test(enabled = true, testName = "Auditor marks case Rejected And Verify case status", priority =21,dependsOnMethods = {
      "completeCaseByAuditorForRejectByAuditor"})
      public void vpdCaseRejectedByAuditor() throws InterruptedException, IOException {
          	  driver.get(baseURL);
      		  logger.info("VPD url opened");
      		  Login log = new Login(driver);
      		  log.loginVkyc(AuditorUsername, AuditorPassword);
      		  logger.info("Auditor LoggedIn successfully!");
      		  VPD reject= new VPD(driver);
      		  String Text=RandomMethod.randomString();
      		  logger.info("got feedback text");
      		  reject.rejectCaseByAuditor(Text);
      		  logger.info("case Rejected by Auditor");
      		  Thread.sleep(4000);
      		  //verify rejected case status by auditor
      		  VPD verify= new VPD(driver);
      		  if(verify.verifyCaseRejectedStatus().contains("Rejected")) {
           		   Assert.assertTrue(true);
       			   logger.info("Rejected Case Status verified successfully by auditor");
       		  }
       		  else {
       			  logger.info("Rejected Case Status NOT verified");
       			  captureScreen(driver, "Rejected Case Status NOT verified");
       			  Assert.assertTrue(false);
       		  }
      		  Thread.sleep(2000);
      		  log.logoutUser();
      		  logger.info("Auditor logged out successfully");
        }
      //check the case availablity in completed-Rejected Bucket at Admin end|Assert based on case availablity in rejected bucket
      @Test(enabled = true, testName = "check case availablity in completed-Rejected BucketAt Admin", priority =22,dependsOnMethods = {
      "vpdCaseRejectedByAuditor"})
      public void checkCaseAvailabilityinRejectedBucket() throws InterruptedException, IOException {
    	     driver.get(baseURL);
		     logger.info("VPD url opened");
		     Login log = new Login(driver);
		     log.loginVkyc(AdminUsername, AdminPassword);
		     logger.info("Admin LoggedIn successfully!");
		     VPD verify= new VPD(driver);
		     if(verify.verifyCaseInRejectedBucket()==true) {
    		     Assert.assertTrue(true);
			     logger.info("Rejected Case verified successfully in Rejected Bucket");
		     }
		     else {
			     logger.info("Rejected Case NOT verified in Rejected Bucket");
			     captureScreen(driver, "Rejected Case NOT verified in Rejected Bucket");
			     Assert.assertTrue(false);
		     }
		     Thread.sleep(2000);
		     log.logoutUser();
		     logger.info("Admin logged out successfully");
        }
      
      
       //Admin uploads a VPD Case | Assert based on alert
       @Test(enabled = true, testName = "Admin upload a case for Reassigning Case", priority = 23)
       public void uploadVpdCaseByAdminForReAssignByAuditor() throws IOException, InterruptedException {
      	  driver.get(baseURL);
  		  logger.info("VPD url opened");
  		  Login log = new Login(driver);
  		  log.loginVkyc(AdminUsername, AdminPassword);
  		  logger.info("Admin LoggedIn successfully!");
      	  VPD upload= new VPD(driver);
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
      	  VPD verify= new VPD(driver);
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
      	  Thread.sleep(2000);
      	  log.logoutUser();
      	  logger.info("Admin loggedout successfully");
       }
      // Auditor checks case in assigned bucket and schedules case|Assert based on case availability in scheduled bucket
      @Test(enabled = true, testName = "verify and schedule case by Auditor for Reassign case", priority =24, dependsOnMethods = {
  	  "uploadVpdCaseByAdminForReAssignByAuditor" })
      public void scheduleCaseByAuditorForReassignByAuditor() throws InterruptedException, IOException {
      	  driver.get(baseURL);
  		  logger.info("VPD url opened");
  		  Login log = new Login(driver);
  		  log.loginVkyc(AuditorUsername, AuditorPassword);
  		  logger.info("Auditor LoggedIn successfully!");
  		  String scheduledTime = RandomMethod.getScheduleTime();
  		  logger.info("got Scheduled time for VPD");
  		  VPD schedule= new VPD(driver);
  		  schedule.scheduleCalendarForVPdByAuditor(scheduledTime);
  		  logger.info("entered scheduled Date and Time for VPD");
  		  VPD verify= new VPD(driver);
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
      //Candidate checks for scheduled VPD mail and submits all Kyc Related info thru available link | Assert based on link availability
      @Test(enabled = true, testName = "VPD started by customer for Reassigning case", priority =25, dependsOnMethods = {
  	  "scheduleCaseByAuditorForReassignByAuditor" })
       public void vpdProcessByCustomerForReassignByAuditor() throws InterruptedException, IOException {
      	   VPD vrf= new VPD(driver);
      	   //customer verify VPD related link in mail
      	   if(vrf.verifyVPDlinkReceivedByCustomer().contains("Click Here to Start")) {
      		  Assert.assertTrue(true);
  			  logger.info("VPD link received on candidate mail verified successfully");
  		    }
  		    else {
  			  logger.info("VPD link received on candidate mail NOT verified");
  			  captureScreen(driver, "VPD link received on candidate mail NOT verified ");
  			  Assert.assertTrue(false);
  		    }
      	   // ClearBrowserCache();
      	   // logger.info("browser cache cleared");
      	    VPD initiate= new VPD(driver);
      	    initiate.customerClickOnKycLink();
      	    logger.info("VPD link hit by customer & compliance checked on page");
      }
     //Customer submits all required documents thru available link and starts vpt call |assert based on doc uploaded 
     @Test(enabled = true, testName = "Customer submits documents and starts call for Reassigning case",priority =26,dependsOnMethods = {
  	 "vpdProcessByCustomerForReassignByAuditor" })
     public void submitDocumentsAndStartVptByCustomerForReassignByAuditor() throws InterruptedException, IOException {
  	      VPD submit= new VPD(driver);
  	      submit.submitDocuments();
  	      logger.info("customer submits documents");
  	      VPD verify= new VPD(driver);
  	      if(verify.verifyAllDocUpload().contains("VIDEO PD INSTRUCTIONS")) {
     		   Assert.assertTrue(true);
  			   logger.info("All Documents submission verified successfully by customer");
  		   }
  		   else {
  			   logger.info("All Documents submission by customer NOT verified");
  			   captureScreen(driver, "All Documents submission by customer NOT verified ");
  			   Assert.assertTrue(false);
  		    }
  	        //verify vpd call started by customer
  	        VPD start= new VPD(driver);
  	        if(start.startVpdCallByCustomerAndVerify().contains("Waiting for agent to start the call")) {
    		    Assert.assertTrue(true);
  			    logger.info("vpd call started by customer verified successfully");
  		     }
  		     else {
  			    logger.info("vpd call started by customer NOT verified");
  			    captureScreen(driver, "vpd call started by customer NOT verified ");
  			    Assert.assertTrue(false);
  		      }
      }
     
      //Auditor starts vpt with candidate and after it is succesfull it marks the case as completed | Assert based on new case status as completed
      @Test(enabled = true, testName = "Auditor starts vpd call and completes case for Reassigning case",priority =27, dependsOnMethods = {
  	  "submitDocumentsAndStartVptByCustomerForReassignByAuditor" })
      public void completeCaseByAuditorForReassignByAuditor() throws InterruptedException, IOException {
  	       VPD start= new VPD(driver);
  	       start.startVPDbyAuditor();
  	       logger.info("vpd call started by auditor");
     	   String Text=RandomMethod.randomString();
           logger.info("got random string text");
     	   String mobNum=RandomMethod.randomMobileNumer();
     	   logger.info("got random mob no.");
           String Amount=RandomMethod.randomApplicantId();
     	   logger.info("got random amount");
     	   String pdDate = RandomMethod.getPresentDate();
  	       logger.info("got pd conducted date/present date");
  	       VPD cst= new VPD(driver);
  	       cst.customerVerificationByAuditor(Text,mobNum,Amount,pdDate);
  	       logger.info("customer verification/questionnaire done by auditor");
  	       VPD verify= new VPD(driver);
  	       //verify details & documents submission success msg
  	       if(verify.verifyDetailsAndDocSubmitSuccessMsg().contains("All steps completed.")) {
     		     Assert.assertTrue(true);
  			     logger.info("All Details & documents submission verified successfully by auditor");
  	        }
  	        else {
  			     logger.info("All Details & documents submission NOT verified");
  			     captureScreen(driver, "All Details & documents submission NOT verified ");
  			     Assert.assertTrue(false);
  	         }
  	         VPD mark= new VPD(driver);
  	         mark.markCaseAsComplete();
  	         logger.info("case marked as complete by Auditor");
  	         VPD ver= new VPD(driver);
  	         //verify case in Completed Bucket
  	         if(ver.verifyCompletedCase()==true) {
     		      Assert.assertTrue(true);
  			      logger.info("Case marked as completed verified successfully by auditor");
  	          }
  	          else {
  			       logger.info("Case marked as completed NOT verified");
  			       captureScreen(driver, "Case marked as completed NOT verified ");
  			       Assert.assertTrue(false);
  	          }
  	         Thread.sleep(2000);
  	         Login log = new Login(driver);
       	     log.logoutUser();
       	     logger.info("Auditor loggedout successfully");
  	   }
      //Auditor marks case as ReAssigned |Assert based on case visibility in active-Reassigned Bucket At Admin end
      @Test(enabled = true, testName = "Auditor marks case ReAssigned And Verify case At Admin", priority =28,dependsOnMethods = {
      "completeCaseByAuditorForReassignByAuditor"})
      public void vpdCaseReAssignByAuditor() throws InterruptedException, IOException {
    	    driver.get(baseURL);
   		    logger.info("VPD url opened");
   		    Login log = new Login(driver);
   		    log.loginVkyc(AuditorUsername, AuditorPassword);
   		    logger.info("Auditor LoggedIn successfully!");
   		    VPD approve= new VPD(driver);
   		    String Text=RandomMethod.randomString();
   		    logger.info("got feedback text");
   		    approve.reAssignCaseByAuditor(Text);
   		    logger.info("case ReAssigned by Auditor");
   		    Thread.sleep(1000);
   		    log.logoutUser();
  		    logger.info("Auditor logged out successfully");
  		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  		    driver.get(baseURL);
		    logger.info("VPD url opened");
		    Login logg = new Login(driver);
		    logg.loginVkyc(AdminUsername, AdminPassword);
		    logger.info("Admin LoggedIn successfully!");
		    VPD verify= new VPD(driver);
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
