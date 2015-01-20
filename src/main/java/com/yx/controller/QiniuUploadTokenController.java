package com.yx.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.api.auth.AuthException;
import com.yx.qiniu.QNConfigUtils;

@Controller
public class QiniuUploadTokenController {
	Logger logger = Logger.getLogger(getClass());

	private String token = "";

	@RequestMapping("/qntoken")
	public void testConnect(HttpServletResponse response) throws IOException,
			AuthException, JSONException {
		PrintWriter writer = response.getWriter();
		if (StringUtils.isEmpty(token)) {
			token = QNConfigUtils.getUploadKey();
		}
		JSONObject result = new JSONObject();
		result.put("uptoken", token);
		writer.write(result.toJSONString());
		writer.flush();
		writer.close();
	}
}
