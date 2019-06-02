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





<form:form modelAttribute="tip" action="tip/veterinarian/edit.do">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="veterinarian" />
<form:hidden path="moment" />



<form:label path="title">
<spring:message code="tip.title"/>
</form:label>
<form:input path="title"/>
<form:errors cssClass="error" path="title" />
		<br />


<form:label path="body">
<spring:message code="tip.body"/>
</form:label>
<form:input path="body"/>
<form:errors cssClass="error" path="body" />
		<br />

<form:label path="petTypes">
<spring:message code="tip.petType"/>
</form:label>				
<jstl:if test="${pageContext.response.locale.language=='es'}">
<form:select path="petTypes">
		    <form:option label="----" value="0" />
		    <form:options items="${types}"  itemLabel = "nombre" itemValue ="id"/>
		    </form:select>
<form:errors cssClass="error" path="petTypes" />
<br />				</jstl:if>
				<jstl:if test="${pageContext.response.locale.language=='en'}">
<form:select path="petTypes">
		    <form:option label="----" value="0" />
		    <form:options items="${types}"  itemLabel = "name" itemValue ="id"/>
		    </form:select>
<form:errors cssClass="error" path="petTypes" />
<br />				</jstl:if>

<input type ="submit" name="save" value="<spring:message code="tip.save"/>" />

<input type="button" name="cancel" value="<spring:message code="tip.cancel" />" onclick="javascript:relativeRedir('tip/veterinarian/list.do');" />

</form:form>










