<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<b><spring:message code="checkup.description"/>: </b>
<jstl:out value="${checkup.description}"/>
<br/>

<b><spring:message code="checkup.moment"/>: </b>
<jstl:out value="${checkup.moment}"/>
<br/>

<b><spring:message code="checkup.pet"/>: </b>
<jstl:out value="${checkup.pet.name}"/>
<br/>


<b><spring:message code="checkup.treatments"/>: </b>
<br/>
	<jstl:forEach items="${treatments}" var="treatment">
		<spring:message code="treatment.treatment"/> <jstl:out value="${treatment.id}"/>:
		<br/>
		<spring:message code="treatment.illness"/>:
		<jstl:out value="${treatment.illness}"/>
		<br/>
		<spring:message code="treatment.treatmentC"/>:
		<jstl:out value="${treatment.treatmentC}"/>
		<br/>
	</jstl:forEach>

<br/>

<br/>