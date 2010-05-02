
<%@ page import="com.cabfessions.Neighborhood" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'neighborhood.label', default: 'Neighborhood')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${neighborhoodInstance}">
            <div class="errors">
                <g:renderErrors bean="${neighborhoodInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city"><g:message code="neighborhood.city.label" default="City" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: neighborhoodInstance, field: 'city', 'errors')}">
                                    <g:select name="city.id" from="${com.cabfessions.City.list()}" optionKey="id" value="${neighborhoodInstance?.city?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="neighborhood.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: neighborhoodInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${neighborhoodInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="borough"><g:message code="neighborhood.borough.label" default="Borough" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: neighborhoodInstance, field: 'borough', 'errors')}">
                                    <g:textField name="borough" value="${neighborhoodInstance?.borough}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="state"><g:message code="neighborhood.state.label" default="State" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: neighborhoodInstance, field: 'state', 'errors')}">
                                    <g:textField name="state" value="${neighborhoodInstance?.state}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="stateCode"><g:message code="neighborhood.stateCode.label" default="State Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: neighborhoodInstance, field: 'stateCode', 'errors')}">
                                    <g:textField name="stateCode" value="${neighborhoodInstance?.stateCode}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="country"><g:message code="neighborhood.country.label" default="Country" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: neighborhoodInstance, field: 'country', 'errors')}">
                                    <g:textField name="country" value="${neighborhoodInstance?.country}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="countryCode"><g:message code="neighborhood.countryCode.label" default="Country Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: neighborhoodInstance, field: 'countryCode', 'errors')}">
                                    <g:textField name="countryCode" value="${neighborhoodInstance?.countryCode}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
