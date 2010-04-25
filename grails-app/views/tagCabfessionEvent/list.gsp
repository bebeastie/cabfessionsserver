
<%@ page import="com.cabfessions.TagCabfessionEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'tagCabfessionEvent.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="creationDate" title="${message(code: 'tagCabfessionEvent.creationDate.label', default: 'Creation Date')}" />
                        
                            <th><g:message code="tagCabfessionEvent.user.label" default="User" /></th>
                   	    
                            <g:sortableColumn property="tag" title="${message(code: 'tagCabfessionEvent.tag.label', default: 'Tag')}" />
                        
                            <th><g:message code="tagCabfessionEvent.cabfession.label" default="Cabfession" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tagCabfessionEventInstanceList}" status="i" var="tagCabfessionEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${tagCabfessionEventInstance.id}">${fieldValue(bean: tagCabfessionEventInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${tagCabfessionEventInstance.creationDate}" /></td>
                        
                            <td>${fieldValue(bean: tagCabfessionEventInstance, field: "user")}</td>
                        
                            <td>${fieldValue(bean: tagCabfessionEventInstance, field: "tag")}</td>
                        
                            <td>${fieldValue(bean: tagCabfessionEventInstance, field: "cabfession")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${tagCabfessionEventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
