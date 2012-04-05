<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/classifieds.css"/>" type="text/css"></link>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<div class="pageTitle">  <spring:message code="admin.edit.heading.title"/> </div>
<portlet:renderURL  portletMode="edit" var="submitHeading"><portlet:param name="action" value="addHeading" /> </portlet:renderURL>
<div class="submitNewItem"> <a href="${submitHeading}">        <img alt="add heading" src="<c:url value="/images/add.png"/>" style="vertical-align: middle"/> <spring:message code="admin.edit.heading.enter.heading"/> </a></div>
  
<c:if test="${ not empty headings }">

	<div class="editColumn" >
		<div class="headingRow"><span class="delCol">Delete</span><span class="editCol">Edit</span><span class="catCol">#Categories</span><span class="nameCol">Name</span></div>

	   	<c:forEach items="${headings}" var="heading" varStatus="lineInfo">

	    	 <c:choose>
  			 	<c:when test="${lineInfo.count % 2 == 0}">
    				<div class="greyLine"> 
   			 	</c:when>   
   			 	<c:otherwise>       
   					<div class="whiteLine">   
   			 	</c:otherwise>
 			</c:choose>
 
			  <c:choose>
  				 	<c:when test="${heading.categoryCnt == 0}">
 						<span  class="delCol">  		
    							<a href="<portlet:actionURL portletMode="edit"><portlet:param name="action" value="deleteHeading"/>
      							<portlet:param name="actionCode" value="delete"/>
      							<portlet:param name="id" value="${ heading.id }"/></portlet:actionURL>"
      							title="<spring:message code='admin.edit.delete.heading'/>">
      							<img onclick="return confirm('<spring:message code="admin.edit.heading.confirm.delete"/>')" 
      								alt="<spring:message code="admin.edit.delete"/>"   
      								style="vertical-align: middle;"  src="<c:url value="/images/delete.png"/>"/>
      							</a>
      					</span>
     				</c:when>
	    		
	    		<c:otherwise> 
	    				<span  class="delColGreyed">  		
    						<a href=""
      								title="<spring:message code='admin.edit.heading.contains.categories'/>">
      							<img alt="delete"  style="vertical-align: middle;" src="<c:url value="/images/delete.png"/>"/>
      						</a>
      					</span>
	    		</c:otherwise>
 			    </c:choose>
 
			<span class="editCol">
  				<a href="<portlet:renderURL portletMode="edit"><portlet:param name="action" value="addHeading"/>
    					<portlet:param name="actionCode" value="edit"/>
    					<portlet:param name="id" value="${ heading.id }"/></portlet:renderURL>"
     						title="<spring:message code='admin.edit.edit.heading'/>">
      						<img alt="edit" style="vertical-align: middle;" src="<c:url value="/images/edit.png"/>"/>
 				</a>
				</span>
 				<span class="catCol"><c:out value="${heading.categoryCnt}"/></span>
 				<span class="nameCol"><c:out value="${heading.headingname}"/> </span>

				</div>
	    		
  		</c:forEach>
	</div>  			
 </c:if>
 <div style="clear:both"></div>
 <div class="footer">
 <a href="<portlet:renderURL portletMode="edit">
        <portlet:param name="action" value="adminMenu"/></portlet:renderURL>">
        <img alt="return" src="<c:url value="/images/arrow_left.png"/>" style="vertical-align: middle"/>
        <spring:message code="admin.return.to.home"/> </a>
 </div>
 
 