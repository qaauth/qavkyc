package com.vKyc.Pageobject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;  
import java.io.BufferedReader;     
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
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



public class videoKyc extends Baseclass {

	WebDriver ldriver;
	WebDriverWait wait;
	JavascriptExecutor js;
	ArrayList<String> tabs;
	SoftAssert asert = new SoftAssert();
    String ActCustomerName=" ";
    String caseStatus=" ";
	public videoKyc(WebDriver rdriver) {
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
	
	@FindBy(xpath ="//*[@id='Check_Pad']")
	@CacheLookup
	WebElement uploadCasePad;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/div[1]/aside/ul/li[3]/a/span[1]")
	@CacheLookup
	WebElement caseManagmentPad;
	
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
	
	@FindBy(xpath ="//*[@type='text'][@placeholder='Enter Business Manager Name']")
	@CacheLookup
	WebElement enterBusinessManagerName;
	
	@FindBy(xpath ="//*[@type='text'][@placeholder='000-000-0000']")
	@CacheLookup
	WebElement enterBusinessManagerMobNum;
	
	@FindBy(xpath ="//textarea[@class='form-control']")
	@CacheLookup
	WebElement enterSpecialInstruction;
	
	@FindBy(xpath ="//input[@type='radio'][@id='customRadioInline1']")
	@CacheLookup
	WebElement vkycRadioBtn;
	
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
	
	//schedule v-kyc by AGENT
	@FindBy(xpath ="//*[@id='assigned-tab']")
	@CacheLookup
	WebElement AssignedCasesTab;
	
	@FindBy(xpath ="(//*[@class='position-relative align-top'])[1]")
	@CacheLookup
	WebElement scheduleKycCalendar;
	
	@FindBy(xpath ="(//*[@class='transfer dropdown dropleft'])[1]")
	@CacheLookup
	WebElement TransferCaseBtn;
	
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
	
	@FindBy(xpath ="//*[contains(text(),'Loading please wait')]")
	@CacheLookup
	WebElement loadingText;
	
	@FindBy(xpath ="//*[@id='scheduled-tab']")
	@CacheLookup
	WebElement ScheduledCasesBucket;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div[1]/div/table/tbody/tr/td[3]")
	@CacheLookup
	WebElement customerName;
	
	@FindBy(xpath ="//*[contains(text(),'Waiting for Customer to join.')]")
	@CacheLookup
	WebElement verifyKycStartedByAgentText;
	
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
	@CacheLookup
	WebElement startKycLink;
	
	@FindBy(xpath ="(//*[contains(text(),'https://vcip-uat.herofincorp.com/reschedule')])[2]")
	@CacheLookup
	WebElement rescheduleKycLink;
	
	@FindBy(xpath ="//*[text()='Reschedule with same Agent']")
	@CacheLookup
	WebElement rescheduleWithSameAgent;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[2]/div[1]/div/div/span")
	@CacheLookup
	WebElement selectTiming;
	
	@FindBy(xpath ="//*[text()='Schedule']")
	@CacheLookup
	WebElement ScheduledBtn;
	
	@FindBy(xpath ="//*[text()='Thank you!']")
	@CacheLookup
	WebElement rescheuleSuccessMsg;
	
	@FindBy(xpath ="/html/body/div/aside/div[1]/a[1]")
	@CacheLookup
	WebElement publicInbox;
	
	@FindBy(xpath ="/html/body/div/main/div[2]/div[3]/div/div[4]/div/div/table/tbody/tr/td[4]")
	@CacheLookup
	WebElement rescheduledReceivedLinkText;
	
	@FindBy(xpath ="//*[text()='Reschedule for another time']")
	@CacheLookup
	WebElement rescheduleforAnotherTime;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div[1]/div/div/div/input")
	@CacheLookup
	WebElement enterScheduleDate;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div[2]/div/div/div/input")
	@CacheLookup
	WebElement enterScheduleTime;
	
	@FindBy(xpath ="//*[text()='Click Here to Start']")
	@CacheLookup
	WebElement linkText;
	
	//consent fill up form
	@FindBy(xpath ="//*[text()='Customer consent']")
	@CacheLookup
	WebElement CustomerConsentText;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div/div[2]/div[1]/input")
	@CacheLookup
	WebElement CompliancecheckBox1;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div/div[2]/div[2]/input")
	@CacheLookup
	WebElement CompliancecheckBox2;
	
	@FindBy(xpath ="//*[text()='Proceed']")
	@CacheLookup
	WebElement proceedBtn;
	
	//pan upload
	
	@FindBy(xpath ="//*[@id='ID_red']")
	@CacheLookup
	WebElement panCardSubmit;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div[1]/div[3]")
	@CacheLookup
	WebElement panCardSubmitUsingCamera;
	
	@FindBy(xpath ="//*[@id='outer-circle']")
	@CacheLookup
	WebElement clickPanPicture;
	
	@FindBy(xpath ="//*[contains(text(),'PAN Card')]")
	@CacheLookup
	WebElement submitPanCardTxt;
	
	@FindBy(xpath ="//label[@for='file-upload']")
	@CacheLookup
	WebElement browseToUploadPanBtn;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[1]")
	@CacheLookup
	WebElement candidateNameVerifyCheck;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div/div[2]/div/div/div/input")
	@CacheLookup
	WebElement candidateName;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div/div[3]/div/div/div/input")
	@CacheLookup
	WebElement candidateFatherName;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div/div[4]/div/div/div/input")
	@CacheLookup
	WebElement candidatePanNumber;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div/div[5]/div/div/div/input")
	@CacheLookup
	WebElement candidateDob;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div/div[4]/div/div/div/input")
	@CacheLookup
	WebElement candidateGender;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div/div[2]/div/div/div/input")
	@CacheLookup
	WebElement candidateAdhaarName;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div/div[2]/div/div/div/input")
	@CacheLookup
	WebElement candidateAdhaarAddress;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[2]")
	@CacheLookup
	WebElement fatherNameVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[3]")
	@CacheLookup
	WebElement panVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[4]")
	@CacheLookup
	WebElement dobVerifyCheck;
	
	@FindBy(xpath ="//*[contains(text(),'PAN information has been successfully uploaded')]")
	@CacheLookup
	WebElement panUploadedSuccessMsg;
	
	@FindBy(xpath ="//*[text()='Next']")
	@CacheLookup
	WebElement nextBtn;
	
	//aadhar upload
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div/div[2]")
	@CacheLookup
	WebElement aadharUploadBox;
	
	@FindBy(xpath ="//*[@id='root']/div/div/section/div/div[2]/div/div[3]/div")
	@CacheLookup
	WebElement aadharImgUpload;
	
	@FindBy(xpath ="//*[@for='file-upload']")
	@CacheLookup
	WebElement aadharFrontImgUpload;
	
	@FindBy(xpath ="//*[contains(text(),'Front photo uploaded')]")
	@CacheLookup
	WebElement frontImgUploadedSuccessTxt;
	
	@FindBy(xpath ="//*[@for='file-upload-2']")
	@CacheLookup
	WebElement aadharBackImgUpload;
	
	@FindBy(xpath ="//*[text()='Aadhaar Front Upload']")
	@CacheLookup
	WebElement aadharUploadText;
	
	@FindBy(xpath ="//*[text()='Aadhaar Image']")
	@CacheLookup
	WebElement verifyAadharUploadText;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[2]/div[2]")
	@CacheLookup
	WebElement aadharFrontImgUploadUsingCamera;
	
	@FindBy(xpath ="//*[@id='outer-circle']")
	@CacheLookup
	WebElement aadharFrontImgCapture;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div/div[3]/div[2]")
	@CacheLookup
	WebElement aadharBackImgUploadUsingCamera;
	
	@FindBy(xpath ="//*[@id='outer-circle']")
	@CacheLookup
	WebElement aadharBackImgCapture;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[1]")
	@CacheLookup
	WebElement candidateVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[2]")
	@CacheLookup
	WebElement yobVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[3]")
	@CacheLookup
	WebElement genderVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[4]")
	@CacheLookup
	WebElement aadharNumVerifyCheck;
	
	@FindBy(xpath ="//*[text()='Next']")
	@CacheLookup
	WebElement adharSubmitNxtBtn;
	
	@FindBy(xpath ="//*[@class='MuiSvgIcon-root']")
	@CacheLookup
	WebElement addressVerifyCheck;
	
	@FindBy(xpath ="//*[text()='Next']")
	@CacheLookup
	WebElement SubmitNxtBtn;
	
	@FindBy(xpath ="//*[text()='Aadhar Card']")
	@CacheLookup
	WebElement aadharUploadVerifyText;
	
	@FindBy(xpath ="//*[text()='Next']")
	@CacheLookup
	WebElement finalSubmitNxtBtn;
	
	@FindBy(xpath ="//*[contains(text(),'Start Video')]")
	@CacheLookup
	WebElement startVideoKycBtn;
	
	//Agent process
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div[2]/div/table/tbody/tr/td/span[1]")
	@CacheLookup
	WebElement AgentcallBtn;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/h6[1]")
	@CacheLookup
	WebElement questionPage;
	
	@FindBy(xpath ="(//*[@id='exampleFormControlSelect'])[2]")
	@CacheLookup
	WebElement selectValidPassportOption;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[2]/div[2]/button")
	@CacheLookup
	WebElement nxtBtn;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[2]")
	@CacheLookup
	WebElement clickOutsideBox;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[1]")
	@CacheLookup
	WebElement AddressVerifyImg;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div/div/input")
	@CacheLookup
	WebElement geoTagAddress;
	
	@FindBy(xpath ="//*[text()='Location details has been successfully verified']")
	@CacheLookup
	WebElement locationCapturedSuccessMsg;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[2]/div[2]/button")
	@CacheLookup
	WebElement next;
	
	@FindBy(xpath ="//*[text()='AADHAAR card has been successfully verified']")
	@CacheLookup
	WebElement aadharCapturedSuccessMsg;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[2]/div[2]/button")
	@CacheLookup
	WebElement nextClickBtn;
	
	@FindBy(xpath ="//*[text()='PAN card has been successfully verified']")
	@CacheLookup
	WebElement panCapturedSuccessMsg;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[2]/div[2]/button")
	@CacheLookup
	WebElement nxtclick;
	
	@FindBy(xpath ="//*[text()='Take Screenshot']")
	@CacheLookup
	WebElement takeCustomerScreenshot;
	
	@FindBy(xpath ="//*[text()='Next']")
	@CacheLookup    
	WebElement nxtClkBtn;
	
	@FindBy(xpath ="//*[text()='Retry']")
	@CacheLookup    
	WebElement retryBtn;
	
	@FindBy(xpath ="//*[@xmlns='http://www.w3.org/2000/svg']")
	@CacheLookup
	WebElement camScreenshotIcon;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[1]/div/div/div[2]/div[1]/div")
	@CacheLookup
	WebElement picture;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[2]/div[2]/button")
	@CacheLookup
	WebElement nxtClickBtn;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[2]/div[2]/button")
	@CacheLookup
	WebElement nxtClikBtn;
	
	@FindBy(xpath ="//*[text()='All Documents Verified.']")
	@CacheLookup
	WebElement docVerifiedSuccessMsg;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[1]")
	@CacheLookup
	WebElement PanHolderNameVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[3]")
	@CacheLookup
	WebElement PanholderFatherNameVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[5]")
	@CacheLookup
	WebElement panNumVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[7]")
	@CacheLookup
	WebElement panDobVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[1]")
	@CacheLookup
	WebElement AadharHolderNameVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[3]")
	@CacheLookup
	WebElement AadharholderGenderVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[5]")
	@CacheLookup
	WebElement AadharHolderAddressVerifyCheck;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[7]")
	@CacheLookup
	WebElement AadharYOBVerifyCheck;
	
	@FindBy(xpath ="//*[@id='navLeaveButton']")
	@CacheLookup
	WebElement endVideocallBtn;
	
	@FindBy(xpath ="/html/body/div/div/div/section/div[3]/div/div/div/div[2]/div/textarea")
	@CacheLookup
	WebElement addCommentsBox;
	
	@FindBy(xpath ="//*[text()='Case Complete']")
	@CacheLookup
	WebElement caseCompleteBtn;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div/div/table/tbody/tr/td[7]")
	@CacheLookup
	WebElement verifyCaseStatusText;
	
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
	
	@FindBy(xpath ="(//*[text()='Rejected'])[1]")
	@CacheLookup
	WebElement caseRejectedStatus;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div[1]/div/div/div/div[2]/div/textarea")
	@CacheLookup
	WebElement addCommentBox;
	
	@FindBy(xpath ="//*[text()='Confirm']")
	@CacheLookup
	WebElement confirmBtn;
	
	@FindBy(xpath ="(//*[@class='MuiSvgIcon-root'])[1]")
	@CacheLookup
	WebElement downloadReportIcon;
	
	@FindBy(xpath ="//*[@class='percentage']")
	@CacheLookup
	WebElement getPercentage;
	
	@FindBy(xpath ="(//*[text()='Rescheduled By Customer'])[1]")
	@CacheLookup
	WebElement rescheduleText;
	
	@FindBy(xpath ="/html/body/div/div/div/table[1]/tr/td/table/tr/td/table[1]/tr/td[1]/table/tr/td[3]")
	@CacheLookup
	WebElement customerNameOnReport;
	
	@FindBy(xpath ="/html/body/div/div/div/table[1]/tr/td/table/tr/td/table[3]/tr/td[1]/table/tr/td[3]")
	@CacheLookup
	WebElement customerMobNumOnReport;
	
	@FindBy(xpath ="/html/body/div/div/div/table[2]/tr/td/table/tr/td/table[3]/tr/td[1]/table/tr/td[3]/time")
	@CacheLookup
	WebElement callStartDateTime;
	
	@FindBy(xpath ="/html/body/div/div/div/table[2]/tr/td/table/tr/td/table[3]/tr/td[2]/table/tr/td[3]/time")
	@CacheLookup
	WebElement callEndDateTime;
	
	@FindBy(xpath ="/html/body/div/div/div/table[4]/tr/td/table/tr/td/table[2]/tr/td[3]")
	@CacheLookup
	WebElement geoTaggingAddress;
	
	@FindBy(xpath ="/html/body/div/div/div/table[4]/tr/td/table/tr/td/table[3]/tr/td[2]/table[1]/tr/td[3]")
	@CacheLookup
	WebElement panNumber;
	
	@FindBy(xpath ="/html/body/div/div/div/table[4]/tr/td/table/tr/td/table[3]/tr/td[2]/table[2]/tr/td[3]")
	@CacheLookup
	WebElement panHolderName;
	
	@FindBy(xpath ="/html/body/div/div/div/table[4]/tr/td/table/tr/td/table[3]/tr/td[2]/table[3]/tr/td[3]")
	@CacheLookup
	WebElement panHolderDob;
	
	@FindBy(xpath ="/html/body/div/div/div/table[4]/tr/td/table/tr/td/table[3]/tr/td[2]/table[4]/tr/td[3]")
	@CacheLookup
	WebElement panHolderFatherName;
	
	@FindBy(xpath ="/html/body/div/div/div/table[5]/tr/td/table/tr/td/table[1]/tr/td[2]/table[1]/tr/td[3]")
	@CacheLookup
	WebElement adhaarHolderName;
	
	@FindBy(xpath ="/html/body/div/div/div/table[5]/tr/td/table/tr/td/table[1]/tr/td[2]/table[2]/tr/td[3]")
	@CacheLookup
	WebElement adhaarHolderAddress;
	
	@FindBy(xpath ="/html/body/div/div/div/table[5]/tr/td/table/tr/td/table[1]/tr/td[2]/table[3]/tr/td[1]/table/tr/td[3]")
	@CacheLookup
	WebElement adhaarHolderDob;
	
	@FindBy(xpath ="/html/body/div/div/div/table[5]/tr/td/table/tr/td/table[1]/tr/td[2]/table[3]/tr/td[2]/table/tr/td[3]")
	@CacheLookup
	WebElement adhaarHolderGender;
	
	@FindBy(xpath ="/html/body/div/div/div/table[5]/tr/td/table/tr/td/table[3]/tr/td/table[1]/tr/td/table[3]/tr/td[3]")
	@CacheLookup
	WebElement fachMatchScore;
	
	@FindBy(xpath ="/html/body/div/div/div/table[8]/tr/td/table/tr/td/table[1]/tr/td/table/tr/td[3]")
	@CacheLookup    
	WebElement auditStatus;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div/div/table/tbody/tr/td[8]/span[1]")
	@CacheLookup
	WebElement downloadRecordedVkycIcon;
	
	//Case upload using excel
	@FindBy(xpath ="//*[@class='custom-upload-btn']")
	@CacheLookup
	WebElement uploadExcelBtn;
	
	@FindBy(xpath ="//*[contains(text(),'Cases uploaded successfully')]")
	@CacheLookup
	WebElement caseUploadSuccessMsg;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/section/div[1]/section/div/div[2]/div/div/div/div/div[3]/div/div[2]/div[1]/div/table/tbody/tr/td[3]")
	@CacheLookup
	WebElement verifyCustomerName;
	
	@FindBy(xpath ="//*[text()='Case Management']")
	@CacheLookup
	WebElement caseManagement;
	
	@FindBy(xpath ="//*[text()='Completed']")
	@CacheLookup
	WebElement completedBucket;
	
	@FindBy(xpath ="//*[text()='Active']")
	@CacheLookup
	WebElement ActiveBucket;
	
	@FindBy(xpath ="//*[text()='Rejected']")
	@CacheLookup
	WebElement rejectedBucket;
	
	@FindBy(xpath ="//*[text()='Approved']")
	@CacheLookup
	WebElement approvedBucket;
	
	@FindBy(xpath ="//*[text()='Reassigned']")
	@CacheLookup
	WebElement reAssignedBucket;
	
	@FindBy(xpath ="/html/body/div[1]/div/div/div[1]/section/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div/div/div[2]/div/div/table/tbody/tr/td[1]")
	@CacheLookup
	WebElement caseName;
	
	@FindBy(xpath ="//select[@id='vkyc']")
	@CacheLookup
	WebElement reportDropdown;
	
	@FindBy(xpath ="//*[text()='Download']")
	@CacheLookup
	WebElement DownloadBtn;
	
	//transfer case
	@FindBy(xpath ="//*[@id='assigned']/div[2]/div[2]/table/tbody/tr/td/span[2]/div/a[2]/span[2]")
	@CacheLookup
	WebElement caseTransferToAgent;
	
	
	
	
    // Admin initiates/uploads a v-kyc case
	public void uploadCase(String customerName,String Text,String mobNum,String Id) throws InterruptedException {
		 wait=new WebDriverWait(ldriver, 120);
	     wait.until(ExpectedConditions.visibilityOf(uploadCasePad));
		 Actions act = new Actions(ldriver);
	     act.moveToElement(uploadCasePad).build().perform();
	     wait.until(ExpectedConditions.visibilityOf(uploadCaseLlink));
	     uploadCaseLlink.click();
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
	     vkycRadioBtn.click();
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
	     Thread.sleep(100);
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
	
	//schedule case by agent
	public void scheduleCalendarForKyc(String scheduledTime) throws InterruptedException, IOException {
		wait=new WebDriverWait(ldriver, 120);
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
		wait.until(ExpectedConditions.elementToBeClickable(SelectDateOkBtn));
		SelectDateOkBtn.click();
		wait.until(ExpectedConditions.visibilityOf(enterScheduledTime));
		enterScheduledTime.sendKeys(scheduledTime);
		wait.until(ExpectedConditions.elementToBeClickable(ScheduleBtn));
		Thread.sleep(50);
		ScheduleBtn.click();
		wait.until(ExpectedConditions.invisibilityOf(loadingText));
		Thread.sleep(2000);
	}
	
	//verify scheduled case by agent
	public boolean verifyCaseInScheduledBucket() throws InterruptedException, IOException {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(scheduleKycCalendar));
		ScheduledCasesBucket.click();
		wait.until(ExpectedConditions.visibilityOf(customerName));
		String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
		String ExpcustomerName[] = data.split(" ");
		boolean temp=false;
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
	
	//customer verify for Vkyc related link in mail on his end
	public String verifyVkycLinkReceivedByCustomer() throws InterruptedException {
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
	//customer reschedules time for kyc using given link
	public void customerRescheduleKycWithSameAgent() throws InterruptedException {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(rescheduleKycLink));
		Thread.sleep(2000);
	    rescheduleKycLink.click();
		tabs = new ArrayList<String>(ldriver.getWindowHandles());
		ldriver.switchTo().window(tabs.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(rescheduleWithSameAgent));
		rescheduleWithSameAgent.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectTiming));
		selectTiming.click();
		wait.until(ExpectedConditions.visibilityOf(ScheduledBtn));
		wait.until(ExpectedConditions.elementToBeClickable(ScheduledBtn));
		ScheduledBtn.click();
	}
	//verify rescheduled success msg
	public String verifyRescheduledSuccessMsg() {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(rescheuleSuccessMsg));
		return rescheuleSuccessMsg.getText();
	}
	//verify rescheduled meeting link
	public String verifyRescheduleKycLink() throws InterruptedException {
		wait=new WebDriverWait(ldriver, 120);
		tabs = new ArrayList<String>(ldriver.getWindowHandles());
		ldriver.switchTo().window(tabs.get(1));
		wait.until(ExpectedConditions.visibilityOf(publicInbox));
		publicInbox.click();
		wait.until(ExpectedConditions.visibilityOf(rescheduledReceivedLinkText));
		return rescheduledReceivedLinkText.getText();
	}
	//verify rescheduled status at Agent end
	public String verifyRescheduleStatusAtAgentEnd() throws InterruptedException, IOException {
		tabs = new ArrayList<String>(ldriver.getWindowHandles());
		ldriver.switchTo().window(tabs.get(0));
		wait.until(ExpectedConditions.visibilityOf(rescheduleText));
		String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
		String ExpcustomerName[] = data.split(" ");
		int rows = ldriver.findElements(By.xpath(
				"//*[@id='scheduled']/div/div[1]/div/table/tbody/tr/td[3]")).size();
		for (int r = 2; r <= rows; r++) {
			ActCustomerName = ldriver.findElement(By.xpath(
					"//*[@id='scheduled']/div["+r+"]/div[1]/div/table/tbody/tr/td[3]")).getText();
			if(ActCustomerName.equals(ExpcustomerName[0])) {
			    WebElement element = ldriver.findElement(By.xpath("//*[@id='scheduled']/div["+r+"]/div[1]/div/table/tbody/tr/td[6]"));
			    caseStatus=element.getText();
			    break;
			}
		}
		return	caseStatus;
	}
	
	//customer reschedule vkyc with different agent
	public void customerRescheduleVkycWithDifferentAgent(String scheduleDate,String scheduleTime) throws InterruptedException {
		wait=new WebDriverWait(ldriver, 120);
	    wait.until(ExpectedConditions.elementToBeClickable(rescheduleKycLink));
	    rescheduleKycLink.click();
		tabs = new ArrayList<String>(ldriver.getWindowHandles());
		ldriver.switchTo().window(tabs.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(rescheduleforAnotherTime));
		rescheduleforAnotherTime.click();
		wait.until(ExpectedConditions.elementToBeClickable(enterScheduleDate));
		enterScheduleDate.sendKeys(scheduleDate);
		Thread.sleep(100);
		enterScheduleTime.sendKeys(scheduleTime);
		Thread.sleep(100);
		wait.until(ExpectedConditions.elementToBeClickable(ScheduledBtn));
		ScheduledBtn.click();
	}
	//on verification customer initiate Kyc process by clicking on link
	public void customerClickOnKycLink() throws InterruptedException {
		wait=new WebDriverWait(ldriver, 120);
		//ClearBrowserCache();
		Thread.sleep(60000);
	    startKycLink.click();
		tabs = new ArrayList<String>(ldriver.getWindowHandles());
		ldriver.switchTo().window(tabs.get(2));
		Thread.sleep(100);
		wait.until(ExpectedConditions.elementToBeClickable(CustomerConsentText));
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
	    //ClearBrowserCache();
		Thread.sleep(60000);
		startKycLink.click();
		wait.until(ExpectedConditions.visibilityOf(CustomerConsentText));
		CompliancecheckBox1.click();
		Thread.sleep(100);
		CompliancecheckBox2.click();
		Thread.sleep(100);
		wait.until(ExpectedConditions.visibilityOf(proceedBtn));
		proceedBtn.click();
	}
	
	//submit pan card using browse to upload by customer
	public void submitPanCardUsingBrowseToUpload() throws InterruptedException {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(panCardSubmit));
		panCardSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(browseToUploadPanBtn));
		browseToUploadPanBtn.click();
		Thread.sleep(100);
		uploadFile(panImgPath);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(candidateNameVerifyCheck));
		candidateNameVerifyCheck.click();
		Thread.sleep(100);
		try {
			File output = new File("candidateName.txt");
			FileWriter writer = new FileWriter(output);
			writer.write(candidateName.getAttribute("value"));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		fatherNameVerifyCheck.click();
		Thread.sleep(100);
		try {
			File output = new File("candidateFatherName.txt");
			FileWriter writer = new FileWriter(output);
			writer.write(candidateFatherName.getAttribute("value"));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		panVerifyCheck.click();
		Thread.sleep(100);
		try {
			File output = new File("candidatePanNum.txt");
			FileWriter writer = new FileWriter(output);
			writer.write(candidatePanNumber.getAttribute("value"));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dobVerifyCheck.click();
		Thread.sleep(1000);
		String CandDob = candidateDob.getAttribute("value").substring(0, 4);
		try {
			File output = new File("candidateDob.txt");
			FileWriter writer = new FileWriter(output);
			writer.write(CandDob);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(100);
		nextBtn.click();
	}
	
	//action method to click on Aadhar doc box
	public void clickOnAdharDocBox() throws InterruptedException {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(aadharUploadBox));
		aadharUploadBox.click();
		Thread.sleep(2000);
	}
	//verify text to upload adhar
	public String verifyToProceedAdharUpload() {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(verifyAadharUploadText));
		return verifyAadharUploadText.getText();
	}
	//submit aadhar using browse to upload
	public boolean submitAadharUsingBrowseToUpload() throws InterruptedException {
		wait=new WebDriverWait(ldriver, 120);
		aadharImgUpload.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(aadharFrontImgUpload));
		aadharFrontImgUpload.click();
		Thread.sleep(3000);
		uploadFile(AadharFrontImgPath);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(frontImgUploadedSuccessTxt));
		aadharBackImgUpload.click();
		Thread.sleep(3000);
		uploadFile(AadharBackImgPath);
		Thread.sleep(3000);
		try{
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Aadhaar Front Upload']")));
	            return true;
	    }catch (TimeoutException te){
	            return false;
	    }
	}
	//verify aadhar upload success
	public String verifyDocDetailsCheckedSuccessMsg() throws InterruptedException {
	    wait=new WebDriverWait(ldriver, 120);
	    wait.until(ExpectedConditions.visibilityOf(candidateVerifyCheck));
		candidateVerifyCheck.click();
		Thread.sleep(100);
		yobVerifyCheck.click();
		Thread.sleep(100);
		genderVerifyCheck.click();
		Thread.sleep(100);
		try {
			File output = new File("candidateGender.txt");
			FileWriter writer = new FileWriter(output);
			writer.write(candidateGender.getAttribute("value"));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		aadharNumVerifyCheck.click();
		Thread.sleep(100);
		try {
			File output = new File("candidateAdhaarName.txt");
			FileWriter writer = new FileWriter(output);
			writer.write(candidateAdhaarName.getAttribute("value"));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(3000);
		adharSubmitNxtBtn.click();
		wait.until(ExpectedConditions.visibilityOf(addressVerifyCheck));
		addressVerifyCheck.click();
		Thread.sleep(100);
		try {
			File output = new File("candidateAdhaarAddress.txt");
			FileWriter writer = new FileWriter(output);
			writer.write(candidateAdhaarAddress.getAttribute("value"));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		SubmitNxtBtn.click();
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(aadharUploadVerifyText));
		return aadharUploadVerifyText.getText();
	 }
	 //start F2F video kyc BY candidate
	 public void startF2FVideoKycByCandidate() throws InterruptedException {
		 wait=new WebDriverWait(ldriver, 120);
		 wait.until(ExpectedConditions.visibilityOf(finalSubmitNxtBtn));
		 finalSubmitNxtBtn.click();
		 Thread.sleep(500);
		 wait.until(ExpectedConditions.visibilityOf(startVideoKycBtn));
		 startVideoKycBtn.click();
		 Thread.sleep(300);
	  }
	  //Agent starts f2f video process
	  public void startVKycByAgent() throws InterruptedException, IOException {
		 tabs = new ArrayList<String>(ldriver.getWindowHandles());
		 ldriver.switchTo().window(tabs.get(0));
		 Thread.sleep(100);
		 wait=new WebDriverWait(ldriver, 120);
		 wait.until(ExpectedConditions.visibilityOf(AgentcallBtn));
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
	   //agent starts doc-Address verification
	   public void doc_AddressVerification(String Text,String mobNum,String Amount) throws InterruptedException {
		  wait=new WebDriverWait(ldriver, 120);
		  wait.until(ExpectedConditions.visibilityOf(questionPage));
		  Thread.sleep(1000);
		  // get questions  count
		  int rows = ldriver.findElements(By.xpath("/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/h6")).size();
		  // Get Reason name Text                   
		  for (int r = 1; r <= rows; r++) {
		  String getQuestionName = ldriver.findElement(By.xpath(
			"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/h6[" + r + "]")).getText();
		  
		    if(getQuestionName.contains("PAN Card Number")) {
			    WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/textarea"));
			    js=(JavascriptExecutor)ldriver;
			    js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			    Thread.sleep(100);
			    wait.until(ExpectedConditions.visibilityOf(answerBox));
			    Thread.sleep(50);
			    answerBox.sendKeys("AFWPT0024N");
			    Thread.sleep(2000);
		     }
		     else if(getQuestionName.contains("Father's")) {
			    WebElement answerBox = ldriver.findElement(By.xpath(
							"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/textarea"));
		        js=(JavascriptExecutor)ldriver;
			    js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			    Thread.sleep(100);
			    wait.until(ExpectedConditions.visibilityOf(answerBox));
			    Thread.sleep(50);
			    answerBox.sendKeys(Text);
			    Thread.sleep(2000);
		     }
		     else if(getQuestionName.contains("How old are you?")) {
			    WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/textarea"));
			    js=(JavascriptExecutor)ldriver;
			    js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			    Thread.sleep(100);
			    wait.until(ExpectedConditions.visibilityOf(answerBox));
			    Thread.sleep(50);
			    answerBox.sendKeys("26");
			    Thread.sleep(2000);
		    }
		    else if(getQuestionName.contains("Date of Birth")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/textarea"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(100);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Thread.sleep(50);
			  answerBox.sendKeys("11-07-1995");
			  Thread.sleep(2000);
		   }
		   else if(getQuestionName.contains("Company Turnover (INR)")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/textarea"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(100);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Thread.sleep(50);
			  answerBox.sendKeys(Amount);
			  Thread.sleep(2000);
		   }
		   else if(getQuestionName.contains("Loan Amount Requested (INR)")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/textarea"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(100);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Thread.sleep(50);
			  answerBox.sendKeys(Amount);
			  Thread.sleep(2000);
		   }
		   else if(getQuestionName.contains("Have you ever undergone KYC process before?")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/select"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(100);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Select sel=new Select(answerBox);
			  Thread.sleep(50);
			  sel.selectByIndex(1);
			  Thread.sleep(2000);
		   }
		   else if(getQuestionName.contains("Do you have a valid passport?")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/select"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(100);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Select sel=new Select(answerBox);
			  Thread.sleep(50);
			  sel.selectByIndex(1);
			  Thread.sleep(2000);
		   }
		   else if(getQuestionName.contains("Please specify your sex as per your Identification documents")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/select"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(1000);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Select sel=new Select(answerBox);
			  Thread.sleep(50);
			  sel.selectByIndex(1);
			  Thread.sleep(2000); 
		  }
		  else if(getQuestionName.contains("What is your email id")) {
			 WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/textarea"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(100);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Thread.sleep(50);
			  answerBox.sendKeys(Text+"@gmail.com");
			  Thread.sleep(2000);
		   }
		   else if(getQuestionName.contains("What is your permanent address?")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/textarea"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(100);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Thread.sleep(50);
			  answerBox.sendKeys(Text);
			  Thread.sleep(2000);
		   }
		   else if(getQuestionName.contains("What is your mobile number?")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/textarea"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(1000);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Thread.sleep(50);
			  answerBox.sendKeys(mobNum);
			  Thread.sleep(2000);
		   }
		   else if(getQuestionName.contains("Constitution")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/select"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(100);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Select sel=new Select(answerBox);
			  Thread.sleep(50);
			  sel.selectByIndex(1);
			  Thread.sleep(2000);
		   }
		   else if(getQuestionName.contains("Tenor")) {
			  WebElement answerBox = ldriver.findElement(By.xpath(
						"/html/body/div/div/div/section/div[1]/div/div[1]/div/div[2]/div[1]/div[" + r + "]/select"));
			  js=(JavascriptExecutor)ldriver;
			  js.executeScript("arguments[0].scrollIntoView(true);", answerBox);
			  Thread.sleep(100);
			  wait.until(ExpectedConditions.visibilityOf(answerBox));
			  Select sel=new Select(answerBox);
			  Thread.sleep(50);
			  sel.selectByIndex(1);
			  Thread.sleep(2000);
		   }
		}
		Thread.sleep(1000);
		clickOutsideBox.click();
		Thread.sleep(50);
		wait.until(ExpectedConditions.elementToBeClickable(nxtBtn));
		nxtBtn.click();
		Thread.sleep(100);
		wait.until(ExpectedConditions.elementToBeClickable(AddressVerifyImg));
		AddressVerifyImg.click();
		Thread.sleep(100);
		try {
			File output = new File("geoTagAddress.txt");
			FileWriter writer = new FileWriter(output);
			writer.write(geoTagAddress.getAttribute("value"));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(100);
	}
	// address verification success msg
	public String verifyAddressVerificationSuccessMsg() {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(locationCapturedSuccessMsg));
		return locationCapturedSuccessMsg.getText();
	}
	//agent starts doc-Pan verification
	public void doc_PanVerification() throws InterruptedException {
		next.click();
		Thread.sleep(200);
		wait=new WebDriverWait(ldriver, 120);
		js=(JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].scrollIntoView(true);", nxtclick);
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOf(nxtclick));
		PanHolderNameVerifyCheck.click();
		Thread.sleep(200);
		PanholderFatherNameVerifyCheck.click();
		Thread.sleep(200);
		panNumVerifyCheck.click();
		Thread.sleep(200);
		panDobVerifyCheck.click();
		Thread.sleep(500);
	}
	//pan verification success msg
	public String panverificationSuccessMsg() {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(panCapturedSuccessMsg));
		return panCapturedSuccessMsg.getText();
	}
	//agent starts doc-adhar verification
	public void doc_AadharVerification() throws InterruptedException {
		nxtclick.click();
		Thread.sleep(200);
		wait=new WebDriverWait(ldriver, 120);
		js=(JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].scrollIntoView(true);", nextClickBtn);
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOf(nextClickBtn));
		AadharHolderNameVerifyCheck.click();
		Thread.sleep(200);
		AadharholderGenderVerifyCheck.click();
		Thread.sleep(200);
		AadharHolderAddressVerifyCheck.click();
		Thread.sleep(200);
		AadharYOBVerifyCheck.click();
		Thread.sleep(100);
	 }
	 //adhar verification success msg
	 public String aadharverificationSuccessMsg() {
		 wait=new WebDriverWait(ldriver, 120);
		 wait.until(ExpectedConditions.visibilityOf(aadharCapturedSuccessMsg));
		 return aadharCapturedSuccessMsg.getText();
	 }
	 //agent take screenshot of window having customer photo
	 public void takeCustomerPictureUsingScreenshot() throws InterruptedException {
		  nextClickBtn.click();
		  Thread.sleep(200);
		  wait=new WebDriverWait(ldriver, 220);
		  wait.until(ExpectedConditions.visibilityOf(takeCustomerScreenshot));
		  takeCustomerScreenshot.click();
		  Thread.sleep(15000);
		  for(int i=0;i<=5;i++) {
			 wait.until(ExpectedConditions.visibilityOf(getPercentage));
		     String percentage=getPercentage.getText().substring(0, 2);
		     Thread.sleep(100);
		     try {
				File output = new File("faceMatchScore.txt");
				FileWriter writer = new FileWriter(output);
				writer.write(percentage);
				writer.flush();
				writer.close();
			  } catch (Exception e) {
				e.printStackTrace();
			  }
		      Thread.sleep(100);
		      int percent=Integer.parseInt(percentage);
		      if(percent<=50) {
			     retryBtn.click();
		       }
		       else {
			      break;
		       }
		 }
		 wait.until(ExpectedConditions.visibilityOf(nxtClkBtn));
		 nxtClkBtn.click();
		 Thread.sleep(100);
		 wait.until(ExpectedConditions.visibilityOf(camScreenshotIcon));
		 camScreenshotIcon.click();
		 Thread.sleep(200);
		 camScreenshotIcon.click();
		 Thread.sleep(200);
		 camScreenshotIcon.click();
		 Thread.sleep(200);
		 camScreenshotIcon.click();
		 Thread.sleep(200);
		 wait.until(ExpectedConditions.visibilityOf(nxtClickBtn));
		 nxtClickBtn.click();
		 Thread.sleep(200);
		 wait.until(ExpectedConditions.visibilityOf(nxtClickBtn));
		 nxtClikBtn.click();
		 Thread.sleep(200);
	}
	//verify doc uploaded success msg
	public String docVerificationSuccessMsg() {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(docVerifiedSuccessMsg));
		return docVerifiedSuccessMsg.getText();
	}
	//agent add comments and marks case as complete
	public void markCaseAsCompleteByAgent() throws InterruptedException {
		endVideocallBtn.click();
		Thread.sleep(200);
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(addCommentsBox));
		addCommentsBox.sendKeys("complete");
		caseCompleteBtn.click();
		Thread.sleep(200);
	}
	//verify case status at Agent end on completion(pending with auditor)
	public String verifyCaseStatusAtAgentEnd() throws IOException {
		wait=new WebDriverWait(ldriver, 120);
		wait.until(ExpectedConditions.visibilityOf(verifyCaseStatusText));
		String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
		String ExpcustomerName[] = data.split(" ");
	    int rows = ldriver.findElements(By.xpath(
				"//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
		for (int r = 2; r <= rows; r++) {
			ActCustomerName = ldriver.findElement(By.xpath(
					"//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
			if(ActCustomerName.equals(ExpcustomerName[0])) {
				WebElement element=ldriver.findElement(By.xpath(
					"//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[7]"));
				caseStatus=element.getText();
				break;
			}
		  }
		return caseStatus;
	}
	//auditor approves case
	public void approveCaseByAuditor() throws InterruptedException, IOException {
		wait=new WebDriverWait(ldriver, 120);
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
		Thread.sleep(200);
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
	//verify report generated status code
	public int getReportGeneratedStatusCode() throws InterruptedException, IOException {
		 String parent = ldriver.getWindowHandle();
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
				break;
			}
	      }
		  Thread.sleep(100);
		  Set<String> allWindows = ldriver.getWindowHandles();
		  ArrayList<String> tabs = new ArrayList<String>(allWindows);
		  ldriver.switchTo().window(tabs.get(0));
		  Thread.sleep(100);
		  ldriver.close();
		  for (String child : allWindows) {
			if (!parent.equalsIgnoreCase(child)) {
				ldriver.switchTo().window(child);
			}
		   }
		   Thread.sleep(500);
		   String strUrl = ldriver.getCurrentUrl();
		   Thread.sleep(100);
		   URL url = new URL(strUrl);
		   HttpURLConnection http = (HttpURLConnection)url.openConnection();
		   int statusCode = http.getResponseCode();  
		   return statusCode;
	 }
	 //verify case in Approved bucket at admin end
	 public boolean verifyCaseInApprovedBucket() throws InterruptedException, IOException {
		 boolean temp=false;
		  wait=new WebDriverWait(ldriver, 120);
		  wait.until(ExpectedConditions.visibilityOf(caseManagmentPad));
		  Actions act = new Actions(driver);
		  act.moveToElement(caseManagmentPad).build().perform();
		  wait.until(ExpectedConditions.visibilityOf(caseManagement));
		  caseManagement.click();
		  Thread.sleep(200);
		  wait.until(ExpectedConditions.elementToBeClickable(completedBucket));
		  completedBucket.click();
		  Thread.sleep(200);
		  wait.until(ExpectedConditions.elementToBeClickable(approvedBucket));
		  approvedBucket.click();
		  Thread.sleep(200);
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
	 //verify customer name on REPORT Generated page
     public String verifyCandidateNameOnReport() {
    	  wait=new WebDriverWait(ldriver, 120);
		  wait.until(ExpectedConditions.visibilityOf(customerNameOnReport));
    	  return customerNameOnReport.getText();
     }
     //verify customer mob num on REPORT Generated page
     public String verifyCustomerMobNumOnReport() throws InterruptedException {
    	   Thread.sleep(100);
		   return customerMobNumOnReport.getText();
     }
     //verify call start date on REPORT Generated page
	 public String verifyCallStartDate() throws InterruptedException {
		   Thread.sleep(100);
		   String date= callStartDateTime.getText().substring(0,2);
		   return date;
	 }
	 //verify call end date on REPORT Generated page
	 public String verifyCallEndDate() throws InterruptedException {
		   Thread.sleep(100);
		   String date= callEndDateTime.getText().substring(0,2);
		   return date;
	 }
	 //verify geo tagging address match
	 public String verifyGeoTaggingAddress() throws InterruptedException {
		   js=(JavascriptExecutor)ldriver;
		   js.executeScript("arguments[0].scrollIntoView(true);", geoTaggingAddress);
		   wait=new WebDriverWait(ldriver, 120);
		   wait.until(ExpectedConditions.visibilityOf(geoTaggingAddress));
		   String address= geoTaggingAddress.getText();
		   return address;
	 }
	 //verify panNumber on report page
	 public String verifyPanNumber() throws InterruptedException {
		   js=(JavascriptExecutor)ldriver;
		   js.executeScript("arguments[0].scrollIntoView(true);", panNumber);
		   wait=new WebDriverWait(ldriver, 120);
		   wait.until(ExpectedConditions.visibilityOf(panNumber));
		   String panNum=panNumber.getText();
		   return panNum; 
	 }
	 //verify pan holder name on report
	 public String verifyPanHolderName() throws InterruptedException {
		   js=(JavascriptExecutor)ldriver;
		   js.executeScript("arguments[0].scrollIntoView(true);", panHolderName);
		   wait=new WebDriverWait(ldriver, 120);
		   wait.until(ExpectedConditions.visibilityOf(panHolderName));
		   String panName= panHolderName.getText();
		   return panName;
	 }
	 //verify pan holder dob on report
	 public String verifyPanHolderDob() throws InterruptedException {
		   Thread.sleep(100);
		   String panDob= panHolderDob.getText().substring(6, 11);
		   return panDob;
	 }
	 //verify pan holder father name on report
	 public String verifyPanHolderFatherName() throws InterruptedException {
		   Thread.sleep(100);
		   String panFatherName= panHolderFatherName.getText();
		   return panFatherName;
	  }
	  //verify adhaar holder name on report
	  public String verifyAadharHolderName() throws InterruptedException {
		   js=(JavascriptExecutor)ldriver;
		   js.executeScript("arguments[0].scrollIntoView(true);", adhaarHolderName);
		   wait=new WebDriverWait(ldriver, 120);
		   wait.until(ExpectedConditions.visibilityOf(adhaarHolderName));
		   String adhaarName= adhaarHolderName.getText();
		   return adhaarName;
	   }
	  //verify adhar holder address on report
	  public String verifyAadharHolderAddress() throws InterruptedException {
		   Thread.sleep(100);
		   String adhaarAddress= adhaarHolderAddress.getText();
		   return adhaarAddress;
	   }
	  //verify adhar holder dob on report
	  public String verifyAadharHolderDob() throws InterruptedException {
		   String adhaarDob= adhaarHolderDob.getText();
		   return adhaarDob;
	   }
	  //verify adhar holder gender on report
	  public String verifyAadharHolderGender() throws InterruptedException {
		   String adhaarGender= adhaarHolderGender.getText();
		   return adhaarGender;
	   }
	   //verify face match score on report
	   public String verifyFaceMatchScore() throws InterruptedException {
		   js=(JavascriptExecutor)ldriver;
		   js.executeScript("arguments[0].scrollIntoView(true);", fachMatchScore);
		   wait=new WebDriverWait(ldriver, 120);
		   wait.until(ExpectedConditions.visibilityOf(fachMatchScore));
		   String faceMatchpercent= fachMatchScore.getText();
		   return faceMatchpercent;
	    }
	   //download Video an verify status code 200
	   public int getDownloadVideoUrlStatusCode() throws InterruptedException, IOException {
		   String parent = ldriver.getWindowHandle();
		   wait=new WebDriverWait(ldriver, 120);
		   wait.until(ExpectedConditions.visibilityOf(downloadRecordedVkycIcon));
		   String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
		   String ExpcustomerName[] = data.split(" ");
		   int rows = ldriver.findElements(By.xpath(
                   "//*[@id='completed']/div/div[1]/div/table/tbody/tr/td[4]")).size();
           for (int r = 2; r <= rows; r++) {
	             ActCustomerName = ldriver.findElement(By.xpath(
		                    "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[4]")).getText();
                  if(ActCustomerName.equals(ExpcustomerName[0])) {
	                    WebElement RecordedVkycIcon=ldriver.findElement(By.xpath(
		                    "//*[@id='completed']/div["+r+"]/div[1]/div/table/tbody/tr/td[8]/span[1]"));
	                    RecordedVkycIcon.click();
	                    Thread.sleep(1000);
		               break;
	                }
            }
		    Thread.sleep(1000);
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
	   
	   //Action method to read csv File
	   public boolean readCSVfile() throws IOException {
		   boolean temp=false;
		   String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
		   String ExpcustomerName[] = data.split(" ");
		   String line = "";  
		   String splitBy = ",";  
		   try  {  
		      //parsing a CSV file into BufferedReader class constructor  
		      @SuppressWarnings("resource")
			  BufferedReader br = new BufferedReader(new FileReader("/home/shashi.ranjan/Downloads/VpdReport/report.csv"));  
		      while ((line = br.readLine()) != null)   //returns a Boolean value  
		      {  
		         String[] employee = line.split(splitBy);   
		         if(employee[3].contains(ExpcustomerName[0])) {
           	       temp=true;
                 }
		      }  
		   }   
		   catch (IOException e)   
		   {  
		      e.printStackTrace();  
		   }  
		return temp;  
	   }
	   
	   //Admin uploads a v-kyc case using excel
		public void uploadCaseUsingExcel() throws InterruptedException {
			 wait=new WebDriverWait(ldriver, 120);
		     wait.until(ExpectedConditions.visibilityOf(uploadCasePad));
			 Actions act = new Actions(driver);
		     act.moveToElement(uploadCasePad).build().perform();
		     wait.until(ExpectedConditions.visibilityOf(uploadCaseLlink));
		     uploadCaseLlink.click();
		     wait.until(ExpectedConditions.visibilityOf(uploadExcelBtn));
		     uploadExcelBtn.click();
			 Thread.sleep(100);
			 uploadFile(ExcelPath);
		}
		//verify case upload success msg
		public String verifyCaseUploadSuccessMsg() {
			 wait=new WebDriverWait(ldriver, 120);
		     wait.until(ExpectedConditions.visibilityOf(caseUploadSuccessMsg));
			 return caseUploadSuccessMsg.getText();
		}
		//verify case uploaded THRU EXCEL at Agent end
		public boolean verifyCaseUploadedAtAgentEnd() {
			boolean temp=false;
			 wait=new WebDriverWait(ldriver, 120);
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
		//Reject case By Auditor
		public void rejectCaseByAuditor(String Text) throws InterruptedException, IOException {
			wait=new WebDriverWait(ldriver, 120);
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
	        }System.out.println("case status=="+caseStatus);
			return caseStatus;
		}
		
		//verify case in rejected bucket at admin end
		public boolean verifyCaseInRejectedBucket() throws InterruptedException, IOException {
			 boolean temp=false;
			 wait=new WebDriverWait(ldriver, 120);
		     wait.until(ExpectedConditions.visibilityOf(uploadCasePad));
			 Actions act = new Actions(driver);
		     act.moveToElement(caseManagmentPad).build().perform();
		     wait.until(ExpectedConditions.visibilityOf(caseManagement));
		     caseManagement.click();
		     wait.until(ExpectedConditions.elementToBeClickable(completedBucket));
		     completedBucket.click();
		     wait.until(ExpectedConditions.elementToBeClickable(rejectedBucket));
		     rejectedBucket.click();
		     wait.until(ExpectedConditions.elementToBeClickable(caseName));
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
			wait.until(ExpectedConditions.visibilityOf(uploadCasePad));
			Actions act = new Actions(driver);
		    act.moveToElement(caseManagmentPad).build().perform();
		    wait.until(ExpectedConditions.visibilityOf(caseManagement));
		    caseManagement.click();
		    Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(ActiveBucket));
			ActiveBucket.click();
			Thread.sleep(2000);
		    wait.until(ExpectedConditions.visibilityOf(reAssignedBucket));
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
		//download Today Report download At Admin End
		public void downloadTodayExcelReport() throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(reportDropdown));
			Select sel=new Select(reportDropdown);
			sel.selectByIndex(0);
			Thread.sleep(100);
			DownloadBtn.click();
		}
		//download last 7 days report download At Admin
		public void downloadSevendaysExcelReport() throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(reportDropdown));
			Select sel=new Select(reportDropdown);
			sel.selectByIndex(1);
			Thread.sleep(100);
			DownloadBtn.click();
		}
		//download last 30 days report download At Admin
		public void downloadThirtydaysExcelReport() throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(reportDropdown));
			Select sel=new Select(reportDropdown);
			sel.selectByIndex(2);
			Thread.sleep(100);
			DownloadBtn.click();
		}
		//download last 90 days report download At Admin
		public void downloadNinetydaysExcelReport() throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(reportDropdown));
			Select sel=new Select(reportDropdown);
			sel.selectByIndex(3);
			Thread.sleep(100);
			DownloadBtn.click();
		}
		//transfer case by Agent
		public void transferCaseByAgent() throws IOException, InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(TransferCaseBtn));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
				                    "//*[@id='assigned']/div/div[1]/div/table/tbody/tr/td[3]")).size();
			for (int r = 2; r <= rows; r++) {
					   ActCustomerName = ldriver.findElement(By.xpath(
						            "//*[@id='assigned']/div["+r+"]/div[1]/div/table/tbody/tr/td[3]")).getText();
				   if(ActCustomerName.equals(ExpcustomerName[0])) {
					    WebElement transferBtn=ldriver.findElement(By.xpath("//*[@id='assigned']/div["+r+"]/div[2]/table/tbody/tr/td/span[2]"));
					    transferBtn.click();
					    Thread.sleep(1000);
					    wait.until(ExpectedConditions.visibilityOf(caseTransferToAgent));
					    caseTransferToAgent.click();
					    Thread.sleep(4000);
						break;
					}
			}
	     }
		//verify case unavailability in Assigned bucket
		public boolean verifyCaseRemovedFromAssignedBucket() throws IOException {
			boolean temp=false;
			wait=new WebDriverWait(ldriver, 120);
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
		//verify Case at Transferred AGENT ASSIGNED Bucket
		public boolean verifyCaseAtTransferredAgentEnd() throws IOException {
			boolean temp=false;
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(TransferCaseBtn));
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
		//mark case as Reschedule later
		public void markCaseAsReschedulelater() throws IOException, InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(AgentcallBtn));
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
				                    "//*[@id='scheduled']/div/div[1]/div/table/tbody/tr/td[3]")).size();
			for (int r = 2; r <= rows; r++) {
					   ActCustomerName = ldriver.findElement(By.xpath(
						            "//*[@id='scheduled']/div["+r+"]/div[1]/div/table/tbody/tr/td[3]")).getText();
				   if(ActCustomerName.equals(ExpcustomerName[0])) {
					    WebElement rescheduleBtn=ldriver.findElement(By.xpath("//*[@id='scheduled']/div["+r+"]/div[2]/div/table/tbody/tr/td/span[2]"));
					    rescheduleBtn.click();
					    Thread.sleep(500);
					    WebElement rescheduleLaterBtn= ldriver.findElement(By.xpath("/html/body/div[1]/div/div/section/div[1]/section/div/div[2]/div/"
					    		+"div/div/div/div[3]/div/div["+r+"]/div[2]/div/table/tbody/tr/td/span[2]/div"));
					    rescheduleLaterBtn.click();
					    Thread.sleep(1000);
						break;
					}
			}Thread.sleep(4000);
		}
		//verify case is removed from scheduled bucket
		public boolean verifyCaseRemovedInScheduledBucket() throws IOException {
			boolean temp=false;
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(AgentcallBtn));
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
		//verify rescheduled case Availability in Assined bucket At Agent end
		public boolean verifyRescheduledCaseInAssignedBucket() throws IOException {
			boolean temp=false;
			wait=new WebDriverWait(ldriver, 120);
			wait.until(ExpectedConditions.visibilityOf(TransferCaseBtn));
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
		//inline reschedule call by agent
		public void inlinineRescheduleCall(String scheduledTime) throws IOException, InterruptedException {
			String data = new String(Files.readAllBytes(Paths.get("customerName.txt")));
			String ExpcustomerName[] = data.split(" ");
			int rows = ldriver.findElements(By.xpath(
				                    "//*[@id='scheduled']/div/div[1]/div/table/tbody/tr/td[3]")).size();
			for (int r = 2; r <= rows; r++) {
					 ActCustomerName = ldriver.findElement(By.xpath(
						            "//*[@id='scheduled']/div["+r+"]/div[1]/div/table/tbody/tr/td[3]")).getText();
				     if(ActCustomerName.equals(ExpcustomerName[0])) {
					   WebElement RescheduleCalendarBtn=ldriver.findElement(By.xpath("//*[@id='scheduled']/div["+r+"]/div[2]/div/table/tbody/tr/td/span[3]"));
					   RescheduleCalendarBtn.click();
					   break;
					}
			}
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(selectDateIcon));
			selectDateIcon.click();
			wait.until(ExpectedConditions.elementToBeClickable(SelectDateOkBtn));
			SelectDateOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(enterScheduledTime));
			wait.until(ExpectedConditions.elementToBeClickable(enterScheduledTime));
			enterScheduledTime.sendKeys(scheduledTime);
			wait.until(ExpectedConditions.elementToBeClickable(ScheduleBtn));
			Thread.sleep(50);
			ScheduleBtn.click();
			wait.until(ExpectedConditions.invisibilityOf(loadingText));
			Thread.sleep(1000);
		}
		
		//verify inline reschedule mail received by customer
		public String verifyInlineRescheduleMail() throws InterruptedException {
			wait=new WebDriverWait(ldriver, 120);
			((JavascriptExecutor) ldriver).executeScript("window.open()");
			tabs = new ArrayList<String>(ldriver.getWindowHandles());
			ldriver.switchTo().window(tabs.get(1));
			Thread.sleep(1000);
			ldriver.navigate().to("https://www.mailinator.com/v4/public/inboxes.jsp");
			wait.until(ExpectedConditions.visibilityOf(searchMailinatorBox));
			searchMailinatorBox.sendKeys(Keys.DELETE);
			searchMailinatorBox.sendKeys("Shashi.ranjan@mailinator.com"+Keys.ENTER);
			wait.until(ExpectedConditions.visibilityOf(rescheduledReceivedLinkText));
			return rescheduledReceivedLinkText.getText();
		}

}
