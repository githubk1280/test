package com.yx.controller.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yx.beans.wx.Button;
import com.yx.wx.json.AccessToken;

@Component
public class WxUtils {
	Logger logger = Logger.getLogger(getClass());

	private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

	/** 自定义菜单 */
	private final static String MENU_ACTION_URL = "https://api.weixin.qq.com/cgi-bin/menu/%s?access_token=%s";

	public enum MenuMethodEnum {
		get, create, delete;
	}

	private final int BYTE_SIZE = 256;

	private Properties properties = null;

	@PostConstruct
	public void init() throws IOException {
		properties = PropertiesLoaderUtils
				.loadAllProperties("pros/app.properties");
	}

	public void operateMenu(String method, Button button) {

	}

	public void createMenu(String method, Button button) {

	}

	public AccessToken getAccessToken() {
		AccessToken accessToke = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(String.format(ACCESS_TOKEN_URL,
				properties.getProperty("id"), properties.getProperty("sId")));
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			InputStream body = entity.getContent();
			byte[] bytes = new byte[BYTE_SIZE];
			StringBuilder bodySb = new StringBuilder();
			int read = 0;
			while ((read = body.read(bytes, 0, body.available())) != -1) {
				bodySb.append(new String(bytes));
			}
			logger.info("response :" + bodySb.toString().trim());
			accessToke = JSON.parseObject(bodySb.toString().trim(),
					AccessToken.class);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return accessToke;
	}

	public static void main(String args[]) throws IOException {
		WxUtils u = new WxUtils();
		u.init();
		u.getAccessToken();
	}
}
