package edu.fortlewis.portlet.ClassifiedsPortlet.web;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.AdService;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.CategoryService;
import javax.portlet.ActionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("EDIT")

public class EditCategoryController 
{
    private static Log log = LogFactory.getLog(EditCategoryController.class);
    @Autowired 
     private CategoryService categoryService;
    @Autowired 
     private AdService adService;

    @RequestMapping(params="action=deleteCategory")
    public String showHeadingsAfterDelete(Model model, RenderRequest request, RenderResponse response)
    		throws PortletException {  	
     	
		List <Category> categories = categoryService.getCategories();
    	model.addAttribute("categories", categories);
    	
		return("editCategories");
     }
	
    @RequestMapping(params="action=deleteCategory")
    public void deleteAd(@RequestParam(value="id", required=false) Long id,
    		@RequestParam(value="actionCode",  required=false) String actionCode,
			ActionResponse response)throws PortletException {
		
		if (actionCode.equals("delete")) {
			categoryService.delete(id);
		}
	}
    
    @RequestMapping(params="action=editCategories") //render phase
    public String showCategories(Model model, RenderRequest request, RenderResponse response)
    throws PortletException {  	
      	
		List <Category> categories = categoryService.getCategories();
    	model.addAttribute("categories", categories);
    	
		return("editCategories");
    }

}
