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
		<spring:message code="adopter.edit.label.name" />:
	</h4>
	<jstl:out value="${adopter.name}"></jstl:out>
	
	<h4>
		<spring:message code="adopter.edit.label.surname" />:
	</h4>
	<jstl:out value="${adopter.surname}"></jstl:out>
	
	<h4>
		<spring:message code="adopter.edit.label.address" />:
	</h4>
	<jstl:out value="${adopter.address}"></jstl:out>
	
	<h4>
		<spring:message code="adopter.edit.label.description" />:
	</h4>
	<jstl:out value="${adopter.description}"></jstl:out>
	
	

	<h4>
		<spring:message code="adopter.edit.label.email" />:
	</h4>
	<jstl:out value="${adopter.email}"></jstl:out>
	
	<h4>
		<spring:message code="adopter.edit.label.phoneNumber" />:
	</h4>
	<jstl:out value="${adopter.phoneNumber}"></jstl:out>

	<h4>
		<spring:message code="adopter.edit.label.username" />:
	</h4>
	<jstl:out value="${adopter.userAccount.username}"></jstl:out>

	<h4>
		<spring:message code="adopter.holderName" />:
	</h4>
	<jstl:out value="${adopter.holderName}"></jstl:out>
	
	<h4>
		<spring:message code="adopter.makeName" />:
	</h4>
	<jstl:out value="${adopter.makeName}"></jstl:out>
	
	<h4>
		<spring:message code="adopter.number" />:
	</h4>
	<jstl:out value="${adopter.number}"></jstl:out>
	
	<h4>
		<spring:message code="adopter.expirationYear" />:
	</h4>
	<jstl:out value="${adopter.expirationYear}"></jstl:out>
	
	<h4>
		<spring:message code="adopter.expirationMonth" />:
	</h4>
	<jstl:out value="${adopter.expirationMonth}"></jstl:out>
	
	<h4>
		<spring:message code="adopter.socialprofile" />:
	</h4>
	<jstl:forEach items="${adopter.socialProfiles}" var="sp">
		<jstl:out value="${sp.nick}"></jstl:out>
	</jstl:forEach>
	
	


<br/><br/>

	<spring:message code="adopter.export.explanation" var="exportExplanation"/>
	<jstl:out value="${exportExplanation}"/>
	
	<a href="adopter/adopter/edit.do"> Link </a>
	
	
	<br/>
