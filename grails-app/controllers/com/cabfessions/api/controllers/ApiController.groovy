package com.cabfessions.api.controllers

import java.util.Calendar;
import java.util.Calendar;
import java.util.Date;
import grails.converters.JSON
import grails.converters.XML
import com.cabfessions.*
import com.cabfessions.api.*
import com.cabfessions.util.*
import java.text.SimpleDateFormat;
import org.apache.commons.lang.time.DateUtils;
import com.cabfessions.services.*
import org.hibernate.*

class ApiController {
	public static  SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")
	public static double DEFAULT_GEO_RANGE = 0.007
	
	static allowedMethods = [post_cabfession: ["POST", "GET"], tag_cabfession : ["POST","GET"]]
	
	def tagCabfessionService
	def neighborhoodService
	
	def user_key = {
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
	
	def cabfessions_by_badge = {
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
			def returnMap = [:]
			city = City.findByName(city);
			
			if (city) {
				cab = Cab.findByBadgeAndCity(badge, city)	
				if (!cab) {
					//we dont' have a cab yet so lets create one
					cab = new Cab(city:city, badge:badge).save()
					if (!cab || cab.errors.hasErrors()) {
						render getCabBadgeError() as JSON
						return
					}
					returnMap.cabfessions = [:]
				} else {
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
					returnMap.cabfessions = cabfessions
				}
				returnMap.cab = cab
				render returnMap as JSON  
			} else {
				//can't find city
				render getInvalidCityError()
			}
		} else {
			//cab badge and city are both required
			render getCabAndCityRequiredError() as JSON		
		}
	}
	
	def cabfessions_by_location = {
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
	
	def cabfessions_by_tag = {
		def maxNumberResults = Math.min(params.max ? params.int('max') : 50, 100)
		def tag = params.tag
		def range = params.range
		def userHashKey = params.user_key
		
		String rangeToday = "today"
		String rangeWeek = "week"
		String rangeMonth = "month"
				
		if (!verifyUser(userHashKey)) {
			render getUserKeyError() as JSON
			return
		}
		
		if (!tag || !TagCabfessionService.VALID_TAGS.contains(tag)) {
			render getInvalidTag() as JSON
			return
		}
		
		Calendar cal = Calendar.getInstance()
		cal.set(2010,01,01)
		
		Date beginDate = cal.getTime()
		beginDate = DateUtils.round(new Date(), Calendar.DATE)
		
		if (range?.equals(rangeToday)) {
			cal.setTime(beginDate)
			cal.add(Calendar.DATE, - 1)
			beginDate = cal.getTime()
		} else if (range?.equals(rangeWeek)) {
			cal.setTime(beginDate)
			cal.add(Calendar.DATE, - 7)
			beginDate = cal.getTime()
		} else if (range?.equals(rangeMonth)) {
			cal.setTime(beginDate)
			cal.add(Calendar.DATE, - 31)
			beginDate = cal.getTime() 
		}
		
		def hql = "select cabfession, count(tags.tag) as counter from TagCabfessionEvent as tags " +
		"where tags.tag = :tag " + 
		" and tags.creationDate > :beginDate " +
		"group by tags.tag, tags.cabfession "  +
		"order by count(tags.tag) desc"
		
		def cabfessions = Cabfession.executeQuery(hql, [beginDate:beginDate, tag:tag])
		
		def output = [cabfessions:cabfessions]
		render output as JSON
	}
	
	def post_cabfession = {
		def cabBadge = params.cab_badge
		def city = params.city
		def userHashKey = params.user_key
		def cabfessionInstance = new Cabfession()
		cabfessionInstance.longitude = params.double('longitude') 
		cabfessionInstance.latitude = params.double('latitude') 
		cabfessionInstance.text = params.text 
		cabfessionInstance.creationDate = new Date() 
		
		def output
		
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
		
		if (cabBadge && city) {
			cab = Cab.findByBadgeAndCity(cabBadge, City.findByName(city))
		} 
		
		//cab is required
		if (!cab)  {
			render getCabBadgeError() as JSON
			return
		}
		
		//now set the cab
		cabfessionInstance.cab = cab
		
		if (cabfessionInstance.latitude && cabfessionInstance.longitude) {
			cabfessionInstance.neighborhood = neighborhoodService.getHood(params.latitude, params.longitude, cab.city)
		}
		
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
	
	
	private def User verifyUser(userHashKey) {
		userHashKey?User.findByHashKey(userHashKey):null
	}
	
	protected static HashMap getInvalidTag() {
		getErrorMap("tag is invalid or not supplied.");
	}
	
	protected static HashMap getInvalidCityError() {
		getErrorMap("city is invalid.");
	}
	protected static HashMap getUserKeyError() {
		getErrorMap("user_key is invalid or not supplied.")
	}
	
	protected static HashMap getCabBadgeError() { 
		getErrorMap("cab_badge format and/or city is invalid.")
	}
	
	protected static HashMap getCabAndCityRequiredError() { 
		getErrorMap("cab_badge and city are both required fields.")
	}
	
	protected static HashMap getTagError() {
		getErrorMap("Unknown error. Ensure proper cabfession_id and tag were supplied.")
	}
	
	protected static HashMap getLocationError() {
		getErrorMap("A valid user_key, latitude and longitude are required.")
	}
	
	protected static HashMap getErrorMap(String error) {
		def errors = [error]
		[errors: errors]
	}
	
	protected static HashMap getHeaderOK() {
		def status = [status: "OK"]
		def header = [header: status]
		return header;
	}
	
	protected static HashMap getHeaderError() {
		def status = [status: "ERROR"]
		def header = [header: status]
		return header;	
	}
	
}
