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
			key: Utils.generateUserKey())
			userInstance.save(flush:true)
		}
		
		if (userInstance.errors.hasErrors()) {
			output = [errors: userInstance.errors]
		} else {
			def userMap = [:]
			userMap.isNew = isNew
			userMap.key = userInstance.key		
			output = [user: userMap] 		
		}
		
		render output as JSON 
	}
	
	def get_cabfessions_by_cab = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		def cabfessionList = [ ]
		
		Cabfession.list(params).each { cabfession ->
			cabfessionList << cabfession
		}
		
		def output = [ cabfessions: cabfessionList ]
		
		render output as JSON
	}
	
	def create_cabfession = {
		
		//the eventual JSON output
		def output
		
		def cabfessionInstance = new Cabfession()
		
		//populate basic info 
		cabfessionInstance.text = params.text 
		cabfessionInstance.longitude = params.double('longitude') 
		cabfessionInstance.latitude = params.double('latitude') 
		cabfessionInstance.creationDate = new Date() 
		
		//check and verify the user
		User user
		if (params.user_key)  {
			user = User.findByKey(params.user_key)
		}
		 
		if (!user) {
			//user is required and must be valid
			output = getUserKeyError() 
			render output as JSON
			return
		}
		
		//set the owner
		cabfessionInstance.owner = user	
		
		//look for the cab
		Cab cab
		
		if (params.cab_badge) {
			cab = Cab.findByBadge(params.cab_badge)
			if (!cab) {
				cab = new Cab(badge: params.cab_badge)
				if (cab.hasErrors() || !cab.save(flush:true)) {
					//cab badge is invalid
					output =  [errors: cab.errors]
					render output as JSON
					return			
				}
			}
		} else {
			//cab badge # is required
			output =  getCabBadgeRequiredError() 
			render output as JSON
			return
		}
		
		//now set the cab
		cabfessionInstance.cab = cab
		
		if (cabfessionInstance.save(flush: true)) {
			output = [cabfession: cabfessionInstance]
		} else {
			output = [errors: cabfessionInstance.errors]  
		}		
		render output as JSON
	}
	
	protected static HashMap buildCabfessionJSON(Cabfession cabfession) { 
		def cabfessionMap = [:]
		cabfessionMap.id = cabfession.id
		cabfessionMap.creationDate = cabfession.creationDate
		cabfessionMap.cab = cabfession.cab
		cabfessionMap.text = cabfession.text
		cabfessionMap.latitude = cabfession.latitude
		cabfessionMap.longitude = cabfession.longitude
		return cabfessionMap
	}
	
	protected static HashMap getUserKeyError() {
		[errors: "user_key is invalid or not supplied."]
	}
	
	protected static HashMap getCabBadgeError() { 
		[errors: "cab_badge is invalid"]
	}
	
	protected static HashMap getCabBadgeRequiredError() { 
		[errors: "cab_badge is a required field"]
	}
	
	
}
