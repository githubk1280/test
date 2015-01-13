package com.yx.controller;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yx.utils.SystemUtils;

@Controller
public class TestController {
	Logger logger = Logger.getLogger(getClass());

	@PostConstruct
	public void init() {
		if (SystemUtils.isWindows()) {
			System.setProperty("LOG4JDIR", SystemUtils.LOG4JDIR_WIN);
		} else {
			System.setProperty("LOG4JDIR", SystemUtils.LOG4JDIR);
		}

		Properties ps = System.getProperties();
		// Enumeration<Object> values = ps.elements();
		// while (values.hasMoreElements()) {
		// logger.info(values.nextElement());
		// }
		// Set<Entry<Object, Object>> values = ps.entrySet();
		// Iterator<Entry<Object, Object>> it = values.iterator();
		// while (it.hasNext()) {
		// // System.out.println();
		// logger.info(it.next());
		// }
	}

	@RequestMapping("/yx/test")
	public void testConnect() {
		logger.info("this is a test");
	}
}
