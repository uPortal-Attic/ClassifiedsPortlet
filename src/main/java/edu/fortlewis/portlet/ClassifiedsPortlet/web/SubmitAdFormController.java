
package edu.fortlewis.portlet.ClassifiedsPortlet.web;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Config;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Ad;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.AdService;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.CategoryEditor;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.ConfigService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.SubmitAdFormValidator;
/**
 * SubmitAdFormController allows a user to create or modify an Ad.
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes("ad")
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
