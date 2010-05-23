package com.cabfessions.api.controllers

import grails.test.*
import grails.converters.JSON
import com.cabfessions.api.ApiUtil as U

class ApiMainControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testUserKeyAllRequired() {
    	//with all supplied fields
    	ApiMainController.metaClass.getParams = {-> [client_id:"1234",client_type:"iPhone"]}
    	def controller = new ApiMainController()
    	controller.login()
    	def json = JSON.parse(controller.getResponse().getContentAsString())
    	assertEquals json.header.status, U.HEADER_OK
    }
    
    void testUserKeyNoClientId() {
    	//with all supplied fields
    	ApiMainController.metaClass.getParams = {-> [client_type:"iPhone"]}
    	def controller = new ApiMainController()
    	controller.login()
    	def json = JSON.parse(controller.getResponse().getContentAsString())
    	assertEquals json.header.status, U.HEADER_ERROR
    }
    
    void testUserKeyNoClientType() {
    	//with all supplied fields
    	ApiMainController.metaClass.getParams = {-> [client_id:"1234"]}
    	def controller = new ApiMainController()
    	controller.login()
    	def json = JSON.parse(controller.getResponse().getContentAsString())
    	assertEquals json.header.status, U.HEADER_ERROR
    }
    
    
}
