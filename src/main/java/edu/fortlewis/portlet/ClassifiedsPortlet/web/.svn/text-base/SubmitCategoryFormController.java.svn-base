
package edu.fortlewis.portlet.ClassifiedsPortlet.web;


import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.SubmitAdFormValidator;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.SubmitCategoryFormValidator;
/**
 * SubmitCategoryFormController allows a user to create or modify a Category
 */
@Controller
@RequestMapping("EDIT")

public class SubmitCategoryFormController  {

	private static Log log = LogFactory.getLog(SubmitCategoryFormController.class);
	
	@Autowired 
	private CategoryService categoryService = null;

	@RequestMapping(params="action=addCategory")
	public String setupForm(
			@RequestParam(value="id", required=false) Long id,
			Model model, PortletRequest request)
	{	
		Category category = new Category();
		
		if (!model.containsAttribute("category"))
		{
			if (id != null) {
				List<Category> categoryList = this.categoryService.getCategory(id);
				category = (Category) categoryList.get(0);

			}
		
			model.addAttribute("category", category);
		}
		return "submitCategoryForm";
	}

	@RequestMapping(params="action=addCategory")
	protected void processFormSubmission(
			@ModelAttribute("category") Category category,
			BindingResult result,
			SessionStatus status, 
			ActionRequest request,
			ActionResponse response,
			Model model) throws PortletException {
		
		new SubmitCategoryFormValidator().validate(category, result);
		if (result.hasErrors()) {
			response.setRenderParameter("action", "addCategory");
			return;
		}
		
			if (!result.hasErrors() && category != null){

	    	categoryService.processCategory(category);
	    		
	    	status.setComplete();

			response.setRenderParameter("action", "editCategories");
	   	}
	}	
}
