package com.cabfessions.api.controllers

import com.cabfessions.api.ApiUtil as U
import com.cabfessions.*
import grails.converters.JSON

class ApiMainController {
	
	def login = {
		def clientId = params.client_id
		def clientType = params.client_type	
		
		def isNew = true
		User userInstance 
		
		if (clientId) { 
			userInstance = User.findByClientId(clientId)
		} 
		
		if (userInstance) {
			isNew = false
		} else {
			userInstance = new User(clientType: clientType, clientId: clientId, 
			hashKey: U.generateUserHashKey())
			userInstance.save(flush:true)
		}
		
		if (userInstance.errors.hasErrors()) {
			render U.wrapError("A valid client_id and client_type are required.") as JSON
			return
		} 
		
		
		def userMap = [:]
		userMap.is_new = isNew
		userMap.hash_key = userInstance.hashKey	
		
		render U.wrapResponse([user:userMap],true) as JSON 
	}	
}
