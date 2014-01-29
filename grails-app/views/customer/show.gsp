
<%@ page import="mx.jhcue.edlm.Customer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customer.label', default: 'Customer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-customer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-customer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list customer">
			
				<g:if test="${customerInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="customer.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${customerInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.fatherLastName}">
				<li class="fieldcontain">
					<span id="fatherLastName-label" class="property-label"><g:message code="customer.fatherLastName.label" default="Father Last Name" /></span>
					
						<span class="property-value" aria-labelledby="fatherLastName-label"><g:fieldValue bean="${customerInstance}" field="fatherLastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.motherLastName}">
				<li class="fieldcontain">
					<span id="motherLastName-label" class="property-label"><g:message code="customer.motherLastName.label" default="Mother Last Name" /></span>
					
						<span class="property-value" aria-labelledby="motherLastName-label"><g:fieldValue bean="${customerInstance}" field="motherLastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.curp}">
				<li class="fieldcontain">
					<span id="curp-label" class="property-label"><g:message code="customer.curp.label" default="Curp" /></span>
					
						<span class="property-value" aria-labelledby="curp-label"><g:fieldValue bean="${customerInstance}" field="curp"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.rfc}">
				<li class="fieldcontain">
					<span id="rfc-label" class="property-label"><g:message code="customer.rfc.label" default="Rfc" /></span>
					
						<span class="property-value" aria-labelledby="rfc-label"><g:fieldValue bean="${customerInstance}" field="rfc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.active}">
				<li class="fieldcontain">
					<span id="active-label" class="property-label"><g:message code="customer.active.label" default="Active" /></span>
					
						<span class="property-value" aria-labelledby="active-label"><g:formatBoolean boolean="${customerInstance?.active}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.birthDate}">
				<li class="fieldcontain">
					<span id="birthDate-label" class="property-label"><g:message code="customer.birthDate.label" default="Birth Date" /></span>
					
						<span class="property-value" aria-labelledby="birthDate-label"><g:formatDate date="${customerInstance?.birthDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:customerInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${customerInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
