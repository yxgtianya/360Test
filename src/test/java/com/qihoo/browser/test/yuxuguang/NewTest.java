package com.qihoo.browser.test.yuxuguang;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class NewTest {

	private AndroidDriver<WebElement> driver;
	private static int caseWidth = 1440;
	private static int caseHeight = 2392;
	private static int execWidth;
	private static int execHeight;

	@Test(invocationCount = 300, threadPoolSize = 1)
	public void case1() throws InterruptedException {
		// 点击快捷方式sogou
		driver.findElement(
				By.xpath("//android.widget.TextView[contains(@text,'sogou')]"))
				.click();
		Thread.sleep(3000);
		// 点击菜单button
		driver.findElement(By.id("com.qihoo.browser:id/a19")).click();
		Thread.sleep(3000);
		// 点击设置button
		driver.tap(1, 900 * execWidth / caseWidth, 2040 * execHeight
				/ caseHeight, 0);
		Thread.sleep(3000);
		slideUp();
		Thread.sleep(2000);
		slideUp();
		Thread.sleep(3000);
		// 点击清理痕迹
		driver.findElement(
				By.xpath("//android.widget.TextView[contains(@text,'清理痕迹')]"))
				.click();
		// driver.tap(1, execWidth/2, 1702*execHeight/caseHeight, 0);
		Thread.sleep(3000);
		// 点击清理痕迹
		driver.findElement(By.id("com.qihoo.browser:id/m_")).click();
		Thread.sleep(3000);
		// 点击清除
		driver.findElement(By.id("com.qihoo.browser:id/oe")).click();
		Thread.sleep(3000);
		// return desktop
		driver.pressKeyCode(4);
		Thread.sleep(2000);
		driver.pressKeyCode(4);
		Thread.sleep(2000);
		driver.pressKeyCode(4);
		Thread.sleep(2000);
		driver.pressKeyCode(4);
		Thread.sleep(500);
		driver.pressKeyCode(4);
		Thread.sleep(10000);
	}

	@Test(invocationCount = 300, threadPoolSize = 1)
	public void case2() throws InterruptedException {
		driver.findElement(
				By.xpath("//android.widget.TextView[contains(@text,'小说')]"))
				.click();
		Thread.sleep(3000);
		// 点击下载文件
		driver.findElement(By.id("com.qihoo.browser:id/oa")).click();
		Thread.sleep(3000);
		// 点击我的
		driver.findElement(By.id("com.qihoo.browser:id/zj")).click();
		Thread.sleep(3000);
		// 点击下载
		driver.findElement(By.id("com.qihoo.browser:id/aek")).click();
		Thread.sleep(3000);
		try {
			driver.tap(
					1,
					driver.findElement(By
							.xpath("//android.widget.TextView[contains(@text,'TXT   529 KB')]")),
					2500);
			Thread.sleep(3000);
			driver.findElement(
					By.xpath("//android.widget.TextView[contains(@text,'删除任务')]"))
					.click();
			Thread.sleep(3000);
			driver.findElement(By.id("com.qihoo.browser:id/pg")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("com.qihoo.browser:id/oe")).click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("没有下载成功");
		} finally {
			// driver.closeApp();
			driver.pressKeyCode(4);
			Thread.sleep(2000);
			driver.pressKeyCode(4);
			Thread.sleep(500);
			driver.pressKeyCode(4);
			Thread.sleep(5000);
		}

	}

	@Test(invocationCount = 300, threadPoolSize = 1)
	public void case3() throws InterruptedException {
		driver.findElement(
				By.xpath("//android.widget.TextView[contains(@text,'3gp')]"))
				.click();
		Thread.sleep(5000);
		// 点击下载文件
		driver.findElement(By.id("com.qihoo.browser:id/oa")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("com.qihoo.browser:id/oa")).click();
		Thread.sleep(3000);
		// 点击我的
		driver.findElement(By.id("com.qihoo.browser:id/zj")).click();
		Thread.sleep(3000);
		// 点击下载
		driver.findElement(By.id("com.qihoo.browser:id/aek")).click();
		try {
			driver.tap(
					1,
					driver.findElement(By
							.xpath("//android.widget.TextView[contains(@text,'3GP   37.82 KB')]")),
					2500);
			Thread.sleep(3000);
			driver.findElement(
					By.xpath("//android.widget.TextView[contains(@text,'删除任务')]"))
					.click();
			Thread.sleep(3000);
			driver.findElement(By.id("com.qihoo.browser:id/pg")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("com.qihoo.browser:id/oe")).click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("没有下载完成");
		} finally {
			driver.pressKeyCode(4);
			Thread.sleep(2000);
			driver.pressKeyCode(4);
			Thread.sleep(500);
			driver.pressKeyCode(4);
			Thread.sleep(5000);
		}

	}

	@Test(invocationCount = 300, threadPoolSize = 1)
	public void case4() throws InterruptedException {
		driver.findElement(
				By.xpath("//android.widget.TextView[contains(@text,'mp3')]"))
				.click();
		Thread.sleep(5000);
		// 点击下载文件
		driver.findElement(By.id("com.qihoo.browser:id/oa")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("com.qihoo.browser:id/oa")).click();
		Thread.sleep(3000);
		// 点击我的
		driver.findElement(By.id("com.qihoo.browser:id/zj")).click();
		Thread.sleep(3000);
		// 点击下载
		driver.findElement(By.id("com.qihoo.browser:id/aek")).click();
		try {
			driver.tap(
					1,
					driver.findElement(By
							.xpath("//android.widget.TextView[contains(@text,'MP3   152 KB')]")),
					2500);
			Thread.sleep(3000);
			driver.findElement(
					By.xpath("//android.widget.TextView[contains(@text,'删除任务')]"))
					.click();
			Thread.sleep(3000);
			driver.findElement(By.id("com.qihoo.browser:id/pg")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("com.qihoo.browser:id/oe")).click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("没有下载完成");
		} finally {
			driver.pressKeyCode(4);
			Thread.sleep(2000);
			driver.pressKeyCode(4);
			Thread.sleep(500);
			driver.pressKeyCode(4);
			Thread.sleep(5000);
		}

	}

	@Test(invocationCount = 300, threadPoolSize = 1)
	public void case5() throws InterruptedException {
		driver.findElement(
				By.xpath("//android.widget.TextView[contains(@text,'jpg')]"))
				.click();
		//等待图片展示
		Thread.sleep(5000);
		// 长按图片
		driver.tap(1, 400 * execWidth / caseWidth, 500 * execHeight
				/ caseHeight, 2500);
		try {
			driver.findElement(
					By.xpath("//android.widget.TextView[contains(@text,'保存图片')]"))
					.click();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			Thread.sleep(5000);
			driver.tap(1, 400 * execWidth / caseWidth, 500 * execHeight
					/ caseHeight, 2500);
			driver.findElement(
					By.xpath("//android.widget.TextView[contains(@text,'保存图片')]"))
					.click();
		}
		Thread.sleep(3000);
		// 点击home键
		driver.findElement(By.id("com.qihoo.browser:id/a1b")).click();
		Thread.sleep(3000);
		// 点击我的
		driver.findElement(By.id("com.qihoo.browser:id/zj")).click();
		Thread.sleep(3000);
		// 点击下载
		driver.findElement(By.id("com.qihoo.browser:id/aek")).click();
		try {
			driver.tap(
					1,
					driver.findElement(By
							.xpath("//android.widget.TextView[contains(@text,'JPG   4.04 KB')]")),
					2500);
			Thread.sleep(3000);
			driver.findElement(
					By.xpath("//android.widget.TextView[contains(@text,'删除任务')]"))
					.click();
			Thread.sleep(3000);
			driver.findElement(By.id("com.qihoo.browser:id/pg")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("com.qihoo.browser:id/oe")).click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("没有下载完成");
		} finally {
			driver.pressKeyCode(4);
			Thread.sleep(2000);
			driver.pressKeyCode(4);
			Thread.sleep(500);
			driver.pressKeyCode(4);
			Thread.sleep(5000);
		}

	}
	
	//case执行控制
	//@Test(invocationCount = 1, threadPoolSize = 1)
	public void case6() throws InterruptedException {
		int i = 300;
		while(i>0){
			case1();
			i--;
		}
		int j = 300;
		while(j>0){
			case2();
			j--;
		}
		int k = 300;
		while(k>0){
			case3();
			k--;
		}
		int m = 300;
		while(m>0){
			case4();
			m--;
		}
		int n = 300;
		while(n>0){
			case5();
			n--;
		}

	}

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		// 不需要安装
		// File classpathRoot = new File(System.getProperty("user.dir"));
		// File appDir = new File(classpathRoot, "apps");
		// File app = new File(appDir, "weiche.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("autoLaunch", false);
		capabilities.setCapability("deviceName", "AppiumTestDevice");
		capabilities.setCapability("platformVersion", "6.0.1");
		// capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.qihoo.browser");
		capabilities.setCapability("appActivity", ".activity.SplashActivity");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("ignoreUnimportantViews", true);
		// 下载速度不确定，server等待时间设置长点大于sleep时间
		capabilities.setCapability("newCommandTimeout", "600000");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), capabilities);
		// 等待元素时间
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		execWidth = driver.manage().window().getSize().width;
		execHeight = driver.manage().window().getSize().height;
		// 执行python脚本，获取traces和mem
		String path1 = "D:\\test\\aperf.py";
		String path2 = "D:\\test\\get_anr_traces.py";
		Runtime run = Runtime.getRuntime();
		try {
			Process process1 = run.exec("cmd.exe /k start " + "python " + path1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Process process2 = run.exec("cmd.exe /k start " + "python " + path2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewgroupslide(int num) throws InterruptedException {
		for (int i = 0; i < num; i++) {
			Thread.sleep(1000);
			slideLeft();
		}
	}

	// 左滑方法
	public void slideLeft() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		System.out.println(x);
		System.out.println(y);
		driver.swipe(x / 5 * 4, y / 2, x / 1920 * 254, y / 2, 300);
	}

	// 上滑方法
	public void slideUp() {
		driver.swipe(execWidth / 2, 4 * execHeight / 5, execWidth / 2,
				execHeight / 5, 1000);
	}

	//确保每次执行从桌面开始
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		driver.closeApp();
	}

//	@AfterMethod
//	public void afterMethod() {
//	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
