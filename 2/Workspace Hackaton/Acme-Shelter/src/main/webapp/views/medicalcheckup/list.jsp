
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table name="medicalCheckUps" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

	<display:column property="description" titleKey="checkup.description"/>
	<display:column property="moment" titleKey="checkup.moment"/>
	<display:column property="stateOfHealth" titleKey="checkup.stateOfHealth"/>

	<display:column>
		<a href="checkup/veterinarian/edit.do?medicalCheckUpId=${row.id}">
			<spring:message code="checkup.edit"/>
		</a>
	</display:column>
</display:table>

	<a href="checkup/veterinarian/create.do">
		<spring:message code="checkup.create"/>
	</a>