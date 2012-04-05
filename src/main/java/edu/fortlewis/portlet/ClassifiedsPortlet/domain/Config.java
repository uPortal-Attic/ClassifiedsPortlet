
package edu.fortlewis.portlet.ClassifiedsPortlet.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.*;
import java.io.Serializable;

@Entity
@Table(name="FLC_CONFIG")

public class Config implements Serializable
{
	private Long  id  = new Long(-1);

	public String help_Title;
	public String help_Text;

	public String policy_Title;
	public String policy_Text;

	
	
	public Config()
	{
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
	@Column(name="help_Title", length=256)
	public String getHelp_Title()
	{
		return this.help_Title;
	}
	public void setHelp_Title(String help_Title)
	{ 
		this.help_Title = help_Title;
	}
	@Column(name="help_Text", length=2550)
	public String getHelp_Text()
	{
		return help_Text;
	}
	public void setHelp_Text(String help_Title)
	{
		this.help_Text = help_Title;
	}
	@Column(name="policy_Title", length=256)
	public String getPolicy_Title()
	{
		return this.policy_Title;
	}
	
	public void setPolicy_Title(String policy_Title)
	{ 
		this.policy_Title = policy_Title;
	}
	@Column(name="policy_Text", length=2550)
	public String getPolicy_Text()
	{
		return policy_Text;
	}
	public void setPolicy_Text(String policy_Text)
	{
		this.policy_Text = policy_Text;
	}
	
	
	
}

