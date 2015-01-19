package com.yx.qiniu;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.springframework.util.StringUtils;

import com.google.common.base.Stopwatch;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

public class QNConfigUtils {
	private final static String ACCESS_KEY = "JKMMVFacGboJI8WUMONlY1jljZJzuti4i98NMyRL";
	private final static String SECRET_KEY = "WghvdZ-yHWbpGr4SzF__2VrjpBjqCreLTeOoMTW6";
	private final static String BUCKET = "pics";

	public static String upToken = "";

	public static void getUploadKey() throws AuthException, JSONException {
		Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
		// 请确保该bucket已经存在
		String bucketName = BUCKET;
		PutPolicy putPolicy = new PutPolicy(bucketName);
		upToken = putPolicy.token(mac);
		System.out.println(upToken);
	}

	public static void uploadFile() throws AuthException, JSONException {
		if (StringUtils.isEmpty(upToken)) {
			getUploadKey();
		}
		PutExtra extra = new PutExtra();
		String key = "image" + new Random().nextInt();
		String path = QNConfigUtils.class.getResource("Lighthouse.jpg")
				.toString();
		String localFile = path.substring(path.indexOf(":") + 1, path.length());
		PutRet ret = IoApi.putFile(upToken, key, localFile, extra);
		System.out.println(ret.getStatusCode() + "--" + ret.getResponse());
	}

	public static void main(String args[]) throws AuthException, JSONException {
		Stopwatch st = Stopwatch.createStarted();
		if (st.isRunning()) {
			st.stop();
		}
		st.start();
		uploadFile();
		st.stop();
		System.out.println(st.elapsed(TimeUnit.MILLISECONDS) + "ms");
	}
}
