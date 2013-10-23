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
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import java.util.*;
import java.io.Serializable;

@Entity
@Table(name="FLC_CONFIG")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

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

