package org.github.yuxin.gapi.reviews.browser.reply;


import java.io.Serializable;

import lombok.Data;

@Data
public class GPReplyRequest implements Serializable {
	private static final long serialVersionUID = -9121848180687289761L;
	
	private String method;
	
	private GPReplyRequestParam params;
	
	private String xsrf;
	
}
