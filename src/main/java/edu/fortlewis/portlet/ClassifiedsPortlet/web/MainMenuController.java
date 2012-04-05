package edu.fortlewis.portlet.ClassifiedsPortlet.web;

import javax.portlet.RenderRequest;

import javax.portlet.PortletPreferences;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Ad;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.UserProperties;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.AdService;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.CategoryService;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Heading;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.HeadingService;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Config;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.ConfigService;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.PortletUserPropertiesResolver;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @RequestMapping  // default 
    public String showMenu(Model model, RenderRequest request) {
  
    		List <Heading> headings = headingService.getHeadings();
    		PortletPreferences prefs = request.getPreferences();
    		
    		int nMaxLatestAdsCnt = Integer.parseInt(prefs.getValue("maxLatestAdsCnt", MAX_LATEST_ADS_CNT));
    		model.addAttribute("latestAdsCnt",adService.getLatestAds(nMaxLatestAdsCnt).size());
    		
    		if (request.getRemoteUser() != null) {
    			
    			UserProperties user = userPropertiesResolver.getProperties(request);
  
    			if (user.getIsAdmin() == true)	{
    				model.addAttribute("user", IS_ADMIN);
    			}
    			else{
    				model.addAttribute("user", IS_NOT_ADMIN);
    			}
    		}
         		
    	List <Config> configs = configService.getConfig();
    	model.addAttribute("configs", configs);
    	model.addAttribute("headings", headings);
     		
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

