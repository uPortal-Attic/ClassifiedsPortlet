
package edu.fortlewis.portlet.ClassifiedsPortlet.web;

import java.util.List;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Config;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.ConfigService;
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
