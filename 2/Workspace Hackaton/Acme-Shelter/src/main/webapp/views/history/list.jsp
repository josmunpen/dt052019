
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table name="histories" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

<display:column property="description" titleKey="history.description"/>
<display:column property="startMoment" titleKey="history.startMoment"/>
<display:column property="endMoment" titleKey="history.endMoment"/>

<display:column>
	<a href="history/petowner/edit.do?historyId=${row.id}">
		<spring:message code="history.edit"/>
	</a>
</display:column>
</display:table>

	<a href="history/petowner/create.do">
		<spring:message code="history.create"/>
	</a>