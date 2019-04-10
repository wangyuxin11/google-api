package org.github.yuxin.gapi.reviews.browser;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



public class Test {


	private static String googleReviewUrl = "https://play.google.com/apps/publish/reviews?account=5416627767168703904";
	
	private static  GoogleSession googleSession;
	
	
	/**
	private static String google_context_json = 
			"{\"headers\":{"
			+ "\"Content-Type\":\"application/javascript; charset=UTF-8\","
			+ "\"Origin\":\"https://play.google.com\","
			+ "\"Referer\":\"https://play.google.com/apps/publish/reviews?account=5416627767168703904\","
			+ "\"User-Agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36\","
			+ "\"X-GWT-Module-Base\":\"https://ssl.gstatic.com/play-apps-publisher-rapid/fox/19816c4252e01d1fc31463df477cf1ac/fox/gwt/\","
			+ "\"X-GWT-Permutation\":\"C4685B8329EFD697B9193BA99C4DE3D4\"}, "
			+ "\"cookies\":{"
			+ "\"_gat\":\"1\","
			+ "\"_ga\":\"GA1.3-3.371363787.1461476170\","
			+ "\"_ga\":\"GA1.3-2.371363787.1461476170\","
			+ "\"PLAY_ACTIVE_ACCOUNT\":\"ICrt_XL61NBE_S0rhk8RpG0k65e0XwQVdDlvB6kxiQ8=giester@gmail.com\","
			+ "\"SID\":\"lQTQrbvJzUM8_jkuQbJw16J82cv9YAHMj51Yra5X2EiqjkWpWUwFQ6EDZC9vimn9A3k5kw.\","
			+ "\"HSID\":\"AWslTulfD7bM2XA1g\","
			+ "\"SSID\":\"AG8AimVsfGmg_XMD8\","
			+ "\"APISID\":\"zKKxSWwx4ImLlFXK/AuRWDXF-LlsQeQUhR\","
			+ "\"SAPISID\":\"7ih54GsGQOVLqFHY/AF128BAFKSli_S9gR\","
			+ "\"enabledapps.uploader\":\"0\","
			+ "\"PLAY_PREFS\":\"CvkICNDb3q12EvAICgJTRxCEna-iuCsasQgSExQVGPEBhAKsAuEDgQWMBeMF5QXoBdcGkJWBBpGVgQaSlYEGlZWBBpeVgQaklYEGuJWBBryVgQa9lYEGwJWBBsGVgQbElYEGxZWBBsiVgQbJlYEGzpWBBs-VgQbQlYEG1JWBBtmVgQbflYEG7pWBBu-VgQbwlYEG8pWBBviVgQb6lYEG_JWBBoCWgQaGloEGh5aBBoqWgQaPloEGkJaBBpuWgQahloEGopaBBqOWgQakloEG7peBBu-XgQaCmIEGhZiBBomYgQa-mIEGq5uBBq2bgQbJm4EGypuBBsubgQbVm4EG8JuBBrydgQbdnYEG552BBpCegQaWnoEG4qKBBvOigQaLo4EGmqSBBrKkgQbppYEGxqaBBv6mgQaAp4EGgqeBBoSngQaGp4EGiKeBBoqngQbPp4EGo6iBBvKogQb0qIEGvKyBBtavgQbBsIEGh7KBBomygQbWsoEGsLSBBta5gQbruoEGjsCBBqLAgQbAwIEG8sCBBoTBgQatwoEGsMKBBtbCgQbtw4EG-MeBBu7IgQbuyYEGqsqBBuvKgQbYzIEG3MyBBuTMgQbdzYEGhs6BBqHPgQbE0oEGldWBBt_XgQba2IEGk9mBBszZgQbU24EG8tuBBtjkgQaX5YEGuOiBBs_rgQbc64EGsOyBBsT0gQbX9YEGuvuBBsD_gQbF_4EGx_-BBsn_gQbVg4IGyISCBt6Fgga5hoIGpoeCBqeHggazh4IG7IeCBu2HggbrjYIG-42CBomOggaUjoIGl46CBsyRggbwl4IG9peCBpWYgga9mYIGmZqCBsGaggbGmoIGzJqCBtqaggbgmoIG95qCBp2bggbWnYIG3Z2CBuGdggadnoIG1Z6CBrqggga7oIIG9qKCBpKlggarpYIGzaWCBvKnggaeqIIGtKiCBoG0ggaDtIIGhrSCBt61ggattoIGsbuCBvG-ggaNv4IGj7-CBqS_ggalv4IG6sCCBvPCggaDw4IGk8OCBqPDgga0w4IGt8OCBrzDgga_w4IGw8OCBtLDggbiw4IG9MOCBvfDggb7w4IGg8SCBr7FggawxoIGm8mCBufJggbDyoIGjsuCBtbLggbby4IG38uCBonQggaL0IIG89GCBrfSggbb04IGgtWCBpPYggab2IIGhdqCBoraggaQ2oIGm9qCBvraggb92oIGhduCBq3bggau24IGwduCBtTbggbu24IG-NuCBvvbggaY3IIGuN6CBpjfggbk34IGr-CCBqbhggar4YIGwuGCBsThggbG4YIGx-GCBojoggaI6YIGnemCBp7pggbA6oIG3OqCBoLrggaF64IGm-uCBpXsggaq7IIG4OyCBvfsggaY7YIGoe2CBoHuggae7oIGx--CBo3wggaj8IIG_PCCBp_xgga48oIGuvKCBrvyggbN8oIG5PKCBiiana-iuCs6JDFmZDU4MWYyLTlmNWUtNDQyNS1hOTI2LTQxNzZmZDQ2YTUzZkABSAAKjwUIABKKBQoCU0cQ0rTa3LcrGssEERITFBXUAdUBpwLEBOMF5QXoBdcG2AbeBt8GkJWBBpGVgQaSlYEGl5WBBriVgQa8lYEGvZWBBsCVgQbBlYEGxJWBBtSVgQbZlYEG8pWBBviVgQabloEGnZaBBp6WgQafloEGoJaBBu6XgQaCmIEGhZiBBomYgQa-mIEGq5uBBq2bgQbJm4EGypuBBsubgQbVm4EG8JuBBrydgQbdnYEG552BBpCegQbiooEG86KBBoujgQaapIEG6qWBBsamgQb-poEGgKeBBoKngQaEp4EGhqeBBoingQaKp4EGo6iBBs6ogQbyqIEG9KiBBrysgQbWr4EGwbCBBoeygQaJsoEG1rKBBrG0gQbWuYEG67qBBqLAgQbAwIEG8sCBBtbCgQbtw4EGjMWBBsrGgQb4x4EGqsqBBtjMgQbczIEG3c2BBobOgQahz4EGxNKBBpXVgQbf14EG2tiBBszZgQbU24EG8tuBBtjkgQaX5YEGuOiBBs_rgQbc64EGsOyBBtf1gQa6-4EGu_-BBsn_gQbVg4IGyISCBt6Fgga5hoIGpoeCBqeHggazh4IG7IeCBu2HggbrjYIG-42CBomOggbMkYIGlZiCBr2ZggaZmoIGwZqCBveaggadm4IGnZ6CBtWegga7oIIG9qKCBpKlggbyp4IGnqiCBrSoggaBtIIGg7SCBoa0ggattoIG8b6CBo2_ggaPv4IG6sCCBr7FggbnyYIGjsuCBpvYggat24IGwduCBsXbggbU24IG-NuCBvvbggaY34IG5N-CBo3wggYovqPi17crOiQ4MDNjZjM0OS00MDUxLTQzNTYtODI5Zi1iMzgzOGVhMzdiNWRAAUgA:S:ANO1ljIw-WiQDbeCqQ\",\"S\":\"billing-ui-v3=uKmNbjatecBOfP576tcuYTStceI1Lj4P:billing-ui-v3-efe=uKmNbjatecBOfP576tcuYTStceI1Lj4P\","
			+ "\"_ga\":\"GA1.3.371363787.1461476170\","
			+ "\"NID\":\"101=sdNJj2mnAd8w8gFqm74RXILPwo5grfHu-511PsgMsnftJbqD3e3s2ABmuhw2Anlrh47bpp6ey6WNI7zszRe234T0cZIeEVu9dkUDhLI7LAMHvjozGxn897VEyIINs9ulGMSPCuLlYuTC6-G2QtYyqlGn1z0PzlabCO6l5Z_SRB2HhIo20CUEJpYiySPC7p3ygtp0ykF4BOksmZHFamygNA9pEwr--ENRVx3S9DLm_tnuXJkhPrbB6zLFq55vOD9uMAQpcXTEGUKrMOibLc1xd9y0r_MtFxzaQRlxgOPc6ZICbMSpM8GX\"}}";

			*/
	
	
	
//	private static String INIT_GOOGLE_XSRF = "AMtNNDEX7BggFW988LmUpa7NYfo_z_oo5w:1554888878948";
	
	
	
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
//		headersMap.put("path", "/apps/publish/reviews?account=5416627767168703904");
//		headersMap.put(":scheme", "https");
//		headersMap.put("accept", "*/*");
//		headersMap.put("accept-encoding:", "gzip, deflate, br");
//		headersMap.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
//		headersMap.put("content-length", "3847");
		headersMap.put("content-type", "application/javascript; charset=UTF-8");
//		headersMap.put("cookie", "_gat=1; _ga=GA1.3-3.75272786.1535349142; _gid=GA1.3-3.1976289437.1554797496; _ga=GA1.3.75272786.1535349142; OTZ=4852967_24_24__24_; ANID=AHWqTUkQ6asUdPI1PWIC7L1k1vewq-bb_hVuYzMV00emCw1dsVsFmV1KSf-5dVgX; HSID=AzDoK6lco534z9HAQ; SSID=AnnH83-IN5_PVAKES; APISID=Y8A_VIKH3t43GZGR/Az5ykkVcUjclI6-UB; SAPISID=sdon6fsymSY2CArI/AJh50xpmpXour6UG-; CONSENT=YES+US.en+; enabledapps.uploader=0; SID=OgeYx5miPrZjug-ap8jAiaF9ybBBkuMSrpszY1LOyJUDEm2xjOgrVjMedpOZB9TK5ixRcA.; PLAY_ACTIVE_ACCOUNT=ICrt_XL61NBE_S0rhk8RpG0k65e0XwQVdDlvB6kxiQ8=gpfeedbackmi@gmail.com; _gid=GA1.3.1698956001.1554820683; S=billing-ui-v3=SiEJq-wjpwval7hEe_MzmEuz3NJzviap:billing-ui-v3-efe=SiEJq-wjpwval7hEe_MzmEuz3NJzviap; NID=181=EezZKkoIHKnJfOP94NIpuUAzAiEqYzIrKjbdWC5Nx3EtqsJuFF9DEtyJZafbERhV3CbKiT3o82gc5UCxUXeh1G9zNTjeMoE6jP7ufIunpKbjwDAuf5rnCHfB7T-VdpIQS6BVNcOQcDJEozVW2ZlIKh_H9ILzeHHjTWw94oK8IRV2ibGaubBRyD9zVo_ipVw_75sgBvLAtXAmiunOC0cJ0ERZcKQTDXPdpg5kQidGsoD7gs8SQ4wP6QfIkAIKUdUNfxJLKFA_7OGIlg; 1P_JAR=2019-4-10-9; SIDCC=AN0-TYvv2AnX3atAilnvXZ1Yy8GaMIbOSx6FE_K1apTjTFFcCVFxXpIve4pdhkiWBhjwKX6a7P0");
		headersMap.put("origin", "https://play.google.com");
		headersMap.put("referer", "https://play.google.com/apps/publish/reviews?account=5416627767168703904");
		headersMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
//		headersMap.put("x-client-data", "CJa2yQEIpLbJAQjDtskBCKmdygEIqKPKAQixp8oBCOOoygEI8anKAQ==");
		headersMap.put("x-gwt-module-base", "https://ssl.gstatic.com/play-apps-publisher-rapid/fox/6d4153e8bf1e0cb3996c06832aa7bd28/fox/gwt/");
		headersMap.put("x-gwt-permutation", "32DEFE6390069B69829D17A2A00BB439");
		
		googleSession.setHeaders(headersMap);
		

//		S=billing-ui-v3=SiEJq-wjpwval7hEe_MzmEuz3NJzviap:billing-ui-v3-efe=SiEJq-wjpwval7hEe_MzmEuz3NJzviap; 
//		NID=181=EezZKkoIHKnJfOP94NIpuUAzAiEqYzIrKjbdWC5Nx3EtqsJuFF9DEtyJZafbERhV3CbKiT3o82gc5UCxUXeh1G9zNTjeMoE6jP7ufIunpKbjwDAuf5rnCHfB7T-VdpIQS6BVNcOQcDJEozVW2ZlIKh_H9ILzeHHjTWw94oK8IRV2ibGaubBRyD9zVo_ipVw_75sgBvLAtXAmiunOC0cJ0ERZcKQTDXPdpg5kQidGsoD7gs8SQ4wP6QfIkAIKUdUNfxJLKFA_7OGIlg; 
				
		Map<String,String> cookiesMap = new HashMap<String, String>();
		cookiesMap.put("_ga", "GA1.3-3.75272786.1535349142");
		cookiesMap.put("_gid", "GA1.3-3.1976289437.1554797496");
		cookiesMap.put("_gid", "GA1.3.1698956001.1554820683");
		cookiesMap.put("_ga", "GA1.3.75272786.1535349142");
		cookiesMap.put("OTZ", "4852967_24_24__24_");
		cookiesMap.put("ANID", "AHWqTUkQ6asUdPI1PWIC7L1k1vewq-bb_hVuYzMV00emCw1dsVsFmV1KSf-5dVgX");
		cookiesMap.put("HSID", "AzDoK6lco534z9HAQ");
		cookiesMap.put("SSID", "AnnH83-IN5_PVAKES");
		cookiesMap.put("APISID", "Y8A_VIKH3t43GZGR/Az5ykkVcUjclI6-UB");
		cookiesMap.put("SAPISID", "sdon6fsymSY2CArI/AJh50xpmpXour6UG-");
		cookiesMap.put("SID", "OgeYx5miPrZjug-ap8jAiaF9ybBBkuMSrpszY1LOyJUDEm2xjOgrVjMedpOZB9TK5ixRcA.");
		cookiesMap.put("PLAY_ACTIVE_ACCOUNT", "ICrt_XL61NBE_S0rhk8RpG0k65e0XwQVdDlvB6kxiQ8=gpfeedbackmi@gmail.com");
		cookiesMap.put("SIDCC", "AN0-TYt77hOH3iBuBFan5GIwjtx_GDpwSKc0rvtiGaHA1S3zXlYASnTaWzLFVNKpWAkAPH6I6ys");
		cookiesMap.put("1P_JAR", "2019-4-10-9");
		cookiesMap.put("CONSENT", "YES+US.en+");
		cookiesMap.put("enabledapps.uploader", "0");
		
		googleSession.setCookies(cookiesMap);
		
		
		//获取 xsrf
		String xsrf = "AMtNNDF8i4cn-b8OcW_Vys-pzJmiuC4xDg:1554890363504";
		
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
