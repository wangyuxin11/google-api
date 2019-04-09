package org.github.yuxin.gapi.reviews;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;



public class Test {

	private static  GoogleSession googleSession;
	
	
	private static String google_context_json = "{\"headers\":{\"Content-Type\":\"application/javascript; charset=UTF-8\",\"Origin\":\"https://play.google.com\",\"Referer\":\"https://play.google.com/apps/publish/?dev_acc=16286403833307817191\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36\",\"X-GWT-Module-Base\":\"https://ssl.gstatic.com/play-apps-publisher-rapid/fox/19816c4252e01d1fc31463df477cf1ac/fox/gwt/\",\"X-GWT-Permutation\":\"C4685B8329EFD697B9193BA99C4DE3D4\"}, \"cookies\":{\"_gat\":\"1\",\"_ga\":\"GA1.3-3.371363787.1461476170\",\"_ga\":\"GA1.3-2.371363787.1461476170\",\"PLAY_ACTIVE_ACCOUNT\":\"ICrt_XL61NBE_S0rhk8RpG0k65e0XwQVdDlvB6kxiQ8=giester@gmail.com\",\"SID\":\"lQTQrbvJzUM8_jkuQbJw16J82cv9YAHMj51Yra5X2EiqjkWpWUwFQ6EDZC9vimn9A3k5kw.\",\"HSID\":\"AWslTulfD7bM2XA1g\",\"SSID\":\"AG8AimVsfGmg_XMD8\",\"APISID\":\"zKKxSWwx4ImLlFXK/AuRWDXF-LlsQeQUhR\",\"SAPISID\":\"7ih54GsGQOVLqFHY/AF128BAFKSli_S9gR\",\"enabledapps.uploader\":\"0\",\"PLAY_PREFS\":\"CvkICNDb3q12EvAICgJTRxCEna-iuCsasQgSExQVGPEBhAKsAuEDgQWMBeMF5QXoBdcGkJWBBpGVgQaSlYEGlZWBBpeVgQaklYEGuJWBBryVgQa9lYEGwJWBBsGVgQbElYEGxZWBBsiVgQbJlYEGzpWBBs-VgQbQlYEG1JWBBtmVgQbflYEG7pWBBu-VgQbwlYEG8pWBBviVgQb6lYEG_JWBBoCWgQaGloEGh5aBBoqWgQaPloEGkJaBBpuWgQahloEGopaBBqOWgQakloEG7peBBu-XgQaCmIEGhZiBBomYgQa-mIEGq5uBBq2bgQbJm4EGypuBBsubgQbVm4EG8JuBBrydgQbdnYEG552BBpCegQaWnoEG4qKBBvOigQaLo4EGmqSBBrKkgQbppYEGxqaBBv6mgQaAp4EGgqeBBoSngQaGp4EGiKeBBoqngQbPp4EGo6iBBvKogQb0qIEGvKyBBtavgQbBsIEGh7KBBomygQbWsoEGsLSBBta5gQbruoEGjsCBBqLAgQbAwIEG8sCBBoTBgQatwoEGsMKBBtbCgQbtw4EG-MeBBu7IgQbuyYEGqsqBBuvKgQbYzIEG3MyBBuTMgQbdzYEGhs6BBqHPgQbE0oEGldWBBt_XgQba2IEGk9mBBszZgQbU24EG8tuBBtjkgQaX5YEGuOiBBs_rgQbc64EGsOyBBsT0gQbX9YEGuvuBBsD_gQbF_4EGx_-BBsn_gQbVg4IGyISCBt6Fgga5hoIGpoeCBqeHggazh4IG7IeCBu2HggbrjYIG-42CBomOggaUjoIGl46CBsyRggbwl4IG9peCBpWYgga9mYIGmZqCBsGaggbGmoIGzJqCBtqaggbgmoIG95qCBp2bggbWnYIG3Z2CBuGdggadnoIG1Z6CBrqggga7oIIG9qKCBpKlggarpYIGzaWCBvKnggaeqIIGtKiCBoG0ggaDtIIGhrSCBt61ggattoIGsbuCBvG-ggaNv4IGj7-CBqS_ggalv4IG6sCCBvPCggaDw4IGk8OCBqPDgga0w4IGt8OCBrzDgga_w4IGw8OCBtLDggbiw4IG9MOCBvfDggb7w4IGg8SCBr7FggawxoIGm8mCBufJggbDyoIGjsuCBtbLggbby4IG38uCBonQggaL0IIG89GCBrfSggbb04IGgtWCBpPYggab2IIGhdqCBoraggaQ2oIGm9qCBvraggb92oIGhduCBq3bggau24IGwduCBtTbggbu24IG-NuCBvvbggaY3IIGuN6CBpjfggbk34IGr-CCBqbhggar4YIGwuGCBsThggbG4YIGx-GCBojoggaI6YIGnemCBp7pggbA6oIG3OqCBoLrggaF64IGm-uCBpXsggaq7IIG4OyCBvfsggaY7YIGoe2CBoHuggae7oIGx--CBo3wggaj8IIG_PCCBp_xgga48oIGuvKCBrvyggbN8oIG5PKCBiiana-iuCs6JDFmZDU4MWYyLTlmNWUtNDQyNS1hOTI2LTQxNzZmZDQ2YTUzZkABSAAKjwUIABKKBQoCU0cQ0rTa3LcrGssEERITFBXUAdUBpwLEBOMF5QXoBdcG2AbeBt8GkJWBBpGVgQaSlYEGl5WBBriVgQa8lYEGvZWBBsCVgQbBlYEGxJWBBtSVgQbZlYEG8pWBBviVgQabloEGnZaBBp6WgQafloEGoJaBBu6XgQaCmIEGhZiBBomYgQa-mIEGq5uBBq2bgQbJm4EGypuBBsubgQbVm4EG8JuBBrydgQbdnYEG552BBpCegQbiooEG86KBBoujgQaapIEG6qWBBsamgQb-poEGgKeBBoKngQaEp4EGhqeBBoingQaKp4EGo6iBBs6ogQbyqIEG9KiBBrysgQbWr4EGwbCBBoeygQaJsoEG1rKBBrG0gQbWuYEG67qBBqLAgQbAwIEG8sCBBtbCgQbtw4EGjMWBBsrGgQb4x4EGqsqBBtjMgQbczIEG3c2BBobOgQahz4EGxNKBBpXVgQbf14EG2tiBBszZgQbU24EG8tuBBtjkgQaX5YEGuOiBBs_rgQbc64EGsOyBBtf1gQa6-4EGu_-BBsn_gQbVg4IGyISCBt6Fgga5hoIGpoeCBqeHggazh4IG7IeCBu2HggbrjYIG-42CBomOggbMkYIGlZiCBr2ZggaZmoIGwZqCBveaggadm4IGnZ6CBtWegga7oIIG9qKCBpKlggbyp4IGnqiCBrSoggaBtIIGg7SCBoa0ggattoIG8b6CBo2_ggaPv4IG6sCCBr7FggbnyYIGjsuCBpvYggat24IGwduCBsXbggbU24IG-NuCBvvbggaY34IG5N-CBo3wggYovqPi17crOiQ4MDNjZjM0OS00MDUxLTQzNTYtODI5Zi1iMzgzOGVhMzdiNWRAAUgA:S:ANO1ljIw-WiQDbeCqQ\",\"S\":\"billing-ui-v3=uKmNbjatecBOfP576tcuYTStceI1Lj4P:billing-ui-v3-efe=uKmNbjatecBOfP576tcuYTStceI1Lj4P\",\"_ga\":\"GA1.3.371363787.1461476170\",\"NID\":\"101=sdNJj2mnAd8w8gFqm74RXILPwo5grfHu-511PsgMsnftJbqD3e3s2ABmuhw2Anlrh47bpp6ey6WNI7zszRe234T0cZIeEVu9dkUDhLI7LAMHvjozGxn897VEyIINs9ulGMSPCuLlYuTC6-G2QtYyqlGn1z0PzlabCO6l5Z_SRB2HhIo20CUEJpYiySPC7p3ygtp0ykF4BOksmZHFamygNA9pEwr--ENRVx3S9DLm_tnuXJkhPrbB6zLFq55vOD9uMAQpcXTEGUKrMOibLc1xd9y0r_MtFxzaQRlxgOPc6ZICbMSpM8GX\"}}";
	
	public static void main(String[] args) {

		System.out.println("GoogleReviewService init start");

		googleSession = new GoogleSession();
		
		Map context = FastJsonUtils.stringToCollect(google_context_json);
		
		System.out.println("context={}" + JSON.toJSONString(context));
		
		Object headers = context.get("headers");
		Object cookies = context.get("cookies");
		
		Map<String,String> headersMap = (Map<String, String>) headers;
		Map<String,String> cookiesMap = (Map<String, String>) cookies;
		
		if(context.containsKey("headers")){
			googleSession.setHeaders(headersMap);
		}
		
		if(context.containsKey("cookies")){
			googleSession.setCookies(cookiesMap);
		}
		
		//获取 xsrf
		
		
		
		
		
	}

}
