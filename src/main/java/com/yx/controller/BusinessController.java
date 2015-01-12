package com.yx.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.yx.utils.CommonUtils;
import com.yx.xmlbeans.Xml;

@Controller
public class BusinessController {
	Logger logger = Logger.getLogger(getClass());

	@ResponseBody
	@RequestMapping(value = "/yx/xml", produces = { "application/json",
			"application/xml" })
	public Xml testXml(@RequestBody Xml xml) throws IOException {
		System.out.println(xml);
		return xml;
	}

	@ResponseBody
	@RequestMapping("/yx")
	public Xml test(String signature, String timestamp, String nonce,
			String echostr, HttpServletResponse respnose, @RequestBody Xml xml)
			throws IOException {
		logger.info(signature + "," + timestamp + "," + nonce + "," + echostr
				+ "," + xml);
		List<String> params = Lists.newArrayList();
		params.add("testyx123");
		params.add(timestamp);
		params.add(nonce);
		// logger.info(params);
		Collections.sort(params);
		// logger.info(params);
		String paramsStr = CommonUtils.list2String(params);
		String encrytpStr = sha1Encoding(paramsStr);
		// logger.info(paramsStr);
		logger.info(signature + " SHA1 match ? " + signature.equals(encrytpStr)
				+ " " + encrytpStr);
		if (null != xml) {
			logger.info(xml);
		}
		String to = xml.getFromUserName();
		String from = xml.getToUserName();
		xml.setCreateTime(new Date().getTime());
		xml.setFromUserName(from);
		xml.setToUserName(to);
		xml.setMsgId(xml.getMsgId().add(new BigDecimal(1)));
		xml.setContent("server said : 谢谢！");
		logger.info(xml);
		return xml;
	}

	private String sha1Encoding(String paramsStr) {
		return CommonUtils.encrypt(paramsStr, "SHA-1");

	}

}
