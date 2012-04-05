<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/classifieds.css"/>" type="text/css"></link>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<div class="pageTitle">  <spring:message code="admin.edit.category.title"/> </div>
<portlet:renderURL portletMode="edit" var="submitCategory"><portlet:param name="action" value="addCategory" /></portlet:renderURL>
<div class="submitNewItem"> <a href="${submitCategory}">  <img alt="return" src="<c:url value="/images/add.png"/>" style="vertical-align: middle"/> <spring:message code="admin.edit.category.enter.category"/> </a></div> 	

<c:if test="${ not empty categories }">
	<div class="editColumn" >
		<div class="headingRow">
			<span class="delCol"><spring:message code="admin.edit.delete"/></span>
			<span class="editCol"><spring:message code="admin.edit.edit"/></span>
			<span class="adsCol"><spring:message code="admin.edit.ads"/></span>
			<span class="nameCol"><spring:message code="admin.edit.name"/></span>
			</div>
					
	    	<c:forEach items="${categories}" var="category" varStatus="lineInfo">

	    	 <c:choose>
  			 	<c:when test="${lineInfo.count % 2 == 0}">
    				<div class="greyLine"> 
   			 	</c:when>   
   			 	<c:otherwise>       
   					<div class="whiteLine">   
   			 	</c:otherwise>
 			</c:choose>
 	 		
 			  <c:choose>
  				 	<c:when test="${category.adcntAll == 0}">
 						<span  class="delCol">  		
    							<a href="<portlet:actionURL portletMode="edit"><portlet:param name="action" value="deleteCategory"/>
      							<portlet:param name="actionCode" value="delete"/>
      							<portlet:param name="id" value="${ category.id }"/></portlet:actionURL>"
      							title="<spring:message code='admin.edit.delete.category'/>">
      							<img onclick="return confirm('<spring:message code="admin.edit.category.confirm.delete"/>')" 
      									alt="<spring:message code="admin.edit.delete"/>"  
      									style="vertical-align: middle;" src="<c:url value="/images/delete.png"/>"/>
      							</a>
      					</span>
     				</c:when>
	    		
	    		<c:otherwise> 
	    				<span  class="delColGreyed">  		
    						<a href=""
      							title="<spring:message code='admin.edit.category.contains.ads'/>">
      							<img alt="delete"  style="vertical-align: middle;" src="<c:url value="/images/delete.png"/>"/>
      						</a>
      					</span>
	    		</c:otherwise>
 			    </c:choose>
   						
  				<span class="editCol">
  				<a href="<portlet:renderURL portletMode="edit"><portlet:param name="action" value="addCategory"/>
    					<portlet:param name="actionCode" value="edit"/>
    					<portlet:param name="id" value="${ category.id }"/></portlet:renderURL>"
     						title="<spring:message code='admin.edit.edit.category'/>">
      						<img alt="edit" style="vertical-align: middle;" src="<c:url value="/images/edit.png"/>"/>
 				</a>
				</span>
 				<span class="adsCol"><c:out value="${category.adcntAll}"/></span>
 				<span class="nameCol"><c:out value="${category.name}"/> </span>

				</div>
	    		
  			</c:forEach>
	</div>  			
 </c:if>
 <div style="clear:both"></div>
 <div class="footer">
 <a href="<portlet:renderURL portletMode="edit">
        <portlet:param name="action" value="adminMenu"/></portlet:renderURL>">
          <img alt="return" src="<c:url value="/images/arrow_left.png"/>" style="vertical-align: middle"/>
        <spring:message code="admin.return.to.home"/></a>
 </div>
 