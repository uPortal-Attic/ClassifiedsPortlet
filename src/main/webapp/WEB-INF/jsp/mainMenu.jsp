<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/classifieds.css"/>" type="text/css"></link>
<link rel="stylesheet" href="<c:url value="/css/pager.css"/>" type="text/css"></link>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>


<script src="/ResourceServingWebapp/rs/jquery/1.3.1/jquery-1.3.1.min.js" type="text/javascript"></script>
<script src="/ResourceServingWebapp/rs/jqueryui/1.6rc6/jquery-ui-1.6rc6.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="/ResourceServingWebapp/rs/jqueryui/1.6rc6/theme/smoothness/ui.all.css" type="text/css"></link>

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


	       	<a href="<portlet:renderURL portletMode="view"><portlet:param name="action" value="selectedCategory"/>
	       	<portlet:param name="selectedCategory" value="-1"/></portlet:renderURL>"
					 id="-1"> <spring:message code="menu.LatestAds"/> (<c:out value="${latestAdsCnt}"/>)</a>

        </div>
    </li>
    
    <portlet:renderURL var="myAdsURL" portletMode="view"><portlet:param name="action" value="MyAds" /></portlet:renderURL>

 <c:if test="${userType != 'isGuest'}">
      <li><a href="${myAdsURL}">My Posts</a></li>
	</c:if> 
 
    <li><a href="#" id="<portlet:namespace/>help">Help</a></li>
</ul>
</div>
 <div style="clear:both"></div>
 <div id="<portlet:namespace/>adGrid"> </div>
 <div style="clear:both"></div>
 <div id="adminSpace"> </div>  
  
<c:if test="${userType == 'isAdmin' }">
  <div id="adminURL" >
    <a  href="<portlet:renderURL portletMode="edit">
        <portlet:param name="action" value="adminMenu"/></portlet:renderURL>">
            Classifieds Administration 	
  	</a>
	</div>

</c:if> 
 
 <script type="text/javascript"> 

  var lines = ${lines};
 
 </script>


 <script type="text/javascript">


function getRowCount()
{
	var rowCount =0;

	for (i=0; i < lines.headings.length ; i++)
	{
      		rowCount++;
   		for (ii=0 ; ii < lines.headings[i].categories.length;ii++)
   		{
			rowCount++;
   		} 
	}
	return rowCount;
}


function getMaxColumnWidth()
{

	var maxColumnWidth = 0;

	for (i=0; i < lines.headings.length ; i++)
	{
      		if (lines.headings[i].heading.length > maxColumnWidth)
			maxColumnWidth = lines.headings[i].heading.length;
		
   		for (ii=0 ; ii < lines.headings[i].categories.length;ii++)
   		{
   			var str = lines.headings[i].categories[ii].category + '(' + lines.headings[i].categories[ii].Adcnt +')';
			if (str.length > maxColumnWidth)
				maxColumnWidth = str.length;
   		} 
	}
	return (maxColumnWidth * 7);
}

function getColumnCount($)
{
	var pageWidth = $("#<portlet:namespace/>adGrid").width();
	
	var columnWidth = getMaxColumnWidth();

	return parseInt(pageWidth/columnWidth);
}
function countFormat( cnt )
{
	var str = "";
	
	str = "(" + cnt + ")";
	
	return str;	
}	
function buildMenu($)
{

	$("#<portlet:namespace/>adGrid").html("");
	var columnCnt = getColumnCount($);
	
	if (columnCnt < 1)
		columnCnt = 1;
		
	var wide = parseInt(100/columnCnt);
	wide = wide * .98;
    var myColumns = new Array();
	
	for (i=0; i< columnCnt; i++)
	{
		myColumns[i] = 0;
		var sId = "<div class='column' id='" + "<portlet:namespace/>" + i + "' ></div>" ;
		$("#<portlet:namespace/>adGrid").append(sId);
		$(".column").width(wide+"%");

	} 	

	
	var column = 0;

	for (i=0; i < lines.headings.length ; i++)
	{
	
		for (iii=0; iii< columnCnt; iii++)
		{
			if (myColumns[iii] < myColumns[column])
				column = iii;
		} 	

         var cId = "#<portlet:namespace/>" + column;		
		$(cId).append("<div class='heading'> " + lines.headings[i].heading + "</div>");
		
		myColumns[column] = myColumns[column]+1;
		
   		for (ii=0 ; ii < lines.headings[i].categories.length;ii++)
   		{
			if (ii == 0)
			{
				$(cId).append("<div class='category-noline'><a href='" + lines.headings[i].categories[ii].url + "'>" + lines.headings[i].categories[ii].category + countFormat( lines.headings[i].categories[ii].Adcnt) + "</a></div>");				
			}
			else
			{
				$(cId).append("<div class='category'><a href='" + lines.headings[i].categories[ii].url + "'>" + lines.headings[i].categories[ii].category + countFormat( lines.headings[i].categories[ii].Adcnt) + "</a></div>");				
			}
			myColumns[column] = myColumns[column]+1;
   		} 
		
	}
	
};

	var classifiedsPortlet = classifiedsPortlet || {};
	classifiedsPortlet["${n}"] = classifiedsPortlet["${n}"] || {};
	classifiedsPortlet["${n}"].jQuery = jQuery.noConflict(true); 


buildMenu(classifiedsPortlet["${n}"].jQuery);

	
	
	(function($){
	
		 $(window).resize(function() { 
  		       buildMenu($);
         });
  
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

 
								