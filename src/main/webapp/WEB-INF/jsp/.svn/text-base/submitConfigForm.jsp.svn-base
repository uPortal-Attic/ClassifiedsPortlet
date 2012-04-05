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

 function validateMyForm(form)
 {	
	return true;
}

</script>


<div id="<portlet:namespace/>confirm"  class="hidden" title="Fort Lewis Classifieds"></div>

<portlet:actionURL var="post"><portlet:param name="action" value="addConfig"/></portlet:actionURL>


<form:form method="POST" commandName="config" action="${post}" onsubmit="return validateMyForm(this)">
<form:hidden path="id" />

<table id="configForm" border="0">
<tr>
	<td></td>
	<td>	<form:errors path="help_Title" cssClass="error" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Help Title:</label> </td>
	<td>	<form:input id="helpTitle"  path="help_Title" maxlength="200" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Help Text:</label> </td>
	<td>	<form:textarea  rows="20" cols="60" id="helpTitleText"  path="help_Text" /></td>
</tr>

<tr>
	<td>	<label class="portlet-form-field-label" >Policy Title:</label> </td>
	<td>	<form:input id="policyTitle"  path="policy_Title" maxlength="200" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Policy Text:</label> </td>
	<td>	<form:textarea  rows="20" cols="60" id="policyTitleText"  path="policy_Text" /></td>
</tr>

<tr>
<td></td>
<td><div class="subCol"> <input type="submit" value="submit"  ></div></td>
</tr>
</table>
</form:form>



<div class="footer">
 <a href="<portlet:renderURL portletMode="edit">
        <portlet:param name="action" value="adminMenu"/></portlet:renderURL>">
         <img alt="return" src="<c:url value="/images/arrow_left.png"/>" style="vertical-align: middle"/>
        <spring:message code="admin.return.to.home"/></a>
 </div>

