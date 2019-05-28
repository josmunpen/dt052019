<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>





<form:form modelAttribute="type" action="type/administrator/edit.do">
<form:hidden path="id"/>
<form:hidden path="version"/>




<form:label path="name">
<spring:message code="type.edit.name"/>
</form:label>
<form:input path="name"/>
<form:errors cssClass="error" path="name" />
		<br />


<form:label path="nombre">
<spring:message code="type.edit.nombre"/>
</form:label>
<form:input path="nombre"/>
<form:errors cssClass="error" path="nombre" />
		<br />

<form:label path="childs">
<spring:message code="type.childs"/>
</form:label>
<form:select path="childs">
		    <form:option label="----" value="0" />
		    <form:options items="${list}"  itemValue ="id"/>
		    </form:select>
<form:errors cssClass="error" path="childs" />
<br />



<form:label path="description">
<spring:message code="type.description"/>
</form:label>
<form:input path="description" />
<form:errors cssClass="error" path="description" />
<br />

<form:label path="zone">
<spring:message code="type.zone"/>
</form:label>
<form:input path="zone" />
<form:errors cssClass="error" path="zone" />
		<br />


<form:label path="scientificTerm">
<spring:message code="type.scientificTerm"/>
</form:label>
<form:input path="scientificTerm" />
<form:errors cssClass="error" path="scientificTerm" />
		<br />

<form:label path="finalMode">
<spring:message code="type.finalMode" />:
</form:label>
<form:select path="finalMode">
<form:option value="true"></form:option>
<form:option value="false"></form:option>
</form:select>
		<br />


<input type ="submit" name="save" value="<spring:message code="type.edit.save"/>" />

<input type="button" name="cancel" value="<spring:message code="type.edit.cancel" />" onclick="javascript:relativeRedir('type/administrator/list.do');" />

<jstl:if test="${type.id != 0 }">
<input type ="submit" name="delete" value="<spring:message code="type.delete"/>" />
</jstl:if>
</form:form>










