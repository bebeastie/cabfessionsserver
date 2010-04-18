
<%@ page import="com.cabfessions.Cabfession" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cabfession.label', default: 'Cabfession')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'cabfession.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="creationDate" title="${message(code: 'cabfession.creationDate.label', default: 'Creation Date')}" />
                        
                            <th><g:message code="cabfession.owner.label" default="Owner" /></th>
                   	    
                            <th><g:message code="cabfession.cab.label" default="Cab" /></th>
                   	    
                            <g:sortableColumn property="text" title="${message(code: 'cabfession.text.label', default: 'Text')}" />
                        
                            <g:sortableColumn property="latitude" title="${message(code: 'cabfession.latitude.label', default: 'Latitude')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cabfessionInstanceList}" status="i" var="cabfessionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cabfessionInstance.id}">${fieldValue(bean: cabfessionInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${cabfessionInstance.creationDate}" /></td>
                        
                            <td>${fieldValue(bean: cabfessionInstance, field: "owner")}</td>
                        
                            <td>${fieldValue(bean: cabfessionInstance, field: "cab")}</td>
                        
                            <td>${fieldValue(bean: cabfessionInstance, field: "text")}</td>
                        
                            <td>${fieldValue(bean: cabfessionInstance, field: "latitude")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${cabfessionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
