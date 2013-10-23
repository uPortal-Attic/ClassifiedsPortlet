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
package org.jasig.portlet.ClassifiedsPortlet.web;

import javax.portlet.RenderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractController;
import org.jasig.portlet.ClassifiedsPortlet.service.HeadingService;

import org.jasig.portlet.ClassifiedsPortlet.domain.PortletUserPropertiesResolver;
import org.jasig.portlet.ClassifiedsPortlet.domain.UserProperties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Edit")
public class AdminMenuController
{
	
	@Autowired 
    private PortletUserPropertiesResolver userPropertiesResolver = null;
	
    private static Log log = LogFactory.getLog(AdminMenuController.class);
 
	@RequestMapping(params="action=adminMenu")
	public String showAdminMenu( Model model, RenderRequest request) {
		
   		if (request.getRemoteUser() != null) {
			
			UserProperties user = userPropertiesResolver.getProperties(request);

			if (user.getIsAdmin() == true)	{
		         return("adminMenu");
			}
   		}
	return ("invalid");
	}
}
