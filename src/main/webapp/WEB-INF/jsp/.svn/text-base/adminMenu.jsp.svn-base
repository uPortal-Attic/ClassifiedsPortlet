<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/classifieds.css"/>" type="text/css"></link>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

	<portlet:renderURL portletMode="edit" var="headings">
    	<portlet:param name="action" value="editHeadings" />
    </portlet:renderURL>

	<portlet:renderURL portletMode="edit" var="categories">
    	<portlet:param name="action" value="editCategories" />
    </portlet:renderURL>
    
   
	<portlet:renderURL portletMode="edit" var="configure">
    	<portlet:param name="action" value="editConfig" />
    </portlet:renderURL>
  
    <div class="pageTitle">  <spring:message code="admin.edit.menu.title"/> </div>
   	
   	<p><a href="${headings}">
   	<img alt="edit" style="vertical-align: middle;" src="<c:url value="/images/edit.png"/>"/>
   	<spring:message code="admin.edit.menu.headings"/>
   	</a></p>
	<p><a href="${categories}">
	<img alt="edit" style="vertical-align: middle;" src="<c:url value="/images/edit.png"/>"/>
	<spring:message code="admin.edit.menu.categories"/>
	</a></p>
	    
	<p><a href="${configure}">
	<img alt="edit" style="vertical-align: middle;" src="<c:url value="/images/edit.png"/>"/>
	<spring:message code="admin.edit.menu.help"/>
	</a></p>
	
	
	 <portlet:renderURL portletMode="view" var="mainMenu"> </portlet:renderURL>
	
	<p><a href="${mainMenu}"><img alt="return" src="<c:url value="/images/arrow_left.png"/>" style="vertical-align: middle"/>
        <spring:message code="admin.return.to.main.menu"/></a></p>
	
	

	