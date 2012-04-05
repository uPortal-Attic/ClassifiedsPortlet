package edu.fortlewis.portlet.ClassifiedsPortlet.service;

import java.util.List;

public interface IAdminGroupService {

	public List<String> getAdminRoles();
	public boolean containsRole(List<String>roles);
}
