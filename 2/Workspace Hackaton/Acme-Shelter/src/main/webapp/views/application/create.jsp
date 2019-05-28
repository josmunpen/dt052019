<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADOPTER')">
	<form:form action="application/adopter/create.do" modelAttribute="application">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<input type="hidden" name="petId" value="${p.id}"/>
		<form:hidden path="version" />
		
		<h2>
			<form:label path="comment">
				<spring:message code="application.comment" />
			</form:label>
		</h2>

		<form:input path="comment" />
		<form:errors cssClass="error" path="comment" />

		<h2>
			<form:label path="photos">
				<spring:message code="application.photos" />
			</form:label>
		</h2>

		<form:input path="photos" />
		<form:errors cssClass="error" path="photos" />

		<br />
		<br />

		<input type="submit" name="save"
			value="<spring:message code="application.save" />" />

		<input type="button" name="cancel"
			onclick="javascript: window.location.replace('application/adopter/list.do')"
			value="<spring:message code="application.cancel" />" />
	</form:form>
</security:authorize>