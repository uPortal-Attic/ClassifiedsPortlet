package edu.fortlewis.portlet.ClassifiedsPortlet.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ServletContextAware;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class AdminGroupService implements ServletContextAware, IAdminGroupService {
	
	final private String PORTLET_XML_PATH = "/WEB-INF/portlet.xml";
	private Document doc;
	private List<String> roles;
	private static Log log = LogFactory.getLog(AdminGroupService.class);
	private ServletContext context;
	
	public boolean containsRole(List<String>roles)
	{
		int i = roles.size();
		
		for (String role : roles){
			if (this.roles.contains(role))
					return true;
		}
			
		return false;
	}
	
	public void init() {
		parseXml();
		
		if (doc != null) {
			String roleNameCandidate;
			roles = new ArrayList<String>();

			NodeList roleSections = doc.getElementsByTagName("security-role-ref");
			for (int i=0; i<roleSections.getLength(); i++) {

				if (roleSections.item(i).hasChildNodes()) {
					NodeList roleNames = roleSections.item(i).getChildNodes();
					for (int j=0; j<roleNames.getLength(); j++) {

						if (roleNames.item(j).getNodeName().equalsIgnoreCase("role-name")) {

							roleNameCandidate = roleNames.item(j).getTextContent();
							if (!roles.contains(roleNameCandidate)) {
								roles.add(roleNameCandidate);
							}
						}
					}
					roleNames = null;
				}
			}
			
			Collections.sort(roles);
			log.info("Admin roles found: ["+getDisplayRoles()+"]");
		}
		else {
			log.error("Error parsing file: "+PORTLET_XML_PATH+".");
		}
	}
		
	public List<String> getAdminRoles() {
		return roles;
	}
	
	private void parseXml() {
			
		URL portletXmlUrl = null;
		try {
			portletXmlUrl = context.getResource(PORTLET_XML_PATH);
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
		} finally {
			
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				InputSource xmlInp = new InputSource(portletXmlUrl.openStream());
				DocumentBuilder dbl = dbf.newDocumentBuilder();
				doc = dbl.parse(xmlInp);
			} catch (ParserConfigurationException e) {
				log.error(e.getMessage());
			} catch (java.io.IOException e) {
				log.error(e.getMessage());
	        } catch (org.xml.sax.SAXException e) {
				log.error(e.getMessage());
	        } catch (Exception e) {
	        	log.error(e.getMessage());
	        } finally {
	        	log.debug("Finished parsing "+PORTLET_XML_PATH+".");
	        }
		}
	}
	private String getDisplayRoles() {
		StringBuffer sb = new StringBuffer();
		for (String role: roles) {
			sb.append(role+", ");
		}
		return sb.toString();
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
}
