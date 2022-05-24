package com.vKyc.Testcases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.vKyc.Pageobject.Login;
import com.vKyc.Pageobject.videoKyc;
import com.vKyc.Utilities.RandomMethod;

   
public class TC_VideoKyc extends Baseclass {
	
	
	 String Videofilename;

	 //Admin initiate/upload a case for V-KYC Process|Assert based on alert
     @Test(enabled = false, testName = "Admin upload a case", priority = 1)
     public void uploadVKycCaseByAdmin() throws IOException, InterruptedException {
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
     @Test(enabled = false, testName = "verify case in AssignedBucket", priority = 2, dependsOnMethods = {
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
 		 Thread.sleep(50);
 		 log.logoutUser();
 		 logger.info("Agent loggedout successfully");
 	}
    
    //Agent schedules case | Assert based on case availability in Scheduled bucket
    @Test(enabled = false, testName = "schedule & verify case", priority = 3, dependsOnMethods = {
	"verifyCaseInAssignedBucket" })
    public void scheduleCaseByAgent() throws InterruptedException, IOException {
    	 driver.get(baseURL);
		 logger.info("v-Kyc url opened");
		 Login log = new Login(driver);
		 log.loginVkyc(AgentUsername, AgentPassword);
		 logger.info("Agent LoggedIn successfully!");
		 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
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
    @Test(enabled = false, testName = "vkyc started by customer", priority = 4, dependsOnMethods = {
	"scheduleCaseByAgent" })
    public void vKycProcessByCustomer() throws InterruptedException, IOException {
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
    	//ClearBrowserCache();
     	logger.info("browser cache cleared");
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
    @Test(enabled = false, testName = "vkyc started by agent", priority = 5, dependsOnMethods = {
   	"vKycProcessByCustomer" })
    public void vkycProcessByAgent() throws InterruptedException, IOException {
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
    	Thread.sleep(2000);
    	Login log = new Login(driver);
    	log.logoutUser();
    	logger.info("Agent loggedout successfully");
     }
    //Auditor verifies completed case and marks it as Approved | Assert based on alert|Assert based on case status as approved
    //verify report generated(url) status code |Assert based on status code
    @Test(enabled = false, testName = "Auditor marks case Approved And Verify report generation status code", priority = 6, dependsOnMethods = {
   	"vkycProcessByAgent" })
    public void vkycCaseApprovedByAuditor() throws InterruptedException, IOException {
    	  driver.get(baseURL);
		  logger.info("v-Kyc url opened");
		  Login log = new Login(driver);
		  log.loginVkyc(AuditorUsername, AuditorPassword);
		  logger.info("Auditor LoggedIn successfully!");
		  videoKyc approve= new videoKyc(driver);
		  approve.approveCaseByAuditor();
		  logger.info("case approved by auditor");
		  //verify approved status by auditor
		  videoKyc verify= new videoKyc(driver);
		  if(verify.verifyCaseApprovedStatus().contains("Approved")) {
     		   Assert.assertTrue(true);
 			   logger.info("Approved Case Status verified successfully by auditor");
 		  }
 		  else {
 			  logger.info("Approved Case Status NOT verified");
 			  captureScreen(driver, "Approved Case Status NOT verified");
 			  Assert.assertTrue(false);
 		  }
		  //verify report generated(url) status code
		  videoKyc ver= new videoKyc(driver);
		  if(ver.getReportGeneratedStatusCode()==200) {
     		  Assert.assertTrue(true);
 			  logger.info("report generated(url) Status verified successfully");
 		  }
 		  else {
 			  logger.info("report generated(url) Status NOT verified");
 			  captureScreen(driver, "report generated(url) Status NOT verified");
 			  Assert.assertTrue(false);
 		   }
        }
		//Assert based on candidate name  on REPORT Generated page
		@Test(enabled = false, testName = "verify candidate name on report", priority = 7,dependsOnMethods = {
	   	"vkycCaseApprovedByAuditor" })
		public void verifyCandidateNameonReport() throws IOException, InterruptedException {
			videoKyc verify= new videoKyc(driver);
			String data = new String(Files.readAllBytes(Paths.get("candidateName.txt")));
			String candidateName[] = data.split(" ");
			logger.info("got candidate Name");
			if(verify.verifyCandidateNameOnReport().contentEquals(candidateName[0].substring(0, 1)+candidateName[0].substring(1, 6).toLowerCase())){
				  Assert.assertTrue(true);
 			      logger.info("candidate Name on Report verified successfully");
 		     }
 		     else {
 			      logger.info("candidate Name on Report  NOT verified");
 			      captureScreen(driver, "candidate Name on Report NOT verified");
 			      Assert.assertTrue(false);
 		     }
		}
		//Assert based on call start date and time/ call end date and time on REPORT Generated page
        @Test(enabled =false, testName = "verify Call Start And EndDate on report", priority = 8,dependsOnMethods = {
	   	"verifyCandidateNameonReport" })
		public void verifyCallStartAndEndDateOnReport() throws IOException, InterruptedException {
			videoKyc verify= new videoKyc(driver);
			String scheduledDate = RandomMethod.getPresentDate().substring(0, 2);
			logger.info("got vkyc call date ");
			if(verify.verifyCallStartDate().contains(scheduledDate)){
				  Assert.assertTrue(true);
			      logger.info("call start date on Report verified successfully");
		     }
		     else {
			      logger.info("call start date on Report  NOT verified");
			      captureScreen(driver, "call start date on Report NOT verified");
			      Assert.assertTrue(false);
		     }
			 //verify call end date on report
			 if(verify.verifyCallEndDate().contains(scheduledDate)){
				  Assert.assertTrue(true);
			      logger.info("call end date on Report verified successfully");
		     }
		     else {
			      logger.info("call end date on Report  NOT verified");
			      captureScreen(driver, "call end date on Report NOT verified");
			      Assert.assertTrue(false);
		     }
		}
		//Assert based on verification step2 :Geo-tagging address fetch on REPORT Generated page
        @Test(enabled = false, testName = "verify geotagging address on report", priority = 9,dependsOnMethods = {
	   	"verifyCallStartAndEndDateOnReport" })
		public void verifyGeotaggingAddressOnReport() throws IOException, InterruptedException {
        	videoKyc verify= new videoKyc(driver);
        	String data = new String(Files.readAllBytes(Paths.get("geoTagAddress.txt")));
			String address[] = data.split(" ");
			if(verify.verifyGeoTaggingAddress().contains(address[0])){
				  Assert.assertTrue(true);
			      logger.info("Geo-tagging address on Report verified successfully");
		     }
		     else {
			      logger.info("Geo-tagging address on Report  NOT verified");
			      captureScreen(driver, "Geo-tagging address on Report NOT verified");
			      Assert.assertTrue(false);
		     }
        }
		//Assert based on verification step3 :Pan data details(panNum,panHolderName,dob,father’sName) on REPORT Generated page
        @Test(enabled = false, testName = "verify pan data on report", priority = 10,dependsOnMethods = {
	   	"verifyGeotaggingAddressOnReport" })
		public void verifyPandataDetailsOnReport() throws IOException, InterruptedException {
        	videoKyc verify= new videoKyc(driver);
        	String data = new String(Files.readAllBytes(Paths.get("candidateName.txt")));
			String panHolderName[] = data.split(" ");
			logger.info("got candidate Name on PAN");
			//verify pan holder name on report
			if(verify.verifyPanHolderName().contains(panHolderName[0])){
				  Assert.assertTrue(true);
			      logger.info("pan holder name on Report verified successfully");
		     }
		     else {
			      logger.info("pan holder name on Report  NOT verified");
			      captureScreen(driver, "pan holder name on Report NOT verified");
			      Assert.assertTrue(false);
		     }
			//verify pan number on report
			String pan = new String(Files.readAllBytes(Paths.get("candidatePanNum.txt")));
			String panNumber[] = pan.split(" ");
			logger.info("got candidate pan number");
			if(verify.verifyPanNumber().contains(panNumber[0])){
				  Assert.assertTrue(true);
			      logger.info("pan number on Report verified successfully");
		     }
		     else {
			      logger.info("pan number on Report  NOT verified");
			      captureScreen(driver, "pan number on Report NOT verified");
			      Assert.assertTrue(false);
		     }
			 //verify pan holder father name on report
			 String pandata = new String(Files.readAllBytes(Paths.get("candidateFatherName.txt")));
			 String panFatherName[] = pandata.split(" ");
			 logger.info("got pan holder father name");
			 if(verify.verifyPanHolderFatherName().contains(panFatherName[0])){
				  Assert.assertTrue(true);
			      logger.info("pan holder father name on Report verified successfully");
		      }
		      else {
			      logger.info("pan holder father name on Report  NOT verified");
			      captureScreen(driver, "pan holder father name on Report NOT verified");
			      Assert.assertTrue(false);
		      }
			  //verify pan holder dob on report
			  String panDOB = new String(Files.readAllBytes(Paths.get("candidateDob.txt")));
			  String panHolderDOB[] = panDOB.split(" ");
			  logger.info("got candidate pan dob");
			  if(verify.verifyPanHolderDob().contains(panHolderDOB[0])){
				  Assert.assertTrue(true);
			      logger.info("pan holder dob on Report verified successfully");
		      }
		      else {
			      logger.info("pan holder dob on Report  NOT verified");
			      captureScreen(driver, "pan holder dob on Report NOT verified");
			      Assert.assertTrue(false);
		      }
         }
         //Assert based on verification step4 :Aadhar data details(AdharHoldername,address,dob,gender) on REPORT Generated page
         @Test(enabled = false, testName = "verify adhaar data on report", priority = 11,dependsOnMethods = {
 	   	"verifyPandataDetailsOnReport"})
		 public void verifyAdhaardataDetailsOnReport() throws IOException, InterruptedException {
        	  videoKyc verify= new videoKyc(driver);
         	  String data = new String(Files.readAllBytes(Paths.get("candidateAdhaarName.txt")));
 			  String candidateAdhaarName[] = data.split(" ");
 			  logger.info("got candidate AdhaarName");
 			  //verify adhaar holder name on report
 			  if(verify.verifyAadharHolderName().contains(candidateAdhaarName[0])){
 				   Assert.assertTrue(true);
 			       logger.info("adhaar holder name on Report verified successfully");
 		      }
 		      else {
 			       logger.info("adhaar holder name on Report  NOT verified");
 			       captureScreen(driver, "adhaar holder name on Report NOT verified");
 			       Assert.assertTrue(false);
 		      }
 			  //verify adhaar holder address on report
 			  String address = new String(Files.readAllBytes(Paths.get("candidateAdhaarAddress.txt")));
			  String candidateAdhaarAddress[] = address.split(" ");
			  logger.info("got candidate Adhar Address");
 			  if(verify.verifyAadharHolderAddress().contains(candidateAdhaarAddress[0])){
				    Assert.assertTrue(true);
			        logger.info("candiate adhaar address on Report verified successfully");
		      }
		      else {
			         logger.info("candiate adhaar address on Report  NOT verified");
			         captureScreen(driver, "candiate adhaar address on Report NOT verified");
			         Assert.assertTrue(false);
		      }
 			  //verify adhaar holder dob on report
 			  String dob = new String(Files.readAllBytes(Paths.get("candidateDob.txt")));
			  String candidateAdhaarDob[] = dob.split(" ");
			  logger.info("got candidate DOB");
			  if(verify.verifyAadharHolderDob().contains(candidateAdhaarDob[0])){
				      Assert.assertTrue(true);
			          logger.info("candiate adhaar dob on Report verified successfully");
		      }
		      else {
			          logger.info("candiate adhaar dob on Report  NOT verified");
			          captureScreen(driver, "candiate adhaar dob on Report NOT verified");
			          Assert.assertTrue(false);
		      }
			  //verify adhaar holder gender on report
 			  String gender = new String(Files.readAllBytes(Paths.get("candidateGender.txt")));
			  String candidateAdhaarGender[] = gender.split(" ");
			  if(verify.verifyAadharHolderGender().contains(candidateAdhaarGender[0])){
				      Assert.assertTrue(true);
			          logger.info("candiate adhaar gender on Report verified successfully");
		      }
		      else {
			          logger.info("candiate adhaar gender on Report  NOT verified");
			          captureScreen(driver, "candiate adhaar gender on Report NOT verified");
			          Assert.assertTrue(false);
		      }
          }
         //Assert based on verification step5:face match score on REPORT Generated page
         @Test(enabled =false, testName = "verify face match score on report", priority = 12,dependsOnMethods = {
  	   	"verifyAdhaardataDetailsOnReport"})
         public void verifyFaceMatchScoreOnReport() throws IOException, InterruptedException {
        	  videoKyc verify= new videoKyc(driver);
        	  String data = new String(Files.readAllBytes(Paths.get("faceMatchScore.txt")));
			  String faceMatchScore[] = data.split(" ");
			  if(verify.verifyFaceMatchScore().contains(faceMatchScore[0])){
			       Assert.assertTrue(true);
		           logger.info("candiate face match score on Report verified successfully");
	          }
	          else {
		           logger.info("candiate face match score on Report  NOT verified");
		           captureScreen(driver, "candiate face match score on Report NOT verified");
		           Assert.assertTrue(false);
	          }
         }
       //Check at Auditor End VKYC Recording is available or not |Assert based on available download
         @Test(enabled = false, testName = "download and verify recorded kyc video", priority = 13,dependsOnMethods = {
   	   	"verifyFaceMatchScoreOnReport"})
         public void downloadAndVerifyRecordedVideo() throws IOException, InterruptedException {
        	 driver.navigate().refresh();
        	 driver.get(baseURL);
    		 logger.info("v-Kyc url opened");
    		 Login log = new Login(driver);
    		 log.loginVkyc(AuditorUsername, AuditorPassword);
    		 logger.info("Auditor LoggedIn successfully");
    		 videoKyc verify= new videoKyc(driver);
    		 //verify recorded vkyc status code
    		 if(verify.getDownloadVideoUrlStatusCode()==200) {
   		         Assert.assertTrue(true);
   		 	     logger.info("downloaded recorded vkyc url status verified successfully");
   		     }
   		     else {
   		 	     logger.info("downloaded recorded vkyc url status NOT verified");
   		 	     captureScreen(driver, "downloaded recorded vkyc url status  NOT verified");
   		 	     Assert.assertTrue(false);
   		     }
         }
         
         //check the case availablity in completed-Approved Bucket at Admin end|Assert based on case availablity in bucket
         @Test(enabled = false, testName = "check case availablity in completed-Approved Bucket", priority =14,dependsOnMethods = {
    	   	"downloadAndVerifyRecordedVideo"})
         public void checkCaseAvailabilityinApprovedBucket() throws InterruptedException, IOException {
        	 Set<String> allWindows = driver.getWindowHandles();
    		 ArrayList<String> tabs = new ArrayList<String>(allWindows);
    		 driver.switchTo().window(tabs.get(0));
        	 driver.get(baseURL);
   		     logger.info("v-Kyc url opened");
   		     Login log = new Login(driver);
   		     log.loginVkyc(AdminUsername, AdminPassword);
   		     logger.info("Admin LoggedIn successfully!");
   		     videoKyc verify= new videoKyc(driver);
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
   		     logger.info("Admin loggedOut successfully");
         }
         
       //verify download of vkyc cases for today and verify report content
        @Test(enabled = false, testName = "verify Today's case download status and content", priority =15, dependsOnMethods ={
        "checkCaseAvailabilityinApprovedBucket"})
        public void verifyTodayReportDownloadStatusAndContent() throws InterruptedException, IOException  {
         	  driver.get(baseURL);
     		  logger.info("v-Kyc url opened");
     		  Login log = new Login(driver);
     		  log.loginVkyc(AdminUsername, AdminPassword);
     		  logger.info("Admin LoggedIn successfully!");
     		  File folder = new File("/home/shashi.ranjan/Downloads/VpdReport");
     		  File fListInitial[] = folder.listFiles();
     		  int initialNumberOfPDFs = fListInitial.length;
     		  videoKyc dwld= new videoKyc(driver);
     		  dwld.downloadTodayExcelReport();
     		  logger.info("Today Excel report Downloaded");
     		  Thread.sleep(5000);
     		  File fListFinal[] = folder.listFiles();
     		  //verify report generated 
     		  if(initialNumberOfPDFs + 1== fListFinal.length) {
     			    Assert.assertTrue(true);
     			 	logger.info("Today's report download verified successfully");
     		   }
     		   else {
     			 	logger.info("Today's report download NOT verified");
     			 	captureScreen(driver, "Today's report download NOT verified");
     			 	Assert.assertTrue(false);
     		   }
     		  //verify Report Downloaded Report content/customer name in Excel report
     		  videoKyc verify  = new videoKyc(driver);
     		  if(verify.readCSVfile()==true) {
     			   Assert.assertTrue(true);
     			   logger.info(" Case is verified in Today's Excel report");
     		  }
     		  else {
     			   logger.info(" Case is NOT verified in Today's Excel report");
     			   captureScreen(driver, " Case is NOT verified in Today's Excel report");
     			   Assert.assertTrue(false);
     		  }
         }
        //verify download Report for last 7 days |Assert based on Files count
        @Test(enabled = false, testName = "verify Last 7 days Report download", priority =16, dependsOnMethods ={
        "verifyTodayReportDownloadStatusAndContent"})
        public void verifySevenDaysReportDownloadStatus() throws InterruptedException, IOException {
            File folder = new File("/home/shashi.ranjan/Downloads/VpdReport");
		    File fListInitial[] = folder.listFiles();
		    int initialNumberOfPDFs = fListInitial.length;
		    videoKyc dwld= new videoKyc(driver);
		    dwld.downloadSevendaysExcelReport();
		    logger.info("last 7 days Excel report Downloaded");
		    Thread.sleep(5000);
		    File fListFinal[] = folder.listFiles();
		    //verify report download  
		    if(initialNumberOfPDFs + 1== fListFinal.length) {
			    Assert.assertTrue(true);
			 	logger.info("Last 7days report download verified successfully");
		     }
		     else {
			 	logger.info("Last 7days report download NOT verified");
			 	captureScreen(driver, "report download NOT verified");
			 	Assert.assertTrue(false);
		     }
        }
       //verify download Report for last 30 days |Assert based on Files count
        @Test(enabled = false, testName = "verify Last 30 days Report download status", priority =17, dependsOnMethods ={
        "verifySevenDaysReportDownloadStatus"})
        public void verifyThirtyDaysReportDownloadStatus() throws InterruptedException, IOException {
            File folder = new File("/home/shashi.ranjan/Downloads/VpdReport");
		    File fListInitial[] = folder.listFiles();
		    int initialNumberOfPDFs = fListInitial.length;
		    videoKyc dwld= new videoKyc(driver);
		    dwld.downloadThirtydaysExcelReport();
		    logger.info("last 30 days Excel report Downloaded");
		    Thread.sleep(5000);
		    File fListFinal[] = folder.listFiles();
		    //verify report download  
		    if(initialNumberOfPDFs + 1== fListFinal.length) {
			    Assert.assertTrue(true);
			 	logger.info("Last 30 days report download verified successfully");
		     }
		     else {
			 	logger.info("Last 30 days report download NOT verified");
			 	captureScreen(driver, "Last 30 days report download NOT verified");
			 	Assert.assertTrue(false);
		     }
         }
       //verify download Report for last 90 days |Assert based on Files count
       @Test(enabled = false, testName = "verify Last 90 days Report download status", priority =18, dependsOnMethods ={
        "verifyThirtyDaysReportDownloadStatus"})
       public void verifyNinetyDaysReportDownloadStatus() throws InterruptedException, IOException {
             File folder = new File("/home/shashi.ranjan/Downloads/VpdReport");
		     File fListInitial[] = folder.listFiles();
		     int initialNumberOfPDFs = fListInitial.length;
		     videoKyc dwld= new videoKyc(driver);
		     dwld.downloadNinetydaysExcelReport();
		     logger.info("last 90 days Excel report Downloaded");
		     Thread.sleep(5000);
		     File fListFinal[] = folder.listFiles();
		     //verify report download  
		     if(initialNumberOfPDFs + 1== fListFinal.length) {
			    Assert.assertTrue(true);
			 	logger.info("Last 90 days report download verified successfully");
		     }
		     else {
			 	logger.info("Last 90 days report download NOT verified");
			 	captureScreen(driver, "Last 90 days report download NOT verified");
			 	Assert.assertTrue(false);
		     }
		     Thread.sleep(2000);
		     Login log=new Login(driver);
		     log.logoutUser();
		     logger.info("Admin logged out successfully");
         }
         //Admin uploads the cases using “upload cases” option via Excel |Assert based on Alert 
         @Test(enabled =false, testName = "upload case using excel", priority = 19)
         public void uploadCaseUsingExcel() throws InterruptedException, IOException {
        	 driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        	 Set<String> allWindows = driver.getWindowHandles();
    		 ArrayList<String> tabs = new ArrayList<String>(allWindows);
    		 driver.switchTo().window(tabs.get(0));
    		 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        	 driver.get(baseURL);
    		 logger.info("v-Kyc url opened");
    		 Login log = new Login(driver);
    		 log.loginVkyc(AdminUsername, AdminPassword);
    		 logger.info("Admin LoggedIn successfully!");
    		 videoKyc upload= new videoKyc(driver);
    		 upload.uploadCaseUsingExcel();
    		 logger.info("case uploaded using excel");
    		 videoKyc verify= new videoKyc(driver);
    		 if(verify.verifyCaseUploadSuccessMsg().contains("CASES UPLOADED SUCCESSFULLY")) {
   		         Assert.assertTrue(true);
   		 	     logger.info("Case Upload using Excel Success Msg verified successfully");
   		     }
   		     else {
   		 	     logger.info("Case Upload using Excel Success Msg NOT verified");
   		 	     captureScreen(driver, "Case Upload using Excel Success Msg NOT verified");
   		 	     Assert.assertTrue(false);
   		     }
    		 Thread.sleep(6000);
    		 log.logoutUser();
    		 logger.info("Admin loggedOut Successfully");
         }
         //Check at  Agent End whether Uploaded case is available or not
         @Test(enabled = false, testName="verify Uploaded case thru Excel at Agent End",priority =20,dependsOnMethods = {
 	     "uploadCaseUsingExcel"})
         public void verifyCaseUploadAtAgentEnd() throws IOException {
        	 driver.get(baseURL);
     		 logger.info("v-Kyc url opened");
     		 Login log = new Login(driver);
     		 log.loginVkyc(AgentUsername, AgentPassword);
     		 logger.info("Agent LoggedIn successfully!");
     		 videoKyc verify= new videoKyc(driver);
    		 if(verify.verifyCaseUploadedAtAgentEnd()==true) {
   		         Assert.assertTrue(true);
   		 	     logger.info("Case Upload using Excel verified successfully  at Agent End");
   		     }
   		     else {
   		 	     logger.info("Case Upload using Excel NOT verified  at Agent End");
   		 	     captureScreen(driver, "Case Upload using Excel NOT verified  at Agent End");
   		 	     Assert.assertTrue(false);
   		     }
     	 }
         //Admin initiate/upload a case for VPT Process for Reject case|Assert based on alert
         @Test(enabled =false, testName = "Admin upload a case for Reject By Auditor", priority = 21)
         public void uploadVKycCaseByAdminForRejectCase() throws IOException, InterruptedException {
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
        	Thread.sleep(100);
        	log.logoutUser();
        	logger.info("Admin loggedout successfully");
        }
         //schedule case for rejected flow By Auditor
         @Test(enabled=false,testName="schedule case BY Agent for Reject case",priority=22,dependsOnMethods= {
         "uploadVKycCaseByAdminForRejectCase"})
         public void scheduleCaseByAgentForCaseRejectAtAuditorEnd() throws InterruptedException, IOException {
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
     		 logger.info("got client/customer name of case");
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
         @Test(enabled = false, testName = "vkyc started by customer for Reject case", priority =23, dependsOnMethods = {
     	 "scheduleCaseByAgentForCaseRejectAtAuditorEnd" })
         public void vKycProcessByCustomerForCaseRejectAtAuditorEnd() throws InterruptedException, IOException {
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
         	 //ClearBrowserCache();
         	 //logger.info("browser cache cleared");
         	 videoKyc initiate= new videoKyc(driver);
         	 initiate.customerProceedOnKycLink();
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
        //Agent starts vkyc with candidate for Reject case and after it is successful it marks the case as completed
        // |Assert based on new case status as Pending with Auditor
        @Test(enabled = false, testName = "vkyc started by agent for Reject case", priority =24, dependsOnMethods = {
         "vKycProcessByCustomerForCaseRejectAtAuditorEnd"})
         public void vkycProcessByAgentForCaseRejectAtAuditorEnd() throws InterruptedException, IOException {
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
         	Thread.sleep(2000);
         	log.logoutUser();
         	logger.info("Agent loggedout successfully");
      }
      //Auditor marks case as Rejected |Assert based on case status as Rejected
      @Test(enabled = false, testName = "Auditor marks case Rejected And Verify case status", priority =25, dependsOnMethods = {
      "vkycProcessByAgentForCaseRejectAtAuditorEnd"})
      public void vkycCaseRejectedByAuditor() throws InterruptedException, IOException {
        	  driver.get(baseURL);
    		  logger.info("v-Kyc url opened");
    		  Login log = new Login(driver);
    		  log.loginVkyc(AuditorUsername, AuditorPassword);
    		  logger.info("Auditor LoggedIn successfully!");
    		  videoKyc reject= new videoKyc(driver);
    		  String Text=RandomMethod.randomString();
    		  logger.info("got feedback text");
    		  reject.rejectCaseByAuditor(Text);
    		  logger.info("case Rejected by Auditor");
    		  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    		  //verify approved status by auditor
    		  videoKyc verify= new videoKyc(driver);
    		  if(verify.verifyCaseRejectedStatus().contains("Rejected")) {
         		   Assert.assertTrue(true);
     			   logger.info("Rejected Case Status verified successfully by auditor");
     		  }
     		  else {
     			  logger.info("Rejected Case Status NOT verified");
     			  captureScreen(driver, "Rejected Case Status NOT verified");
     			  Assert.assertTrue(false);
     		  }
    		  Thread.sleep(5000);
    		  log.logoutUser();
    		  logger.info("Auditor logged out successfully");
      }
      //check the case availablity in completed-Rejected Bucket at Admin end|Assert based on case availablity in bucket
      @Test(enabled = false, testName = "check case availablity in completed-Rejected Bucket", priority =26, dependsOnMethods = {
      "vkycCaseRejectedByAuditor"})
      public void checkCaseAvailabilityinRejectedBucket() throws InterruptedException, IOException {
    	     driver.get(baseURL);
		     logger.info("v-Kyc url opened");
		     Login log = new Login(driver);
		     log.loginVkyc(AdminUsername, AdminPassword);
		     logger.info("Admin LoggedIn successfully!");
		     videoKyc verify= new videoKyc(driver);
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
      
      //Admin initiate/upload a case for Re-assign by Auditor|Assert based on alert
      @Test(enabled = false, testName = "Admin uploads a case for Re-Assign", priority =27)
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
     @Test(enabled = false, testName = "verify and schedule case for Re-Assign", priority = 28, dependsOnMethods = {
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
     @Test(enabled = false, testName = "vkyc started by customer for Re-Assign", priority =29, dependsOnMethods = {
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
     	 //ClearBrowserCache();
     	 //logger.info("browser cache cleared");
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
     @Test(enabled = false, testName = "vkyc started by agent for Re-Assign", priority =30, dependsOnMethods = {
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
    @Test(enabled = false, testName = "Auditor marks case ReAssigned And Verify", priority =31, dependsOnMethods ={
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
    //Admin initiate/upload a case for VPT Process|Assert based on alert
    @Test(enabled = true, testName = "Admin upload a case for case transfer by agent", priority =32)
    public void uploadVKycCaseByAdminForCaseTransfer() throws IOException, InterruptedException {
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
    //Transfer case from Assigned Bucket at Agent End |Assert based on case unavailability in Assigned bucket
    @Test(enabled = true, testName = "Transfer case from Assigned Bucket by Agent", priority =33,dependsOnMethods ={
    "uploadVKycCaseByAdminForCaseTransfer"})
    public void transferCaseByAgent() throws IOException, InterruptedException {
    	  driver.get(baseURL);
 		  logger.info("v-Kyc url opened");
 		  Login log = new Login(driver);
 		  log.loginVkyc(AgentUsername, AgentPassword);
 		  logger.info("Agent LoggedIn successfully!");
 		  videoKyc transfer= new videoKyc(driver);
 		  transfer.transferCaseByAgent();
 		  logger.info("case transferred by Agent to another Agent");
 		  Thread.sleep(3000);
  		  log.logoutUser();
 		  logger.info("Agent logged out successfully");
 		  driver.get(baseURL);
		  logger.info("v-Kyc url opened");
		  Login logg = new Login(driver);
		  logg.loginVkyc(AgentUsername, AgentPassword);
		  logger.info("Agent LoggedIn successfully!");
 		  videoKyc verify= new videoKyc(driver);
 		  //verify case removed from assigned bucket
 		  if(verify.verifyCaseRemovedFromAssignedBucket()==false) {
 			   Assert.assertTrue(true);
 			   logger.info("case removal from Assigned bucket verified successfully");
 		  }
 		  else {
 			 logger.info("case removal from Assigned bucket NOT verified");
 			 captureScreen(driver, "case removal from Assigned bucket NOT verified");
			 Assert.assertTrue(false);
 		  }
 		  Thread.sleep(3000);
 		  logg.logoutUser();
 		  logger.info("Agent loggedOut successfully");
     }
    //After case transfer verify case At Transferred Agent End |Assert based on case availability
    @Test(enabled = true, testName = "verify Case At Transferred Agent End", priority =34,dependsOnMethods ={
    "transferCaseByAgent"})
    public void verifyCaseAtTransferredAgent() throws IOException, InterruptedException {
    	  driver.get(baseURL);
 		  logger.info("v-Kyc url opened");
 		  Login log = new Login(driver);
 		  log.loginVkyc(SecondAgentUsername, SecondAgentPassword);
 		  logger.info("Second Agent LoggedIn successfully!");
 		  videoKyc verify= new videoKyc(driver);
 		  if(verify.verifyCaseAtTransferredAgentEnd()==true) {
 			   Assert.assertTrue(true);
 			   logger.info("Transferred Case verified successfully At Transferred Agent End");
 		  }
 		  else {
 			 logger.info("Transferred Case NOT verified At Transferred Agent End");
 			 captureScreen(driver, "ransferred Case NOT verified At Transferred Agent End");
			 Assert.assertTrue(false);
 		  }
 		  Thread.sleep(1000);
 		  log.logoutUser();
 		  logger.info("second Agent loggedout successfully");
    }
    //Admin initiate/upload a case for VPT Process|Assert based on alert
    @Test(enabled = false, testName = "Admin upload a case for Reschedule later", priority =35)
    public void uploadVKycCaseByAdminForReschedulelater() throws IOException, InterruptedException {
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
    
    //Agent schedules case | Assert based on case availability in Scheduled bucket
    @Test(enabled = false, testName = "schedule & verify case for Reschedule later", priority =36, dependsOnMethods = {
	"uploadVKycCaseByAdminForReschedulelater"})
    public void scheduleCaseByAgentForReschedulelater() throws InterruptedException, IOException {
    	 driver.get(baseURL);
		 logger.info("v-Kyc url opened");
		 Login log = new Login(driver);
		 log.loginVkyc(AgentUsername, AgentPassword);
		 logger.info("Agent LoggedIn successfully!");
		 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
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
    //Mark Reschedule later for a case in Scheduled Bucket at Agent End|Assert based case removal from schedule bucket
    @Test(enabled = false, testName = "mark case as Reschedule later", priority =37, dependsOnMethods = {
	"scheduleCaseByAgentForReschedulelater"})
    public void markCaseAsReschedulelater() throws IOException, InterruptedException {
    	videoKyc reschedule= new videoKyc(driver);
    	reschedule.markCaseAsReschedulelater();
    	logger.info("Case is Rescheduled later by Agent");
    	//verify case is Removed from scheduled bucket
    	videoKyc verify= new videoKyc(driver);
    	if(verify.verifyCaseRemovedInScheduledBucket()==false) {
    		Assert.assertTrue(true);
			logger.info(" Reschedule later case Removal from scheduled bucket verified successfully");
		 }
		 else {
			logger.info(" Reschedule later case Removal from scheduled bucket NOT verified ");
			captureScreen(driver, " Reschedule later case Removal from scheduled bucket NOT verified ");
			Assert.assertTrue(false);
		 }
    	Thread.sleep(2000);
    	Login log = new Login(driver);
    	log.logoutUser();
    	logger.info("Agent logged out successfully");
    }
    //After Rescheduling ,verify case is moved to Assigned bucket at Agent end
    @Test(enabled = false, testName = "verify case is moved to Assigned bucket", priority =38, dependsOnMethods = {
	"markCaseAsReschedulelater"})
    public void verifyCaseAvailabilityInAssignedBucket() throws IOException, InterruptedException {
    	 driver.get(baseURL);
		 logger.info("v-Kyc url opened");
		 Login log = new Login(driver);
		 log.loginVkyc(AgentUsername, AgentPassword);
		 logger.info("Agent LoggedIn successfully!");
		 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    	 videoKyc verify= new videoKyc(driver);
    	 if(verify.verifyRescheduledCaseInAssignedBucket()==true) {
    		 Assert.assertTrue(true);
			 logger.info("Rescheduled later case verified successfully in Assigned bucket");
		 }
		 else {
			logger.info("Rescheduled later case NOT verified in Assigned bucket ");
			captureScreen(driver, "Rescheduled later case NOT verified in Assigned bucket");
			Assert.assertTrue(false);
		 }
         Thread.sleep(2000);
		 log.logoutUser();
		 logger.info("Agent loggedout successfully");
    }
    //Inline Reschedule the call from Scheduled bucket |Assert based on mail received at customer end
    //Admin initiate/upload a case for VPT Process|Assert based on alert
    @Test(enabled = true, testName = "Admin upload a case for Inline call Reschedule", priority =39)
    public void uploadVKycCaseByAdminForInlineReschedule() throws IOException, InterruptedException {
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
    
    //Agent schedules case | Assert based on case availability in Scheduled bucket
    @Test(enabled = true, testName = "schedule & verify case for Inline call Reschedule", priority =40, dependsOnMethods = {
	"uploadVKycCaseByAdminForInlineReschedule"})
    public void scheduleCaseByAgentForInlineReschedule() throws InterruptedException, IOException {
    	 driver.get(baseURL);
		 logger.info("v-Kyc url opened");
		 Login log = new Login(driver);
		 log.loginVkyc(AgentUsername, AgentPassword);
		 logger.info("Agent LoggedIn successfully!");
		 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
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
    //Inline Reschedule the call from Scheduled bucket |Assert based on mail received at customer end
    @Test(enabled = true, testName = "Inline call Reschedule AND verify", priority =41, dependsOnMethods = {
	"uploadVKycCaseByAdminForInlineReschedule"})
    public void inlineRescheduleCall() throws IOException, InterruptedException {
    	videoKyc reschedule= new videoKyc(driver);
    	String scheduledTime = RandomMethod.getScheduleTime();
		logger.info("got reScheduled time for inline reschedule vkyc");
    	reschedule.inlinineRescheduleCall(scheduledTime);
    	logger.info("call is Inline Rescheduled by Agent");
    	videoKyc verify= new videoKyc(driver);
    	//verify rescheduled received link on mail
    	if(verify.verifyRescheduleKycLink().contains("just now")) {
    		Assert.assertTrue(true);
			logger.info("InlineReschedule kyc link received on mail verified");
		}
		else {
			logger.info("InlineReschedule kyc link received on mail NOT verified");
			captureScreen(driver, "InlineReschedule kyc link received on mail NOT verified ");
			Assert.assertTrue(false);
		}
    }
}
