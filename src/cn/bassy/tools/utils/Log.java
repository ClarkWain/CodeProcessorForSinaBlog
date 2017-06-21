package cn.bassy.tools.utils;

/**
 * 概述：日志输出工具类
 * 
 * @author 韦天鹏
 * @create 2017年6月16日
 */
public class Log {
	/** 是否开启调试输出 */
	private static boolean debug = true;

	public static boolean isDebug() {
		return debug;
	}

	public static void setDebug(boolean debug) {
		Log.debug = debug;
	}

	public static void i(String format, Object... args) {
		if (debug) {
			System.out.println(String.format(format, args));
		}
	}

	public static void e(String format, Object... args) {
		if (debug) {
			System.err.println(String.format(format, args));
		}
	}

}
