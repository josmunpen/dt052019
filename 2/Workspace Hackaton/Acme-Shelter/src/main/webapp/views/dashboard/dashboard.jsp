<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
<h3><spring:message code="dashboard.statictics"/></h3>

	<spring:message code="dashboard.avgPets"/>
	<jstl:out value="${avgPets}"/>
	<br/>
	<spring:message code="dashboard.minPets"/>
	<jstl:out value="${minPets}"/>
	<br/>
	<spring:message code="dashboard.maxPets"/>
	<jstl:out value="${maxPets}"/>
	<br/>
	<spring:message code="dashboard.stddevPets"/>
	<jstl:out value="${stddevPets}"/>
	<br/>
	
	<spring:message code="dashboard.avgApplications"/>
	<jstl:out value="${avgApplications}"/>
	<br/>
	<spring:message code="dashboard.minApplications"/>
	<jstl:out value="${minApplications}"/>
	<br/>
	<spring:message code="dashboard.maxApplications"/>
	<jstl:out value="${maxApplications}"/>
	<br/>
	<spring:message code="dashboard.stddevApplications"/>
	<jstl:out value="${stddevApplications}"/>
	<br/>
	
	<spring:message code="dashboard.avgAge"/>
	<jstl:out value="${avgAge}"/>
	<br/>
	<spring:message code="dashboard.minAge"/>
	<jstl:out value="${minAge}"/>
	<br/>
	<spring:message code="dashboard.maxAge"/>
	<jstl:out value="${maxAge}"/>
	<br/>
	<spring:message code="dashboard.stddevAge"/>
	<jstl:out value="${stddevAge}"/>
	<br/>
	
	<spring:message code="dashboard.avgHistories"/>
	<jstl:out value="${avgHistories}"/>
	<br/>
	<spring:message code="dashboard.minHistories"/>
	<jstl:out value="${minHistories}"/>
	<br/>
	<spring:message code="dashboard.maxHistories"/>
	<jstl:out value="${maxHistories}"/>
	<br/>
	<spring:message code="dashboard.stddevHistories"/>
	<jstl:out value="${stddevHistories}"/>
	<br/>
	
	<spring:message code="dashboard.avgMedicalCheckUp"/>
	<jstl:out value="${avgMedicalCheckUp}"/>
	<br/>
	<spring:message code="dashboard.minMedicalCheckUp"/>
	<jstl:out value="${minMedicalCheckUp}"/>
	<br/>
	<spring:message code="dashboard.maxMedicalCheckUp"/>
	<jstl:out value="${maxMedicalCheckUp}"/>
	<br/>
	<spring:message code="dashboard.stddevMedicalCheckUp"/>
	<jstl:out value="${stddevMedicalCheckUp}"/>
	<br/>
	
<h3><spring:message code="dashboard.tops"/></h3>
	<jstl:if test="${language=='en'}">
	<spring:message code="dashboard.top3TypesEng"/>
	<jstl:out value="${top3TypesEng}"/>
	<br/>
	</jstl:if>
	<jstl:if test="${language=='es'}">
	<spring:message code="dashboard.top3TypesEsp"/>
	<jstl:out value="${top3TypesEsp}"/>
	<br/>
	</jstl:if>
	<spring:message code="dashboard.top3Veterinarians"/>
	<jstl:out value="${top3Veterinarians}"/>
	<br/>
	<spring:message code="dashboard.top3Adopters"/>
	<jstl:out value="${top3Adopters}"/>
	<br/>
	
<h3><spring:message code="dashboard.ratios"/></h3>
	<spring:message code="dashboard.ratioPendingApplications"/>
	<jstl:out value="${ratioPendingApplications}"/>
	<br/>
	<spring:message code="dashboard.ratioAcceptedApplications"/>
	<jstl:out value="${ratioAcceptedApplications}"/>
	<br/>
	<spring:message code="dashboard.ratioRejectedApplications"/>
	<jstl:out value="${ratioRejectedApplications}"/>
	<br/>
	<spring:message code="dashboard.ratioPetsWithMedicalCheckUp"/>
	<jstl:out value="${ratioPetsWithMedicalCheckUp}"/>
	<br/>
	
	
</security:authorize>