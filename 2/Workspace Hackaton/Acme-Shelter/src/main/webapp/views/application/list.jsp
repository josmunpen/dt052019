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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="applications" requestURI="${requestURI}" id="row">

	<display:column property="moment" titleKey="application.moment" />
	<display:column property="pet.name" titleKey="pet.name" />

	<jstl:if test="${idioma == 'en'}">
		<display:column property="status" titleKey="application.status" />
	</jstl:if>

	<jstl:if test="${idioma == 'es'}">
		<display:column titleKey="application.status">
			<jstl:if test="${row.status == 'ACCEPTED' }">
				<spring:message code="application.accepted" />
			</jstl:if>
			<jstl:if test="${row.status == 'REJECTED' }">
				<spring:message code="application.rejected" />
			</jstl:if>
			<jstl:if test="${row.status == 'PENDING' }">
				<spring:message code="application.pending" />
			</jstl:if>

		</display:column>
	</jstl:if>

	<display:column property="pet.identifier" titleKey="pet.identifier" />

	<display:column>
		<a href="application/adopter/show.do?applicationId=${row.id }"> <spring:message
				code="application.show" />
		</a>
	</display:column>

</display:table>