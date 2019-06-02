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


<b><spring:message code="comment.moment"/>:</b>
<jstl:out value="${c.moment}"/>
<br/>

<b><spring:message code="comment.author"/>:</b>
<jstl:if test="${c.adopter.id != 0 }"> 
<jstl:out value="${c.adopter.name }"/>
</jstl:if>
<jstl:if test="${c.petOwner.id != 0 }"> 
<jstl:out value="${c.petOwner.name }"/>
</jstl:if>
<br/>

<b><spring:message code="comment.comment"/>:</b>
<jstl:out value="${c.commentText}"/>
<br/>

<b><spring:message code="comment.score"/>:</b>
<jstl:out value="${c.score}"/>
<br/>
