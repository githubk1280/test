package com.yx.controller.utils;

public class GlobalConfig {
	public final static String accessToken = "access_token";

	public enum MessageTypeEnum {
		text, image, voice, video, location, link;
	}

	public enum EventTypeEnum {
		subscribe, unsubscribe, SCAN, LOCATION, CLICK, VIEW;
	}
}
