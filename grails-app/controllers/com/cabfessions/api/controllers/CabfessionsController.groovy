package com.cabfessions.api.controllers


import grails.converters.JSON
import com.cabfessions.Cabfession
import com.cabfessions.User
import com.cabfessions.Cab
import com.cabfessions.api.*
import com.cabfessions.util.*

class CabfessionsController {
	static allowedMethods = [create_cabfession: ["POST","GET"], update: "POST", delete: "POST"]
	
	
	def get_or_create_user = {
		def clientId = params.clientId
		def clientType = params.clientType
		
		
		def output
		def isNew = true
		User userInstance 
		
		
		if (params.clientId) {
			userInstance = User.findByClientId(clientId)
		} 
		
		if (userInstance) {
			isNew = false
		} else {
			userInstance = new User(clientType: params.clientType, clientId: params.clientId, 
					userKey: Utils.generateUserKey())
			userInstance.save(flush:true)
		}
		
		if (userInstance.errors.hasErrors()) {
			output = [errors: userInstance.errors]
		} else {
			def userMap = [:]
			userMap.isNew = isNew
			userMap.key = userInstance.userKey		
			output = [user: userMap] 		
		}
		 
		render output as JSON 
	}
	
	def cabfessions_by_cab = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		def cabfessionList = [ ]
		
		Cabfession.list(params).each { cabfession ->
			def cabfessionMap = [:]
			cabfessionMap.id = cabfession.id
			cabfessionMap.text = cabfession.text
			cabfessionMap.latitude = cabfession.latitude
			cabfessionMap.longitude = cabfession.longitude
			cabfessionList << cabfessionMap
		}
		
		def output = [ cabfessions: cabfessionList ]
		
		render output as JSON
	}
	
	def create_cabfession = {
		def cabfessionInstance = new Cabfession()
		
		cabfessionInstance.text = params.text
		cabfessionInstance.longitude = params.double('longitude')
		cabfessionInstance.latitude = params.double('latitude')
		cabfessionInstance.creationDate = new Date()
		cabfessionInstance.owner = new User(clientType: "clientType", clientId: "clientId").save()
		cabfessionInstance.cab = new Cab(badge:"4F56").save()
		
		if (cabfessionInstance.save(flush: true)) {
			render "success"
		} else {
			render cabfessionInstance.errors as JSON  
		}
		
		
	}
	
}
