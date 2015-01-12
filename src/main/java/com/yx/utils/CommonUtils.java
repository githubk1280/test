package com.yx.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;

public class CommonUtils {
	Logger logger = Logger.getLogger(getClass());

	public static String list2String(List<String> source) {
		StringBuilder sb = new StringBuilder();
		for (String s : source) {
			sb.append(s);
		}
		return sb.toString();
	}

	public static String encrypt(String message, String type) {
		try {
			MessageDigest md = MessageDigest.getInstance(type);
			byte[] b = md.digest(message.getBytes("utf-8"));
			return byteToHexStringSingle(b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String byteToHexStringSingle(byte[] byteArray) {
		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
}