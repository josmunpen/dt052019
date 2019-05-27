<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADOPTER')">
	
	<form:form action="finder/adopter/edit.do" modelAttribute="finder">
		<form:hidden path="id"/>		
		
		<form:label path="keyword">
			<spring:message code="finder.keyword"/>
		</form:label>
		<form:input path="keyword"/>
		<form:errors cssClass ="error" path ="keyword"/>
		<br />
		
		<form:label path="age">
			<spring:message code="finder.age"/>
		</form:label>
		<form:input path="age"/>
		<form:errors cssClass ="error" path ="age"/>
		<br/>
		<jstl:if test="${language=='en'}">
		<form:label path="sex">
			<spring:message code="finder.sex"/>
		</form:label>
		<form:select path="sex">
		<form:option value="" label="-----"/>
		<form:option value="MALE" label="MALE"/>
		<form:option value="FEMALE" label="FEMALE"/>
		</form:select>
		<form:errors cssClass ="error" path ="sex"/>
		<br/>
		<form:label path="type">
			<spring:message code="finder.type"/>
		</form:label>
		<form:select path="type">
			<form:option value="" label="-----"/>
			<form:options items="${ptnames}"/>
		</form:select>
		</jstl:if>
				<jstl:if test="${language=='es'}">
		<form:label path="sex">
			<spring:message code="finder.sex"/>
		</form:label>
		<form:select path="sex">
		<form:option value="" label="-----"/>
		<form:option value="MALE" label="MACHO"/>
		<form:option value="FEMALE" label="HEMBRA"/>
		</form:select>
		<form:errors cssClass ="error" path ="sex"/>
		<br/>
		<form:label path="type">
			<spring:message code="finder.type"/>
		</form:label>
		<form:select path="type">
			<form:option value="" label="-----"/>
			<form:options items="${ptnombres}"/>
		</form:select>
		</jstl:if>
		
		<input type="button" name="cancel" value="<spring:message code="finder.cancel"/>" onclick ="javascript: relativeRedir('finder/adopter/show.do');"/>
		<input type="submit" name="save" value="<spring:message code="finder.save"/>"/>
		<input type="submit" name="clear" value="<spring:message code="finder.clear"/>"/>
	</form:form>
	
	
</security:authorize>