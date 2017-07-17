package com.qihoo.browser.test.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qihoo.browser.test.lib.AppiumBase;

public class NewTest1 extends AppiumBase {
	// 300000ms等待
	private int waittime = 300000;
	private ArrayList<String> trafficInfo = new ArrayList<String>();

	// 退到后台通用用例
	public void caseInBg(String pkgName, String activity)
			throws InterruptedException {
		killPid(pkgName);
		driver.startActivity(pkgName, activity);
		double beginTraffic[] = getTraffic(pkgName);
		// home键应用退到后台
		driver.pressKeyCode(3);
		Thread.sleep(waittime);
		double endTraffic[] = getTraffic(pkgName);
		trafficInfo.add(pkgName + ",rx," + (endTraffic[0] - beginTraffic[0]));
		trafficInfo.add(pkgName + ",tx," + (endTraffic[1] - beginTraffic[1]));
		System.out
				.println(pkgName + " rx:" + (endTraffic[0] - beginTraffic[0]));
		System.out
				.println(pkgName + " tx:" + (endTraffic[1] - beginTraffic[1]));
	}

	// 360退到后台
//	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case1() throws InterruptedException {
		caseInBg("com.qihoo.browser", "com.qihoo.browser.BrowserActivity");
	}

	// qq退到后台
//	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case2() throws InterruptedException {
		caseInBg("com.tencent.mtt", "com.tencent.mtt.MainActivity");
	}

	// UC退到后台
//	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case3() throws InterruptedException {
		caseInBg("com.UCMobile", "com.uc.browser.InnerUCMobile");
	}

	// 360 open baidu.com
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case4() throws InterruptedException {
		killPid("com.qihoo.browser");
		driver.launchApp();
		double beginTraffic[] = getTraffic("com.qihoo.browser");
		driver.findElementByXPath(
				"//android.widget.EditText[@text=\"搜索或输入网址\"]").click();
		driver.findElementByXPath(
				"//android.widget.EditText[@text=\"搜索或输入网址\"]").sendKeys(
				"baidu.com");
		driver.findElementByXPath("//android.widget.Button[@text=\"前往\"]")
				.click();
		Thread.sleep(waittime);
		double endTraffic[] = getTraffic("com.qihoo.browser");
		trafficInfo.add("com.qihoo.browser" + ",rx,"
				+ (endTraffic[0] - beginTraffic[0]));
		trafficInfo.add("com.qihoo.browser" + ",tx,"
				+ (endTraffic[1] - beginTraffic[1]));
		System.out.println("com.qihoo.browser:"
				+ (endTraffic[0] - beginTraffic[0]));
		System.out.println("com.qihoo.browser:"
				+ (endTraffic[1] - beginTraffic[1]));
	}

	// qq open baidu.com
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case5() throws InterruptedException {
		killPid("com.tencent.mtt");
		driver.startActivity("com.tencent.mtt", "com.tencent.mtt.MainActivity");
		double beginTraffic[] = getTraffic("com.tencent.mtt");
		driver.findElementByXPath("//android.view.View[@text=\"搜索或输入网址\"]")
				.click();
		input("baidu.com");
		driver.findElementByXPath("//android.widget.TextView[@text=\"进入\"]")
				.click();
		Thread.sleep(waittime);
		double endTraffic[] = getTraffic("com.tencent.mtt");
		trafficInfo.add("com.tencent.mtt" + ",rx,"
				+ (endTraffic[0] - beginTraffic[0]));
		trafficInfo.add("com.tencent.mtt" + ",tx,"
				+ (endTraffic[1] - beginTraffic[1]));
		System.out.println("com.tencent.mtt:"
				+ (endTraffic[0] - beginTraffic[0]));
		System.out.println("com.tencent.mtt:"
				+ (endTraffic[1] - beginTraffic[1]));

	}

	// uc open baidu.com
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case6() throws InterruptedException {
		killPid("com.UCMobile");
		driver.startActivity("com.UCMobile", "com.uc.browser.InnerUCMobile");
		double beginTraffic[] = getTraffic("com.UCMobile");
		driver.findElementByXPath(
				"//android.view.View[@content-desc=\"homepage_search\"]")
				.click();
		input("baidu.com");
		driver.findElementByXPath(
				"//android.widget.Button[@resource-id=\"com.UCMobile:id/cancel\"]")
				.click();
		Thread.sleep(waittime);
		double endTraffic[] = getTraffic("com.UCMobile");
		trafficInfo.add("com.UCMobile" + ",rx,"
				+ (endTraffic[0] - beginTraffic[0]));
		trafficInfo.add("com.UCMobile" + ",tx,"
				+ (endTraffic[1] - beginTraffic[1]));
		System.out.println("com.UCMobile:" + (endTraffic[0] - beginTraffic[0]));
		System.out.println("com.UCMobile:" + (endTraffic[1] - beginTraffic[1]));
	}

	// 解决sendkeys有时bug无法输入的问题 （非中文输入）
	public void input(String s) {
		String id = null;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(Runtime.getRuntime()
					.exec("adb devices").getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				if (line.contains("device")) {
					id = line.trim().split("device")[0].trim();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		try {
			Runtime.getRuntime().exec("adb -s " + id + " shell input text " + s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 杀应用进程
	public void killPid(String packageName) {
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run
					.exec("adb shell am force-stop " + packageName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得流量数据
	public double[] getTraffic(String packageName) {
		double totalTraffic[] = new double[2];
		double rx_bytes = 0;
		double tx_bytes = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime
					.exec("adb shell cat /proc/net/xt_qtaguid/stats | grep "
							+ getUid(packageName));
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

	// 获得当前时间
	public String getTime() {
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return df.format(now);
	}

	// uid
	public int getUid(String packageName) {
		int uid = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime
					.exec("adb shell ps | grep " + packageName);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String uidLine = in.readLine();
			uid = 10000 + Integer.parseInt(uidLine.trim().split(" ")[0]
					.split("a")[1]);
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
		File tfcFile = new File(System.getProperty("user.dir")
				+ "/files/traffic.csv");
		FileWriter fw = null;
		if (!tfcFile.exists()) {
			try {
				tfcFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fw = new FileWriter(tfcFile);
			fw.write("PACKAGENAME,TRFFIC(KB)\r\n");
			for (String item : trafficInfo) {
				fw.write(item + "\r\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
