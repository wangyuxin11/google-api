package org.github.yuxin.gapi.reviews.browser.reply;

import java.util.HashMap;
import java.util.Map;

import org.github.yuxin.gapi.reviews.browser.GoogleSession;
import org.github.yuxin.gapi.reviews.browser.WebUtils;

import com.google.gson.Gson;

/**
 * GP回复 Demo, F12看需要的参数 、、todo位置
 * 
 * 
 * 
 */
public class TestReviewReply {

	static final String packageName = "com.michatapp.im";
	
	private static String general_request_url = "";  //TODO googleReviewUrl
	
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
		headersMap.put("referer", "".trim());  //TODO
		headersMap.put("user-agent", "".trim()); //TODO
//		headersMap.put("x-client-data", "");
		headersMap.put("x-gwt-module-base", "".trim());  //TODO
		headersMap.put("x-gwt-permutation", "".trim());  //TODO
		
		googleSession.setHeaders(headersMap);
		

		Map<String,String> cookiesMap = new HashMap<String, String>();
		cookiesMap.put("_ga", "".trim());      //TODO
		cookiesMap.put("_gid", "".trim());   //TODO
		cookiesMap.put("_gid", "".trim());   //TODO
		cookiesMap.put("_ga", "".trim());   //TODO
		cookiesMap.put("OTZ", "".trim());   //TODO
		cookiesMap.put("ANID", "".trim());   //TODO
		cookiesMap.put("HSID", "".trim());   //TODO
		cookiesMap.put("SSID", "".trim());   //TODO
		cookiesMap.put("APISID", "".trim());   //TODO
		cookiesMap.put("SAPISID", "".trim());   //TODO
		cookiesMap.put("SID", "".trim());   //TODO
		cookiesMap.put("PLAY_ACTIVE_ACCOUNT", "".trim());   //TODO
		cookiesMap.put("SIDCC", "".trim());   //TODO
		cookiesMap.put("1P_JAR", "".trim());   //TODO
		cookiesMap.put("CONSENT", "".trim());   //TODO
		cookiesMap.put("enabledapps.uploader", "".trim());   //TODO
		
		googleSession.setCookies(cookiesMap);
		
		
		//获取 xsrf
		XSRF = "".trim();   //TODO
		
		System.out.println("google xsrf :  " + XSRF);
		
		googleSession.setXsrf(XSRF);
		
		System.out.println("GoogleReviewService init end");
		
		/**
		 *  请求url获取resultJson再将其格式化
		 */
//		reviewService.sendReply(r.getReviewId(), r.getContent())
		
		
		String reviewId = "";     //TODO  comment_id 唯一ID
		String replyText = "";     //TODO  回复语
		
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
					general_request_url,
					30*000,
					json,
					googleSession.getHeaders(),
					googleSession.getCookies());
			
			System.out.println(resultJson);
			/**
			 * resultJson 返回信息格式
			 * 		{"result":{"1":{"1":"tks","3":"1554897372577"},"2":true,"3":1},"xsrf":"AMtNNDEbWCIQLej0_llZvpRk1IaAAxiViw:1554897372978"}
			 * 		{"result":{"1":{"1":"tks","3":"1554898825654"},"2":true,"3":1},"xsrf":"AMtNNDHepsyVlwyVJvB2IiZgKz0bXDfnfA:1554898826071"}
			 * 
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
