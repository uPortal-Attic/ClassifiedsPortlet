
package edu.fortlewis.portlet.ClassifiedsPortlet.domain;

import java.util.Date;
import javax.persistence.*;

import java.io.Serializable;


import org.apache.commons.lang.time.DateUtils;
import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;
@Entity
@Table(name="FLC_ADS")

public class Ad  implements Serializable
{
	
	private Long id = new Long(-1);
	@Transient	
	private Category category;

	private String userid;
	private Date createDate = new Date() ;
	private Date startDate = new Date();

	private Date endDate = DateUtils.addDays(startDate, 30);
	
	private String title;
	private String description;

	private String price;
	private int status;
	private int timesViewed;
	
	private boolean policyAccepted;
	
		
	public Ad()
	{
		
	}
	public boolean hasId() {
		return (this.id != -1);
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
	
	public Date getStartDate() 
	{
		return this.startDate;
	}
	public void setStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}
	
	public Date getEndDate() 
	{
		return this.endDate;
	}
	public void setEndDate(Date endDate) 
	{
		this.endDate = endDate;
	}
		
	public Date getCreateDate() 
	{
		return this.createDate;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}	

	public String getPrice() 
	{
		return this.price;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}
	public String getUserid() 
	{
		return this.userid;
	}
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}
	public int getStatus() 
	{
		return this.status;
	}
	public void setStatus(int status) 
	{
		this.status = status;
	}
	public int getTimesViewed() 
	{
		return this.timesViewed;
	}
	public void setTimesViewed(int timesViewed) 
	{
		this.timesViewed = timesViewed;
	}
	
	
	@ManyToOne 
	@JoinColumn(name="category_id")
	public Category getCategory() 
	{
		return this.category;
	}
	public void setCategory(Category category) 
	{
		this.category = category;
	}
		
	public String getTitle() 
	{
		return this.title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	@Column(name="description", length=2056)
	public String getDescription() 
	{
		return this.description;
	}
	public void setPolicyAccepted(boolean policyAccepted) 
	{
		this.policyAccepted = policyAccepted;
	}
	public boolean getPolicyAccepted() 
	{
		return this.policyAccepted;
	}
}

