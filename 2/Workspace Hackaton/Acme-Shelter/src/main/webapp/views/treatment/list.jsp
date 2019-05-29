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

<security:authorize access="hasRole('VETERINARIAN')">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="treatments" requestURI="${requestURI}" id="row">

	<display:column property="moment" titleKey="t.moment"  />
	<display:column property="illness" titleKey="t.illness"  />
	<display:column property="treatmentC" titleKey="t.treatment"  />

	<display:column>
			<a href="treatment/veterinarian/show.do?treatmentId=${row.id}">
			<spring:message code="t.show" />
			</a>
	</display:column>
	
	<display:column>

	<a href="treatment/veterinarian/edit.do?treatmentId=${row.id}">
			<spring:message code="t.edit" />
			</a>
	</display:column>
	
</display:table>
			
</security:authorize>