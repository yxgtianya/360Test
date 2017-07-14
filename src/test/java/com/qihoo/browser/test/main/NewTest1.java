package com.qihoo.browser.test.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qihoo.browser.test.lib.AppiumBase;

public class NewTest1 extends AppiumBase {
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case1() throws InterruptedException {
		killPid("com.qihoo.browser");
		driver.launchApp();
		double beginTraffic[] = getTraffic("com.qihoo.browser");
		driver.pressKeyCode(3);
		Thread.sleep(300000);
		double endTraffic[]  = getTraffic("com.qihoo.browser");
		System.out.println("360接收:" + (endTraffic[0]-beginTraffic[0]));
		System.out.println("360发送:" + (endTraffic[1]-beginTraffic[1]));
	}
	
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case2() throws InterruptedException {
		killPid("com.tencent.mtt");
		driver.startActivity("com.tencent.mtt", "com.tencent.mtt.MainActivity");
		double beginTraffic[] = getTraffic("com.tencent.mtt");
		driver.pressKeyCode(3);
		Thread.sleep(300000);
		double endTraffic[]  = getTraffic("com.tencent.mtt");
		System.out.println("qq接收:" + (endTraffic[0]-beginTraffic[0]));
		System.out.println("qq发送:" + (endTraffic[1]-beginTraffic[1]));
	}
	
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case3() throws InterruptedException {
		killPid("com.UCMobile");
		driver.startActivity("com.UCMobile", "com.uc.browser.InnerUCMobile");
		double beginTraffic[] = getTraffic("com.UCMobile");
		driver.pressKeyCode(3);
		Thread.sleep(300000);
		double endTraffic[]  = getTraffic("com.UCMobile");
		System.out.println("uc接收:" + (endTraffic[0]-beginTraffic[0]));
		System.out.println("uc发送:" + (endTraffic[1]-beginTraffic[1]));
	}
	
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case4() throws InterruptedException {
		killPid("com.qihoo.browser");
		driver.launchApp();
		double beginTraffic[] = getTraffic("com.qihoo.browser");
		driver.findElementByXPath(
				"//android.widget.EditText[@text=\"搜索或输入网址\"]")
				.click();
		driver.findElementByXPath(
				"//android.widget.EditText[@text=\"搜索或输入网址\"]")
				.sendKeys("baidu.com");
		driver.findElementByXPath(
				"//android.widget.Button[@text=\"前往\"]")
				.click();
		Thread.sleep(300000);
		double endTraffic[]  = getTraffic("com.qihoo.browser");
		System.out.println("360接收:" + (endTraffic[0]-beginTraffic[0]));
		System.out.println("360发送:" + (endTraffic[1]-beginTraffic[1]));
	}
	
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case5() throws InterruptedException {
		killPid("com.tencent.mtt");
		driver.startActivity("com.tencent.mtt", "com.tencent.mtt.MainActivity");
		double beginTraffic[] = getTraffic("com.tencent.mtt");
		driver.findElementByXPath(
				"//android.view.View[@text=\"搜索或输入网址\"]")
				.click();
		driver.findElementByXPath(
				"//android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]")
				.sendKeys("baidu.com");
		driver.findElementByXPath(
				"//android.widget.TextView[@text=\"进入\"]")
				.click();
		Thread.sleep(300000);
		double endTraffic[]  = getTraffic("com.tencent.mtt");
		System.out.println("qq接收:" + (endTraffic[0]-beginTraffic[0]));
		System.out.println("qq发送:" + (endTraffic[1]-beginTraffic[1]));
	}
	
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case6() throws InterruptedException {
		killPid("com.UCMobile");
		driver.startActivity("com.UCMobile", "com.uc.browser.InnerUCMobile");
		double beginTraffic[] = getTraffic("com.UCMobile");
		driver.findElementByXPath(
				"//android.view.View[@content-desc=\"homepage_search\"]")
				.click();
		try {
			Runtime.getRuntime().exec("adb -s ZX1G224C8R shell input text baidu.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElementByXPath(
				"//android.widget.Button[@resource-id=\"com.UCMobile:id/cancel\"]")
				.click();
		Thread.sleep(300000);
		double endTraffic[]  = getTraffic("com.UCMobile");
		System.out.println("uc接收:" + (endTraffic[0]-beginTraffic[0]));
		System.out.println("uc发送:" + (endTraffic[1]-beginTraffic[1]));
	}

	
	
	public void input(String s){
		String id = null;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					Runtime.getRuntime().exec("adb devices").getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				if (line.contains("device")) {
					id = line.trim().split("device")[0].trim();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}	
		try {
			Runtime.getRuntime().exec("adb -s "+id+"shell input text " + s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void killPid(String packageName){
		Runtime run = Runtime.getRuntime();
		 try {
		 Process process = run.exec("adb shell am force-stop "+packageName);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
	}
	
	public double[] getTraffic(String packageName){
		double totalTraffic[] = new double[2];
		int rx_bytes = 0;
		int tx_bytes = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime
					.exec("adb shell cat /proc/net/xt_qtaguid/stats | grep "+ getUid(packageName));
			BufferedReader in = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				if (!line.equals("")) {
					rx_bytes += Integer.parseInt(line.trim().split(" ")[5]);
					tx_bytes += Integer.parseInt(line.trim().split(" ")[7]);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalTraffic[0] = rx_bytes / 1024;
		totalTraffic[1] = tx_bytes / 1024;
		return totalTraffic;
	}
	
	public String getTime() {
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return df.format(now);
	}
	
	public int getUid(String packageName){
		int uid = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("adb shell ps | grep "+packageName);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String uidLine = in.readLine();
			uid = 10000 + Integer.parseInt(uidLine.trim().split(" ")[0].split("a")[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return uid;
	}

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
