<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="pet/petOwner/edit.do"
	modelAttribute="pet">
	<jstl:if test="${pet.id==0 }">
	<form:hidden path="identifier"/>
	</jstl:if>
	<form:hidden path="id" />

	<fieldset>
		<legend align="left">
			<spring:message code="edit.pet" />
		</legend>

		<form:label path="address">
			<spring:message code="pet.address" />* :
		</form:label>
		<form:input path="address" />
		<form:errors cssClass="error" path="address" />
		<br /> <br />
		
		<form:label path="age">
			<spring:message code="pet.age" />:
		</form:label>
		<form:input path="age" />
		<form:errors cssClass="error" path="age" />
		<br /> <br />
		
		<form:label path="careRequirements">
			<spring:message code="pet.care.requirements" />:
		</form:label>
		<form:input path="careRequirements" />
		<form:errors cssClass="error" path="careRequirements" />
		<br /> <br />
		
		<form:label path="dietRequirements">
			<spring:message code="pet.diet.requirements" />:
		</form:label>
		<form:input path="dietRequirements" />
		<form:errors cssClass="error" path="dietRequirements" />
		<br /> <br />
		
		<form:label path="familyRequirements">
			<spring:message code="pet.family.requirements" />:
		</form:label>
		<form:input path="familyRequirements" />
		<form:errors cssClass="error" path="familyRequirements" />
		<br /> <br />
		
		<form:label path="managementCost">
			<spring:message code="pet.management.cost" />* :
		</form:label>
		<form:input path="managementCost" />
		<form:errors cssClass="error" path="managementCost" />
		<br /> <br />
		
		<form:label path="name">
			<spring:message code="pet.name" />* :
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br /> <br />
		
		<form:label path="nature">
			<spring:message code="pet.nature" />:
		</form:label>
		<form:input path="nature" />
		<form:errors cssClass="error" path="nature" />
		<br /> <br />
		
		<form:label path="pedigree">
			<spring:message code="pet.pedigree" />:
		</form:label>
		<form:input path="pedigree" />
		<form:errors cssClass="error" path="pedigree" />
		<br /> <br />
		
		<form:label path="petsRequirements">
			<spring:message code="pet.requirements" />:
		</form:label>
		<form:input path="petsRequirements" />
		<form:errors cssClass="error" path="petsRequirements" />
		<br /> <br />
		
		<form:label path="petType">
			<spring:message code="pet.type" />* :
		</form:label>
		<form:select path="petType" >
		<jstl:forEach items="${lpt}" var="pt">
		<jstl:if test="${pageContext.response.locale.language=='es'}">
		<form:option value="${pt}"><jstl:out value="${pt.nombre}"/></form:option>
		</jstl:if>
		<jstl:if test="${pageContext.response.locale.language=='en'}">
		<form:option value="${pt}"><jstl:out value="${pt.name}"/></form:option>
		</jstl:if>
		</jstl:forEach>
		</form:select>
		
		<form:errors cssClass="error" path="petType" />
		<br /> <br />
		
		<form:label path="photos">
			<spring:message code="pet.photos" />:
		</form:label>
		<form:input path="photos" />
		<form:errors cssClass="error" path="photos" />
		<br /> <br />
		
		<form:label path="sex">
			<spring:message code="pet.sex" />* :
		</form:label>
		<form:select path="sex">
		<jstl:if test="${pageContext.response.locale.language=='es'}">
		<form:option value="MALE">Macho</form:option>
		<form:option value="FEMALE">Hembra</form:option>
		</jstl:if>
		<jstl:if test="${pageContext.response.locale.language=='en'}">
		<form:option value="MALE">Male</form:option>
		<form:option value="FEMALE">Female</form:option>
		</jstl:if>
		</form:select>
		<form:errors cssClass="error" path="sex" />
		<br /> <br />
		
		<form:label path="status">
			<spring:message code="pet.status" />* :
		</form:label>
		<form:select path="status">
		<jstl:if test="${pageContext.response.locale.language=='es'}">
		<form:option value="LOW">Malo</form:option>
		<form:option value="MEDIUM">Neutro</form:option>
		<form:option value="HIGH">Bueno</form:option>
		</jstl:if>
		<jstl:if test="${pageContext.response.locale.language=='en'}">
		<form:option value="LOW">Low</form:option>
		<form:option value="MEDIUM">Medium</form:option>
		<form:option value="HIGH">High</form:option>
		</jstl:if>
		</form:select>
		<form:errors cssClass="error" path="status" />
		<br /> <br />
		

	</fieldset>
	<br />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="pet.save" />" />&nbsp;
	<jstl:if test="${pet.id!=0 }">
	<input type="submit" name="delete"
		value="<spring:message code="pet.delete" />"
		onclick="return confirm('<spring:message code="pet.confirm.delete" />')" />&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('pet/petOwner/list.do')"
		value="<spring:message code="pet.edit.cancel" />" />

</form:form>