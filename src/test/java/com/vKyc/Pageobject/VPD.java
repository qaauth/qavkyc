package com.vKyc.Pageobject;


import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.vKyc.Testcases.Baseclass;

public class VPD extends Baseclass {

	WebDriver ldriver;
	WebDriverWait wait;
	JavascriptExecutor js;
	ArrayList<String> tabs;
	String ActCustomerName=" ";
	String caseStatus=" ";
	SoftAssert asert = new SoftAssert();

	public VPD(WebDriver rdriver) {
		this.ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath ="//*[@id='Check_Pad']")
	@CacheLookup
	WebElement uploadCasePad;
	
	@FindBy(xpath ="//*[text()='Upload Cases']")
	@CacheLookup
	WebElement uploadCaseLlink;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='Enter Case Name']")
	@CacheLookup
	WebElement enterCaseName;
	
	@FindBy(xpath ="(//select[@class='form-control form-control-sm'])[1]")
	@CacheLookup
	WebElement selectAgentfromDropdown;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='Enter Your Source System']")
	@CacheLookup
	WebElement enterSourceSystem;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='Enter Your Source Application Number']")
	@CacheLookup
	WebElement enterSourceApplicationNum;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='Enter Your Line Of Business']")
	@CacheLookup
	WebElement enterLineOfBusiness;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='Enter Your Lead ID']")
	@CacheLookup
	WebElement enterLeadId;
	
	@FindBy(xpath ="//textarea[@class='form-control']")
	@CacheLookup
	WebElement enterSpecialInstruction;
	
	@FindBy(xpath ="//*[@type='text'][@placeholder='Enter Business Manager Name']")
	@CacheLookup
	WebElement enterBusinessManagerName;
	
	@FindBy(xpath ="//*[@type='text'][@placeholder='000-000-0000']")
	@CacheLookup
	WebElement enterBusinessManagerMobNum;
	
	@FindBy(xpath ="//input[@type='radio'][@id='customRadioInline2']")
	@CacheLookup
	WebElement VPdRadioBtn;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='Enter Your Connection Name']")
	@CacheLookup
	WebElement enterConnectionName;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='Example@abc.com']")
	@CacheLookup
	WebElement enterConnectionEmail;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='000-0000-000']")
	@CacheLookup
	WebElement enterConnectionMobile;
	
	@FindBy(xpath ="(//select[@class='form-control form-control-sm'])[2]")
	@CacheLookup
	WebElement selectUserRolefromDropdown;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='Enter Your Source System UCIC']")
	@CacheLookup
	WebElement enterSourceSystemUCIC;
	
	@FindBy(xpath ="//input[@type='text'][@placeholder='Enter Your Guarantor Co-Applicant ID']")
	@CacheLookup
	WebElement enterGuarantorId;
	
	@FindBy(xpath ="(//*[@type='submit'])[2]")
	@CacheLookup
	WebElement submitCase;
	
	@FindBy(xpath ="//*[text()='Submit']")
	@CacheLookup
	WebElement finalSubmitCase;
	
	@FindBy(xpath ="//*[@id='root']/div/div/div[1]/section/div/div/div[2]/h3")
	@CacheLookup
	WebElement verifyCaseSuccessfullyUploadedText;
	
	//schedule VPD by AGENT
	@FindBy(xpath ="//*[@id='assigned-tab']")
	@CacheLookup
	WebElement AssignedCasesTab;
	
	@FindBy(xpath ="//*[@class='nav-link'][@id='vkyc']")
	@CacheLookup
	WebElement vpdIcon;
	
	@FindBy(xpath ="(//*[@class='position-relative align-top'])[1]")
	@CacheLookup
	WebElement scheduleKycCalendar;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div[2]/table/tbody/tr/td/span[1]/div/div[6]/div[1]/div/div/div/div/button")
	@CacheLookup
	WebElement selectDateIcon;
	
	@FindBy(xpath ="//*[text()='OK']")
	@CacheLookup
	WebElement SelectDateOkBtn;
	
	@FindBy(xpath ="//*[@id='assigned']/div[2]/div[2]/table/tbody/tr/td/span[1]/div/div[6]/div[2]/div/div/div/input")
	@CacheLookup
	WebElement enterScheduledTime;
	
	@FindBy(xpath ="(//*[@class='schedule-button'])[1]")
	@CacheLookup
	WebElement ScheduleBtn;
	
	@FindBy(xpath ="//*[@id='scheduled-tab']")
	@CacheLookup
	WebElement ScheduledCasesBucket;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div[1]/div/table/tbody/tr/td[3]")
	@CacheLookup
	WebElement customerName;
	
	@FindBy(xpath ="//*[@id='inbox_field']")
	@CacheLookup
	WebElement searchMailinatorBox;
	
	@FindBy(xpath ="/html/body/div/main/div[2]/div[3]/div/div[4]/div/div/table/tbody/tr")
	@CacheLookup
	WebElement receivedMail;
	
	@FindBy(xpath ="//*[@id='pills-links-tab']")
	@CacheLookup
	WebElement linksTab;
	
	@FindBy(xpath ="(//*[contains(text(),'https://vcip-uat.herofincorp.com/user')])[2]")
	WebElement receivedLink;
	
	@FindBy(xpath ="//*[text()='Click Here to Start']")
	@CacheLookup
	WebElement linkText;
	
	//consent fill up form
	@FindBy(xpath ="//*[text()='Customer consent']")
	@CacheLookup
	WebElement CustomerConsentText;
	
	@FindBy(xpath ="(//*[@type='checkbox'])[1]")
	@CacheLookup
	WebElement CompliancecheckBox1;
	
	@FindBy(xpath ="(//*[@type='checkbox'])[2]")
	@CacheLookup
	WebElement CompliancecheckBox2;
	
	@FindBy(xpath ="//*[text()='Proceed']")
	@CacheLookup
	WebElement proceedBtn;
	
	@FindBy(xpath ="//label[@for='customField-0']")
	@CacheLookup
	WebElement firstDocUploadBox;
	
	@FindBy(xpath ="//label[@for='customField-1']")
	@CacheLookup
	WebElement secondDocUploadBox;
	
	@FindBy(xpath ="//label[@for='customField-2']")
	@CacheLookup
	WebElement thirdDocUploadBox;
	
	@FindBy(xpath ="//label[@for='customField-3']")
	@CacheLookup
	WebElement fourthDocUploadBox;
	
	@FindBy(xpath ="//label[@for='customField-4']")
	@CacheLookup
	WebElement fifthDocUploadBox;
	
	@FindBy(xpath ="/html/body/div/div/div/div/section/div/div[1]/div/h4")
	@CacheLookup
	WebElement docUploadVerifyText;
	
	@FindBy(xpath ="//*[text()='Next']")
	@CacheLookup
	WebElement nextBtn;
	
	@FindBy(xpath ="//*[text()='Next']")
	@CacheLookup
	WebElement secondNextBtn;
	
	@FindBy(xpath ="//*[contains(text(),'Start Video')]")
	@CacheLookup
	WebElement startVPDCall;
	
	@FindBy(xpath ="/html/body/div/div/div[1]/p")
	@CacheLookup
	WebElement verifyVpdStartedByCustomer;
	
	//Agent process
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div[2]/div/table/tbody/tr/td/span[1]")
	@CacheLookup
	WebElement AuditorcallBtn;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/h5")
	@CacheLookup
	WebElement questionPage;
	
	@FindBy(xpath ="//*[@id='exampleFormControlTextarea1']")
	@CacheLookup
	WebElement NotesBox;
	
	@FindBy(xpath ="//*[@id='headingOne']")
	@CacheLookup
	WebElement takePictureDropDown;
	
	@FindBy(xpath ="(//*[@xmlns='http://www.w3.org/2000/svg'])[2]")
	@CacheLookup
	WebElement takePictureIcon;
	
	@FindBy(xpath ="//*[contains(text(),'All steps completed.')]")
	@CacheLookup
	WebElement verifyCaseCompleteMsg;
	
	@FindBy(xpath ="//*[@id='navLeaveButton']")
	@CacheLookup
	WebElement endCallBtn;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[3]/div/div/div/div[2]/div/textarea")
	@CacheLookup
	WebElement addCommentsBox;
	
	@FindBy(xpath ="//*[text()='Case Complete']")
	@CacheLookup
	WebElement caseCompleteBtn;
	
	@FindBy(xpath ="//*[@id='completed-tab']")
	@CacheLookup
	WebElement CompletedBucket;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div/div/table/tbody/tr/td[10]/div")
	@CacheLookup
	WebElement ClientName;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div/div/table/tbody/tr/td[9]/span")
	@CacheLookup
	WebElement actionCaseDropdown;
	
	@FindBy(xpath ="(//*[@class='dropdown-item'][@data-val='Approved'])[1]")
	@CacheLookup
	WebElement caseApproveBtn;
	
	@FindBy(xpath ="(//*[@class='dropdown-item'][@data-val='Rejected'])[1]")
	@CacheLookup
	WebElement caseRejectBtn;
	
	@FindBy(xpath ="(//*[@class='dropdown-item'][@data-val='Reassigned'])[1]")
	@CacheLookup
	WebElement caseReAssignBtn;
	
	@FindBy(xpath ="(//*[text()='Approved'])[1]")
	@CacheLookup
	WebElement caseApprovedStatus;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[1]")
	@CacheLookup
	WebElement downloadReportIcon;
	
	@FindBy(xpath="/html/body/div[1]/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div/div/table/tbody/tr/td[8]/span[1]")
	@CacheLookup
	WebElement downloadRecorededVkycIcon;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div[1]/div/table/tbody/tr/td[3]")
	@CacheLookup
	WebElement verifyCustomerName;
	
	//Case upload using excel
	@FindBy(xpath ="//*[contains(text(),'Cases uploaded successfully')]")
	@CacheLookup
	WebElement caseUploadSuccessMsg;
	
	@FindBy(xpath ="//*[@class='custom-upload-btn']")
	@CacheLookup
	WebElement uploadExcelBtn;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div[1]/div/div/div/div[2]/div/textarea")
	@CacheLookup
	WebElement addCommentBox;
	
	@FindBy(xpath ="//*[text()='Confirm']")
	@CacheLookup
	WebElement confirmBtn;
	
	@FindBy(xpath ="(//*[text()='Rejected'])[1]")
	@CacheLookup
	WebElement caseRejectedStatus;
	
	@FindBy(xpath ="//*[text()='Case Management']")
	@CacheLookup
	WebElement caseManagement;
	
	@FindBy(xpath ="//*[text()='Completed']")
	@CacheLookup
	WebElement completedBucket;
	
	@FindBy(xpath ="//*[text()='Approved']")
	@CacheLookup
	WebElement approvedBucket;
	
	@FindBy(xpath ="//*[text()='Active']")
	@CacheLookup
	WebElement ActiveBucket;
	
	@FindBy(xpath ="//*[text()='Rejected']")
	@CacheLookup
	WebElement rejectedBucket;
	
	@FindBy(xpath ="//*[text()='Reassigned']")
	@CacheLookup
	WebElement reAssignedBucket;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/div[1]/section/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div/div/div[2]/div/div/table/tbody/tr/td[1]")
	@CacheLookup
	WebElement caseName;
	
	@FindBy(xpath ="//*[contains(text(),'Loading please wait')]")
	@CacheLookup
	WebElement loadingPleaseWaitText;
	
	    //Admin initiates/uploads a VPD case
		public void uploadCase(String customerName,String Text,String mobNum,String Id) {
			 wait=new WebDriverWait(ldriver, 120);
		     wait.until(ExpectedConditions.visibilityOf(uploadCasePad));
			 Actions act = new Actions(driver);
		     act.moveToElement(uploadCasePad).build().perform();
		     uploadCaseLlink.click();
		     wait=new WebDriverWait(ldriver, 120);
		     wait.until(ExpectedConditions.visibilityOf(enterCaseName));
		     enterCaseName.sendKeys(customerName);
		     Select sel=new Select(selectAgentfromDropdown);
		     sel.selectByIndex(3);
		     enterSourceSystem.sendKeys(Text);
		     enterSourceApplicationNum.sendKeys(Text);
		     enterLineOfBusiness.sendKeys(Text);
		     enterLeadId.sendKeys(Text);
		     enterBusinessManagerName.sendKeys(Text);
		     enterBusinessManagerMobNum.sendKeys(mobNum);
		     enterSpecialInstruction.sendKeys(Text);
		     VPdRadioBtn.click();
		     enterConnectionName.sendKeys("Shashi");
		     enterConnectionEmail.sendKeys("Shashi.ranjan@mailinator.com");
		     enterConnectionMobile.sendKeys(mobNum);
		     js=(JavascriptExecutor)ldriver;
		     js.executeScript("arguments[0].scrollIntoView(true);", selectUserRolefromDropdown);
		     Select selrole=new Select(selectUserRolefromDropdown);
		     selrole.selectByIndex(1);
		     wait.until(ExpectedConditions.visibilityOf(enterSourceSystemUCIC));
		     enterSourceSystemUCIC.sendKeys(Id);
		     enterGuarantorId.sendKeys(Id);
		     js.executeScript("arguments[0].scrollIntoView(true);", submitCase);
		     wait.until(ExpectedConditions.visibilityOf(submitCase));
		     submitCase.click();
		     wait.until(ExpectedConditions.visibilityOf(finalSubmitCase));
		     finalSubmitCase.click();
		}
		//verify CASE initiated/uploaded SUCCESS msg
		public String verifyUploadSuccessText() {
			wait=new WebDriverWait(ldriver, 120);
		    wait.until(ExpectedConditions.visibilityOf(verifyCaseSuccessfullyUploadedText));
			return verifyCaseSuccessfullyUploadedText.getText();
		}
		
		//verify case in Assigned bucket at agent
		public boolean verifyCaseInAssignedBucket() throws InterruptedException, IOException {
			boolean temp=false;
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(vpdIcon));
			Thread.sleep(2000);
			vpdIcon.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(scheduleKycCalendar));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
					"//*[@id='assigned']/div/div[1]/div/table/tbody/tr/td[3]")).size();
			for (int r = 2; r <= rows; r++) {
				ActCustomerName = ldriver.findElement(By.xpath(
				"//*[@id='assigned']/div["+r+"]/div[1]/div/table/tbody/tr/td[3]")).getText();
				if(ActCustomerName.equals(ExpcustomerName[0])) {
					temp=true;
					break;
				}
			}
			return temp;
		}
		
		//schedule case by auditor
		public void scheduleCalendarForVPdByAuditor(String scheduledTime) throws InterruptedException, IOException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(vpdIcon));
			Thread.sleep(3000);
			vpdIcon.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(scheduleKycCalendar));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
					"//*[@id='assigned']/div/div[1]/div/table/tbody/tr/td[3]")).size();
			for (int r = 2; r <= rows; r++) {
				ActCustomerName = ldriver.findElement(By.xpath(
						"//*[@id='assigned']/div["+r+"]/div[1]/div/table/tbody/tr/td[3]")).getText();
				if(ActCustomerName.equals(ExpcustomerName[0])) {
				    WebElement calenderelement = ldriver.findElement(By.xpath("//*[@id='assigned']/div["+r+"]/div[2]/table/tbody/tr/td/span[1]"));
				    calenderelement.click();
				    break;
				}
			}
			wait.until(ExpectedConditions.visibilityOf(selectDateIcon));
			selectDateIcon.click();
			Thread.sleep(100);
			wait.until(ExpectedConditions.visibilityOf(SelectDateOkBtn));
			SelectDateOkBtn.click();
			Thread.sleep(100);
			wait.until(ExpectedConditions.visibilityOf(enterScheduledTime));
			enterScheduledTime.sendKeys(scheduledTime);
			Thread.sleep(50);
			ScheduleBtn.click();
			wait.until(ExpectedConditions.invisibilityOf(loadingPleaseWaitText));
			Thread.sleep(2000);
		}
		//verify scheduled case by auditor
		public boolean verifyCaseInScheduledBucket() throws InterruptedException, IOException {
			boolean temp=false;
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(scheduleKycCalendar));
			ScheduledCasesBucket.click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(customerName));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
					"//*[@id='scheduled']/div/div[1]/div/table/tbody/tr/td[3]")).size();
			for (int r = 2; r <= rows; r++) {
				ActCustomerName = ldriver.findElement(By.xpath(
						"//*[@id='scheduled']/div["+r+"]/div[1]/div/table/tbody/tr/td[3]")).getText();
				if(ActCustomerName.equals(ExpcustomerName[0])) {
					temp=true;
					break;
				}
			}
			return temp;
		}
		//customer verify for VPD related link in mail on his end
		public String verifyVPDlinkReceivedByCustomer() throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			((JavascriptExecutor) ldriver).executeScript("window.open()");
			tabs = new ArrayList<String>(ldriver.getWindowHandles());
			ldriver.switchTo().window(tabs.get(1));
			Thread.sleep(1000);
			ldriver.navigate().to("https://www.mailinator.com/v4/public/inboxes.jsp");
			wait.until(ExpectedConditions.visibilityOf(searchMailinatorBox));
			searchMailinatorBox.sendKeys(Keys.DELETE);
			searchMailinatorBox.sendKeys("Shashi.ranjan@mailinator.com"+Keys.ENTER);
			wait.until(ExpectedConditions.visibilityOf(receivedMail));
			receivedMail.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(linksTab));
			linksTab.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(linkText));
			return linkText.getText();
		}
		//on verification customer initiate Kyc process by clicking on link
		public void customerClickOnKycLink() throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			Thread.sleep(90000);
			receivedLink.click();
			tabs = new ArrayList<String>(ldriver.getWindowHandles());
			ldriver.switchTo().window(tabs.get(2));
	        Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(CustomerConsentText));
			CompliancecheckBox1.click();
			Thread.sleep(100);
			CompliancecheckBox2.click();
			Thread.sleep(100);
			wait.until(ExpectedConditions.visibilityOf(proceedBtn));
			proceedBtn.click();
		}
		//on verification customer initiate Kyc process by clicking on link
		public void customerProceedOnKycLink() throws InterruptedException {
			 wait=new WebDriverWait(ldriver, 120);
			 Thread.sleep(90000);
			 receivedLink.click();
			 wait.until(ExpectedConditions.visibilityOf(CustomerConsentText));
			 CompliancecheckBox1.click();
			 Thread.sleep(100);
			 CompliancecheckBox2.click();
			 Thread.sleep(100);
			 wait.until(ExpectedConditions.visibilityOf(proceedBtn));
			 proceedBtn.click();
			 Thread.sleep(2000);
		}
	   //submit documents by customer
		public void submitDocuments() throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(firstDocUploadBox));
			firstDocUploadBox.click();
			Thread.sleep(100);
			uploadFile(panImgPath);
			Thread.sleep(6000);
			wait.until(ExpectedConditions.visibilityOf(secondDocUploadBox));
			secondDocUploadBox.click();
			Thread.sleep(100);
			uploadFile(AadharFrontImgPath);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(thirdDocUploadBox));
			thirdDocUploadBox.click();
			Thread.sleep(100);
			uploadFile(AadharBackImgPath);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(fourthDocUploadBox));
			fourthDocUploadBox.click();
			Thread.sleep(100);
			uploadFile(panImgPath);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(fifthDocUploadBox));
			fifthDocUploadBox.click();
			Thread.sleep(100);
			uploadFile(panImgPath);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(nextBtn));
			nextBtn.click();
			Thread.sleep(100);
		}
		//verify doc upload text
		public String verifyAllDocUpload() {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(docUploadVerifyText));
			return docUploadVerifyText.getText();
		}
		//start vpd call by customer and verify
		public String startVpdCallByCustomerAndVerify() throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(startVPDCall));
			startVPDCall.click();
			Thread.sleep(200);
			wait.until(ExpectedConditions.visibilityOf( verifyVpdStartedByCustomer));
			return verifyVpdStartedByCustomer.getText();
		}
		
		//Auditor starts f2f video process
		public void startVPDbyAuditor() throws InterruptedException, IOException {
			tabs = new ArrayList<String>(ldriver.getWindowHandles());
			ldriver.switchTo().window(tabs.get(0));
			Thread.sleep(5000);
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(ScheduledCasesBucket));
			ScheduledCasesBucket.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(AuditorcallBtn));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
					"//*[@id='scheduled']/div/div[1]/div/table/tbody/tr/td[3]")).size();
			 for (int r = 2; r <= rows; r++) {
				ActCustomerName = ldriver.findElement(By.xpath(
						"//*[@id='scheduled']/div["+r+"]/div[1]/div/table/tbody/tr/td[3]")).getText();
				if(ActCustomerName.equals(ExpcustomerName[0])) {
					WebElement callBtn=ldriver.findElement(By.xpath(
							"//*[@id='scheduled']/div["+r+"]/div[2]/div/table/tbody/tr/td/span[1]"));
					callBtn.click();
					break;
				}
			  }
		 }
		//auditor starts verification process
		public void customerVerificationByAuditor(String Text,String mobNum,String Amount,String pdDate) throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(nextBtn));
			nextBtn.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(secondNextBtn));
			secondNextBtn.click();
			Thread.sleep(1000);
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(questionPage));
			// get questions  count
			int rows = ldriver.findElements(By.xpath("/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/h6")).size();
			// Get Reason name Text                   
			for (int r = 1; r <= rows; r++) {
			String getQuestionName = ldriver.findElement(By.xpath(
								"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/h6[" + r + "]")).getText();
			  
			  if(getQuestionName.contains("Name of Applicants*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contentEquals("Scheme – GST / ABB*")) {
			      WebElement answerBox = ldriver.findElement(By.xpath(
								"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/select"));
			      js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Select sel=new Select(answerBox);
				  Thread.sleep(50);
				  sel.selectByIndex(1);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Constitution*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/select"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Select sel=new Select(answerBox);
				  Thread.sleep(50);
				  sel.selectByIndex(1);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Place (with address) of PD*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Owned or Rented, if rented details of lease deeds etc*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Persons Met on Video Call (Names and Designations)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Contact Number (Please mention specifically)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(mobNum);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("PD done by (Name & Designation)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Date of PD*")) {        
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(pdDate);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Source of Reference check (Please mention two reference details with Name & Contact No)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Loan Amount Requested (INR)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Amount);
                  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Tenor Requested*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/select"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Select sel=new Select(answerBox);
				  sel.selectByIndex(2);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Investment in Plant & machinery (INR)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(1000);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Amount);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("End Use*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(1000);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Business Model*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(1000);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys("FOCO");
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Business TO Details – PY / CY / Next Year*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(1000);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Debtor / Creditor details*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Supplier details*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Text);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Details of Product manufactured*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys("clothing products");
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Industry*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys("clothing industry");
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Manufacture/Trader/Services (Please mention the percentage as well)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys("manufacturer,80%");
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Level of Activity (Total Capacity Vs Running Capacity) Bifurcation of RM/WIP/FG , (30:40:30)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys("80/20");
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("No of workers*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys(Amount);
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("No of shifts (8hr/12hr)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys("2");
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Comments on Infra/Shed & Safety details*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/textarea"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Thread.sleep(50);
				  answerBox.sendKeys("good safety & infra");
				  Thread.sleep(1000);
			  }
			  else if(getQuestionName.contains("Overall Opinion (Positive/Negative)*")) {
				  WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div[1]/div/div/div/div[" + r + "]/select"));
				  js=(JavascriptExecutor)ldriver;
				  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
				  Thread.sleep(100);
				  wait.until(ExpectedConditions.visibilityOf(answerBox));
				  Select sel=new Select(answerBox);
				  Thread.sleep(50);
				  sel.selectByIndex(1);
				  Thread.sleep(1000);
			  }
		   }
			js=(JavascriptExecutor)ldriver;
			js.executeScript("arguments[0].scrollIntoView(true);", NotesBox);
			wait.until(ExpectedConditions.visibilityOf(NotesBox));
			NotesBox.click();
			wait.until(ExpectedConditions.visibilityOf(nextBtn));
			nextBtn.click();
			wait.until(ExpectedConditions.visibilityOf(takePictureDropDown));
			takePictureDropDown.click();
			wait.until(ExpectedConditions.visibilityOf(takePictureIcon));
			takePictureIcon.click();
			Thread.sleep(1000);
			nextBtn.click();
			Thread.sleep(100);
		}
		//verify case completed success msg
		public String verifyDetailsAndDocSubmitSuccessMsg() {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(verifyCaseCompleteMsg));
			return verifyCaseCompleteMsg.getText();
		}
		//mark case as complete by auditor
		public void markCaseAsComplete() throws InterruptedException {
			endCallBtn.click();
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(addCommentsBox));
			addCommentsBox.sendKeys("case complete");
			Thread.sleep(200);
			caseCompleteBtn.click();
			Thread.sleep(100);
		}
		//verify completed case in completed vpd bucket
		public boolean verifyCompletedCase() throws InterruptedException, IOException {
			boolean temp=false;
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(vpdIcon));
			Thread.sleep(3000);
			vpdIcon.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(CompletedBucket));
			CompletedBucket.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(ClientName));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
		    int rows = ldriver.findElements(By.xpath(
					"//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
			for (int r = 2; r <= rows; r++) {
				ActCustomerName = ldriver.findElement(By.xpath(
						"//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
				if(ActCustomerName.equals(ExpcustomerName[0])) {
					temp=true;
					break;
				}
			  }
			return temp;
		}
		//auditor approves case
		public void approveCaseByAuditor() throws InterruptedException, IOException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(vpdIcon));
			Thread.sleep(3000);
			vpdIcon.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(completedBucket));
			completedBucket.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(actionCaseDropdown));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
                             "//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
            for (int r = 2; r <= rows; r++) {
	                ActCustomerName = ldriver.findElement(By.xpath(
		                        "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
                if(ActCustomerName.equals(ExpcustomerName[0])) {
	                  WebElement actionDropdown=ldriver.findElement(By.xpath(
		                           "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[9]"));
		            actionDropdown.click();
		            break;
	            }
            }
			wait.until(ExpectedConditions.visibilityOf(caseApproveBtn));
			caseApproveBtn.click();
			Thread.sleep(2000);
		}
		//verify approved case status by auditor
		public String verifyCaseApprovedStatus() throws IOException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(caseApprovedStatus));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
			         "//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
		    for (int r = 2; r <= rows; r++) {
				  ActCustomerName = ldriver.findElement(By.xpath(
					             "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
			   if(ActCustomerName.equals(ExpcustomerName[0])) {
				   WebElement element=ldriver.findElement(By.xpath(
					              "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[9]"));
				    caseStatus=element.getText();
					break;
				}
		     }
			 return caseStatus;
		}
		//verify case in Approved bucket at admin end
		public boolean verifyCaseInApprovedBucket() throws InterruptedException, IOException {
			boolean temp=false;
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(vpdIcon));
			Thread.sleep(2000);
			vpdIcon.click();
			Thread.sleep(3000);
		    wait.until(ExpectedConditions.elementToBeClickable(uploadCasePad));
			Actions act = new Actions(driver);
			act.moveToElement(uploadCasePad).build().perform();
			caseManagement.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(completedBucket));
			completedBucket.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(approvedBucket));
			approvedBucket.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(caseName));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
				                    "//*[@id='approved']/div/div/div/div/div/table/tbody/tr/td[1]")).size();
			  for (int r = 2; r <= rows; r++) {
					   ActCustomerName = ldriver.findElement(By.xpath(
						            "//*[@id='approved']/div/div/div["+r+"]/div/div/table/tbody/tr/td[1]")).getText();
				 if(ActCustomerName.equals(ExpcustomerName[0])) {
					 temp=true;
						break;
			     }
			  }
			  return temp;
		 }
		//download generated report 
		public void clickOnGeneratedReport() throws InterruptedException, IOException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(vpdIcon));
			Thread.sleep(2000);
			vpdIcon.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(completedBucket));
			completedBucket.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(downloadReportIcon));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
			                    "//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
		    for (int r = 2; r <= rows; r++) {
				   ActCustomerName = ldriver.findElement(By.xpath(
					            "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
			   if(ActCustomerName.equals(ExpcustomerName[0])) {
				    WebElement reportIcon=ldriver.findElement(By.xpath(
					              "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[8]/span[3]"));
					reportIcon.click();
					Thread.sleep(12000);
					break;
				}
		    }
		}
		//download Video anD verify status code 200
		public int getDownloadVideoUrlStatusCode() throws InterruptedException, IOException {
			String parent = ldriver.getWindowHandle();
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(vpdIcon));
			Thread.sleep(2000);
		    vpdIcon.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(completedBucket));
			completedBucket.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(downloadRecorededVkycIcon));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
	                   "//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
	        for (int r = 2; r <= rows; r++) {
		             ActCustomerName = ldriver.findElement(By.xpath(
			                    "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
	              if(ActCustomerName.equals(ExpcustomerName[0])) {
		              WebElement RecordedVPDIcon=ldriver.findElement(By.xpath(
			                    "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[8]/span[1]"));
		              RecordedVPDIcon.click();
		              Thread.sleep(1000);
			          break;
		           }
	        }
	        Thread.sleep(100);
	        Set<String> allWindows = ldriver.getWindowHandles();
			ArrayList<String> tabs = new ArrayList<String>(allWindows);
			ldriver.switchTo().window(tabs.get(0));
			//ldriver.close();
			for (String child : allWindows) {
			     if (!parent.equalsIgnoreCase(child)) {
					  ldriver.switchTo().window(child);
				  }
			 }
			 String strUrl = ldriver.getCurrentUrl();
			 Thread.sleep(100);
			 URL url = new URL(strUrl);
			 HttpURLConnection http = (HttpURLConnection)url.openConnection();
			 int statusCode = http.getResponseCode();  
			 return statusCode; 
	     }

		//Admin uploads a VPD case using excel
		public void uploadCaseUsingExcel() throws InterruptedException {
			 wait=new WebDriverWait(ldriver, 120);
		     wait.until(ExpectedConditions.visibilityOf(uploadCasePad));
			 Actions act = new Actions(driver);
		     act.moveToElement(uploadCasePad).build().perform();
		     uploadCaseLlink.click();
		     wait=new WebDriverWait(ldriver, 120);
		     wait.until(ExpectedConditions.visibilityOf(uploadExcelBtn));
		     uploadExcelBtn.click();
			 Thread.sleep(100);
			 uploadFile(VpdExcelPath);
		}
		
		//verify case uploaded at Agent end thru excel
	    public boolean verifyCaseUploadedUsingExcelAtAuditorEnd() throws InterruptedException, IOException {
			 boolean temp=false;
			 wait=new WebDriverWait(ldriver, 120);
			 wait.until(ExpectedConditions.elementToBeClickable(vpdIcon));
			 Thread.sleep(2000);
			 vpdIcon.click();
			 Thread.sleep(2000);
			 wait.until(ExpectedConditions.elementToBeClickable(AssignedCasesTab));
			 AssignedCasesTab.click();
			 wait.until(ExpectedConditions.visibilityOf(verifyCustomerName));
			 int rows = ldriver.findElements(By.xpath(
					"//*[@id='assigned']/div/div[1]/div/table/tbody/tr/td[3]")).size();
			 for (int r = 2; r <= rows; r++) {
					ActCustomerName = ldriver.findElement(By.xpath(
						"//*[@id='assigned']/div["+r+"]/div[1]/div/table/tbody/tr/td[3]")).getText();
				if(ActCustomerName.equals("DMM")) {
					 temp=true;
					 break;
				}
			 }
				return temp;
		}
		//verify case upload thru excel success msg
		public String verifyCaseUploadSuccessMsg() {
			 wait=new WebDriverWait(ldriver, 120);
			 wait.until(ExpectedConditions.visibilityOf(caseUploadSuccessMsg));
			 return caseUploadSuccessMsg.getText();
		}
		
		//Reject case By Auditor
		public void rejectCaseByAuditor(String Text) throws InterruptedException, IOException {
			 wait=new WebDriverWait(ldriver, 120);
			 wait.until(ExpectedConditions.elementToBeClickable(vpdIcon));
			 Thread.sleep(2000);
			 vpdIcon.click();
			 Thread.sleep(3000);
			 wait.until(ExpectedConditions.elementToBeClickable(CompletedBucket));
			 CompletedBucket.click();
			 Thread.sleep(3000);
			 wait.until(ExpectedConditions.elementToBeClickable(actionCaseDropdown));
			 String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			 String ExpcustomerName[] = data.split(" ");
			 int rows = ldriver.findElements(By.xpath(
		                   "//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
		     for (int r = 2; r <= rows; r++) {
			         ActCustomerName = ldriver.findElement(By.xpath(
				            "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
		             if(ActCustomerName.equals(ExpcustomerName[0])) {
			                WebElement actionDropdown=ldriver.findElement(By.xpath(
				                   "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[9]"));
			                actionDropdown.click();
				            break;
			         }
		      }
			  wait.until(ExpectedConditions.visibilityOf(caseRejectBtn));
			  caseRejectBtn.click();
			  wait.until(ExpectedConditions.visibilityOf(addCommentBox));
			  addCommentBox.sendKeys(Text);
			  confirmBtn.click();
			  Thread.sleep(200);
	    }
		//verify rejected case status by auditor
		public String verifyCaseRejectedStatus() throws IOException {
			  wait=new WebDriverWait(ldriver, 120);
			  wait.until(ExpectedConditions.visibilityOf(caseRejectedStatus));
			  String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			  String ExpcustomerName[] = data.split(" ");
			  int rows = ldriver.findElements(By.xpath(
		                   "//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
		      for (int r = 2; r <= rows; r++) {
			          ActCustomerName = ldriver.findElement(By.xpath(
				                   "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
		              if(ActCustomerName.equals(ExpcustomerName[0])) {
			                WebElement element=ldriver.findElement(By.xpath(
				                   "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[9]"));
			                caseStatus= element.getText();
				            break;
			          }
		       }
			  return caseStatus;
		 }
		 //verify case in rejected bucket at admin end
		 public boolean verifyCaseInRejectedBucket() throws InterruptedException, IOException {
			  boolean temp=false;
			  wait=new WebDriverWait(ldriver, 120);
			  wait.until(ExpectedConditions.visibilityOf(vpdIcon));
			  Thread.sleep(2000);
			  vpdIcon.click();
			  Thread.sleep(3000);
			  wait.until(ExpectedConditions.visibilityOf(uploadCasePad));
			  Actions act = new Actions(driver);
			  act.moveToElement(uploadCasePad).build().perform();
			  caseManagement.click();
			  Thread.sleep(2000);
			  wait.until(ExpectedConditions.visibilityOf(completedBucket));
			  completedBucket.click();
			  Thread.sleep(2000);
			  wait.until(ExpectedConditions.visibilityOf(rejectedBucket));
			  rejectedBucket.click();
			  Thread.sleep(2000);
			  wait.until(ExpectedConditions.visibilityOf(caseName));
			  String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			  String ExpcustomerName[] = data.split(" ");
			  int rows = ldriver.findElements(By.xpath(
					                    "//*[@id='rejected']/div/div/div/div/div/table/tbody/tr/td[1]")).size();
				 for (int r = 2; r <= rows; r++) {
						   ActCustomerName = ldriver.findElement(By.xpath(
							            "//*[@id='rejected']/div/div/div["+r+"]/div/div/table/tbody/tr/td[1]")).getText();
					   if(ActCustomerName.equals(ExpcustomerName[0])) {
						   temp=true;
							break;
						}
				  }
			  return temp;
		 }
		//ReAssign case By Auditor
	    public void reAssignCaseByAuditor(String Text) throws InterruptedException, IOException {
	    	   wait=new WebDriverWait(ldriver, 120);
			   wait.until(ExpectedConditions.elementToBeClickable(vpdIcon));
			   Thread.sleep(2000);
			   vpdIcon.click();
			   Thread.sleep(3000);
			   wait.until(ExpectedConditions.elementToBeClickable(completedBucket));
			   completedBucket.click();
			   Thread.sleep(3000);
			   wait.until(ExpectedConditions.elementToBeClickable(actionCaseDropdown));
			   String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			   String ExpcustomerName[] = data.split(" ");
			   int rows = ldriver.findElements(By.xpath(
		                   "//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
		       for (int r = 2; r <= rows; r++) {
			            ActCustomerName = ldriver.findElement(By.xpath(
				                    "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
		                if(ActCustomerName.equals(ExpcustomerName[0])) {
			                    WebElement actionDropdown=ldriver.findElement(By.xpath(
				                    "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[9]"));
			                    actionDropdown.click();
				                break;
			            }
		        }
			   wait.until(ExpectedConditions.visibilityOf(caseReAssignBtn));
			   caseReAssignBtn.click();
			   wait.until(ExpectedConditions.visibilityOf(addCommentBox));
			   addCommentBox.sendKeys(Text);
			   confirmBtn.click();
			   Thread.sleep(200);
		}
	    //verify case in reAssigned bucket at admin end
	    public boolean verifyCaseInReassignedBucket() throws InterruptedException, IOException {
	    	   boolean temp=false;
	    	   wait=new WebDriverWait(ldriver, 120);
			   wait.until(ExpectedConditions.elementToBeClickable(vpdIcon));
			   Thread.sleep(2000);
			   vpdIcon.click();
			   Thread.sleep(3000);
	    	   wait.until(ExpectedConditions.elementToBeClickable(uploadCasePad));
	    	   Actions act = new Actions(driver);
	    	   act.moveToElement(uploadCasePad).build().perform();
	    	   caseManagement.click();
	    	   Thread.sleep(2000);
	    	   wait.until(ExpectedConditions.elementToBeClickable(ActiveBucket));
	    	   ActiveBucket.click();
	    	   Thread.sleep(2000);
	    	   wait.until(ExpectedConditions.elementToBeClickable(reAssignedBucket));
	    	   reAssignedBucket.click();
	    	   Thread.sleep(2000);
	    	   wait.until(ExpectedConditions.visibilityOf(caseName));
	    	   String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			   String ExpcustomerName[] = data.split(" ");
			   int rows = ldriver.findElements(By.xpath(
					                    "//*[@id='reassigned']/div/div/div/div/div/table/tbody/tr/td[1]")).size();
				for (int r = 2; r <= rows; r++) {
						   ActCustomerName = ldriver.findElement(By.xpath(
							            "//*[@id='reassigned']/div/div/div["+r+"]/div/div/table/tbody/tr/td[1]")).getText();
					   if(ActCustomerName.equals(ExpcustomerName[0])) {
						    temp=true;
							break;
						}
				}
				return temp;
	     }

}
