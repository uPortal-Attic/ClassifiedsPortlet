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


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jasig.portlet.ClassifiedsPortlet.domain.Config;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import org.jasig.portlet.ClassifiedsPortlet.domain.Ad;
import org.jasig.portlet.ClassifiedsPortlet.service.AdService;
import org.jasig.portlet.ClassifiedsPortlet.domain.Category;
import org.jasig.portlet.ClassifiedsPortlet.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.jasig.portlet.ClassifiedsPortlet.domain.CategoryEditor;
import org.jasig.portlet.ClassifiedsPortlet.service.ConfigService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.jasig.portlet.ClassifiedsPortlet.domain.SubmitAdFormValidator;
/**
 * SubmitAdFormController allows a user to create or modify an Ad.
 */
@Controller
@RequestMapping("VIEW")
public class SubmitAdFormController {

	private static Log log = LogFactory.getLog(SubmitAdFormController.class);
	
	@Autowired 
	private AdService adService = null;
	
	@Autowired 
	private ConfigService configService = null;

	@Autowired 
	private CategoryService categoryService = null;

	@RequestMapping(params="action=addAd")
	public String setupForm(
			@RequestParam(value="id", required=false) Long id,
			Model model, PortletRequest request) {
		
		Ad ad = new Ad();
		
		if (!model.containsAttribute("ad"))
		{
			if (id != null) {
				List<Ad> adList = this.adService.getAd(id);
				ad = (Ad)adList.get(0);
				ad.setPolicyAccepted(false);
			}

			model.addAttribute("ad", ad);
		}
	
		return "submitAdForm";
	}
	@InitBinder 
    public void initBinder(PortletRequest request, PortletRequestDataBinder binder) throws Exception{
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    	dateFormat.setLenient(false);
    
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(
            dateFormat, true));
    
    	binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
    }
    

	    
	@ModelAttribute("categories")
	public List <Category> getCategories() {
		return categoryService.getCategories(); 
	}
	
	@ModelAttribute("configs")
	public List <Config> getCofigs() {
		return configService.getConfig();
	}
	
	@RequestMapping(params="action=addAd")
	protected void processFormSubmission(
			@ModelAttribute("ad") Ad ad,
			BindingResult result,
			SessionStatus status, 
			ActionRequest request,
			ActionResponse response,
			Model model) throws PortletException {
		
		new SubmitAdFormValidator().validate(ad, result);
		if (result.hasErrors()) {
			response.setRenderParameter("action", "addAd");
			return;
		}
		
	    if (!result.hasErrors() && ad != null){
	    	
	    		if (request.getRemoteUser() != null) {
	    			if (ad.getUserid() == null)
	    			{
	    				ad.setUserid(request.getRemoteUser());
	    			}
	    			ad.setStatus(0);
	    			adService.processAd(ad);
	    		}
	    		
	    	status.setComplete();
				
			response.setRenderParameter("action", "MyAds");
	   	}
	}
}
