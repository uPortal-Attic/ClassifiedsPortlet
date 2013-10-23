<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>
<link rel="stylesheet" href="<c:url value="/css/classifieds.css"/>" type="text/css"></link>

<portlet:actionURL portletMode="edit" var="post"><portlet:param name="action" value="addHeading"/></portlet:actionURL>

<script src="/ResourceServingWebapp/rs/jquery/1.3.1/jquery-1.3.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/ResourceServingWebapp/rs/jqueryui/1.6rc6/jquery-ui-1.6rc6.min.js"></script> 
<link rel="stylesheet" href="<c:url value="/ResourceServingWebapp/rs/jqueryui/1.6rc6/theme/smoothness/ui.all.css"/>" type="text/css"></link> 

<c:set var="n"><portlet:namespace/></c:set>

<script type="text/javascript">
	
    function validateMyForm(form)
    {	
 	   	return true;
    }


</script>
<portlet:renderURL portletMode="edit" var="editHeadingsURL">
    <portlet:param name="action" value="editHeadings" />
    </portlet:renderURL>

 <form:form  name="myform" method="POST" commandName="heading" action="${post}" onsubmit="return validateMyForm(this)" >
 <form:errors path="*" />
 <form:hidden path="id" />

<table id="headingForm" border="0">
<tr>
	<td></td>
	<td>	<form:errors path="headingname" cssClass="error" /></td>
</tr>
<tr>
	<td>	<label class="portlet-form-field-label" >Heading</label></td>
	<td>	<form:input id="headingFormText"  path="headingname" maxlength="36" /></td>
	<td>    <form:errors path="headingname" /> </td>
</tr>

<tr>
	<td>	<label class="portlet-form-field-label" >Category</label> </td>
	
	<td>
		   <form:select path="categories" multiple="true"> 
		  		 <!-- <input type="hidden" name="_${status.expression}"/> -->
 		   		<form:options items="${categories}" itemValue="id" itemLabel="name"/>
			</form:select>
	</td>
	<td>    <form:errors path="categories" /> </td>

</tr>
<tr>
	<td></td>
 	<td align=right> <input type="submit" value="submit"  > </td>
</tr>

<tr>
 	<td > <a href="${editHeadingsURL}"><img alt="return" src="<c:url value="/images/arrow_left.png"/>" style="vertical-align: middle"/>
        <spring:message code="admin.edit.heading.return.admin"/></a></td>
 	<td></td>
</tr> 
</table>
</form:form>


