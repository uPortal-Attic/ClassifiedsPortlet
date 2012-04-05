
package edu.fortlewis.portlet.ClassifiedsPortlet.web;

import java.util.List;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.CategoryEditor;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.SubmitCategoryFormValidator;

import org.springframework.validation.BindingResult;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Heading;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.HeadingService;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.CategoryService;

import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.SubmitHeadingFormValidator;
@Controller
@RequestMapping("EDIT")

public class SubmitHeadingFormController  {

	private static Log log = LogFactory.getLog(SubmitHeadingFormController.class);
	
	@Autowired 
	private HeadingService headingService;
	 

	@Autowired 
	private CategoryService categoryService = null;

	
	@RequestMapping(params="action=addHeading")
	public String setupForm(
			@RequestParam(value="id", required=false) Long id,
			Model model, PortletRequest request)
	{
		Heading heading = new Heading();
		
		if (!model.containsAttribute("heading")) {
			
			if (id != null) {
				List<Heading> headingList = this.headingService.getHeading(id);
				heading = (Heading) headingList.get(0);
			}
			model.addAttribute("heading", heading);
		}
		
		return "submitHeadingForm";
	}


	 //converting strings from drop down list in form to Category class 
	
	@InitBinder 
    public void initBinder(PortletRequest request, PortletRequestDataBinder binder) throws Exception{
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService)); 

	}
	
	@ModelAttribute("categories")
	public List <Category> getCategories() {
		return categoryService.getCategories(); 
	}
  
 	
	@RequestMapping(params="action=addHeading")
	protected void processFormSubmission(
			@ModelAttribute("heading") Heading heading,
			BindingResult result,
			SessionStatus status, 
			ActionRequest request,
			ActionResponse response,
			Model model) throws PortletException {
	
		new SubmitHeadingFormValidator().validate(heading, result);
		if (result.hasErrors()) {
			response.setRenderParameter("action", "addHeading");
			return;
		}
		
		
	    if (!result.hasErrors() && heading != null){
	    	
	    	if (log.isDebugEnabled())
				log.debug("No errors in form");
	    	
	    	headingService.processHeading(heading);
	    		
	    	status.setComplete();

			response.setRenderParameter("action", "editHeadings");
	   	}
	}	

}
