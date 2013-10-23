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

import org.jasig.portlet.ClassifiedsPortlet.domain.Heading;
import org.jasig.portlet.ClassifiedsPortlet.domain.Category;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

import javax.portlet.RenderResponse;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JSONObject;

import javax.portlet.PortletMode;



public class JsonForMenu {
	
	JSONObject object = new JSONObject();

	public JsonForMenu(List <Heading> headings, RenderResponse response) throws PortletException, IOException {
		
		Iterator<Heading> hIterator = headings.iterator();
		JSONArray headingArray = new JSONArray();

	    	while (hIterator.hasNext()) {

			headingArray.add( loadHeading(hIterator.next(), response));
		} 
		object.put("headings", headingArray);
	
	}
	
	public String toString()
	{
		return this.object.toString();
	}
	
	 public JSONObject loadHeading(Heading heading, RenderResponse response) throws PortletException, IOException  {

	    	JSONObject object = new JSONObject();
	    	
	        JSONArray array = loadCategories(heading, response);

	    	object.put("categories", array); 
	    	object.put("heading", heading.getHeadingname());

	    	return object;
	    }

	    public JSONArray loadCategories(Heading heading, RenderResponse response) throws PortletException, IOException  {
	    	
	    	Set<Category> categories = heading.getCategories();
	    	Iterator<Category> cIterator = categories.iterator();

	    	JSONArray array = new JSONArray();

	    	while (cIterator.hasNext()) {
	    		JSONObject object = loadCategory(cIterator.next(), response);
	    		array.add(object);
	    	} 
	       				
	    	return array;
	    }

	    public JSONObject loadCategory(Category category, RenderResponse response) throws PortletException, IOException  {

	    	javax.portlet.PortletURL sUrl = response.createRenderURL();
	    	
	    	sUrl.setPortletMode(PortletMode.VIEW);
	    	sUrl.setParameter("action", "selectedCategory");
	    	sUrl.setParameter("selectedCategory",Long.toString(category.getId()));

	      	JSONObject object = new JSONObject();

	    	object.put("category",category.getName() );
	    	object.put("url",sUrl.toString());
	    	object.put("Adcnt", Integer.toString(category.getAds().size()) );
	    	object.put("id",Long.toString(category.getId()) );
	  
	    	return object;
	    }

	
}
