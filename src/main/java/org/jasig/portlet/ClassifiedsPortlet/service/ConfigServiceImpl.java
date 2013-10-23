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
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.jasig.portlet.ClassifiedsPortlet.domain.Config;
import org.jasig.portlet.ClassifiedsPortlet.service.ConfigService;
import org.hibernate.Session;
import java.sql.SQLException;


public class ConfigServiceImpl extends HibernateDaoSupport implements ConfigService 
{
	public List<Config> getConfig()
	{ 
		 final String query = " from Config ";

	      HibernateCallback callback = new HibernateCallback() 
	     {
	         public Object doInHibernate(Session session) 
	         throws HibernateException,SQLException {
	         		
	         	return session.createQuery(query).setCacheable(true).list();	         }
	     };

	    return (List<Config>) getHibernateTemplate().execute(callback);

	}
	public List<Config> getConfig(Long id)
	{ 
		try {
			return (List<Config>) getHibernateTemplate().find(" from Category where ID = ?", id);
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
    public void processConfig(Config config) {
		try {
			getHibernateTemplate().saveOrUpdate(config);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
    
 }    	

