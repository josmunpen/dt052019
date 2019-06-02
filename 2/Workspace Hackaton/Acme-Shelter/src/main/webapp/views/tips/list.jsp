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


<display:table name="tips" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

<display:column property="title" titleKey="tip.title"/>
<display:column property="body" titleKey="tip.body"/>
<display:column property="moment" titleKey="tip.moment"/>
<display:column property="veterinarian.name" titleKey="tip.veterinarian"/>
<display:column> <a href="tip/veterinarian/edit.do?tipId=${row.id}"><spring:message code="tip.edit"/></a></display:column>
<display:column> <a href="tip/show.do?tipId=${row.id}"><spring:message code="tip.show"/></a></display:column>
</display:table>
<security:authorize access="hasRole('VETERINARIAN')">
<a href="tip/veterinarian/create.do">
<spring:message code="tip.create"/>
</a>
</security:authorize>









