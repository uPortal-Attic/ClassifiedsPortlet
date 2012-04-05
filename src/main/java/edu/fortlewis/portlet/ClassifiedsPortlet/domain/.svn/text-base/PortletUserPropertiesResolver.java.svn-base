
package edu.fortlewis.portlet.ClassifiedsPortlet.domain;

import edu.fortlewis.portlet.ClassifiedsPortlet.service.IAdminGroupService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;

public class PortletUserPropertiesResolver {
	
	 private IAdminGroupService adminGroupService;

	private String nameKey = "user.name.full";
	private String idKey = "user.login.id";
	private String emailKey = "mail";

	public UserProperties getProperties(PortletRequest request) {

		Map<String, String> userinfo = (Map<String, String>) request.getAttribute("javax.portlet.userinfo");

		UserProperties props = new UserProperties();
		List <String> adminRoles = adminGroupService.getAdminRoles();

		boolean b = false;
		
		for (String s : adminRoles)
		{
			if ( b = request.isUserInRole(s)) {
				props.setIsAdmin(true); 
				break;
			}
		}
		
		if (nameKey != null)
			props.setUsername((String) userinfo.get(nameKey));

		if (idKey != null)
			props.setUserid((String) userinfo.get(idKey));

		if (emailKey != null)
			props.setUseremail((String) userinfo.get(emailKey));

		return props;
	}

	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}
	
	public void setAdminGroupService(IAdminGroupService adminGroupService) {
		this.adminGroupService = adminGroupService;
	}

}

