/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlet.ClassifiedsPortlet.domain;

import org.jasig.portlet.ClassifiedsPortlet.service.IAdminGroupService;
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

