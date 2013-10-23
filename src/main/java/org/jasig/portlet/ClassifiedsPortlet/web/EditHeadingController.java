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
package org.jasig.portlet.ClassifiedsPortlet.web;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jasig.portlet.ClassifiedsPortlet.service.HeadingService;
import org.jasig.portlet.ClassifiedsPortlet.domain.Heading;
import javax.portlet.ActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("Edit")
public class EditHeadingController 
{
    private static Log log = LogFactory.getLog(EditHeadingController.class);
    
    @Autowired
    private HeadingService headingService = null;

      
    @RequestMapping(params="action=deleteHeading")
    public String showHeadingsAfterDelete(Model model, RenderRequest request, RenderResponse response)
    		throws PortletException {  	
     	
		List <Heading> headings = headingService.getHeadings();
    	model.addAttribute("headings", headings);
  	
		return("editHeadings");
  
    }
    
    @RequestMapping(params="action=deleteHeading")
    public void deleteHeading(@RequestParam(value="id", required=false) Long id,
    		@RequestParam(value="actionCode",  required=false) String actionCode,
			ActionResponse response)throws PortletException {
		
		if (actionCode.equals("delete")) {
			headingService.delete(id);
		}
	}
    
    @RequestMapping(params="action=editHeadings")
	public String editHeading( Model model, RenderRequest request) {
    	
    	
		List <Heading> headings = headingService.getHeadings();
		
    	model.addAttribute("headings", headings);
    	
		return("editHeadings");
	}
}
