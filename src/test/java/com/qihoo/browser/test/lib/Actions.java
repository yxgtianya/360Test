package com.qihoo.browser.test.lib;


import io.appium.java_client.android.AndroidDriver;


import org.openqa.selenium.WebElement;

public class Actions{
	public AndroidDriver<WebElement> driver;
	public Actions(AndroidDriver<WebElement> driver){
		this.driver = driver;
	}

	public void launchApk() throws InterruptedException{
		driver.launchApp();
		Thread.sleep(3000);
	}
	
	
	
	public void dial() throws InterruptedException{
		System.out.println(driver.currentActivity());
		driver.startActivity("com.google.android.dialer", ".extensions.GoogleDialtactsActivity");
		Thread.sleep(3000);
		driver.findElementById("com.android.dialer:id/floating_action_button").click();
		driver.findElementById("com.android.dialer:id/one").click();
		driver.findElementById("com.android.dialer:id/zero").click();
		driver.findElementById("com.android.dialer:id/zero").click();
		driver.findElementById("com.android.dialer:id/eight").click();
		driver.findElementById("com.android.dialer:id/six").click();
		driver.findElementById("com.android.dialer:id/dialpad_floating_action_button").click();
		driver.pressKeyCode(3);
		Thread.sleep(3000);
	}
}
