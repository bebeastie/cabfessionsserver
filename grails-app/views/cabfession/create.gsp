
<%@ page import="com.cabfessions.Cabfession" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cabfession.label', default: 'Cabfession')}" />
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
            <g:hasErrors bean="${cabfessionInstance}">
            <div class="errors">
                <g:renderErrors bean="${cabfessionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="creationDate"><g:message code="cabfession.creationDate.label" default="Creation Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'creationDate', 'errors')}">
                                    <g:datePicker name="creationDate" precision="day" value="${cabfessionInstance?.creationDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="owner"><g:message code="cabfession.owner.label" default="Owner" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'owner', 'errors')}">
                                    <g:select name="owner.id" from="${com.cabfessions.User.list()}" optionKey="id" value="${cabfessionInstance?.owner?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cab"><g:message code="cabfession.cab.label" default="Cab" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'cab', 'errors')}">
                                    <g:select name="cab.id" from="${com.cabfessions.Cab.list()}" optionKey="id" value="${cabfessionInstance?.cab?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="text"><g:message code="cabfession.text.label" default="Text" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'text', 'errors')}">
                                    <g:textField name="text" value="${cabfessionInstance?.text}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="latitude"><g:message code="cabfession.latitude.label" default="Latitude" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'latitude', 'errors')}">
                                    <g:textField name="latitude" value="${fieldValue(bean: cabfessionInstance, field: 'latitude')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="longitude"><g:message code="cabfession.longitude.label" default="Longitude" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'longitude', 'errors')}">
                                    <g:textField name="longitude" value="${fieldValue(bean: cabfessionInstance, field: 'longitude')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountFunny"><g:message code="cabfession.tagCountFunny.label" default="Tag Count Funny" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountFunny', 'errors')}">
                                    <g:textField name="tagCountFunny" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountFunny')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountCrazy"><g:message code="cabfession.tagCountCrazy.label" default="Tag Count Crazy" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountCrazy', 'errors')}">
                                    <g:textField name="tagCountCrazy" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountCrazy')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountScary"><g:message code="cabfession.tagCountScary.label" default="Tag Count Scary" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountScary', 'errors')}">
                                    <g:textField name="tagCountScary" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountScary')}" />
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
