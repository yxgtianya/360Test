package com.qihoo.browser.test.yuxuguang;

import java.io.IOException;

public class Actions{
	public static void main(String[] args){
		try {
			Runtime.getRuntime().exec("cmd.exe /k start adb logcat *:W > D:/logcat.log");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}