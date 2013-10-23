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
package org.jasig.portlet.ClassifiedsPortlet.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.jasig.portlet.ClassifiedsPortlet.domain.Ad;
import org.jasig.portlet.ClassifiedsPortlet.domain.Category;
import org.jasig.portlet.ClassifiedsPortlet.domain.Heading;
import org.jasig.portlet.ClassifiedsPortlet.service.HeadingService;

public class HeadingServiceImpl extends HibernateDaoSupport implements HeadingService 
{
	 public List<Heading> getHeadings() 
	 {
	 	
	     final String query = "from Heading order by headingname ";
	     
	     HibernateCallback callback = new HibernateCallback() 
	     {
	         public Object doInHibernate(Session session) 
	         throws HibernateException,SQLException {
	         		org.hibernate.Filter filter = session.enableFilter("VALID_ADS");
	         		filter.setParameter("categoryRecordFilterParam", new Date());

	         	return session.createQuery(query).setCacheable(true).list();
	         }
	     };
	     
	    return (List<Heading>) getHibernateTemplate().execute(callback);
	 }
	 

    
    public List<Heading> getHeading(Long id)
	{ 
    	try
    	{
		return (List<Heading>) getHibernateTemplate().find(" from Heading where ID = ?", id);
		
    	} catch (HibernateException ex) {
    		throw convertHibernateAccessException(ex);
    	}
	}
    
    public void processHeading(Heading heading) {
		try {
			getHibernateTemplate().saveOrUpdate(heading);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
    
    public void delete(Long id)
	{
		try {
			Heading heading = (Heading)getHibernateTemplate().load(Heading.class, id);
			getHibernateTemplate().delete(heading);
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
    
 }    	

