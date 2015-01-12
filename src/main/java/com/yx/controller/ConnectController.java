package com.yx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.yx.utils.CommonUtils;

@Controller
public class ConnectController {
	Logger logger = Logger.getLogger(getClass());

	@RequestMapping("/yx/connect")
	public void testConnect(String signature, String timestamp, String nonce,
			String echostr, HttpServletResponse respnose) throws IOException {
		logger.info(signature + "," + timestamp + "," + nonce + "," + echostr);
		List<String> params = Lists.newArrayList();
		params.add("123token321");
		params.add(timestamp);
		params.add(nonce);
		logger.info(params);
		Collections.sort(params);
		logger.info(params);
		String paramsStr = list2String(params);
		String encrytpStr = sha1Encoding(paramsStr);
		// logger.info(paramsStr);
		logger.info(signature + " SHA1 match ? " + signature.equals(encrytpStr)
				+ " " + encrytpStr);
		PrintWriter writer = respnose.getWriter();
		writer.write(echostr);
	}

	private String sha1Encoding(String paramsStr) {
		return CommonUtils.encrypt(paramsStr, "SHA-1");

	}

	private String list2String(List<String> source) {
		StringBuilder sb = new StringBuilder();
		for (String s : source) {
			sb.append(s);
		}
		return sb.toString();
	}

}
