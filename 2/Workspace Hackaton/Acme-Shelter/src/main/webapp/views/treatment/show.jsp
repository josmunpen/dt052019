<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<security:authorize access="hasRole('VETERINARIAN')">
	<h3 style="color:blue;">
		<spring:message code="t.moment" />:
	</h3>
	<jstl:out value="${treatment.moment}"></jstl:out>
	
	
	<h3 style="color:blue;">
		<spring:message code="t.illness" />:
	</h3>
	<jstl:out value="${treatment.illness}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="t.treatment" />:
	</h3>
	
	<jstl:out value="${treatment.treatmentC}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="t.comment" />:
	</h3>
	<jstl:out value="${treatment.comment}"></jstl:out>
	
	<br/>
	<br/>
	
	<form:form action="treatment/veterinarian/edit.do" modelAttribute="treatment">
	<form:hidden path="id"/>
	<input type="submit" name="delete"
			value="<spring:message code="t.delete" />"
			onclick="return confirm('<spring:message code="t.confirm.delete" />')" />&nbsp;
	</form:form>
	
	<input type="button" name="back" onclick="javascript: window.location.replace('treatment/veterinarian/list.do')"
		value="<spring:message code="t.back" />" />
</security:authorize>