
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table name="applications" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	<jstl:choose>
		<jstl:when test="${row.status == 'ACCEPTED' }">
			<display:column style="background-color:lightgreen" property="moment"
				titleKey="application.moment" />

			<display:column style="background-color:lightgreen"
				property="comment" titleKey="application.comment" />
			<display:column style="background-color:lightgreen"
				property="adopter.name" titleKey="application.adopter" />
			<display:column style="background-color:lightgreen"
				property="pet.name" titleKey="application.pet" />

			<display:column style="background-color:lightgreen">
				<jstl:if test="${row.status == 'PENDING'}">
					<a href="application/petowner/edit.do?applicationId=${row.id}">
						<spring:message code="application.edit" />
					</a>
				</jstl:if>
			</display:column>

			<jstl:if test="${pageContext.response.locale.language=='en'}">
				<display:column style="background-color:lightgreen"
					property="status" titleKey="application.status" />
			</jstl:if>

			<jstl:if test="${pageContext.response.locale.language=='es'}">
				<display:column style="background-color:lightgreen"
					titleKey="application.status">
					<jstl:if test="${row.status == 'ACCEPTED' }">
						<spring:message code="application.accepted" />
					</jstl:if>
					<jstl:if test="${row.status == 'REJECTED' }">
						<spring:message code="application.rejected" />
					</jstl:if>
					<jstl:if test="${row.status == 'PENDING' }">
						<spring:message code="application.pending" />
					</jstl:if>

				</display:column>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${row.status == 'PENDING' }">
			<display:column style="background-color:lightorange"
				property="moment" titleKey="application.moment" />

			<display:column style="background-color:lightorange"
				property="comment" titleKey="application.comment" />
			<display:column style="background-color:lightorange"
				property="adopter.name" titleKey="application.adopter" />
			<display:column style="background-color:lightorange"
				property="pet.name" titleKey="application.pet" />

			<display:column style="background-color:lightorange">
				<jstl:if test="${row.status == 'PENDING'}">
					<a href="application/petowner/edit.do?applicationId=${row.id}">
						<spring:message code="application.edit" />
					</a>
				</jstl:if>
			</display:column>

			<jstl:if test="${pageContext.response.locale.language=='en'}">
				<display:column style="background-color:lightorange"
					property="status" titleKey="application.status" />
			</jstl:if>

			<jstl:if test="${pageContext.response.locale.language=='es'}">
				<display:column style="background-color:lightorange"
					titleKey="application.status">
					<jstl:if test="${row.status == 'ACCEPTED' }">
						<spring:message code="application.accepted" />
					</jstl:if>
					<jstl:if test="${row.status == 'REJECTED' }">
						<spring:message code="application.rejected" />
					</jstl:if>
					<jstl:if test="${row.status == 'PENDING' }">
						<spring:message code="application.pending" />
					</jstl:if>

				</display:column>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${row.status == 'REJECTED' }">
			<display:column style="background-color:lightcoral" property="moment"
				titleKey="application.moment" />

			<display:column style="background-color:lightcoral"
				property="comment" titleKey="application.comment" />
			<display:column style="background-color:lightcoral"
				property="adopter.name" titleKey="application.adopter" />
			<display:column style="background-color:lightcoral"
				property="pet.name" titleKey="application.pet" />

			<display:column style="background-color:lightcoral">
				<jstl:if test="${row.status == 'PENDING'}">
					<a href="application/petowner/edit.do?applicationId=${row.id}">
						<spring:message code="application.edit" />
					</a>
				</jstl:if>
			</display:column>

			<jstl:if test="${pageContext.response.locale.language=='en'}">
				<display:column style="background-color:lightcoral"
					property="status" titleKey="application.status" />
			</jstl:if>

			<jstl:if test="${pageContext.response.locale.language=='es'}">
				<display:column style="background-color:lightcoral"
					titleKey="application.status">
					<jstl:if test="${row.status == 'ACCEPTED' }">
						<spring:message code="application.accepted" />
					</jstl:if>
					<jstl:if test="${row.status == 'REJECTED' }">
						<spring:message code="application.rejected" />
					</jstl:if>
					<jstl:if test="${row.status == 'PENDING' }">
						<spring:message code="application.pending" />
					</jstl:if>

				</display:column>
			</jstl:if>
		</jstl:when>
	</jstl:choose>

</display:table>

