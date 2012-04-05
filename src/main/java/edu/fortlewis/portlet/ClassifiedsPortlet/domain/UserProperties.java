
package edu.fortlewis.portlet.ClassifiedsPortlet.domain;

import java.util.ArrayList;
import java.util.List;


public class UserProperties {

	private String userid;
	private String username;
	private boolean isAdmin = false;
	private String useremail;
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(boolean b) {
		this.isAdmin = b;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

}

