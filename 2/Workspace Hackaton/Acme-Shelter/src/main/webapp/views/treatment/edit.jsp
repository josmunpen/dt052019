<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="treatment/veterinarian/edit.do"
	modelAttribute="treatment">
	<jstl:if test="${treatment.id!=0 }">
	<form:hidden path="moment"/>
	</jstl:if>
	<jstl:if test="${treatment.id==0 }">
	<form:hidden path="medicalCheckUp"/>
	</jstl:if>
	<form:hidden path="id" />

	<fieldset>
		<legend align="left">
			<spring:message code="edit.treatment" />
		</legend>

		<form:label path="illness">
			<spring:message code="t.illness" />* :
		</form:label>
		<form:input path="illness" />
		<form:errors cssClass="error" path="illness" />
		<br /> <br />
		
		<form:label path="treatmentC">
			<spring:message code="t.treatment" />* :
		</form:label>
		<form:input path="treatmentC" />
		<form:errors cssClass="error" path="treatmentC" />
		<br /> <br />
		
		<form:label path="comment">
			<spring:message code="t.comment" /> :
		</form:label>
		<form:textarea path="comment" />
		<form:errors cssClass="error" path="comment" />
		<br /> <br />

	</fieldset>
	<br />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="t.save" />" />&nbsp;
	<jstl:if test="${treatment.id!=0 }">
	<input type="submit" name="delete"
		value="<spring:message code="t.delete" />"
		onclick="return confirm('<spring:message code="t.confirm.delete" />')" />&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('treatment/veterinarian/list.do')"
		value="<spring:message code="t.edit.cancel" />" />

</form:form>