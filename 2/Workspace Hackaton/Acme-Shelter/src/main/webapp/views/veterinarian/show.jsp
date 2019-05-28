<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<h4>
		<spring:message code="veterinarian.edit.label.name" />:
	</h4>
	<jstl:out value="${veterinarian.name}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.edit.label.surname" />:
	</h4>
	<jstl:out value="${veterinarian.surname}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.edit.label.address" />:
	</h4>
	<jstl:out value="${veterinarian.address}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.edit.label.email" />:
	</h4>
	<jstl:out value="${veterinarian.email}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.edit.label.phoneNumber" />:
	</h4>
	<jstl:out value="${veterinarian.phoneNumber}"></jstl:out>

	<h4>
		<spring:message code="veterinarian.edit.label.username" />:
	</h4>
	<jstl:out value="${veterinarian.userAccount.username}"></jstl:out>
	
	<h4>
	<spring:message code="veterinarian.edit.label.description" />:
	</h4>
	<jstl:out value="${veterinarian.description}"></jstl:out>
	

	<h4>
		<spring:message code="veterinarian.holderName" />:
	</h4>
	<jstl:out value="${veterinarian.holderName}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.makeName" />:
	</h4>
	<jstl:out value="${veterinarian.makeName}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.number" />:
	</h4>
	<jstl:out value="${veterinarian.number}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.expirationYear" />:
	</h4>
	<jstl:out value="${veterinarian.expirationYear}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.expirationMonth" />:
	</h4>
	<jstl:out value="${veterinarian.expirationMonth}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.cvv" />:
	</h4>
	<jstl:out value="${veterinarian.cvv}"></jstl:out>
	
	<h4>
		<spring:message code="veterinarian.socialprofile" />:
	</h4>
	<jstl:forEach items="${veterinarian.socialProfiles}" var="sp">
		<jstl:out value="${sp.nick}"></jstl:out>
	</jstl:forEach>

<br/><br/>

	<spring:message code="veterinarian.export.explanation" var="exportExplanation"/>
	<jstl:out value="${exportExplanation}"/>
	
	<a href="veterinarian/veterinarian/edit.do"> Link </a>
	
	
	<br/>

