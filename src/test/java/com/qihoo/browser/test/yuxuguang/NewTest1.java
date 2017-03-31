package com.qihoo.browser.test.yuxuguang;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class NewTest1 extends TestBase {

	@Test
	public void f() throws InterruptedException {
		System.out.println(driver.currentActivity());
	}
}
