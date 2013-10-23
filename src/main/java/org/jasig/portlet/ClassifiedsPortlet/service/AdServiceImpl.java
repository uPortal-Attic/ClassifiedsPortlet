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

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.jasig.portlet.ClassifiedsPortlet.domain.Ad;
import org.jasig.portlet.ClassifiedsPortlet.web.EditHeadingController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.sql.Date;

public class AdServiceImpl extends HibernateDaoSupport implements AdService{
	private static Log log = LogFactory.getLog(AdServiceImpl.class);
	
	public void deleteExpiredAds() {
		List<Ad> result = null;
		
		try {
			result = getHibernateTemplate().find("from Ad where endDate < current_timestamp()");
			if (result == null || result.size() == 0) {
				return;
			}
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		
		if (result.size() > 0) {
			log.info("Ad groomer Deleting "+result.size()+" expired ads.");
			
			try {
				for (Ad a: result) {
					getHibernateTemplate().delete(a);
					getHibernateTemplate().getSessionFactory().evict(Ad.class);
				}
				getHibernateTemplate().flush();
			} catch (HibernateException ex) {
				throw convertHibernateAccessException(ex);
			}
		}
		
	}

	public void delete(Long id)
	{
		try {
			Ad ad = (Ad)getHibernateTemplate().load(Ad.class, id);
			getHibernateTemplate().delete(ad);
			getHibernateTemplate().getSessionFactory().evict(Ad.class);
			getHibernateTemplate().flush();
			
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
	
		
	
	public List<Ad> getLatestAds(int nMaxResults) 
	{
		final java.util.Date today = new java.util.Date();
		final java.sql.Date sqlToday = new java.sql.Date(today.getTime());
		
	    final String query = "from Ad where STARTDATE <= ? and ENDDATE >= ? order by startDate desc  ";
	    final int maxResults = nMaxResults;
	    
	    HibernateCallback callback = new HibernateCallback() 
	    {
	        public Object doInHibernate(Session session) 
	        throws HibernateException,SQLException {
	        	return session.createQuery(query)
				.setCacheable(true)
	        	.setDate(0, sqlToday)
	        	.setDate(1, sqlToday)
	        	.setMaxResults(maxResults)
	        	.list();
	        }
	    };
	    
	   return (List<Ad>) getHibernateTemplate().execute(callback);
	}
	
	 // Gets all active Ads for the Admin to edit/delete
    public List<Ad> getAdsForAdmin() {
            try {
		return (List<Ad>) getHibernateTemplate().find("from Ad order by startDate desc ");
	} catch (HibernateException ex) {
		throw convertHibernateAccessException(ex);
	}
    }


	public List<Ad> getAdsByUserId(String userid)
	{
		try {
			return (List<Ad>) getHibernateTemplate().find("from Ad where USERID = ? order by startDate desc ", userid);
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
	
	public List<Ad> getAd(Long id)
	{ 
		try {
			return (List<Ad>) getHibernateTemplate().find(" from Ad where ID = ? ", id);
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
		
	public void processAd(Ad ad) {
		try {
			getHibernateTemplate().saveOrUpdate(ad);
			getHibernateTemplate().getSessionFactory().evict(Ad.class);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
}
