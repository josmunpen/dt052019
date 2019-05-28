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
		<spring:message code="petOwner.edit.label.name" />:
	</h4>
	<jstl:out value="${petOwner.name}"></jstl:out>
	
	<h4>
		<spring:message code="petOwner.edit.label.surname" />:
	</h4>
	<jstl:out value="${petOwner.surname}"></jstl:out>
	
	<security:authorize access="hasRole('PETOWNER')">
	<h4>
		<spring:message code="petOwner.edit.label.address" />:
	</h4>
	<jstl:out value="${petOwner.address}"></jstl:out>
	</security:authorize>
	
	<h4>
		<spring:message code="petOwner.edit.label.description" />:
	</h4>
	<jstl:out value="${petOwner.description}"></jstl:out>
	
	

	<h4>
		<spring:message code="petOwner.edit.label.email" />:
	</h4>
	<jstl:out value="${petOwner.email}"></jstl:out>
	
	<security:authorize access="hasRole('PETOWNER')">
	<h4>
		<spring:message code="petOwner.edit.label.phoneNumber" />:
	</h4>
	<jstl:out value="${petOwner.phoneNumber}"></jstl:out>
	</security:authorize>
	
	<h4>
		<spring:message code="petOwner.edit.label.username" />:
	</h4>
	<jstl:out value="${petOwner.userAccount.username}"></jstl:out>

	<security:authorize access="hasRole('PETOWNER')">
	<h4>
		<spring:message code="petOwner.holderName" />:
	</h4>
	<jstl:out value="${petOwner.holderName}"></jstl:out>
	
	<h4>
		<spring:message code="petOwner.makeName" />:
	</h4>
	<jstl:out value="${petOwner.makeName}"></jstl:out>
	
	<h4>
		<spring:message code="petOwner.number" />:
	</h4>
	<jstl:out value="${petOwner.number}"></jstl:out>
	
	<h4>
		<spring:message code="petOwner.expirationYear" />:
	</h4>
	<jstl:out value="${petOwner.expirationYear}"></jstl:out>
	
	<h4>
		<spring:message code="petOwner.expirationMonth" />:
	</h4>
	<jstl:out value="${petOwner.expirationMonth}"></jstl:out>
	</security:authorize>
	
	<h4>
		<spring:message code="petOwner.socialprofile" />:
	</h4>
	<jstl:forEach items="${petOwner.socialProfiles}" var="sp">
		<jstl:out value="${sp.nick}"></jstl:out>
	</jstl:forEach>
	
	


<br/><br/>
<security:authorize access="hasRole('PETOWNER')">
	<spring:message code="petOwner.export.explanation" var="exportExplanation"/>
	<jstl:out value="${exportExplanation}"/>
	
	<a href="petowner/petowner/edit.do"> Link </a>
	<br/>
	</security:authorize>
	
	
