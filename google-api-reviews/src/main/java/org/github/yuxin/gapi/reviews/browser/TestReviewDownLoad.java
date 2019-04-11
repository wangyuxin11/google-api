package org.github.yuxin.gapi.reviews.browser;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestReviewDownLoad {
	
	//reviews 页面
	private static String googleReviewUrl = "https://play.google.com/apps/publish/reviews?account=";
	
	private static  GoogleSession googleSession;
	
	private static String GOOGLE_XSRF = "";
	
	public static void main(String[] args) {

		System.out.println("GoogleReviewService init start");

		googleSession = new GoogleSession();
		
//		Map context = FastJsonUtils.stringToCollect(google_context_json);
//		
//		System.out.println("google_context_json ： " + JSON.toJSONString(context));
//		
//		Object headers = context.get("headers");
//		Object cookies = context.get("cookies");
//		
//		Map<String,String> headersMap = (Map<String, String>) headers;
//		Map<String,String> cookiesMap = (Map<String, String>) cookies;
//		
//		if(context.containsKey("headers")){
//			googleSession.setHeaders(headersMap);
//		}
//		
//		if(context.containsKey("cookies")){
//			googleSession.setCookies(cookiesMap);
//		}
		
		Map<String,String> headersMap = new HashMap<String, String>();
//		headersMap.put("authority", "play.google.com");
//		headersMap.put("method", "POST");
//		headersMap.put("path", "");
//		headersMap.put(":scheme", "https");
//		headersMap.put("accept", "*/*");
//		headersMap.put("accept-encoding:", "gzip, deflate, br");
//		headersMap.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
//		headersMap.put("content-length", "3847");
		headersMap.put("content-type", "application/javascript; charset=UTF-8");
//		headersMap.put("cookie", "");
		headersMap.put("origin", "");
		headersMap.put("referer", "");
		headersMap.put("user-agent", "");
//		headersMap.put("x-client-data", "CJa2yQEIpLbJAQjDtskBCKmdygEIqKPKAQixp8oBCOOoygEI8anKAQ==");
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
		String xsrf = "";
		
		System.out.println("google xsrf :  " + xsrf);
		
		googleSession.setXsrf(xsrf);
		
		System.out.println("GoogleReviewService init end");
		
		/**
		 *  请求url获取resultJson再将其格式化
		 */
//		ListReviewsResp resp = GoogleProxy.listReviews(googleSession, proxyConfig, startRecord, pageSize);
		
		int start = 0; 
		int pageSize = 50;
		
		String json = new String("{\"method\":\"getReviews\","
				+ "\"params\":{\"1\":\"com.michatapp.im\","
				+ "\"2\":${start},"
				+ "\"3\":${pagesize},"
				+ "\"8\":\"zh-CN\","
				+ "\"10\":0,"
				+ "\"14\":\"1222790400000\","
				+ "\"15\":\"1554912000000\","
				+ "\"18\":1,"
				+ "\"23\":0},"
				+ "\"xsrf\":\"${xsrf}\"}")
				.replace("${start}", String.valueOf(start))
				                         .replace("${pagesize}", String.valueOf(pageSize))
				                        .replace("${xsrf}", googleSession.getXsrf());
		
		
		try {
			String resultJson = WebUtils.PostJson(
					googleReviewUrl,
					30*000,
					json,
					googleSession.getHeaders(),
					googleSession.getCookies());
			
			System.err.println("POST url:" + googleReviewUrl);
//			System.err.println("POST resultJson:" + resultJson);
			
			
			JSONObject resultJsonObject = JSONObject.parseObject(resultJson);
			
			
			String result_ = (String) resultJsonObject.getString("result");
			String xsrf_ = (String) resultJsonObject.getString("xsrf");
			
			
			System.err.println("xsrf_ : " + xsrf_);
			
			JSONObject result_obj = JSONObject.parseObject(result_);
			String result_1 = result_obj.getString("1");
			String result_2 = result_obj.getString("2");
			String result_3 = result_obj.getString("3");
//
			
			JSONArray result_array_1 = JSONArray.parseArray(result_1);
			if(!result_array_1.isEmpty()){
				for(int i=0;i<result_array_1.size();i++){
					JSONObject job = result_array_1.getJSONObject(i);
					
					String _1 = job.getString("1");
					String _2 = job.getString("2");
					String _3 = job.getString("3");
					String _4 = job.getString("4");
					String _5 = job.getString("5");
					String _6 = job.getString("6");
					String _7 = job.getString("7");
					String _8 = job.getString("8");
					String _9 = job.getString("9");
					String _10 = job.getString("10");
					String _11 = job.getString("11");
					String _12 = job.getString("12");
					String _13 = job.getString("13");
					String _14 = job.getString("14");
					String _15 = job.getString("15");
					String _16 = job.getString("16");
					String _17 = job.getString("17");
					String _18 = job.getString("18");
					String _19 = job.getString("19");
					
					System.out.println(_1);
					System.out.println(_2);
					System.out.println(_3);
					System.out.println(_4);
					System.out.println(_5);
					System.out.println(_6);
					System.out.println(_7);
					System.out.println(_8);
					System.out.println(_9);
					System.out.println(_10);
					System.out.println(_11);
					System.out.println(_12);
					System.out.println(_13);
					System.out.println(_14);
					System.out.println(_15);
					System.out.println(_16);
					System.out.println(_17);
					System.out.println(_18);
					System.out.println(_19);
				}
			}
			
//			Gson gson = new Gson();
//			ListReviewsResp resp = gson.fromJson(resultJson, ListReviewsResp.class);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
