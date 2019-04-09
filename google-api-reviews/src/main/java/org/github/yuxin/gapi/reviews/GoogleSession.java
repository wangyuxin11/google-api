package org.github.yuxin.gapi.reviews;


import java.io.Serializable;
import java.util.Map;

public class GoogleSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6813600132507205579L;

	private Map<String, String> headers;
	
	private Map<String, String> cookies;
	
	private String userAgent; 

	private String contentType;
	
	private String xsrf;

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getXsrf() {
		return xsrf;
	}

	public void setXsrf(String xsrf) {
		this.xsrf = xsrf;
	}
}