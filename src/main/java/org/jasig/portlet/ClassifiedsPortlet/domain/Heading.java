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

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="FLC_HEADING")
@Cache(region="heading", usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)


public class Heading  implements Serializable 
{
    private Long  id = new Long(-1);
	public String headingname;

	@Transient
	public Set<Category> categories =  new LinkedHashSet<Category>();
	
	@Transient
	public int categoryCnt = 0;

	public Heading()
	{
	}
	@Transient
	public int getCategoryCnt()
	{
		return categories.size();
	}
	@Transient
	public void setCategoryCnt(int size)
	{
		categoryCnt = size;
	}
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@Cache(region="category",usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JoinTable(
			name="FLC_HEADINGCATEGORY", 
			joinColumns=@JoinColumn(name="HEADING_ID"),
    		inverseJoinColumns=@JoinColumn(name="CATEGORY_ID")
	)
	@OrderBy("name")
	public Set<Category> getCategories() 
	{

		return categories;
	}
	public void setCategories(Set<Category> categories)
	{

		this.categories=categories;
		
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
	
	
	public String getHeadingname() 
	{
		return this.headingname;
	}
	public void setHeadingname(String headingname) 
	{
		this.headingname = headingname;
	}
	public Heading(String headingname)
	{
		this.headingname = headingname;
	}
	
	
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((headingname == null) ? 0 : headingname.hashCode());
	        return result;
	    }


	    public boolean equals(Object obj) {
	        
	        
	        if (this == obj)
	            return true;
	        if (obj.hashCode() == 0)
	        	return false;
	        if (obj == null)
	            return false;
	        Heading other = (Heading) obj;
	        
	      //  System.out.println("comparing selected heading: " + other.getHeadingname() );
	        if (headingname == null) {
	            if (other.getHeadingname() != null)
	                return false;
	        } else if (!headingname.equals(other.getHeadingname()))
	            return false;
	            
	        return true;
	    }
	
}

