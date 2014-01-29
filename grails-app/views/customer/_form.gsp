<%@ page import="mx.jhcue.edlm.Customer" %>



<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="customer.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" maxlength="128" value="${customerInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'fatherLastName', 'error')} ">
	<label for="fatherLastName">
		<g:message code="customer.fatherLastName.label" default="Father Last Name" />
		
	</label>
	<g:textField name="fatherLastName" maxlength="128" value="${customerInstance?.fatherLastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'motherLastName', 'error')} ">
	<label for="motherLastName">
		<g:message code="customer.motherLastName.label" default="Mother Last Name" />
		
	</label>
	<g:textField name="motherLastName" maxlength="128" value="${customerInstance?.motherLastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'curp', 'error')} ">
	<label for="curp">
		<g:message code="customer.curp.label" default="Curp" />
		
	</label>
	<g:textField name="curp" maxlength="18" pattern="${customerInstance.constraints.curp.matches}" value="${customerInstance?.curp}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'rfc', 'error')} ">
	<label for="rfc">
		<g:message code="customer.rfc.label" default="Rfc" />
		
	</label>
	<g:textField name="rfc" maxlength="13" pattern="${customerInstance.constraints.rfc.matches}" value="${customerInstance?.rfc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="customer.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${customerInstance?.active}" />
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'birthDate', 'error')} required">
	<label for="birthDate">
		<g:message code="customer.birthDate.label" default="Birth Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="birthDate" precision="day"  value="${customerInstance?.birthDate}"  />
</div>

