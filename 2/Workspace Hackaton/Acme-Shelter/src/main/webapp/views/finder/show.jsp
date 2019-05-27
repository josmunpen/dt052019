<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADOPTER')">
	
	<strong>
	<spring:message code="finder.moment"/>:</strong>
	<jstl:out value="${finder.moment}"/>
	<br/>
	
	<strong><spring:message code="finder.keyword"/>:</strong>
	<jstl:out value="${finder.keyword}"/>
	<br/>
	
	<strong><spring:message code="finder.sex"/>:</strong>
	<jstl:out value="${finder.sex}"/>
	<br/>
	
	<strong><spring:message code="finder.age"/>:</strong>
	<jstl:out value="${finder.age}"/>
	<br/>
	
	<strong><spring:message code="finder.type"/>:</strong>
	<jstl:out value="${finder.type}"/>
	<br/>
	
	<h3><spring:message code="finder.results"/>:</h3>
	<br/>
	<display:table pagesize="${customisation.resultsNumber}" class="displaytag" keepStatus="true" name="finder.pets" id="pet">
		<display:column property="name" titleKey="finder.name" />
		<display:column property="age" titleKey="finder.age1" />
		<jstl:if test="${language=='es'}">
		<display:column titleKey="finder.sex">
			<jstl:if test="${pet.sex == 'MALE'}">
				<jstl:out value="MACHO"/>
		</jstl:if>
		<jstl:if test="${pet.sex == 'FEMALE'}">
				<jstl:out value="HEMBRA"/>
		</jstl:if>
		</display:column>
		<display:column property="petType.nombre" titleKey="finder.type"/>
		</jstl:if>
		<jstl:if test="${language=='en'}">
		<display:column titleKey="finder.sex" property="sex"/>
		<display:column property="petType.name" titleKey="finder.type"/>
		</jstl:if>
		
	</display:table>
	<br/>
	
	<a href="finder/adopter/edit.do"><spring:message code="finder.edit"/></a>
</security:authorize>