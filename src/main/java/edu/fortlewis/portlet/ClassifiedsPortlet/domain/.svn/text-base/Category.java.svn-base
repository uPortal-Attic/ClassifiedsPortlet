
package edu.fortlewis.portlet.ClassifiedsPortlet.domain;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.Serializable;
import org.hibernate.annotations.*;

@Entity
@Table(name="FLC_CATEGORIES")
@FilterDef(name="VALID_ADS",
		parameters=@ParamDef( name="categoryRecordFilterParam", type="date" ) )

//public class Category implements Comparable<Category>
public class Category implements Serializable 
{

	
	private Long id = new Long(-1);
	private String name;
	@Transient
	public Set<Heading> headings = new LinkedHashSet<Heading>();
	@Transient
	public Set<Ad> ads = new LinkedHashSet<Ad>();
	
	@Transient
	public int adcnt = 0;
	@Transient
	public int adcntAll = 0;

	public Category()
	{
	}
	

@Transient
  
public int getAdcntAll()
{
	 
	return ads.size();
}
@Transient
public void setAdcntAll(int adcntAll)
{
	
	this.adcntAll = adcntAll;

}
@Transient
	public int getAdcnt()
	{

		return ads.size();
	}
@Transient
	public void setAdcnt(int adcnt)
	{
		
		this.adcnt = adcnt;

	}
		
	@OneToMany(mappedBy="category",
			targetEntity=Ad.class,
			fetch=FetchType.EAGER)
			
	@Filter(name="VALID_ADS", condition = "startDate <= :categoryRecordFilterParam and endDate >= :categoryRecordFilterParam" )		
	@OrderBy("startDate desc" )
 
	public Set<Ad> getAds() 
	{

		return ads;
	}
	public void setAds(Set<Ad> ads)
	{

		 this.ads=ads;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() 
	{
		return this.id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return this.name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public Category(String name)
	{
		this.name = name;
	}
	@Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((name == null) ? 0 : name.hashCode());
	        return result;
	    }
	@Override
	    public boolean equals(Object obj) {
	        
	        
	        if (this == obj)
	            return true;
	        if (obj.hashCode() == 0)
	        	return false;
	        if (obj == null)
	            return false;
	        Category other = (Category) obj;
	        
	    //    System.out.println("comparing selected category: " + other.getName() );
	        if (name == null) {
	            if (other.getName() != null)
	                return false;
	        } else if (!name.equals(other.getName()))
	            return false;
	            
	        return true;
	    }
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="FLC_HEADINGCATEGORY", 
			joinColumns=@JoinColumn(name="CATEGORY_ID"),
    		inverseJoinColumns=@JoinColumn(name="HEADING_ID")
	)

	public Set<Heading> getHeadings() 
	{
		return headings;
	}
	public void setHeadings(Set<Heading> headings)
	{
		this.headings=headings;
		
	}
	
}

