
<%@ page import="com.cabfessions.Cab" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cab.label', default: 'Cab')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'cab.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="badge" title="${message(code: 'cab.badge.label', default: 'Badge')}" />
                        
                            <th><g:message code="cab.city.label" default="City" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cabInstanceList}" status="i" var="cabInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cabInstance.id}">${fieldValue(bean: cabInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: cabInstance, field: "badge")}</td>
                        
                            <td>${fieldValue(bean: cabInstance, field: "city")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${cabInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
