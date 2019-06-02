
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table name="medicalCheckUps" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

	<display:column property="description" titleKey="checkup.description"/>
	<display:column property="moment" titleKey="checkup.moment"/>
	<jstl:if test="${pageContext.response.locale.language=='en'}">
		<display:column property="stateOfHealth" titleKey="checkup.stateOfHealth"  />
	</jstl:if>
	<jstl:if test="${pageContext.response.locale.language=='es'}">
		<jstl:if test="${row.stateOfHealth=='EXCELENT'}">
		<display:column titleKey="checkup.stateOfHealth" value="EXCELENTE"/>
		</jstl:if>
		
		<jstl:if test="${row.stateOfHealth=='GOOD'}">
		<display:column titleKey="checkup.stateOfHealth" value="BUENO"/>
		</jstl:if>
		
		<jstl:if test="${row.stateOfHealth=='BAD'}">
		<display:column titleKey="checkup.stateOfHealth" value="MALO"/>
		
		<jstl:if test="${row.stateOfHealth=='CRITICAL'}">
		<display:column titleKey="checkup.stateOfHealth" value="CRÍTICO"/>
	</jstl:if>
	</jstl:if>
	</jstl:if>
	<display:column>
		<a href="checkup/veterinarian/edit.do?medicalCheckUpId=${row.id}">
			<spring:message code="checkup.edit"/>
		</a>
	</display:column>
		<display:column>
		<a href="checkup/veterinarian/show.do?medicalCheckUpId=${row.id}">
			<spring:message code="checkup.show"/>
		</a>
	</display:column>
	<display:column>
		<a href="treatment/veterinarian/create.do?medicalCheckUpId=${row.id}">
			<spring:message code="treatment.create"/>
		</a>
	</display:column>

</display:table>

	<a href="checkup/veterinarian/create.do">
		<spring:message code="checkup.create"/>
	</a>