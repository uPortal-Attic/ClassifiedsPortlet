<!-- <jsp:directive.include file="/WEB-INF/jsp/include.jsp"/> -->

<link href="<c:url value="/css/feedback.css"/>" type="text/css" rel="stylesheet"/>
    
<h1><spring:message code="submit.Heading.success.title"/></h1>
   	
<p><spring:message code="submit.Heading.success.message"/></p>
   	
<portlet:renderURL portletMode="edit" var="editHeadingURL">
	<portlet:param name="action" value="editHeading" />
</portlet:renderURL>
     	
<p><a href="${editHeadingURL}"><spring:message code="submit.Heading.success.more"/></a></p>

