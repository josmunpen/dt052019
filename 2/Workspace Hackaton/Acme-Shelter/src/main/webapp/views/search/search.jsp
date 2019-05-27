<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form action="search/search.do">
	<label><spring:message code="search.keyword" /></label> <input
		type="text" name="keyword" />
	<button type="submit" name="search"><spring:message code="search.button"/></button>
</form>
<jstl:if test="${flagSearch == true}">
	<spring:message code="search.keyword2" />:
	<strong><jstl:out value="${keyword}" /></strong><br/>
	<display:table pagesize="5" class="displaytag" keepStatus="true" name="pets" id="pet">
		<display:column property="name" titleKey="search.pet.name" />
		<display:column property="age" titleKey="search.pet.age" />
		<jstl:if test="${language=='es'}">
		<display:column titleKey="search.pet.status">
		<jstl:if test="${pet.status == 'LOW'}">
				<jstl:out value="BAJA"/>
		</jstl:if>
		<jstl:if test="${pet.status == 'MEDIUM'}">
				<jstl:out value="MEDIA"/>
		</jstl:if>
		<jstl:if test="${pet.status == 'HIGH'}">
				<jstl:out value="ALTA"/>
		</jstl:if>
		</display:column>
		</jstl:if>
		<jstl:if test="${language=='en'}">
		<display:column titleKey="search.pet.status" property="status"/>
		</jstl:if>
		<jstl:if test="${language=='es'}">
		<display:column property="petType.nombre" titleKey="search.pet.petType" />
		</jstl:if>
		<jstl:if test="${language=='en'}">
		<display:column property="petType.name" titleKey="search.pet.petType" />
		</jstl:if>
		<jstl:if test="${language=='en'}">
		<display:column property ="sex" titleKey="search.pet.sex"/>
		</jstl:if>
		<jstl:if test="${language=='es'}">
		<display:column titleKey="search.pet.sex">
			<jstl:if test="${pet.sex == 'MALE'}">
				<jstl:out value="MACHO"/>
		</jstl:if>
		<jstl:if test="${pet.sex == 'FEMALE'}">
				<jstl:out value="HEMBRA"/>
		</jstl:if>		
		</display:column>
		</jstl:if>
	</display:table>
</jstl:if>
