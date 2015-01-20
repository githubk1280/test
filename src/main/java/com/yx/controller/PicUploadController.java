package com.yx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.io.PutRet;
import com.yx.controller.utils.JsonResponseUtils;
import com.yx.qiniu.QNConfigUtils;

@Controller
public class PicUploadController {
	Logger logger = Logger.getLogger(getClass());

	@RequestMapping("/push")
	public void upload(MultipartFile file, HttpServletResponse response)
			throws IllegalStateException, IOException, AuthException,
			JSONException {
		PutRet result = QNConfigUtils.uploadFile(file.getInputStream());
		logger.info(file.getSize() / 1024 + "KB");
		System.out.println(file.getSize() / 1024 + "KB");
		System.out.println(result.getResponse());
		JsonResponseUtils.returnJsonResponse(response, result.getKey(),
				result.ok(), result.statusCode);
	}

}
