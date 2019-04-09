package org.github.yuxin.gapi.reviews;


import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;


import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.Request.Builder;

public class WebUtils {

	public static final MediaType MEDIA_JSON = MediaType.parse("application/json; charset=utf-8");

	public static final MediaType MEDIA_JS = MediaType.parse("application/javascript; charset=UTF-8");

	public static String PostJson(String postUrl, int timeoutMS, String json, Map<String, String> headers,
			Map<String, String> cookies) throws Exception {

		RequestBody body = RequestBody.create(MEDIA_JS, json);

		Builder builder = new Request.Builder().url(postUrl).post(body);

		if (headers != null) {
			for (String key : headers.keySet()) {
				builder.header(key, headers.get(key));
			}
		}

		Request request = builder.build();

		OkHttpClient client = new OkHttpClient();

		URI url = new URI(postUrl);

		//java.net.CookieManager
		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		if (cookies != null) {
			for (String key : cookies.keySet()) {
				HttpCookie cookie = new HttpCookie(key, cookies.get(key));
				cookie.setPath("/");
				cookie.setVersion(0);
				cookie.setDomain(url.getHost());
				cookieManager.getCookieStore().add(new URI(url.getHost()), cookie);
			}
		}

		client.setCookieHandler(cookieManager);
		client.setConnectTimeout(timeoutMS, TimeUnit.MILLISECONDS);
		client.setReadTimeout(timeoutMS, TimeUnit.MILLISECONDS);
		client.setWriteTimeout(timeoutMS, TimeUnit.MILLISECONDS);
		return ReadRespString(client, request);
	}

	public static String PostBytes(String postUrl, int TimeoutMS, String contentType, byte[] bytes) throws Exception {

		RequestBody body = RequestBody.create(MediaType.parse(contentType), bytes);

		Request request = new Request.Builder().url(postUrl).post(body).build();

		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		client.setWriteTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		return ReadRespString(client, request);
	}

	public static String PostDict(String postUrl, int TimeoutMS, Map<String, Object> map) throws Exception {

		FormEncodingBuilder form = new FormEncodingBuilder();

		for (String k : map.keySet()) {
			form.addEncoded(k, String.valueOf(map.get(k)));
		}

		RequestBody body = form.build();

		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		client.setWriteTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		Request request = new Request.Builder().url(postUrl).post(body).build();

		return ReadRespString(client, request);
	}

	public static String GetString(String getUrl, int TimeoutMS) throws IOException {

		Request request = new Request.Builder().url(getUrl).get().build();

		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		client.setWriteTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		return ReadRespString(client, request);
	}

	public static String GetString(String getUrl, int TimeoutMS, Map<String, String> headers) throws IOException {

		Builder builder = new Request.Builder().url(getUrl).get();

		if (headers != null) {
			for (Entry<String, String> k : headers.entrySet()) {
				builder.addHeader(k.getKey(), k.getValue());
			}
		}

		Request request = builder.build();

		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		client.setWriteTimeout(TimeoutMS, TimeUnit.MILLISECONDS);
		return ReadRespString(client, request);
	}

	private static String ReadRespString(OkHttpClient client, Request request) throws IOException {
		Response response = client.newCall(request).execute();

		if (response.isSuccessful()) {
			ResponseBody body = response.body();

			String contentEncoding = response.header("Content-Encoding");
			String contenType = body.contentType().toString() + ";" + contentEncoding;

			byte[] bytes = null;
			if (StringUtils.isNotBlank(contenType) && contenType.indexOf("gzip") >= 0) {
				try {
					bytes = GZipUtils.decompress(body.bytes());
				} catch (Exception ex) {
					throw new IOException("decompress error : " + response);
				}
			}

			if (bytes == null) {
				bytes = body.bytes();
			}

			return new String(bytes, body.contentType().charset());
		}

		throw new IOException("unexpected code : " + response);
	}
}