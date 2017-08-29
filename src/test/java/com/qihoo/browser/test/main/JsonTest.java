package com.qihoo.browser.test.main;

import java.awt.image.RescaleOp;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sound.midi.VoiceStatus;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		JsonTest jtJsonTest = new JsonTest();
		jtJsonTest.printlog();		
//		jtJsonTest.getRespond();
		String jsonString = jtJsonTest.getRespond();
//		Gson gson = new Gson();
//		Respond res = gson.fromJson(jsonString, Respond.class);
		if(jsonString != null){
		JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
		JsonArray array = jsonObject.get("mv_style").getAsJsonArray();
		for(JsonElement jsonElement : array){
			System.out.println(jsonElement.getAsJsonObject().get("type").getAsString());
			JsonArray mv_list = jsonElement.getAsJsonObject().get("mv_list").getAsJsonArray();
			for(JsonElement json_mv : mv_list){
				System.out.println(json_mv.getAsJsonObject().get("adspace_id").getAsString());
				System.out.println(json_mv.getAsJsonObject().get("extend_size").getAsString());
				System.out.println(json_mv.getAsJsonObject().get("r_id").getAsString());
				System.out.println(json_mv.getAsJsonObject().get("width").getAsString());
				System.out.println(json_mv.getAsJsonObject().get("height").getAsString());
				String gray_marks_string = json_mv.getAsJsonObject().get("gray_marks").getAsString().replace("\\", "");
				//System.out.println(gray_marks_string);
				JsonObject jsonObject1 = new JsonParser().parse(gray_marks_string).getAsJsonObject();
				System.out.println(jsonObject1.getAsJsonObject().get("ssp_rulenum").getAsString());
//				System.out.println(gray_marks.getAsJsonObject().get("ssp_rulenum").getAsString());
				JsonObject adm = mv_list.get(0).getAsJsonObject().get("adm").getAsJsonObject();
				JsonObject nativej = adm.getAsJsonObject().get("native").getAsJsonObject();
				JsonObject img = nativej.getAsJsonObject().get("img").getAsJsonObject();
				System.out.println(nativej.getAsJsonObject().get("desc").getAsString());
				System.out.println(img.getAsJsonObject().get("height").getAsInt());
				System.out.println(img.getAsJsonObject().get("width").getAsInt());
			}
			System.out.println("******************************");
		}
		}
	}
	
	public void getJsonItem(String json){
		
	}
	
	public String getRespond(){
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
			try {
				requestjsonString = jsonString.substring(jsonString.indexOf("channel")-2,jsonString.indexOf("activity_style")-2);
				respondjsonString = jsonString.substring(requestjsonString.length());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("没有抓到响应");
				return null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		System.out.println(respondjsonString);	
		return respondjsonString;
	}
	
	public void printlog() throws InterruptedException{
		Runtime runtime = Runtime.getRuntime();
		try {
			Process proc1 = runtime.exec("adb logcat -c ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Process proc = runtime.exec("cmd.exe /C start d:\\test.bat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(15000);
		try {
			Process proc2 = runtime.exec("cmd.exe /C start d:\\close.bat");
			Thread.sleep(1000);
			Process proc3 = runtime.exec("cmd.exe /C start d:\\close.bat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void getScreen() throws IOException{
//		File scrFile = driver.getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(scrFile, new File("d:\\screen\\screenshot" + getTime() + ".png"));
//	}
//	
//	public String getTime() {
//		Date now = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
//		return df.format(now);
//	}

}
