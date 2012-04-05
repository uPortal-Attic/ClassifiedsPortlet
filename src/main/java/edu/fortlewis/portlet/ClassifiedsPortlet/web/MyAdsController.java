package edu.fortlewis.portlet.ClassifiedsPortlet.web;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;
import javax.portlet.PortletException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Ad;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.AdService;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.PortletUserPropertiesResolver;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.UserProperties;
import javax.portlet.ActionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("VIEW")
public class MyAdsController
{
    private static Log log = LogFactory.getLog(MyAdsController.class);
    
    @Autowired 
    private AdService adService;
  
    @Autowired 
   private PortletUserPropertiesResolver userPropertiesResolver;
    
    @RequestMapping(params="action=deleteAd")
    public String showMyAdsAfterDelete(Model model, RenderRequest request, RenderResponse response)
    		throws PortletException {  	
 			
   	if (request.getRemoteUser() != null) 
   	{
    	UserProperties user = userPropertiesResolver.getProperties(request);
    	List <Ad> ads = adService.getAdsByUserId(request.getRemoteUser());
     
    	model.addAttribute("ads", ads);
  	}
     			
	return "myAds";
    }
 
    @RequestMapping(params="action=deleteAd")
    public void deleteAd(@RequestParam(value="id", required=false) Long id,
    		@RequestParam(value="actionCode",  required=false) String actionCode,
			ActionResponse response)throws PortletException {
		
		if (actionCode.equals("delete")) {
			adService.delete(id);
		}
	}
 
    	
  @RequestMapping(params="action=MyAds") //render phase
  public String showMyAds(Model model, RenderRequest request, RenderResponse response)
  throws PortletException {  	
    			if (request.getRemoteUser() != null) 
    			{
    				UserProperties user = userPropertiesResolver.getProperties(request);
    				List <Ad> ads = adService.getAdsByUserId(request.getRemoteUser());
     				model.addAttribute("ads", ads);
    			}
    			return "myAds";
            }
}
