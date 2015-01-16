package com.yx.utils;

import java.io.File;

public class SystemUtils {

	public final static String LOG4JDIR = File.separator + "data"
			+ File.separator + "applogs";
	public final static String LOG4JDIR_WIN = "F:" + LOG4JDIR;

	public enum SystemEnum {
		windows, linux;
	}

	public static String getSystem() {
		String sysSymbol = System.getProperty("os.name", "Windows");
		if (sysSymbol.toLowerCase().contains("windows")) {
			return SystemEnum.windows.name();
		}
		return SystemEnum.linux.name();
	}

	public static boolean isWindows() {
		return SystemEnum.windows.name().equals(getSystem());
	}
}
