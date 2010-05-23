
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
                                    <label for="neighborhood"><g:message code="cabfession.neighborhood.label" default="Neighborhood" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'neighborhood', 'errors')}">
                                    <g:select name="neighborhood.id" from="${com.cabfessions.Neighborhood.list()}" optionKey="id" value="${cabfessionInstance?.neighborhood?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountEw"><g:message code="cabfession.tagCountEw.label" default="Tag Count Ew" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountEw', 'errors')}">
                                    <g:textField name="tagCountEw" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountEw')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountGeeky"><g:message code="cabfession.tagCountGeeky.label" default="Tag Count Geeky" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountGeeky', 'errors')}">
                                    <g:textField name="tagCountGeeky" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountGeeky')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountWtf"><g:message code="cabfession.tagCountWtf.label" default="Tag Count Wtf" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountWtf', 'errors')}">
                                    <g:textField name="tagCountWtf" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountWtf')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountOmg"><g:message code="cabfession.tagCountOmg.label" default="Tag Count Omg" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountOmg', 'errors')}">
                                    <g:textField name="tagCountOmg" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountOmg')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountDevil"><g:message code="cabfession.tagCountDevil.label" default="Tag Count Devil" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountDevil', 'errors')}">
                                    <g:textField name="tagCountDevil" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountDevil')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountTrashy"><g:message code="cabfession.tagCountTrashy.label" default="Tag Count Trashy" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountTrashy', 'errors')}">
                                    <g:textField name="tagCountTrashy" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountTrashy')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountOld"><g:message code="cabfession.tagCountOld.label" default="Tag Count Old" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountOld', 'errors')}">
                                    <g:textField name="tagCountOld" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountOld')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountLol"><g:message code="cabfession.tagCountLol.label" default="Tag Count Lol" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountLol', 'errors')}">
                                    <g:textField name="tagCountLol" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountLol')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountAngel"><g:message code="cabfession.tagCountAngel.label" default="Tag Count Angel" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountAngel', 'errors')}">
                                    <g:textField name="tagCountAngel" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountAngel')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCountCute"><g:message code="cabfession.tagCountCute.label" default="Tag Count Cute" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cabfessionInstance, field: 'tagCountCute', 'errors')}">
                                    <g:textField name="tagCountCute" value="${fieldValue(bean: cabfessionInstance, field: 'tagCountCute')}" />
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
