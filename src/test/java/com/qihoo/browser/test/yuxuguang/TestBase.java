package com.qihoo.browser.test.yuxuguang;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestBase {
	public static AndroidDriver<WebElement> driver;
	//nexus6尺寸
	protected static int caseWidth = 1440;
	protected static int caseHeight = 2392;
	//测试机尺寸
	protected static int execWidth;
	protected static int execHeight;
	
  @BeforeClass
  public void beforeClass() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("autoLaunch", false);
		capabilities.setCapability("deviceName", "AppiumTestDevice");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("appPackage", "com.qihoo.browser");
		capabilities.setCapability("appActivity", ".activity.SplashActivity");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("newCommandTimeout", "600000");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		execWidth = driver.manage().window().getSize().width;
		execHeight = driver.manage().window().getSize().height;
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
