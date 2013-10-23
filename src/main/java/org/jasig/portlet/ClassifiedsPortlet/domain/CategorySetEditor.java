/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlet.ClassifiedsPortlet.domain;
import java.lang.Long;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jasig.portlet.ClassifiedsPortlet.domain.Category;

import java.beans.PropertyEditorSupport;

import org.jasig.portlet.ClassifiedsPortlet.service.CategoryService;
import org.jasig.portlet.ClassifiedsPortlet.web.MyAdsController;

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
