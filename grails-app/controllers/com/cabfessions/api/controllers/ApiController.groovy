package com.cabfessions.api.controllers

import java.util.Date;
import grails.converters.JSON
import grails.converters.XML
import com.cabfessions.*
import com.cabfessions.api.*
import com.cabfessions.util.*
import java.text.SimpleDateFormat;

class ApiController {
	public static  SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmssZ")
	
	static allowedMethods = [create_cabfession: ["POST","GET"], update: "POST", delete: "POST"]
	
	def tagCabfessionService
	
	/**
	 * 
	 * 
	 * 
	 */
	def get_user = {
		def clientId = params.client_id
		def clientType = params.client_type	
		
		def output
		
		def isNew = true
		User userInstance 
			
		if (clientId) { 
			userInstance = User.findByClientId(clientId)
		} 
		
		if (userInstance) {
			isNew = false
		} else {
			userInstance = new User(clientType: clientType, clientId: clientId, 
					hashKey: Utils.generateUserHashKey())
			userInstance.save(flush:true)
		}
		
		if (userInstance.errors.hasErrors()) {
			output = userInstance.errors
		} else {
			def userMap = [:]
			userMap.is_new = isNew
			userMap.hash_key = userInstance.hashKey		
			output = [user: userMap] 		
		}
		render output as JSON 
	}
	
	def get_cabfessions_by_badge = {
		def maxNumberResults = Math.min(params.max ? params.int('max') : 50, 100)
		def badge = params.cab_badge
		def city = params.city
		def olderThan = params.older_than
		
		Cab cab
		
		if (badge && city) {
			cab = Cab.findByBadgeAndCity(badge, city)
			if (!cab) {
				//we dont' have a cab yet so lets create one
				cab = new Cab(city:city, badge:badge).save()
				if (!cab || cab.errors.hasErrors()) {
					render getCabBadgeError() as JSON
					return
				}
			} 
		} else {
			//cab badge and city are both required
			render getCabAndCityRequiredError() as JSON
			return		
		}
		
		def c = Cabfession.createCriteria()
		def cabfessions = c {
			and {
				eq('cab', cab)
				if(olderThan && olderThan != '') {
					gt('creationDate', DATE_FORMATTER.parse(olderThan) )
				}
				order("creationDate", "desc")
				maxResults(maxNumberResults)
			}
		}
		
		def returnMap = [:]
		returnMap.cab = cab
		returnMap.cabfessions = cabfessions
		render returnMap as JSON
	}
	
	def create_cabfession = {
		//select inputs
		def cabId = params.cab_id
		def userHashKey = params.user_key
		
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
		if (userHashKey)  
			user = User.findByHashKey(userHashKey)
				 
		if (!user) {
			//user is required and must be valid
			render getUserKeyError() as JSON
			return
		}
		
		//set the owner
		cabfessionInstance.owner = user	
		
		//look for the cab
		Cab cab
		
		if (cabId) 
			cab = Cab.findById(cabId)
		
		//cab is required
		if (!cab)  {
			render getCabIdRequired() as JSON
			return
		}
		
		//now set the cab
		cabfessionInstance.cab = cab
		
		if (cabfessionInstance.save(flush: true)) {
			output = [cabfession: cabfessionInstance]
		} else {
			output = cabfessionInstance.errors  
		}		
		render output as JSON
	}
	

//	def tag_cabfession = {
//		if (params.cabfession_id && params.tag_name) {
//			Cabfession cabfession = Cabfession.findById(params.cabfession_id)
//			Tag tag = Tag.findByType(params.tag_name)
//			
//			
//			tagCabfessionService.tagCabfession(cabfession, tag)
//			render "Success"
//		} else {
//			render "Need inputs"
//		}
//	}
	
	protected static HashMap getUserKeyError() {
		[errors: "user_key is invalid or not supplied."]
	}
	
	protected static HashMap getCabBadgeError() { 
		[errors: "cab_badge format and/or city is invalid"]
	}
	
	protected static HashMap getCabAndCityRequiredError() { 
		[errors: "cab_badge and city are both required fields"]
	}
	
	protected static HashMap getCabIdRequired() { 
		[errors: "cab_id is required"]
	}
	
}
