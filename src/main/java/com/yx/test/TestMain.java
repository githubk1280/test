package com.yx.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class TestMain {

	public static void main(String[] args) {
		TestMain t = new TestMain();
		System.out.println(t.encrypt("91497008714207883858677187056457220083",
				"SHA-1"));
		System.out.println("ffc72bd2203b7eeaaa2dbfc45d6ddce2fab9bd65");
		System.out.println(new Date ().getTime());
	}

	public String encrypt(String message, String type) {
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

	private String byteToHexStringSingle(byte[] byteArray) {
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
