package com.vKyc.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	{
		// properties class object

		File src = new File("./configuration/config.properties");
		// src is refFering config file variable src.
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);// Load every text values
		} catch (Exception e) {

			System.out.println("Exception is " + e.getMessage());
		}
	}

	// To read each and every value from this variable create different method (config.properties)
		public String getApplication() {
			String url = pro.getProperty("baseURL");
			return url;
		}

	    public String getChromePath() {
			String chromepath = pro.getProperty("chromepath");
			return chromepath;
		}

		public String getFirefoxPath() {
			String firefoxpath = pro.getProperty("firefoxpath");
			return firefoxpath;
		}

		public String getIePath() {
			String iepath = pro.getProperty("iepath");
			return iepath;
		}
		
		public String getAdminUsername() {
			String username = pro.getProperty("AdminUsername");
			return username;
		}

		public String getAdminPassword() {
			String password = pro.getProperty("AdminPassword");
			return password;
		}
		
		public String getAgentUsername() {
			String username = pro.getProperty("AgentUsername");
			return username;
		}
		
		public String getAgentPassword() {
			String password = pro.getProperty("AgentPassword");
			return password;
		}
		
		public String getSecondAgentUsername() {
			String username = pro.getProperty("SecondAgentUsername");
			return username;
		}
		
		public String getSecondAgentPassword() {
			String username = pro.getProperty("SecondAgentPassword");
			return username;
		}
		
		public String getAuditorUsername() {
			String username = pro.getProperty("AuditorUsername");
			return username;
		}
		
		public String getAuditorPassword() {
			String password = pro.getProperty("AuditorPassword");
			return password;
		}
		
		public String getPanImgPath() {
				String panImgPath = pro.getProperty("panImgPath");
				return panImgPath;
		 }
		public String getAadharFrontImgPath() {
			String AadharFrontImgPath = pro.getProperty("AadharFrontImgPath");
			return AadharFrontImgPath;
	    }
		public String getAadharBackImgPath() {
			String AadharBackImgPath = pro.getProperty("AadharBackImgPath");
			return AadharBackImgPath;
	    }
		public String getExcelPath() {
			String ExcelPath = pro.getProperty("Excelpath");
			return ExcelPath;
	    }
		public String getVpdExcelPath() {
			String VpdExcelPath = pro.getProperty("VpdExcelPath");
			return VpdExcelPath;
	    }
		
		
}
