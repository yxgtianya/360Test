package com.qihoo.browser.test.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import com.gargoylesoftware.htmlunit.javascript.host.file.FileReader;
import com.qihoo.browser.test.lib.AppiumBase;

public class AdTest extends AppiumBase{
	Logger logger = Logger.getLogger(AdTest.class);
	 
	@Test
	public void testNewsdetail() throws InterruptedException, IOException{
		action.launchApk();
		
	}
	
	@Test
	public void testNewslistup(){
		
	}
	
	@Test
	public void testNewslistdown(){
		
	}
	
	@Test
	public void testVideodetail(){
		
	}
	
	@Test
	public void testVideolistup(){
		
	}
	
	@Test
	public void testVideolistdown(){
		
	}
	
	@Test
	public void testMainpageAd(){
		
	}
	
	
	public void getScreen() throws IOException{
		File scrFile = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("d:\\screen\\screenshot" + getTime() + ".png"));
	}
	
	public String getTime() {
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return df.format(now);
	}
	
	
	public String getJson(){
		File file = new File("d:\\log.txt");
		BufferedReader br = null;
		String jsonString;
		jsonString = "";
		String requestjsonString = "";
		String respondjsonString = "";
		try {
			br = new BufferedReader(new java.io.FileReader(file));
			String tempString = null;
			while((tempString = br.readLine()) != null){
				if((tempString.length() > 600) || (tempString.endsWith("}"))){
					jsonString += tempString.substring(tempString.indexOf("NEWS_SDK_NETWORK:")+18);
				}
			}
			br.close();
			requestjsonString = jsonString.substring(jsonString.indexOf("channel")-2,jsonString.indexOf("activity_style")-2);
			respondjsonString = jsonString.substring(requestjsonString.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		return respondjsonString;
	}
	
	public void printlog(){
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("adb logcat -s NEWS_SDK_NETWORK -c");
			runtime.exec("adb logcat -s NEWS_SDK_NETWORK > d:\\log.txt");
			}
			 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
