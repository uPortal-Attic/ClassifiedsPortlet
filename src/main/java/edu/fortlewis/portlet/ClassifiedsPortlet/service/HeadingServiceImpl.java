
package edu.fortlewis.portlet.ClassifiedsPortlet.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Ad;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Heading;
import edu.fortlewis.portlet.ClassifiedsPortlet.service.HeadingService;

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

	         	return session.createQuery(query)
	         	.list();
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

