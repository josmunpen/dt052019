<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="adopter/adopter/edit.do"
	modelAttribute="adopter">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />

	<fieldset>
		<legend align="left">
			<spring:message code="adopter.edit.contact" />
		</legend>

		<form:label path="name">
			<spring:message code="adopter.edit.label.name" />* :
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />

		<br /> <br />

		<form:label path="surname">
			<spring:message code="adopter.edit.label.surname" />* :
		</form:label>
		<form:input path="surname" />
		<form:errors cssClass="error" path="surname" />

		<br /> <br />

		<form:label path="address">
			<spring:message code="adopter.edit.label.address" />:
		</form:label>
		<form:input path="address" />
		<form:errors cssClass="error" path="address" />

		<br /> <br />

		<form:label path="email">
			<spring:message code="adopter.edit.label.email" />*:
		</form:label>
		<form:input path="email"/>
		<form:errors cssClass="error" path="email" />
		<br /> <br />


		<form:label path="phoneNumber">
			<spring:message code="adopter.edit.label.phoneNumber" />:
		</form:label>
		<form:input path="phoneNumber" onchange="check(this)"
			pattern="^(\d|\(|\)| |\+)+$" />
		<form:errors cssClass="error" path="phoneNumber" />

		<script language='javascript' type='text/javascript'>
			var re = /^\+\d{1,3} \(\d{1,3}\) \d{4,}$/;
			var re2 = /^\+\d{1,3} \d{4,}$/;
			var re3 = /^\d{4,}$/;

			function check(input) {
				var OK = re.exec(input.value);
				var OK2 = re2.exec(input.value);
				var OK3 = re3.exec(input.value);
				if (!(OK || OK2 || OK3)) {
					alert("<spring:message code="adopter.confirm" />");
				}
			}
		</script>
		<br /> <br />

		<form:label path="photo">
			<spring:message code="adopter.edit.label.photo" />:
		</form:label>
		<form:input path="photo" />
		<form:errors cssClass="error" path="photo" />

		<br /> <br />

		<form:label path="description">
			<spring:message code="adopter.edit.label.description" />*:
		</form:label>
		<form:input path="description" />
		<form:errors cssClass="error" path="description" />

		<br /> <br />
	</fieldset>
	<br />
	<br />

	<fieldset>
		<legend align="left">
			<spring:message code="adopter.creditCard" />
		</legend>
		
		<form:label path="holderName">
			<spring:message code="adopter.holderName" />* :
		</form:label>
		<form:input path="holderName" />
		<form:errors cssClass="error" path="holderName" />
		<br />
		
		<form:label path="makeName">
			<spring:message code="adopter.makeName" />* :
		</form:label>
		<form:input path="makeName" />
		<form:errors cssClass="error" path="makeName" />
		<br />
				
		<form:label path="number">
			<spring:message code="adopter.number" />* :
		</form:label>
		<form:input path="number" />
		<form:errors cssClass="error" path="number" />
		<br />
		
		<form:label path="expirationYear">
			<spring:message code="adopter.expirationYear" />* :
		</form:label>
		<form:input path="expirationYear" />
		<form:errors cssClass="error" path="expirationYear" />
		<br />	
					
		<form:label path="expirationMonth">
			<spring:message code="adopter.expirationMonth" />* :
		</form:label>
		<form:input path="expirationMonth" />
		<form:errors cssClass="error" path="expirationMonth" />
		<br />	
				
		<form:label path="cvv">
			<spring:message code="adopter.cvv" />* :
		</form:label>
		<form:input path="cvv" />
		<form:errors cssClass="error" path="cvv" />
		<br />			
	</fieldset>

	<br />
	<spring:message code="adopter.leave.explanation"
		var="leaveExplanation" />
	<jstl:out value="${leaveExplanation}" />

	<a href="adopter/adopter/show.do"> Link </a>

	<br />
	<input type="submit" name="save"
		value="<spring:message code="adopter.edit.save.save" />" />&nbsp;
	<input type="submit" name="leave"
		value="<spring:message code="adopter.edit.leave" />"
		onclick="return confirm('<spring:message code="adopter.confirm.delete" />')" />&nbsp;
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="adopter.edit.cancel" />" />

</form:form>