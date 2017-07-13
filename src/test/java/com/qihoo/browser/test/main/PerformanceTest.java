package com.qihoo.browser.test.main;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.sun.jna.platform.win32.Netapi32Util.User;

public class PerformanceTest {
	private AndroidDriver<WebElement> driver;
	private static int caseWidth = 1440;
	private static int caseHeight = 2392;
	private static int execWidth;
	private static int execHeight;
	private static ArrayList<Double> memInfo = new ArrayList<Double>();
	private static ArrayList<String> cpuInfo = new ArrayList<String>();
	private static ArrayList<String> verboseInfo = new ArrayList<String>();
	private static String pkgName = "com.qihoo.browser";

	/*
	 * 启动->home键->启动
	 */
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case1() throws InterruptedException {
		for (int i = 0; i <= 5; i++) {
			driver.launchApp();
			getInfo();
			driver.pressKeyCode(3);
			getInfo();
		}
	}

	/*
	 * 信息流上滑->下滑->上滑
	 */
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case2() throws InterruptedException {
		getInfo();
		Thread.sleep(5000);
		for (int i = 1; i <= 20; i++) {
			slideUp();
			getInfo();
		}
		for (int i = 1; i <= 20; i++) {
			slideDown();
			getInfo();
		}
		for (int i = 1; i <= 20; i++) {
			slideUp();
			getInfo();
		}
	}

	/*
	 * 启动后tab切换热点->视频->直播->我的->热点
	 */
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case3() throws InterruptedException {
		getInfo();
		Thread.sleep(5000);
		slideUp();
		getInfo();
		for (int i = 1; i <= 5; i++) {
			driver.tap(1, 437 * execWidth / caseWidth, 2308 * execHeight
					/ caseHeight, 300);
			getInfo();
			driver.tap(1, 725 * execWidth / caseWidth, 2308 * execHeight
					/ caseHeight, 300);
			getInfo();
			driver.tap(1, 1013 * execWidth / caseWidth, 2308 * execHeight
					/ caseHeight, 300);
			getInfo();
			driver.tap(1, 149 * execWidth / caseWidth, 2308 * execHeight
					/ caseHeight, 300);
			getInfo();
			// driver.findElementByXPath(
			// "//android.widget.FrameLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView[1]")
			// .click();
			// getInfo();
			// driver.findElementByXPath(
			// "//android.widget.FrameLayout[1]/android.widget.RelativeLayout[3]/android.widget.TextView[1]")
			// .click();
			// getInfo();
			// driver.findElementByXPath(
			// "//android.widget.FrameLayout[1]/android.widget.RelativeLayout[4]/android.widget.TextView[1]")
			// .click();
			// getInfo();
			// driver.findElementByXPath(
			// "//android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
			// .click();
			// getInfo();
		}
	}

	/*
	 * 反复上滑，点击新闻
	 */
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case4() throws InterruptedException {
		getInfo();
		Thread.sleep(5000);
		for (int i = 1; i <= 20; i++) {
			slideUp();
			getInfo();
			Thread.sleep(2000);
			clickCenter();
			Thread.sleep(3000);
			// 判断是否回到5Tab页面
			while (!(driver.getPageSource().contains("视频")
					&& driver.getPageSource().contains("直播")
					&& driver.getPageSource().contains("热点") && driver
					.getPageSource().contains("我的"))) {
				driver.pressKeyCode(4);
				Thread.sleep(2000);
			}
		}
		getInfo();
	}

	/*
	 * 一个标签页打开多个大站
	 */
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case8() throws InterruptedException, IOException {
		getInfo();
		Thread.sleep(5000);
		driver.findElementByXPath(
				"//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]")
				.click();
		String relativelyPath = System.getProperty("user.dir");
		File urls = new File(relativelyPath + "/files/url.csv");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(urls));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = "";
		ArrayList<String> linesList = new ArrayList<String>();
		try {
			while ((line = br.readLine()) != null) {
				linesList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		for (int j = 0; j < linesList.size(); j++) {
			System.out.println(linesList.get(j));
			driver.findElementByXPath(
					"//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]")
					.sendKeys(linesList.get(j));
			driver.findElementByXPath(
					"//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.Button[1]")
					.click();
			getInfo();
			driver.findElementByXPath(
					"//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					.clear();
		}
	}

	/*
	 * 连续点击back键返回
	 */
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case5() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			driver.launchApp();
			getInfo();
			Thread.sleep(5000);
			driver.pressKeyCode(4);
			// 返回键checkbox
			// driver.findElementByXPath("//android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.CheckBox[1]").click();
			// 确定按钮
			driver.findElementByXPath(
					"//android.widget.FrameLayout[1]/android.widget.TextView[4]")
					.click();
			getInfo();
		}

	}

	/*
	 * 网页左右滑前进后退
	 */
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case6() throws InterruptedException {
		getInfo();
		Thread.sleep(5000);
		driver.findElementByXPath(
				"//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]")
				.click();
		driver.findElementByXPath(
				"//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]")
				.sendKeys("weibo.com");
		driver.findElementByXPath(
				"//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.Button[1]")
				.click();
		Thread.sleep(5000);
		getInfo();
		driver.findElementByXPath(
				"//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				.clear();
		driver.findElementByXPath(
				"//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]")
				.sendKeys("youku.com");
		driver.findElementByXPath(
				"//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.Button[1]")
				.click();
		Thread.sleep(5000);
		getInfo();
		for (int i = 0; i < 5; i++) {
			slideRight();
			Thread.sleep(1000);
			getInfo();
			slideLeft();
			Thread.sleep(1000);
			getInfo();
		}
	}

	/*
	 * 打开关闭多个标签页->关闭多个标签页
	 */
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void case7() throws InterruptedException {
		getInfo();
		Thread.sleep(5000);
		for (int i = 0; i < 10; i++) {
			driver.findElementByXPath(
					"//android.widget.FrameLayout[1]/android.widget.ImageView[4]")
					.click();
			driver.findElementByXPath(
					"//android.widget.FrameLayout[1]/android.widget.ImageView[2]")
					.click();
			Thread.sleep(1000);
			getInfo();
		}
		for (int j = 0; j < 10; j++) {
			driver.findElementByXPath(
					"//android.widget.FrameLayout[1]/android.widget.ImageView[4]")
					.click();
			driver.findElementByXPath(
					"//android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]")
					.click();
			driver.pressKeyCode(4);
			getInfo();
		}
	}

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
		capabilities.setCapability("ignoreUnimportantViews", true);
		capabilities.setCapability("resetKeyboard", true);
		// server等待时间
		capabilities.setCapability("newCommandTimeout", "60000");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), capabilities);
		// 等待元素时间10s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// 获得屏幕尺寸
		execWidth = driver.manage().window().getSize().width;
		execHeight = driver.manage().window().getSize().height;
	}

	// 划过引导页
	public void viewgroupslide(int num) throws InterruptedException {
		for (int i = 0; i < num; i++) {
			Thread.sleep(1000);
			slideLeft();
		}
	}

	// 点击屏幕中心
	public void clickCenter() {
		driver.tap(1, execWidth / 2, execHeight / 2, 300);
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

	// 上滑
	public void slideUp() {
		driver.swipe(execWidth / 2, 4 * execHeight / 5, execWidth / 2,
				execHeight / 5, 1000);
	}

	// 下滑
	public void slideDown() {
		driver.swipe(execWidth / 2, 2 * execHeight / 5, execWidth / 2,
				4 * execHeight / 5, 1000);
	}

	public void getInfo() {
		getMem();
		getCpuinfo();
	}

	// 获取内存方法
	public void getMem() {
		double totalMem = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("adb shell dumpsys meminfo | grep "
					+ pkgName);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			Set<String> memSet = new HashSet<String>();
			String line = null;
			while ((line = in.readLine()) != null) {
				if (!line.equals("")) {
					memSet.add(line.trim());
				}
			}
			for (String mem : memSet) {
				try {
					totalMem += (Integer.parseInt(mem.split(" ")[0]));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		memInfo.add(totalMem / 1024);
		verboseInfo.add(getTime() + "," + pkgName + ","
				+ Double.toString(totalMem / 1024));
		// getAnrTraces();
	}

	// 获取cpu占用率
	public void getCpuinfo() {
		double totalCpu = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("adb shell dumpsys cpuinfo | grep "
					+ pkgName);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			Set<String> cpuSet = new HashSet<String>();
			String line = null;
			while ((line = in.readLine()) != null) {
				if (!line.equals("")) {
					cpuSet.add(line.trim());
				}
			}
			for (String cpu : cpuSet) {
				try {
					totalCpu += (Double.parseDouble((cpu.split("%")[0])));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpuInfo.add(getTime() + "," + pkgName + ","+totalCpu + "%");
	}

	// 获取ANR traces
	public void getAnrTraces() {
		Runtime runtime = Runtime.getRuntime();
		try {
			Process proc = runtime
					.exec(String
							.format("adb pull /data/anr/traces.txt D:\\test\\traces\\traces_%s.txt",
									(System.currentTimeMillis())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getUid() {
		int uid = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime
					.exec("adb shell ps | grep com.qihoo.browser");
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

	public double getTraffic() {
		float totalTraffic = 0f;
		int rx_bytes = 0;
		int tx_bytes = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime
					.exec("adb shell cat /proc/net/xt_qtaguid/stats | grep "
							+ getUid());
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
		totalTraffic = (rx_bytes + tx_bytes) / 1024;
		return totalTraffic;
	}

	// 获取当前时间
	public String getTime() {
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return df.format(now);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.launchApp();
	}

	@AfterMethod
	public void afterMethod() {
		driver.closeApp();
	}

	@AfterClass
	public void afterClass() throws IOException {
		File memFile = new File(System.getProperty("user.dir")
				+ "/files/mem.csv");
		File cpuFile = new File(System.getProperty("user.dir")
				+ "/files/cpu.csv");
		FileWriter fw = null;
		FileWriter fwc = null;
		if (!memFile.exists()) {
			try {
				memFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fw = new FileWriter(memFile);
			fw.write("***********内存占用（MB)***********\r\n");
			for (String item : verboseInfo) {
				fw.write(item + "\r\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fw.close();
		}
		try {
			if (!cpuFile.exists()) {
				cpuFile.createNewFile();
			}
			fwc = new FileWriter(cpuFile);
			fwc.write("***********cpu消耗（%)***********\r\n");
			for (String item : cpuInfo) {
				fwc.write(item + "\r\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fwc.close();
		}
		driver.quit();
	}
}
