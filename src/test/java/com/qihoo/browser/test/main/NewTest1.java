package com.qihoo.browser.test.main;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qihoo.browser.test.lib.AppiumBase;

public class NewTest1 extends AppiumBase {
	
	@Test
	public void f() throws InterruptedException {
		driver.launchApp();
		Thread.sleep(5000);
	}

	//

	// 上滑
	public void slideUp() {
		driver.swipe(execWidth / 2, 4 * execHeight / 5, execWidth / 2,
				execHeight / 5, 1000);
	}

	// 左滑
	public void slideRight() {
		driver.swipe(execWidth / 6, execHeight / 3, execWidth / 6 * 5,
				execHeight / 3, 1000);
	}

	// 右滑
	public void slideLeft() {
		driver.swipe(execWidth / 6 * 5, execHeight / 3, execWidth / 6,
				execHeight / 3, 1000);
	}
	
	// 点击屏幕中心
	public void clickCenter() {
		driver.tap(1, execWidth / 2, execHeight / 2, 300);
	}

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		super.beforeClass();
	}

	@AfterClass
	public void afterClass() {
		super.afterClass();
	}
}
