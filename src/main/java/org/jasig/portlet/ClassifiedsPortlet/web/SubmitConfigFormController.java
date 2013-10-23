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

import java.util.List;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.jasig.portlet.ClassifiedsPortlet.domain.Config;
import org.jasig.portlet.ClassifiedsPortlet.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("EDIT")

/**
 * SubmitConfigFormController allows a user to modify Classifieds config properties 
 */

public class SubmitConfigFormController  {

	private static Log log = LogFactory.getLog(SubmitConfigFormController.class);
	@Autowired 

	private ConfigService configService = null;

	@RequestMapping(params="action=editConfig")
	public String setupForm(
			@RequestParam(value="id", required=false) Long id,
			Model model, PortletRequest request)
	{
		
		if (!model.containsAttribute("config")) {
		
			Config config = new Config();
		
			List<Config> configList = this.configService.getConfig();
		
			if (configList.size() == 0) 
			{
				model.addAttribute("config", config);
			}
			else
			{
				config = (Config) configList.get(0);
				model.addAttribute("config", config);
			}
		}
		
		return "submitConfigForm";
	}

	
	@RequestMapping(params="action=addConfig")
	protected void processFormSubmission(
			@ModelAttribute("config") Config config,
			BindingResult result,
			SessionStatus status, 
			ActionRequest request,
			ActionResponse response,
			Model model) throws PortletException {
		
	    if (!result.hasErrors() && config != null){
	    	
	    	configService.processConfig(config);
	    		
	    	status.setComplete();

			response.setRenderParameter("action", "adminMenu");
	   	}
	}	
}
