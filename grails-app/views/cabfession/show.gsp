
<%@ page import="com.cabfessions.Cabfession" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cabfession.label', default: 'Cabfession')}" />
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
                            <td valign="top" class="name"><g:message code="cabfession.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.creationDate.label" default="Creation Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${cabfessionInstance?.creationDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.owner.label" default="Owner" /></td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${cabfessionInstance?.owner?.id}">${cabfessionInstance?.owner?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.cab.label" default="Cab" /></td>
                            
                            <td valign="top" class="value"><g:link controller="cab" action="show" id="${cabfessionInstance?.cab?.id}">${cabfessionInstance?.cab?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.text.label" default="Text" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "text")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.latitude.label" default="Latitude" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "latitude")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.longitude.label" default="Longitude" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "longitude")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.neighborhood.label" default="Neighborhood" /></td>
                            
                            <td valign="top" class="value"><g:link controller="neighborhood" action="show" id="${cabfessionInstance?.neighborhood?.id}">${cabfessionInstance?.neighborhood?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tags.label" default="Tags" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${cabfessionInstance.tags}" var="t">
                                    <li><g:link controller="tagCabfessionEvent" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountEw.label" default="Tag Count Ew" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountEw")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountGeeky.label" default="Tag Count Geeky" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountGeeky")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountWtf.label" default="Tag Count Wtf" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountWtf")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountOmg.label" default="Tag Count Omg" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountOmg")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountDevil.label" default="Tag Count Devil" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountDevil")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountTrashy.label" default="Tag Count Trashy" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountTrashy")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountOld.label" default="Tag Count Old" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountOld")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountLol.label" default="Tag Count Lol" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountLol")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountAngel.label" default="Tag Count Angel" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountAngel")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cabfession.tagCountCute.label" default="Tag Count Cute" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cabfessionInstance, field: "tagCountCute")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${cabfessionInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
