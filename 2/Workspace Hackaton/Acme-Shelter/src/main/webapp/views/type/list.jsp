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


<jstl:if test="${lang=='en' }">

<display:table name="types" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

<display:column property="name" titleKey="type.name"/>
<display:column property="scientificTerm" titleKey="type.scientificTerm"/>
<display:column property="description" titleKey="type.description"/>
<display:column property="zone" titleKey="type.zone"/>
<display:column property="parent" titleKey ="type.parent" />
<display:column property="finalMode" titleKey="type.finalMode"/>

<display:column>
<jstl:if test ="${row.finalMode == false }">
<a href="type/administrator/edit.do?typeId=${row.id}">
<spring:message code="type.edit"/>
</a>
</jstl:if>
</display:column>
</display:table>
</jstl:if>

<jstl:if test="${lang!='en' }">
<display:table name="types" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

	<display:column property="nombre" titleKey="type.name"/>
	<display:column property="scientificTerm" titleKey="type.scientificTerm"/>
	<display:column property="description" titleKey="type.description"/>
	<display:column property="zone" titleKey="type.zone"/>

	<display:column titleKey="type.parent">
			<jstl:if test="${pageContext.response.locale.language=='en'}">
				<jstl:out value="${row.parent.name}"/>
			</jstl:if>
			<jstl:if test="${pageContext.response.locale.language=='es'}">
				<jstl:out value="${row.parent.nombre}"/>
			</jstl:if>
	</display:column>
	
	<display:column property="finalMode" titleKey="type.finalMode"/>
	
	<display:column>
		<jstl:if test ="${row.finalMode == false }">
			<a href="type/administrator/edit.do?typeId=${row.id}">
			<spring:message code="type.edit"/>
			</a>
		</jstl:if>
	</display:column>
	<display:column>
		<a href="type/administrator/display.do?typeId=${row.id}">
		<spring:message code="type.display"/>
		</a>
	</display:column>
</display:table>

</jstl:if>
<a href="type/administrator/create.do">
<spring:message code="type.create"/>
</a>








