<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>
<link rel="stylesheet" href="<c:url value="/css/classifieds.css"/>" type="text/css"></link>



<script src="/ResourceServingWebapp/rs/jquery/1.3.1/jquery-1.3.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/ResourceServingWebapp/rs/jqueryui/1.6rc6/jquery-ui-1.6rc6.min.js"></script> 
<link rel="stylesheet" href="<c:url value="/ResourceServingWebapp/rs/jqueryui/1.6rc6/theme/smoothness/ui.all.css"/>" type="text/css"></link> 


<c:set var="n"><portlet:namespace/></c:set>


<script type="text/javascript">

	var ${n}_portletName = ${n}_portletName || {}; 
	${n}_portletName.jQuery = jQuery.noConflict(true); 
	var $ = ${n}_portletName.jQuery;

	var futureDate = "";
	
    function validateMyForm(form)
    {	
    	var start = form.startDate.value;
    	var mm = 0;
			
		if (start.charAt(0) == "0")
		{	
			mm = parseInt(start.substring(1,2)) - 1;
		}
		else
		{	
			
			mm = parseInt(start.substring(0,1)) - 1;
		}

		var startDate = new Date();
			
	   	startDate.setFullYear(start.substring(6,10),mm.toString(),start.substring(3,5));

		var  today = new Date();

		if ( startDate > today)
		{
			var startMonth = startDate.getMonth() + 1;

			 $("#<portlet:namespace/>confirm").html("<p></p><p>Your Ad will not be visible on the Classifieds until the start date of " + startMonth + "/" + startDate.getDate() + "/" + startDate.getFullYear() + ". If you would like to have the Ad appear sooner please re-enter the start date.</p><p> Do you want to continue?</p>"); 
			 $("#<portlet:namespace/>confirm").show();  
			 $("#<portlet:namespace/>confirm").dialog("open"); 

			 return false;  
		}

	   	return true;
    }

$(function(){
	$("#startDatepicker").datepicker({minDate: '0d', maxDate: '60d'});
	$("#endDatepicker").datepicker({minDate: '1d', maxDate: '120d'});
});

$(document).ready(function(){
	$("#<portlet:namespace/>confirm").dialog({
		buttons: { "Cancel": function() { $(this).dialog("close"); }, "OK": function() { $(this).dialog("close"); document.myform.submit();} },
		autoOpen: false,
		bgiframe: true,
		height: 180,
		width: 470,
		hide: 'normal',
		position: 'center',
		resizable: true,
		modal: true
	});
});


$(document).ready(function(){
	$("#<portlet:namespace/>dialog").dialog({
		buttons: { "Close": function() { $(this).dialog("close"); } },
		autoOpen: false,
		bgiframe: true,
		height: 510,
		width: 510,
		hide: 'normal',
		position: 'center',
		resizable: true,
		modal: true
	});
});

$(document).ready(function() {
     $("#<portlet:namespace/>harassmentLink").click(function() 	{
    	 $("#<portlet:namespace/>dialog").show();  
		$("#<portlet:namespace/>dialog").dialog("open");        
     });
});

	

</script>
<div id="<portlet:namespace/>confirm"  class="hidden" title="Fort Lewis Classifieds">
</div>


 <c:if test="${ not empty configs }">
   	<c:forEach items="${configs}" var="config" >
   		<div id="<portlet:namespace/>dialog"  class="hidden" title="${config.policy_Title}">${config.policy_Text}</div>
 	</c:forEach>
 </c:if> 

<portlet:renderURL var="myAdsURL">
    <portlet:param name="action" value="MyAds" />
</portlet:renderURL>

<portlet:actionURL var="postUrl">
	<portlet:param name="action" value="addAd"/>
</portlet:actionURL>

 <form:form  name="myform" method="POST" commandName="ad" action="${postUrl}" onsubmit="return validateMyForm(this)" >
 
 <form:hidden path="id" />

<table id="adForm" border="0" >
<tr>
<td id="adCol1" ></td>
<td id="adCol1" ></td>
</tr>
<tr>
	<td></td>
	<td>	<form:errors path="category" cssClass="error" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Category</label> </td>
	
	<td>	<form:select path="category" >
			<form:option value="" label="--select a category--"/>
		 <form:options items="${categories}" itemLabel="name"/> 
			</form:select>
			
	</td>

</tr>
<tr>
	<td></td>
	<td>	<form:errors path="startDate" cssClass="error" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Start Date</label> </td>
	<td>	<form:input path="startDate" id="startDatepicker" /></td>
</tr>
<tr>
	<td></td>
	<td>	<form:errors path="endDate" cssClass="error" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >End Date</label> </td>
	<td>	<form:input path="endDate" id="endDatepicker"  /></td>
</tr>
<tr>
	<td></td>
	<td>	<form:errors path="title" cssClass="error" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Ad Title</label></td>
	<td >	<form:input id="adFormTitle"  path="title" maxlength="46" /></td>
</tr>
<tr>
	<td></td>
	<td>	<form:errors path="description" cssClass="error" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Description and contact information</label></td>
	<td>	<form:textarea path="description" cols="200" rows="5"  /></td>
</tr>
<tr>
	<td></td>
	<td>	<form:errors path="price" cssClass="error" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Price (Example: $650.00)</label></td>
	<td>	<form:input id="adFormPrice" path="price" size="6" maxlength="12"/> (Optional)</td>
</tr>
<tr>
	<td colspan="2">	<form:errors path="policyAccepted" cssClass="error" /></td>
</tr>
<tr>
	<td colspan="2"><label class="SubmitAdFormPolicy" >I have read and accept the <a id="<portlet:namespace/>harassmentLink" >Classified Ads Use Policy</a> .</label>
<form:checkbox id="adFormPolicyAcceptance" path="policyAccepted" /></td>
</tr>
<tr>
<td></td>
 <td align=right> <input type="submit" value="submit"  > </td>
</tr>
<tr>
<td></td>


 <td align=right> <a href="${myAdsURL}">
  <img alt="return" src="<c:url value="/images/arrow_left.png"/>" style="vertical-align: middle"/>
 <spring:message code="submit.Ad.success.more"/></a></td>
 </tr>
</tr> 
</table>
</form:form>

