<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>
<link rel="stylesheet" href="<c:url value="/css/classifieds.css"/>" type="text/css"></link>

<portlet:actionURL portletMode="edit" var="post"><portlet:param name="action" value="addCategory"/></portlet:actionURL>

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
 	   	return true;
    }


</script>
<portlet:renderURL portletMode="edit" var="editCategoryURL">
    <portlet:param name="action" value="editCategories" />
    </portlet:renderURL>

 <form:form  name="myform" method="POST" commandName="category" action="${post}" onsubmit="return validateMyForm(this)" >
 
 <form:hidden path="id" />

<table id="categoryForm" border="0">
<tr>
	<td></td>
	<td>	<form:errors path="name" cssClass="error" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Category</label></td>
	<td>	<form:input id="categoryFormText"  path="name" maxlength="36" /></td>
</tr>

<tr>
	<td></td>
 	<td align=right> <input type="submit" value="submit"  > </td>
</tr>

<tr>
 	<td > <a href="${editCategoryURL}"> <img alt="return" src="<c:url value="/images/arrow_left.png"/>" style="vertical-align: middle"/>
        <spring:message code="admin.edit.category.return.admin"/></a></td>
 	<td></td>
</tr> 
</table>
</form:form>


