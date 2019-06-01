<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="pets" requestURI="${requestURI}" id="row">

	<display:column property="identifier" titleKey="pet.identifier"  />
	<display:column property="name" titleKey="pet.name"  />	
		<jstl:if test="${pageContext.response.locale.language=='es'}">
	<jstl:if test="${row.sex=='MALE'}">
	<display:column titleKey="pet.sex" value="Macho"/>
	</jstl:if>
	
	<jstl:if test="${row.sex=='FEMALE'}">
	<display:column titleKey="pet.sex" value="Hembra"/>
	</jstl:if>
	
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<display:column property="sex" titleKey="pet.sex"  />
	</jstl:if>
	
	
	<jstl:if test="${pageContext.response.locale.language=='es'}">
	<display:column property="petType.nombre" titleKey="pet.type"  />
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<display:column property="petType.name" titleKey="pet.type"  />
	</jstl:if>
	<jstl:if test="${pageContext.response.locale.language=='es'}">
	<jstl:if test="${row.status=='LOW'}">
	<display:column titleKey="pet.status" value="Malo"/>
	</jstl:if>
	
	<jstl:if test="${row.status=='MEDIUM'}">
	<display:column titleKey="pet.status" value="Neutro"/>
	</jstl:if>
	
	<jstl:if test="${row.status=='HIGH'}">
	<display:column titleKey="pet.status" value="Bueno"/>
	</jstl:if>
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<display:column property="status" titleKey="pet.status"  />
	</jstl:if>
	
	<display:column property="address" titleKey="pet.address"  />
	<display:column property="age" titleKey="pet.age" />
	<security:authorize access="hasRole('PETOWNER')">	
	<display:column property="careRequirements" titleKey="pet.care.requirements"  />
	<display:column property="dietRequirements" titleKey="pet.diet.requirements"  />
	<display:column property="familyRequirements" titleKey="pet.family.requirements"  />
	<display:column property="petsRequirements" titleKey="pet.requirements"  />
	</security:authorize>

	
	<display:column>
			<a href="pet/show.do?petId=${row.id}">
			<spring:message code="pet.show" />
			</a>
	</display:column>

	
	<security:authorize access="hasRole('PETOWNER')">
	<display:column>
	<a href="history/petowner/list.do?petId=${row.id}">
		<spring:message code="pet.histories"/>
	</a>
	</display:column>
	
	<display:column>
			<a href="pet/petOwner/show.do?petId=${row.id}">
			<spring:message code="pet.show" />
			</a>
	</display:column>
	
	<display:column>

	<a href="pet/petOwner/edit.do?petId=${row.id}">
			<spring:message code="pet.edit" />
			</a>
	</display:column>
</security:authorize>
<jstl:if test="${Anon==true }">
<display:column><a href="petowner/show.do?petOwnerId=${row.petOwner.id}">
			<spring:message code="pet.petowner" />
			</a></display:column>
</jstl:if>		

<security:authorize access="hasRole('ADOPTER')">
	<display:column>
	<a href="application/adopter/create.do?petId=${row.id}">
		<spring:message code="pet.apply"/>
	</a>
	</display:column>
</security:authorize>

</display:table>
<security:authorize access="hasRole('PETOWNER')">
<a href="pet/petOwner/create.do">
			<spring:message code="pet.register" />
			</a>
			
</security:authorize>