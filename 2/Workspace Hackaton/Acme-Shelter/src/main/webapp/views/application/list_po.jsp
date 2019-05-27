
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table name="applications" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

<display:column property="moment" titleKey="application.moment"/>
<display:column property="status" titleKey="application.status"/>
<display:column property="comment" titleKey="application.comment"/>
<display:column property="adopter.name" titleKey="application.adopter"/>
<display:column property="pet.name" titleKey="application.pet"/>

<display:column>
	<a href="application/petowner/edit.do?applicationId=${row.id}">
		<spring:message code="application.edit"/>
	</a>
</display:column>
</display:table>

