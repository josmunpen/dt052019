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

<security:authorize access="!(hasRole('ADOPTER') || hasRole('PETOWNER'))">
<spring:message code="tip.comment.authorities2"/>
</security:authorize>
<display:table name="comments" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

<display:column property="commentText" titleKey="comment.comment"/>
<display:column property="moment" titleKey="comment.moment"/>
<display:column property="score" titleKey="comment.score"/>
<display:column titleKey="comment.author">
<jstl:if test="${row.adopter.id != 0 }"> 
<jstl:out value="${row.adopter.name }"/>
</jstl:if>
<jstl:if test="${row.petOwner.id != 0 }"> 
<jstl:out value="${row.petOwner.name }"/>
</jstl:if>

</display:column>

<display:column>
<jstl:if test="${row.petOwner.id == actorId || row.adopter.id == actorId }"> 
<a href ="comment/tip/delete.do?commentId=${row.id }"><spring:message code="comment.delete"/></a>
</jstl:if>
</display:column>
<display:column>
<a href ="comment/tip/show.do?commentId=${row.id}"><spring:message code ="comment.show"/></a></display:column>
</display:table>
<security:authorize access="hasRole('ADOPTER') || hasRole('PETOWNER')">
<a href="comment/tip/create.do?tipId=${idTip}">
<spring:message code="comment.create"/>
</a>
</security:authorize>









