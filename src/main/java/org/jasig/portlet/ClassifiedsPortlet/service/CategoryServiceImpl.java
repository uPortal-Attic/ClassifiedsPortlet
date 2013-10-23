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

import java.util.List;
import java.util.Date;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.jasig.portlet.ClassifiedsPortlet.domain.Ad;
import org.jasig.portlet.ClassifiedsPortlet.domain.Category;
import org.jasig.portlet.ClassifiedsPortlet.domain.Heading;
import org.jasig.portlet.ClassifiedsPortlet.service.CategoryService;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.hibernate.annotations.*;

public class CategoryServiceImpl extends HibernateDaoSupport implements CategoryService 
{
	 public void delete(Long id)
		{
			try {
				Category category = (Category)getHibernateTemplate().load(Category.class, id);
				getHibernateTemplate().delete(category);
			} catch (HibernateException ex) {
				throw convertHibernateAccessException(ex);
			}
		}
	 


    public List<Category> getCategories() 
    {
    	try
    	{
     	 String query = "from Category order by name";
    	    	
		return (List<Category>) getHibernateTemplate()
				.find(query);
     	 
    	} catch (HibernateException ex) {
    		throw convertHibernateAccessException(ex);
    	}
    }

    public List<Object> getCategoriesWithCounts() 
    {
    	final java.util.Date today = new java.util.Date();
		final java.sql.Date sqlToday = new java.sql.Date(today.getTime());
		
    	final String query = "select FLC_Categories.name, FLC_Categories.id, count(FLC_AdS.ID) from FLC_Categories, FLC_Ads where FLC_Categories.id = FLC_Ads.category_id(+) and FLC_Ads.STARTDATE(+) <= ? and FLC_Ads.ENDDATE(+) >= ? group by FLC_Categories.category_id,FLC_Categories.name order by FLC_Categories.name";
    	HibernateCallback callback = new HibernateCallback() 
    	{
            public Object doInHibernate(Session session) 
                throws HibernateException,SQLException {
                return session.createSQLQuery(query).setDate(0, sqlToday).setDate(1, sqlToday).list();
            }
        };        
        return (List<Object>) getHibernateTemplate().execute(callback);
    }
    
    public List<Category> getCategory(Long id) 
	{
		
	    final String query = "from Category where ID = ? ";
	    final Long lCategory = id;
	    
	    HibernateCallback callback = new HibernateCallback() 
	    {
	        public Object doInHibernate(Session session) 
	        throws HibernateException,SQLException {
         		org.hibernate.Filter filter = session.enableFilter("VALID_ADS");
        		filter.setParameter("categoryRecordFilterParam", new Date());
	        	return session.createQuery(query)
	        	.setLong(0, lCategory)
	        	.list();
	        }
	    };
	    
	   return (List<Category>) getHibernateTemplate().execute(callback);
	}

  
    
    public void processCategory(Category category) {
		try {
			getHibernateTemplate().saveOrUpdate(category);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
 }    	

