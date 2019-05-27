<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Acme Shelter Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADOPTER')">
			<li><a class="fNiv"><spring:message	code="master.page.adopter" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="adopter/adopter/edit.do"><spring:message code="master.page.edit.adopter" /></a></li>
			</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('PETOWNER')">
			<li><a class="fNiv"><spring:message	code="master.page.petOwner" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="petowner/petowner/edit.do"><spring:message code="master.page.edit.petOwner" /></a></li>
					<li><a href="pet/petOwner/list.do"><spring:message code="master.page.petOwner.pets" /></a></li>
										
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="adopter/register.do"><spring:message code="master.page.register.adopter" /></a></li>
			<li><a class="fNiv" href="petowner/register.do"><spring:message code="master.page.register.petowner" /></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>

		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

