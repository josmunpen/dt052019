<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form modelAttribute="application" action="application/petowner/edit.do">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="moment"/>
		<form:hidden path="comment"/>
		<form:hidden path="rejectCause"/>
		<form:hidden path="photos"/>
		<form:hidden path="pet"/>
		<form:hidden path="adopter"/>
		

		<form:label path="status">
		<spring:message code="application.status" />:
	    </form:label>
	    <form:select path="status">

		<form:option value="ACCEPTED"></form:option>
		<form:option value="REJECTED"></form:option>
		</form:select>
		
		<form:label path="rejectCause">
		<spring:message code="application.rejectCause" />:*
		</form:label>
		<form:input path="rejectCause" />
		<form:errors cssClass="error" path="rejectCause" />
		<br />

	
			
	<input type ="submit" name="save" value="<spring:message code="application.save"/>" />

	<input type="button" name="cancel" value="<spring:message code="application.cancel" />" onclick="javascript:relativeRedir('application/petowner/list.do');" />
	

</form:form>





