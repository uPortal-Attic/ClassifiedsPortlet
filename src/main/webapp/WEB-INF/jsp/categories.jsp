<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/classifieds.css"/>" type="text/css"></link>
<link rel="stylesheet" href="<c:url value="/css/pager.css"/>" type="text/css"></link>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<script src="/ResourceServingWebapp/rs/jquery/1.3.1/jquery-1.3.1.min.js" type="text/javascript"></script>
<script src="/ResourceServingWebapp/rs/jqueryui/1.6rc6/jquery-ui-1.6rc6.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="/ResourceServingWebapp/rs/jqueryui/1.6rc6/theme/smoothness/ui.all.css" type="text/css"></link>

<script type="text/javascript" src="<c:url value="/scripts/jquery.pager.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/scripts/toolbar.js"/>"></script> 


 <c:if test="${ not empty configs }">
   	<c:forEach items="${configs}" var="config" >
   		<div id="<portlet:namespace/>dialog"  class="hidden" title="${config.help_Title}">${config.help_Text}</div>
 	</c:forEach>
 </c:if> 
 
<c:set var="n"><portlet:namespace/></c:set>


<div>
<ul id="sddm" >
   
    <li><a href="<portlet:renderURL><portlet:param name="action" value="view"/></portlet:renderURL>"
					 id="<c:out value=""/>" 
        onmouseover="mopen('<portlet:namespace/>m2')" 
        onmouseout="mclosetime()"><spring:message code="menu.Home"/></a>
        <div id="<portlet:namespace/>m2" class="dropdown"
            onmouseover="mcancelclosetime()" 
            onmouseout="mclosetime()">


	   
	       	<a href="<portlet:renderURL><portlet:param name="action" value="selectedCategory"/>
	       	<portlet:param name="selectedCategory" value="-1"/></portlet:renderURL>"
					 id="-1"> <spring:message code="menu.LatestAds"/> (<c:out value="${latestAdsCnt}"/>)</a>
					 
        </div>
    </li>
    
    <portlet:renderURL var="myAdsURL"><portlet:param name="action" value="MyAds" /></portlet:renderURL>
    
  <c:if test="${userType != 'isGuest'}">
      <li><a href="${myAdsURL}">My Posts</a></li>
	</c:if> 
    <li><a href="#" id="<portlet:namespace/>help">Help</a></li>
</ul>
</div>
 <div style="clear:both"></div>
  
  <div><p></p></div>
  
  <div id="categoryHeading"><p >
   	<c:choose>
  		<c:when test="${ not empty categoryHeading }">
  			<c:out value="${categoryHeading}"/> 
  		</c:when>
  		<c:otherwise>
  			<spring:message code="menu.LatestAds"/>
  		</c:otherwise>
  	 </c:choose>
   </p></div>
 
 <div id="<portlet:namespace/>ads" >
       
  <c:if test="${ not empty ads }">
  
   	<c:forEach items="${ads}" var="ad" varStatus="lineInfo">
   		<fmt:formatDate var="startDate" value="${ad.startDate}" pattern="MMMM d"/>
   		
   		
   		<c:choose>
   			<c:when test="${lineInfo.count % 2 != 0}">
   				<c:set var="adClass">adGrey</c:set> 
   			</c:when>
   			<c:otherwise>
   				<c:set var="adClass">adWhite</c:set> 
   			</c:otherwise>
 		</c:choose>
   		
   				
  		<c:choose>
   			<c:when test="${lineInfo.count > maxAdsPerPage}">
   				<c:set var="adStyle">display: none</c:set> 
   			</c:when>
   			<c:otherwise>
   				<c:set var="adStyle">display: block</c:set> 
   			</c:otherwise>
   		</c:choose>	
   				
   		<div id="<portlet:namespace/>ad" class="${adClass}" style="${adStyle}" >
   			<p id="adHeader"    >
 	    		<span class='underline' > <c:out value="${ad.title}"/> - ${ startDate } </span>
			</p> 
			<p id="adDetail" style=" display: none " > <c:out value="${ad.description}"/><br>
				<c:if test="${not empty ad.price}">	
					<b> <spring:message code="categories.price"/>:<c:out value="${ad.price}"/> </b> </c:if>
			</p>
		</div>

 	</c:forEach>
 </c:if> 
 
</div>

 <c:if test="${ empty ads }">
    	<p>No 
    	
    	<c:choose>
  		<c:when test="${ not empty categoryHeading }">
  			<c:out value="${categoryHeading}"/> 
  		</c:when>
  		<c:otherwise>
  			<spring:message code="menu.LatestAds"/>
  		</c:otherwise>
  	 	</c:choose>
  	 	
    	 ads found. </p>
</c:if>
 
<p> <div id="<portlet:namespace/>pager" class="pagerGuy"></div> </p>

<script type="text/javascript">

	var classifiedsPortlet = classifiedsPortlet || {};
	classifiedsPortlet["${n}"] = classifiedsPortlet["${n}"] || {};
	classifiedsPortlet["${n}"].jQuery = jQuery.noConflict(true); 

    var <portlet:namespace/>adCount = 0;
	var <portlet:namespace/>pageCount = 0;

    <portlet:namespace/>PageClick = function(pageclickednumber) 
    {
    		
    	classifiedsPortlet["${n}"].jQuery("#<portlet:namespace/>pager").pager({ pagenumber: pageclickednumber, 
    			pagecount: <portlet:namespace/>pageCount,
   			  buttonClickCallback: <portlet:namespace/>PageClick });

    	var start=(pageclickednumber-1) * ${maxAdsPerPage};
		var end=pageclickednumber  * ${maxAdsPerPage};

		if (end > <portlet:namespace/>adCount)
			end = <portlet:namespace/>adCount;
    	
   		var $table = classifiedsPortlet["${n}"].jQuery('#<portlet:namespace/>ads');
   		$table.find('#<portlet:namespace/>ad').hide();
   		$table.find('#<portlet:namespace/>ad').slice(start,end).fadeIn();

    };
		
	classifiedsPortlet["${n}"].jQuery(document).ready(function()    {
		 var $table = classifiedsPortlet["${n}"].jQuery('#<portlet:namespace/>ads');
		 <portlet:namespace/>adCount = $table.find('#<portlet:namespace/>ad').length;
		 <portlet:namespace/>pageCount = Math.ceil(<portlet:namespace/>adCount / ${maxAdsPerPage});
		 if (<portlet:namespace/>pageCount == 1)
			 	{<portlet:namespace/>pageCount = 0};

		classifiedsPortlet["${n}"].jQuery("#<portlet:namespace/>pager").pager
			(
				{ 	pagenumber: 1, 
  			  		pagecount: <portlet:namespace/>pageCount, 
  			  		buttonClickCallback: <portlet:namespace/>PageClick 
  				 }
	  		 );
		});
		
	(function($){			  		 
 	
   		$("#<portlet:namespace/>ads div p").live("click", function() {
			$(this).next('p').slideToggle('fast'); 
		});

    	$("#<portlet:namespace/>dialog").dialog({
    		buttons: { "Close": function() { $(this).dialog("close"); } },
    		autoOpen: false,
    		bgiframe: true,
    		height: 610,
    		width: 540,
    		hide: 'normal',
    		position: 'center',
    		resizable: true,
    		modal: true
    	});

   		$("#<portlet:namespace/>help").click(function() 	{
        	 $("#<portlet:namespace/>dialog").show();  
    		 $("#<portlet:namespace/>dialog").dialog("open");        
         });
    })(classifiedsPortlet["${n}"].jQuery);
    </script>



