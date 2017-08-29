package com.qihoo.browser.test.lib;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.qihoo.browser.test.main.AdTest;


public class AppiumBase {

	public static AndroidDriver<WebElement> driver;
	//public Logger logger = Logger.getLogger(AppiumBase.class);
	//nexus6尺寸
	protected static int caseWidth = 1440;
	protected static int caseHeight = 2392;
	//测试机尺寸
	protected static int execWidth;
	protected static int execHeight;
	public Actions action;
	
	
  @BeforeClass
  public void beforeClass() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("autoLaunch", false);
		capabilities.setCapability("deviceName", "AppiumTestDevice");
		capabilities.setCapability("platformVersion", "7.0");
		capabilities.setCapability("appPackage", "com.qihoo.browser");
		capabilities.setCapability("appActivity", ".activity.SplashActivity");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("newCommandTimeout", "600000");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		execWidth = driver.manage().window().getSize().width;
		execHeight = driver.manage().window().getSize().height;
		action = new Actions(driver);
		//driver.closeApp();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
