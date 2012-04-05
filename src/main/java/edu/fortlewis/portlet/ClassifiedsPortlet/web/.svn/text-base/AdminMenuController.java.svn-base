package edu.fortlewis.portlet.ClassifiedsPortlet.web;

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
import edu.fortlewis.portlet.ClassifiedsPortlet.service.HeadingService;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.PortletUserPropertiesResolver;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.UserProperties;

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
