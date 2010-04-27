package com.cabfessions.api.controllers

import java.util.Date;
import grails.converters.JSON
import grails.converters.XML
import com.cabfessions.*
import com.cabfessions.api.*
import com.cabfessions.util.*
import java.text.SimpleDateFormat;
import com.cabfessions.services.*

class ApiController {
	public static  SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")
	public static double DEFAULT_GEO_RANGE = 0.007
	
	static allowedMethods = [create_cabfession: ["POST","GET"], update: "POST", delete: "POST"]
	
	def tagCabfessionService
	def neighborhoodService
	
	def get_user_key = {
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
		def userHashKey = params.user_key
		
		Cab cab
		
		if (!verifyUser(userHashKey)) {
			render getUserKeyError() as JSON
			return
		}
		
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
			}
			order("creationDate", "desc")
			maxResults(maxNumberResults)
		}
		
		def returnMap = [:]
		returnMap.cab = cab
		returnMap.cabfessions = cabfessions
		render returnMap as JSON
	}
	
	def get_cabfessions_by_location = {
		def maxNumberResults = Math.min(params.max ? params.int('max') : 50, 100)
		def olderThan = params.older_than
		def latitude = params.double("latitude")
		def longitude = params.double("longitude")
		def range = params.range ? params.double("range") : DEFAULT_GEO_RANGE
		def userHashKey = params.user_key
		
		if (!verifyUser(userHashKey)) {
			render getUserKeyError() as JSON
			return
		}
		
		if (latitude && longitude) {
			def c = Cabfession.createCriteria()
			def cabfessions = c {
				and {
					between("latitude", latitude - range, latitude + range)
					between("longitude", longitude - range, longitude + range)
					if(olderThan && olderThan != '') {
						gt('creationDate', DATE_FORMATTER.parse(olderThan) )
					}
				}
				order("creationDate", "desc")
				maxResults(maxNumberResults)
			}
			def output = [cabfessions:cabfessions]
			render output as JSON
			return
		} else {
			render getLocationError() as JSON
			return
		}
	}
	
	def get_cabfessions_by_tag = {
		def maxNumberResults = Math.min(params.max ? params.int('max') : 50, 100)
		def startDate = params.start_date ? DATE_FORMATTER.parse(params.start_date) : null
		def endDate = params.end_date ? DATE_FORMATTER.parse(params.end_date) : null
		def tag = params.tag
		
//		if (!verifyUser(userHashKey)) {
//			render getUserKeyError() as JSON
//			return3
//		}
//		
//		if (!TagCabfessionService.VALID_TAGS.contains(tag)) {
//			def output = [errors: "tag is not valid"]
//			render output as JSON
//		}
		
				
		def hql = "select cabfession, count(tags.tag) as counter from TagCabfessionEvent as tags " +
				"where tags.tag = 'funny' " + 
				"group by tags.tag, tags.cabfession "  +
				"order by count(tags.tag) desc"
		def cabfessions = Cabfession.executeQuery(hql)
		
		render cabfessions as JSON
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
		User user = verifyUser(userHashKey)
		
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
	
	def tag_cabfession = {
		def cabfessionId = params.cabfession_id
		def tag = params.tag
		def userHashKey = params.user_key
		
		//check and verify the user
		User user = verifyUser(userHashKey)
		
		if (!user) {
			//user is required and must be valid
			render getUserKeyError() as JSON
			return
		}
		
		TagCabfessionEvent event
		
		if (cabfessionId && tag && userHashKey) {			
			event = tagCabfessionService.tagCabfession(user, Cabfession.findById(cabfessionId)
					, tag)
		} 
		
		if (event) {
			render event as JSON
		} else {
			render getTagError() as JSON
		}
	}
	
	def hood_test = {
		render neighborhoodService.getHood(params.latitude, params.longitude);
	}
	
	private def User verifyUser(userHashKey) {
		userHashKey?User.findByHashKey(userHashKey):null
	}
	
	protected static HashMap getUserKeyError() {
		getErrorMap("user_key is invalid or not supplied.")
	}
	
	protected static HashMap getCabBadgeError() { 
		getErrorMap("cab_badge format and/or city is invalid")
	}
	
	protected static HashMap getCabAndCityRequiredError() { 
		getErrorMap("cab_badge and city are both required fields")
	}
	
	protected static HashMap getCabIdRequired() { 
		getErrorMap("cab_id is required")
	}
	
	protected static HashMap getTagError() {
		getErrorMap("Unknown error. Ensure proper cabfession_id and tag were supplied")
	}
	
	protected static HashMap getLocationError() {
		getErrorMap("A valid user_key, latitude and longitude are required.")
	}
	
	protected static HashMap getErrorMap(String error) {
		def errors = [error]
		[errors: errors]
	}
		
}