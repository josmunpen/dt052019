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





<form:form modelAttribute="comment" action="comment/tip/edit.do">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="moment" />
<form:hidden path="tip" />


<form:label path="commentText">
<spring:message code="comment.comment"/>
</form:label>
<form:input path="commentText"/>
<form:errors cssClass="error" path="commentText" />
		<br />


<form:label path="score">
<spring:message code="comment.score"/>
</form:label>
<form:input path="score"/>
<form:errors cssClass="error" path="score" />
		<br />

<input type ="submit" name="save" value="<spring:message code="tip.save"/>" />

<input type="button" name="cancel" value="<spring:message code="tip.cancel" />" onclick="javascript:relativeRedir('tip/list.do');" />

</form:form>










