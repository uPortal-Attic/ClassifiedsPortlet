<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/classifieds.css"/>" type="text/css"></link>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<div class="pageTitle">  <spring:message code="myads.title"/> </div>

<portlet:renderURL var="submitAdURL"><portlet:param name="action" value="addAd" /></portlet:renderURL>
<div class="submitNewItem"> <a href="${submitAdURL}"> <img alt="add Ad" src="<c:url value="/images/add.png"/>" style="vertical-align: middle"/> <spring:message code="myads.place.new.ad"/> </a></div> 	
 

<c:if test="${ not empty ads }">
  
	<div class="editColumn" >
		<div class="headingRow"><span class="delCol">Delete</span><span class="editCol">Edit</span><span class="dateCol">Date</span><span class="nameCol">Title</span></div>
  
 	    <c:forEach items="${ads}" var="ad" varStatus="lineInfo">

	    	 <c:choose>
  			 	<c:when test="${lineInfo.count % 2 == 0}">
    				<div class="greyLine"> 
   			 	</c:when>   
   			 	<c:otherwise>       
   					<div class="whiteLine">   
   			 	</c:otherwise>
 			</c:choose>
  
  			<span  class="delCol">  		
    			<a href="<portlet:actionURL><portlet:param name="action" value="deleteAd"/>
      				<portlet:param name="actionCode" value="delete"/>
      				<portlet:param name="id" value="${ ad.id }"/></portlet:actionURL>"
      				title="Delete ad">
      				<img onclick="return confirm('<spring:message code="myads.confirm.delete"/>')"	alt="delete" style="vertical-align: middle;"  src="<c:url value="/images/delete.png"/>"/>
      			</a>
      		</span>
  
  			<span class="editCol">
  				<a href="<portlet:renderURL><portlet:param name="action" value="addAd"/>
      					<portlet:param name="actionCode" value="edit"/>
      					<portlet:param name="id" value="${ ad.id }"/></portlet:renderURL>"
      					title="Edit ad">
      				<img alt="edit" style="vertical-align: middle;" src="<c:url value="/images/edit.png"/>"/>
      			</a>
      			
      		</span>
      			
 				<fmt:formatDate var="createDate" value="${ad.createDate}" pattern="MM-dd-yyyy"/> 
				<span class="dateCol"><c:out value="${createDate}"/>  </span>
				<span class="nameCol"><c:out value="${ad.title}"/> - <c:out value="${ad.price}"/> </span>
				</div>
  
  			</c:forEach>
   	</div>
</c:if>
<div style="clear:both"></div>

<div class="footer">
 	<a href="<portlet:renderURL/>">
 	  <img alt="return" src="<c:url value="/images/arrow_left.png"/>" style="vertical-align: middle"/>
 	<spring:message code="myads.return.to.home"/></a>
</div>
 
 
 