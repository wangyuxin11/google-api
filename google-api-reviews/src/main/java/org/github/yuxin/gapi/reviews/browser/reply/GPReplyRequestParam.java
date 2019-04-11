package org.github.yuxin.gapi.reviews.browser.reply;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class GPReplyRequestParam implements Serializable {
	private static final long serialVersionUID = -600063785291992168L;

	@SerializedName("1")
	private String pkgName;
	
	@SerializedName("2")
	private String reviewId;
	
	@SerializedName("3")
	private String replyText;

}
