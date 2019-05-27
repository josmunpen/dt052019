<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	<h3 style="color:blue;">
		<spring:message code="pet.address" />:
	</h3>
	<jstl:out value="${pet.address}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.age" />:
	</h3>
	<jstl:out value="${pet.age}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.care.requirements" />:
	</h3>
	<jstl:out value="${pet.careRequirements}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.diet.requirements" />:
	</h3>
	<jstl:out value="${audit.dietRequirements}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.family.requirements" />:
	</h3>
	<jstl:out value="${pet.familyRequirements}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.management.cost" />:
	</h3>
	<jstl:out value="${pet.managementCost}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.name" />:
	</h3>
	<jstl:out value="${pet.name}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.nature" />:
	</h3>
	<jstl:out value="${pet.nature}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.pedigree" />:
	</h3>
	<jstl:out value="${pet.pedigree}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.requirements" />:
	</h3>
	<jstl:out value="${pet.petsRequirements}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.type" />:
	</h3>
		<jstl:if test="${pageContext.response.locale.language=='es'}">
		<jstl:out value="${pet.petType.nombre}"/>
		</jstl:if>
		<jstl:if test="${pageContext.response.locale.language=='en'}">
		<jstl:out value="${pet.petType.name}"/>
		</jstl:if>
	
	<h3 style="color:blue;">
		<spring:message code="pet.photos" />:
	</h3>
	<jstl:out value="${pet.photos}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="pet.sex" />:
	</h3>
	
	<jstl:if test="${pageContext.response.locale.language=='es'}">
	<jstl:if test="${pet.sex=='MALE'}">
	<jstl:out value="Macho"/>
	</jstl:if>
	
	<jstl:if test="${pet.sex=='FEMALE'}">
	<jstl:out value="Hembra"/>
	</jstl:if>
	
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<jstl:out value="${pet.sex}"></jstl:out>
	</jstl:if>
	
	<h3 style="color:blue;">
		<spring:message code="pet.status" />:
	</h3>
	<jstl:if test="${pageContext.response.locale.language=='es'}">
	<jstl:if test="${pet.status=='LOW'}">
	<jstl:out value="Malo"/>
	</jstl:if>
	
	<jstl:if test="${pet.status=='MEDIUM'}">
	<jstl:out value="Neutro"/>
	</jstl:if>
	
	<jstl:if test="${pet.status=='HIGH'}">
	<jstl:out value="Bueno"/>
	</jstl:if>
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<jstl:out value="${pet.status}"></jstl:out>
	</jstl:if>
	
	<br/>
	<br/>
	
	<form:form action="pet/petOwner/edit.do" modelAttribute="pet">
	<form:hidden path="id"/>
	<input type="submit" name="delete"
			value="<spring:message code="pet.delete" />"
			onclick="return confirm('<spring:message code="pet.confirm.delete" />')" />&nbsp;
	</form:form>
	
	
	<input type="button" name="back" onclick="javascript: window.location.replace('pet/petOwner/list.do')"
		value="<spring:message code="pet.back" />" />
