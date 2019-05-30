<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form modelAttribute="medicalCheckUp" action="checkup/veterinarian/edit.do">
		<form:hidden path="id"/>
		<form:hidden path="moment"/>
		
		<form:label path="description">
			<spring:message code="checkup.description" />:*
		</form:label>
		<form:input path="description" />
		<form:errors cssClass="error" path="description" />
		<br />	

		<form:label path="stateOfHealth">
			<spring:message code="checkup.stateOfHealth" />:*
		</form:label>
		<form:select path="stateOfHealth">
		<jstl:if test="${pageContext.response.locale.language=='en'}">
			<form:option value="EXCELENT">Excelent</form:option>
			<form:option value="GOOD">Good</form:option>
			<form:option value="BAD">Bad</form:option>
			<form:option value="CRITICAL">Critical</form:option>
		</jstl:if>
		<jstl:if test="${pageContext.response.locale.language=='es'}">
			<form:option value="EXCELENT">Excelente</form:option>
			<form:option value="GOOD">Bueno</form:option>
			<form:option value="BAD">Malo</form:option>
			<form:option value="CRITICAL">Crítico</form:option>
		</jstl:if>
		</form:select>
		<form:errors cssClass="error" path="stateOfHealth" />
		<br />	

		<jstl:if test="${medicalCheckUp.id==0}">
			<form:label path="pet">
				<spring:message code="checkup.pet" />* :
			</form:label>
			<form:select path="pet" >
			<jstl:forEach items="${pets}" var="pet">
			<jstl:if test="${pageContext.response.locale.language=='es'}">
			<form:option value="${pet}"><jstl:out value="${pet.nombre}"/></form:option>
			</jstl:if>
			<jstl:if test="${pageContext.response.locale.language=='en'}">
			<form:option value="${pet}"><jstl:out value="${pet.name}"/></form:option>
			</jstl:if>
			</jstl:forEach>
			</form:select>
			<form:errors cssClass="error" path="pet" />
		</jstl:if>				

		<br/>
			
	<input type ="submit" name="save" value="<spring:message code="checkup.save"/>" />

	<input type="button" name="cancel" value="<spring:message code="checkup.cancel" />" onclick="javascript:relativeRedir('checkup/veterinarian/list.do?checkupId=${checkupId}');" />
	<jstl:if test ="${medicalCheckUp.id !=0}">
		<input type ="submit" name="delete" value="<spring:message code="checkup.delete"/>" />
	</jstl:if>

</form:form>





