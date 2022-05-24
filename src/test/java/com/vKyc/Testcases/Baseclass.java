package com.vKyc.Testcases;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.vKyc.Utilities.ReadConfig;

public class Baseclass {

	// Create ReadConfig class object
		ReadConfig readconfig = new ReadConfig();
		public String baseURL = readconfig.getApplication();
		
		//Admin login details
	 	public String AdminUsername = readconfig.getAdminUsername();
	 	public String AdminPassword = readconfig.getAdminPassword();
	 	
	    // Agent login Details
		public String AgentUsername =readconfig.getAgentUsername();
		public String AgentPassword=readconfig.getAgentPassword();
		
		// second Agent login Details
		public String SecondAgentUsername =readconfig.getSecondAgentUsername();
		public String SecondAgentPassword=readconfig.getSecondAgentPassword();
				
		//Auditor login details
		public String AuditorUsername=readconfig.getAuditorUsername();
		public String AuditorPassword=readconfig.getAuditorPassword();
		
		//pan Img path
		public String panImgPath=readconfig.getPanImgPath();
		
		//Aadhar img path
		public String AadharFrontImgPath=readconfig.getAadharFrontImgPath();
		public String AadharBackImgPath=readconfig.getAadharBackImgPath();
		
		//get Excel path
		public String ExcelPath=readconfig.getExcelPath();
		public String VpdExcelPath=readconfig.getVpdExcelPath();
		
		public static WebDriver driver;
		public static Logger logger;
		
		ArrayList<String> tabs;
		
		@Parameters("browser")
		@BeforeClass
		public void setup(String br) {
			logger = Logger.getLogger("Ergon"); // import right package Apache POI logger
			PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");
			if (br.equals("chrome")) {
				
				String downloadFilepath = "/home/shashi.ranjan/Downloads/VpdReport";
				ChromeOptions options = new ChromeOptions();
				HashMap<String, Integer> conentSettings = new HashMap<String, Integer>();
				HashMap<String,Object> profile = new HashMap<String, Object>();
				HashMap<String,Object> prefs = new HashMap<String, Object>();
				conentSettings.put("notifications", 1);
				conentSettings.put("geolocation", 1);
				conentSettings.put("media_stream", 1);
				profile.put("managed_default_content_settings", conentSettings);
				prefs.put("profile", profile);
				prefs.put("profile.default_content_settings.popups", 0);     //to suppress any os based popup
				prefs.put("plugins.always_open_pdf_externally",false);       //to disallow open pdf externally.
				prefs.put("download.default_directory", downloadFilepath);  //to set desired path for any download file
				options.setExperimentalOption("prefs", prefs);
			    options.addArguments("disable-notifications");              //to disable notifications
				//Create object of DesiredCapabilities class
				DesiredCapabilities cap=DesiredCapabilities.chrome();
				//Set ACCEPT_SSL_CERTS  variable to true
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				cap.setCapability("applicationCacheEnabled", false);
				options.merge(cap);
				//set chrome driver path
				System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
				driver = new ChromeDriver(options);
			} else if (br.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
				driver = new FirefoxDriver();
			} else if (br.equals("ie")) {
				System.setProperty("webdriver.ie.driver", readconfig.getIePath());
				driver = new InternetExplorerDriver();
			}
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(baseURL);
		}

		@AfterClass
		public void tearDown() {
			driver.quit();
		  //driver.close();
		}
		// If you want to catch failed test cases you will have to add "captureScreen" method.
        public void captureScreen(WebDriver driver, String tname) throws IOException {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
			FileUtils.copyFile(source, target);
			System.out.println("Screenshot taken");
		}
		// This method will set any parameter string to the system's clipboard.
	    public static void setClipboardData(String string) {
			   //StringSelection is a class that can be used for copy and paste operations.
			   StringSelection stringSelection = new StringSelection(string);
			   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	    //robot class to upload a file
		public static void uploadFile(String fileLocation) {
	        try {
	        	//Setting clip board with file location
	            setClipboardData(fileLocation);
	            //native key strokes for CTRL, V and ENTER keys
	            Robot robot = new Robot();
		        robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	        } catch (Exception exp) {
	        	exp.printStackTrace();
	        }
	    }
		//clear cookies
		public void ClearBrowserCache() throws InterruptedException
		{
//			((JavascriptExecutor) driver).executeScript("window.open()");
//			tabs = new ArrayList<String>(driver.getWindowHandles());
//			driver.switchTo().window(tabs.get(1));
//			Thread.sleep(1000);
//			driver.navigate().to("chrome://settings/clearBrowserData");
//	        Thread.sleep(5000);
//	        driver.switchTo().activeElement();
//	        driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).sendKeys(Keys.ENTER);
//	        Thread.sleep(5000);
//	        driver.close();
//			//driver.findElement("//settings-ui").sendKeys(Keys.ENTER);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(3000);
		}
}
