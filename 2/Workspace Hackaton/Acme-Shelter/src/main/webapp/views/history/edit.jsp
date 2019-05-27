<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form modelAttribute="history" action="history/petowner/edit.do">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="actor"/>
		
		<form:label path="description">
			<spring:message code="history.description" />:*
		</form:label>
		<form:input path="description" />
		<form:errors cssClass="error" path="description" />
		<br />	
		
		<form:label path="startMoment">
			<spring:message code="history.startMoment" />:*
		</form:label>
		<form:input path="startMoment" />
		<form:errors cssClass="error" path="startMoment" />
		<br />	
		
		<form:label path="endMoment">
			<spring:message code="history.endMoment" />:*
		</form:label>
		<form:input path="endMoment" />
		<form:errors cssClass="error" path="endMoment" />
		<br />
			
	<input type ="submit" name="save" value="<spring:message code="history.save"/>" />

	<input type="button" name="cancel" value="<spring:message code="history.cancel" />" onclick="javascript:relativeRedir('history/petowner/list.do?petId=${petId}');" />
	<jstl:if test ="${history.id !=0}">
	<input type ="submit" name="delete" value="<spring:message code="history.delete"/>" />
	</jstl:if>

</form:form>





