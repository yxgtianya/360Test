package com.qihoo.browser.test.main;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Performace {
	public static void main(String args[]) throws IOException, InterruptedException{
		Performace pe = new Performace();
		//System.out.println(pe.fetchMemAndCpu());
		pe.getCpuinfo();
	}
	
	public String[] fetchMemAndCpu() throws IOException, InterruptedException{
		 String line = null;
		 String temp[];
		 Runtime runtime = Runtime.getRuntime();
         Process proc = runtime.exec("adb shell top -n 1 | grep 'com.qihoo.browser$'");
         try {
             if (proc.waitFor() != 0) {
                 System.err.println("exit value = " + proc.exitValue());
             }
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     proc.getInputStream()));
             if((line = in.readLine()) != null) {
            	 temp = line.split(" ");
             }
         }finally{
             try {
                 proc.destroy();
             } catch (Exception e2) {
             }
         }
		return null;
         
	}
	
	// 获取内存方法
	public void getMem(){
		double totalMem = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("adb shell dumpsys meminfo | grep com.qihoo.browser");
			BufferedReader in = new BufferedReader(new InputStreamReader(
			        proc.getInputStream()));
			Set<String> memSet = new HashSet<String>();
			String line;
			while (( line = in.readLine()) != null) {
				if(!line.equals("")){
				    memSet.add(line.trim()); 
				}      
			}
			for(String mem : memSet){
				System.out.println(mem);
//				if(mem.split(" ")[0].trim() != null);{
//					System.out.println(mem.split(" ")[0].trim().length());
//				}
				try {
					totalMem+=(Integer.parseInt(mem.split(" ")[0]));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(now)+","+totalMem/1024);
	}
	
	// 获取cpu占用率
	public void getCpuinfo(){
		double totalCpu = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime
					.exec("adb shell dumpsys cpuinfo | grep com.qihoo.browser");
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
		System.out.println(totalCpu + "%");
	}
	
	
	public double getTraffic(){
		float totalTraffic = 0f;
		int rx_bytes = 0;
		int tx_bytes = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime
					.exec("adb shell cat /proc/net/xt_qtaguid/stats | grep "+ getUid());
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
	
	public String getTime() {
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return df.format(now);
	}
	
	public int getUid(){
		int uid = 0;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("adb shell ps | grep com.qihoo.browser");
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String uidLine = in.readLine();
			uid = 10000 + Integer.parseInt(uidLine.trim().split(" ")[0].split("a")[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return uid;
	}
	
	public void getAnrTraces(){
		Runtime runtime = Runtime.getRuntime();
		try {
			Process proc = runtime
					.exec(String.format("adb pull /data/anr/traces.txt D:\\test\\traces\\traces_%s.txt",getTime()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testCsv(){
		File memFile = new File(System.getProperty("user.dir")
				+ "/files/test.csv");
		FileWriter fw = null;
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
			fw.write("a");
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

