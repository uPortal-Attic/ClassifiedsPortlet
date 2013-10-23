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
import javax.portlet.RenderResponse;
import javax.portlet.PortletURL;
import javax.portlet.PortletMode;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jasig.portlet.ClassifiedsPortlet.domain.Ad;
import org.jasig.portlet.ClassifiedsPortlet.domain.UserProperties;
import org.jasig.portlet.ClassifiedsPortlet.service.AdService;
import org.jasig.portlet.ClassifiedsPortlet.domain.Category;
import org.jasig.portlet.ClassifiedsPortlet.service.CategoryService;
import org.jasig.portlet.ClassifiedsPortlet.domain.Heading;
import org.jasig.portlet.ClassifiedsPortlet.service.HeadingService;
import org.jasig.portlet.ClassifiedsPortlet.domain.Config;
import org.jasig.portlet.ClassifiedsPortlet.service.ConfigService;
import org.jasig.portlet.ClassifiedsPortlet.domain.PortletUserPropertiesResolver;

import org.jasig.portlet.ClassifiedsPortlet.domain.JsonForMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")

public class MainMenuController 
{
    private static Log log = LogFactory.getLog(MainMenuController.class);
    
    @Autowired 
    private CategoryService categoryService = null;
 
    @Autowired 
    private AdService adService = null;
 
    @Autowired 
    private HeadingService headingService = null;

    @Autowired 
    private ConfigService configService = null;
 
    @Autowired 
    private PortletUserPropertiesResolver userPropertiesResolver = null;

    public static final String MAX_LATEST_ADS_CNT = "40";
    public static final String MAX_ADS_PER_PAGE = "10";
    public static final int LATEST_ADS = -1;
    
    public static final String IS_ADMIN = "isAdmin";
    public static final String IS_NOT_ADMIN = "";

    public static final String IS_GUEST = "isGuest";
    
    @RequestMapping  // default 
    public String showMenu(Model model, RenderRequest request, RenderResponse response) throws PortletException, IOException {
       		
    		PortletPreferences prefs = request.getPreferences();
    		
    		int nMaxLatestAdsCnt = Integer.parseInt(prefs.getValue("maxLatestAdsCnt", MAX_LATEST_ADS_CNT));
    		model.addAttribute("latestAdsCnt",adService.getLatestAds(nMaxLatestAdsCnt).size());
    		
    		if (request.getRemoteUser() != null) {
 
    			UserProperties user = userPropertiesResolver.getProperties(request);

    			if (user.getIsAdmin() == true)	{
    				model.addAttribute("userType", IS_ADMIN);
    			}
    			else{
    				model.addAttribute("userType", IS_NOT_ADMIN);
    			}
    		} else {

			model.addAttribute("userType", IS_GUEST);
		}
         		
    	List <Config> configs = configService.getConfig();
    	model.addAttribute("configs", configs);
    	
   		List <Heading> headings = headingService.getHeadings();
   		
    	JsonForMenu json = new JsonForMenu(headings, response);
    	model.addAttribute("lines", json.toString());
    	
   		return "mainMenu";
   		
     }
 
      
    
@RequestMapping(params="action=selectedCategory")
 public String showCategory(@RequestParam(value="selectedCategory", required=false) Long selectedCategory,
		 Model model, RenderRequest request) {
  
    		
    		PortletPreferences prefs = request.getPreferences();
    		
    		int nMaxLatestAdsCnt = Integer.parseInt(prefs.getValue("maxLatestAdsCnt", MAX_LATEST_ADS_CNT));
    		model.addAttribute("latestAdsCnt",adService.getLatestAds(nMaxLatestAdsCnt).size());
    		
    		int nMaxAdsPerPage = Integer.parseInt(prefs.getValue("maxAdsPerPage", MAX_ADS_PER_PAGE));
    		model.addAttribute("maxAdsPerPage", nMaxAdsPerPage);


    		if (request.getRemoteUser() != null) {
     			model.addAttribute("userType", IS_NOT_ADMIN);
    			} else {
    				model.addAttribute("userType", IS_GUEST);
    			}
		
      		List <Config> configs = configService.getConfig();
      		model.addAttribute("configs", configs);
  			
   			String categoryHeading = "";
   				
   			if (selectedCategory == LATEST_ADS)
   			{
  				List<Ad> ads = adService.getLatestAds(nMaxLatestAdsCnt);
  				model.addAttribute("ads", ads);
  				categoryHeading = "Latest Ads";
   			}
   			else
   			{
   				List <Category> cat = categoryService.getCategory(selectedCategory);
   				Set<Ad> ads = cat.get(0).getAds();
   				model.addAttribute("ads", ads);
   				categoryHeading =  cat.get(0).getName();
   			}
   				
   			model.addAttribute("categoryHeading", categoryHeading);
     		return("categories");

     }
    
}

