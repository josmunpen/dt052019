<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<b><spring:message code="type.name"/>: </b>
<jstl:out value="${type.name}"/>
<br/>

<b><spring:message code="type.edit.nombre"/>: </b>
<jstl:out value="${type.nombre}"/>
<br/>

<b><spring:message code="type.description"/>: </b>
<jstl:out value="${type.description}"/>
<br/>

<b><spring:message code="type.scientificTerm"/>: </b>
<jstl:out value="${type.scientificTerm}"/>
<br/>

<b><spring:message code="type.zone"/>: </b>
<jstl:out value="${type.zone}"/>
<br/>

<b><spring:message code="type.parent"/>: </b>

	<jstl:if test="${pageContext.response.locale.language=='en'}">
		<jstl:out value="${type.parent.name}"/>
	</jstl:if>
	<jstl:if test="${pageContext.response.locale.language=='es'}">
		<jstl:out value="${type.parent.nombre}"/>
	</jstl:if>
	
<br/>

<b><spring:message code="type.finalMode"/>: </b>
<jstl:out value="${type.finalMode}"/>
<br/>