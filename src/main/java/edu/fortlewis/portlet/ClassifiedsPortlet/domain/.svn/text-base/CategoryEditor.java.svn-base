package edu.fortlewis.portlet.ClassifiedsPortlet.domain;
import java.lang.Long;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;

import java.beans.PropertyEditorSupport;

import edu.fortlewis.portlet.ClassifiedsPortlet.service.CategoryService;
import edu.fortlewis.portlet.ClassifiedsPortlet.web.MyAdsController;


public class CategoryEditor extends PropertyEditorSupport {
	
	 private static Log log = LogFactory.getLog(CategoryEditor.class);

     private CategoryService categoryService;
     
     public CategoryEditor(CategoryService categoryService) {
         this.categoryService = categoryService; }
     @Override
	public String getAsText() {
		
    	 if (getValue() == null) {
    		
             return "null";
          }
    	 
		if (this.getValue() != null && this.getValue() instanceof Category) {
			String s = ((Category)this.getValue()).getId().toString();
			 
			return ((Category)this.getValue()).getId().toString();
		}
		return this.getValue() == null ? "" : this.getValue().toString();
		
	}
     @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	
    	
    	if ((text != null) && (text.length() > 0)) 
    	{
    		Long category_id = Long.parseLong(text);
    	    		List<Category> categories = categoryService.getCategory(category_id);
    	            
    	        	Category cat =  categories.get(0);
    	        
    	        	setValue(cat);
    	} else {
    
    				setValue(null);
    	}
    	 
    }
}
