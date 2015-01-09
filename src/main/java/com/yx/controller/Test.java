package com.yx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;

@Controller
public class Test {
	Logger logger = Logger.getLogger(getClass());

	@RequestMapping("/yx")
	public void test(String signature, String timestamp, String nonce,
			String echostr, HttpServletResponse respnose) throws IOException {
		logger.info(signature + "," + timestamp + "," + nonce + "," + echostr);
		List<String> params = Lists.newArrayList();
		params.add("testyx123");
		params.add(timestamp);
		params.add(nonce);
		logger.info(params);
		Collections.sort(params);
		logger.info(params);
		String paramsStr = list2String(params);
		String encrytpStr = sha1Encoding(paramsStr);
		logger.info(paramsStr);
		logger.info(signature + " SHA1 match ? " + signature.equals(encrytpStr)
				+ " " + encrytpStr);
		PrintWriter writer = respnose.getWriter();
		writer.write(echostr);
	}

	private String sha1Encoding(String paramsStr) {
		return encrypt(paramsStr, "SHA-1");

	}

	private String list2String(List<String> source) {
		StringBuilder sb = new StringBuilder();
		for (String s : source) {
			sb.append(s);
		}
		return sb.toString();
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
