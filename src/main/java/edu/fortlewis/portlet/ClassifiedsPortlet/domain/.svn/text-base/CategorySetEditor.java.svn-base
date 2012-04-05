package edu.fortlewis.portlet.ClassifiedsPortlet.domain;
import java.lang.Long;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;

import java.beans.PropertyEditorSupport;

import edu.fortlewis.portlet.ClassifiedsPortlet.service.CategoryService;
import edu.fortlewis.portlet.ClassifiedsPortlet.web.MyAdsController;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;


public class CategorySetEditor extends CustomCollectionEditor {
	
	 private static Log log = LogFactory.getLog(CategorySetEditor.class);

     private CategoryService categoryService;
     
     public CategorySetEditor(Class collectionType, CategoryService categoryService) {
    	 super(collectionType, false);

         this.categoryService = categoryService; 
        // System.out.println("CategorySetEditor initialized") ;	    

     }
     
     @Override
     protected Object convertElement(Object element) 
     {
    	 
    	 Category category = null;
    	 
    	 if (element instanceof Category)
    	 {
    		 //System.out.println("element is a Category object") ;
    	 }
    	 else
    	 {
    		 if (element != null) 
    		 {
    			 Long category_id = Long.parseLong(element.toString());
    			 List<Category> categories = categoryService.getCategory(category_id);
    			 category =  categories.get(0);
    		 }
    	 }
        	return category;
     }
}
