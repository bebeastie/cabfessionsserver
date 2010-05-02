
<%@ page import="com.cabfessions.Neighborhood" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'neighborhood.label', default: 'Neighborhood')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="neighborhood.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: neighborhoodInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="neighborhood.city.label" default="City" /></td>
                            
                            <td valign="top" class="value"><g:link controller="city" action="show" id="${neighborhoodInstance?.city?.id}">${neighborhoodInstance?.city?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="neighborhood.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: neighborhoodInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="neighborhood.borough.label" default="Borough" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: neighborhoodInstance, field: "borough")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="neighborhood.state.label" default="State" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: neighborhoodInstance, field: "state")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="neighborhood.stateCode.label" default="State Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: neighborhoodInstance, field: "stateCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="neighborhood.country.label" default="Country" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: neighborhoodInstance, field: "country")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="neighborhood.countryCode.label" default="Country Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: neighborhoodInstance, field: "countryCode")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${neighborhoodInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
