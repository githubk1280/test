package com.xyx.controller;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller
public class Test2Controller {
	Logger logger = Logger.getLogger(getClass());

	@PostConstruct
	public void init() {
		int i = 0;
		while (i++ < 10) {
			logger.info(i);
		}
	}
}
