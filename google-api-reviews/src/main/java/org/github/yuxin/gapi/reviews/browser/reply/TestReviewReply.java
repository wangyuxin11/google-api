package org.github.yuxin.gapi.reviews.browser.reply;

import java.util.HashMap;
import java.util.Map;

import org.github.yuxin.gapi.reviews.browser.GoogleSession;
import org.github.yuxin.gapi.reviews.browser.WebUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

public class TestReviewReply {

	static final String packageName = "com.michatapp.im";
	
	private static String googleReviewUrl = "";
	
	private static  GoogleSession googleSession;
	
	private static String XSRF = null;
	
	public static void main(String[] args) {

		System.out.println("GoogleReviewService init start");

		googleSession = new GoogleSession();
		
		Map<String,String> headersMap = new HashMap<String, String>();
//		headersMap.put("authority", "play.google.com");
//		headersMap.put("method", "POST");
//		headersMap.put("path", "");
//		headersMap.put(":scheme", "https");
//		headersMap.put("accept", "*/*");
//		headersMap.put("accept-encoding:", "");
//		headersMap.put("accept-language", "");
//		headersMap.put("content-length", "");
		headersMap.put("content-type", "application/javascript; charset=UTF-8");
//		headersMap.put("cookie", "");
		headersMap.put("origin", "https://play.google.com");
		headersMap.put("referer", "");
		headersMap.put("user-agent", "");
//		headersMap.put("x-client-data", "");
		headersMap.put("x-gwt-module-base", "");
		headersMap.put("x-gwt-permutation", "");
		
		googleSession.setHeaders(headersMap);
		

		Map<String,String> cookiesMap = new HashMap<String, String>();
		cookiesMap.put("_ga", "");
		cookiesMap.put("_gid", "");
		cookiesMap.put("_gid", "");
		cookiesMap.put("_ga", "");
		cookiesMap.put("OTZ", "");
		cookiesMap.put("ANID", "");
		cookiesMap.put("HSID", "");
		cookiesMap.put("SSID", "");
		cookiesMap.put("APISID", "");
		cookiesMap.put("SAPISID", "");
		cookiesMap.put("SID", "");
		cookiesMap.put("PLAY_ACTIVE_ACCOUNT", "");
		cookiesMap.put("SIDCC", "");
		cookiesMap.put("1P_JAR", "");
		cookiesMap.put("CONSENT", "");
		cookiesMap.put("enabledapps.uploader", "");
		
		googleSession.setCookies(cookiesMap);
		
		
		//获取 xsrf
		XSRF = "";
		
		System.out.println("google xsrf :  " + XSRF);
		
		googleSession.setXsrf(XSRF);
		
		System.out.println("GoogleReviewService init end");
		
		/**
		 *  请求url获取resultJson再将其格式化
		 */
//		reviewService.sendReply(r.getReviewId(), r.getContent())
		
		
		String reviewId = "";
		String replyText = "";
		
		GPReplyRequest req = new GPReplyRequest();
		req.setParams(new GPReplyRequestParam());
		req.setMethod("sendReply");
		req.setXsrf(XSRF);
		GPReplyRequestParam p = new GPReplyRequestParam();
		p.setPkgName(packageName);
		p.setReviewId(reviewId);
		p.setReplyText(replyText);
		req.setParams(p);

		Gson gson = new Gson();
		String json = gson.toJson(req);
		

		try {
			String resultJson = WebUtils.PostJson(
					googleReviewUrl,
					30*000,
					json,
					googleSession.getHeaders(),
					googleSession.getCookies());
			
			System.out.println(resultJson);
			
			//{"result":{"1":{"1":"tks","3":"1554897372577"},"2":true,"3":1},"xsrf":"AMtNNDEbWCIQLej0_llZvpRk1IaAAxiViw:1554897372978"}
			//{"result":{"1":{"1":"tks","3":"1554898825654"},"2":true,"3":1},"xsrf":"AMtNNDHepsyVlwyVJvB2IiZgKz0bXDfnfA:1554898826071"}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
