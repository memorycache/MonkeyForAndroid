package com.monkey.android.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetPerfermanceData {
	private static Process process;
	private static BufferedReader result = null;
	private static String line = null;
	private static FileWriter fileWriter;
	private static ArrayList<String> memdata, cpudata;

	public static ArrayList<String> getMeminfo() throws Exception {
		return getData("meminfo");
	}

	public static ArrayList<String> getCpuinfo() throws Exception {
		return getData("cpuinfo");
	}

	private static ArrayList<String> getData(String type) throws Exception {
		ArrayList<String> data = new ArrayList<String>();
		process = Runtime.getRuntime().exec("adb shell dumpsys " + type);
		result = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		while ((line = result.readLine()) != null) {
			if (line.contains(Enviroment.getConf("packageName"))) {
				data.add(line.trim());
			}
		}

		if (type.equalsIgnoreCase("meminfo")) {
			int totalMem = 0;
			for (int i = 0; i < (data.size() / 2); i++) {
				String[] line = data.get(i).split(":");
				totalMem += Integer.parseInt(line[0].replaceAll("kB", "")
						.trim());
			}
			result.close();
			process.destroy();

			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
			String time = format.format(now);
			memdata = new ArrayList<String>();
			memdata.add(time);
			memdata.add(String.valueOf(totalMem));
			return memdata;
		}else if (type.equalsIgnoreCase("cpuinfo")) {
			double totalCpu = 0;
			for (int i = 0; i < data.size(); i++) {
				String[] info = data.get(i).split("%");
				totalCpu += Double.parseDouble(info[0]);
			}
			result.close();
			process.destroy();
			
			cpudata = new ArrayList<String>();
			cpudata.add(String.valueOf(totalCpu));
			return cpudata;
		}else{
			Log.print("----------传的参数错误", "debug");
			return null;
		}
	}
}
