
<%@ page import="com.cabfessions.TagCabfessionEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tagCabfessionEventInstance}">
            <div class="errors">
                <g:renderErrors bean="${tagCabfessionEventInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${tagCabfessionEventInstance?.id}" />
                <g:hiddenField name="version" value="${tagCabfessionEventInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="creationDate"><g:message code="tagCabfessionEvent.creationDate.label" default="Creation Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tagCabfessionEventInstance, field: 'creationDate', 'errors')}">
                                    <g:datePicker name="creationDate" precision="day" value="${tagCabfessionEventInstance?.creationDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="user"><g:message code="tagCabfessionEvent.user.label" default="User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tagCabfessionEventInstance, field: 'user', 'errors')}">
                                    <g:select name="user.id" from="${com.cabfessions.User.list()}" optionKey="id" value="${tagCabfessionEventInstance?.user?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tag"><g:message code="tagCabfessionEvent.tag.label" default="Tag" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tagCabfessionEventInstance, field: 'tag', 'errors')}">
                                    <g:select name="tag" from="${tagCabfessionEventInstance.constraints.tag.inList}" value="${tagCabfessionEventInstance?.tag}" valueMessagePrefix="tagCabfessionEvent.tag"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="cabfession"><g:message code="tagCabfessionEvent.cabfession.label" default="Cabfession" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tagCabfessionEventInstance, field: 'cabfession', 'errors')}">
                                    <g:select name="cabfession.id" from="${com.cabfessions.Cabfession.list()}" optionKey="id" value="${tagCabfessionEventInstance?.cabfession?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
