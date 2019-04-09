package org.github.yuxin.gapi.auth;

import java.util.List;

import com.google.api.services.mybusiness.v4.MyBusiness;


import com.google.api.services.mybusiness.v4.MyBusiness.Accounts.Locations.Reviews;
import com.google.api.services.mybusiness.v4.model.ListReviewsResponse;


//https://developers.google.com/my-business/content/review-data
public class ReviewsUtil {
	
	
	/**
	 * Returns a list of reviews.
	 * @param locationName Name of the location to retrieve reviews for.
	 * @return List A list of reviews.
	 * @throws Exception
	 */
	public static List listReviews(String locationName) throws Exception {
		MyBusiness.Accounts.Locations.Reviews.List reviewsList = null;
		
		
//	  Mybusiness.Accounts.Locations.Reviews.List reviewsList =
//	    mybusiness.accounts().locations().reviews().list(locationName);
//	  ListReviewsResponse response = accountsList.execute();
//	  List reviews = response.getReviews();
//
//	  for (Reviews review : reviews) {
//	    System.out.println(review.toPrettyString());
//	  }
//	  return reviews;
		
		
		return null;
	}
	
	
	

}
