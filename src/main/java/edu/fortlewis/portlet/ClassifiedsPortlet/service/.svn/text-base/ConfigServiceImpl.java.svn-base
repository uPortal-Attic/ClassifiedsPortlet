
package edu.fortlewis.portlet.ClassifiedsPortlet.service;

import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Config;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.ConfigService;


public class ConfigServiceImpl extends HibernateDaoSupport implements ConfigService 
{
	public List<Config> getConfig()
	{ 
		try {
			return (List<Config>) getHibernateTemplate().find(" from Config");
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
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

