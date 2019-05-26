<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="pets" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="identifier" titleKey="pet.identifier" />
	<display:column property="name" titleKey="pet.name" />
	<display:column property="age" titleKey="pet.age" />
	<display:column property="status" titleKey="pet.status" />
	<display:column property="sex" titleKey="pet.sex" />

	<security:authorize access="hasRole('ADOPTER')">
		<display:column>
			<a href="application/adopter/create.do?petId=${row.id}"> <spring:message
					code="pet.apply" />
			</a>
		</display:column>
	</security:authorize>
	
	<jstl:if test="${showError == true}">
		<div class="error">
			<spring:message code="pet.apply.error" />
		</div>
	</jstl:if>

</display:table>