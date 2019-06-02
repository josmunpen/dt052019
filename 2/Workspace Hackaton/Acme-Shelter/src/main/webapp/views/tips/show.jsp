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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<b><spring:message code="tip.title"/>: </b>
<jstl:out value="${tip.title}"/>
<br/>

<b><spring:message code="tip.body"/>: </b>
<jstl:out value="${tip.body}"/>
<br/>

<b><spring:message code="tip.veterinarian"/>: </b>
<jstl:out value="${tip.veterinarian.name}"/>
<br/>

<b><spring:message code="tip.petType"/>: </b>
<c:forEach items="${tip.petTypes}" var="item">
<jstl:out value="${item.name} / ${item.nombre }"/>
<br/>
</c:forEach>
<br/>