package com.yx.controller.utils;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class GlobalCache {
	private ConcurrentHashMap<String, Object> globalCache = new ConcurrentHashMap<String, Object>();

	@Autowired
	private WxUtils utils;

	@PostConstruct
	public void init() {
		globalCache.put(GlobalConfig.accessToken, utils.getAccessToken());
	}

	public Object getValue(String key) {
		return globalCache.get(key);
	}
}
