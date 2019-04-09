package org.github.yuxin.gapi.auth;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.mybusiness.v4.MyBusiness;
import com.google.api.services.mybusiness.v4.model.ListAccountsResponse;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Collections;
import java.util.List;



/**
 * 
 * 实施OAuth 2.0授权流程    -    https://developers.google.com/my-business/content/set-up-java-client
 * 
 * 此示例演示如何使用AuthorizationCodeFlow类允许应用程序访问Google My Business帐户数据。
 * 
 * 
 * 
 * 
 * @author wangyx
 *
 */
public class Test {

	private static final String APPLICATION_NAME = "Google My Business API Java Quickstart";
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), 
			".store/mybusiness_sample");
	private static FileDataStoreFactory dataStoreFactory;
	private static HttpTransport httpTransport;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static MyBusiness mybusiness;
	
	

    /**
     * Demonstrates the authentication flow to use
     * with the Google My Business API Java client library.
     * @return AuthorizationCodeInstalledApp
     */
    private static Credential authorize() throws Exception {
        // Creates an InputStream to hold the client ID and secret.
        InputStream secrets = Test.class.getResourceAsStream("/client_secret.json");

        // Prompts the user if no credential is found.
        if (secrets == null) {
            System.out.println(
                "Enter Client ID and Secret from Google API Console "
                    + "into google-my-business-api-sample/src/main/resources/client_secrets.json");
            System.exit(1);
        }

        // Uses the InputStream to create an instance of GoogleClientSecrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
            new InputStreamReader(secrets));
        if (clientSecrets.getDetails().getClientId().startsWith("Enter")
            || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println(
                "Enter Client ID and Secret from Google API Console "
                    + "into google-my-business-api-sample/src/main/resources/client_secrets.json");
            System.exit(1);
        }

        // Sets up the authorization code flow.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            httpTransport, JSON_FACTORY, clientSecrets,
            Collections.singleton("https://www.googleapis.com/auth/business.manage"))
            .setDataStoreFactory(dataStoreFactory).build();
        // Returns the credential.
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String[] args) throws Exception {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

        // Calls the authorize() function to get a credential.
        Credential credential = authorize();

        // Calls MyBusiness.Builder to create a new instance named 'mybusiness'.
        mybusiness = new MyBusiness.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME).build();

        // Uses the 'mybusiness' instance to send an API call.
        MyBusiness.Accounts.List accountsList = mybusiness.accounts().list();
        ListAccountsResponse response = accountsList.execute();
        List accounts = response.getAccounts();
        
        /*
        {
        	  "code" : 403,
        	  "errors" : [ {
        	    "domain" : "usageLimits",
        	    "message" : "Google My Business API has not been used in project 720135326514 before or it is disabled. Enable it by visiting https://console.developers.google.com/apis/api/mybusiness.googleapis.com/overview?project=720135326514 then retry. If you enabled this API recently, wait a few minutes for the action to propagate to our systems and retry.",
        	    "reason" : "accessNotConfigured",
        	    "extendedHelp" : "https://console.developers.google.com"
        	  } ],
        	  "message" : "Google My Business API has not been used in project 720135326514 before or it is disabled. Enable it by visiting https://console.developers.google.com/apis/api/mybusiness.googleapis.com/overview?project=720135326514 then retry. If you enabled this API recently, wait a few minutes for the action to propagate to our systems and retry.",
        	  "status" : "PERMISSION_DENIED"
        	}
        	*/
    }

}
