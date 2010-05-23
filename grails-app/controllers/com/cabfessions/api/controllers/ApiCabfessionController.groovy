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
import com.cabfessions.api.ApiUtil as U

class ApiCabfessionController {

	static allowedMethods = [create0: ["POST", "GET"], tag : ["POST","GET"]]
	
	def tagService
	def neighborhoodService
	
	def by_badge = {
		def maxNumberResults = Math.min(params.max ? params.int('max') : 50, 100)
		def badge = params.cab_badge
		def city = params.city
		def olderThan = params.older_than
		def userHashKey = params.user_key
		
		Cab cab
		
		if (!U.verifyUser(userHashKey)) {
			render U.wrapUserKeyError() as JSON
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
						render U.wrapError("cab_badge is invalid or not supplied") as JSON
						return
					}
					returnMap.cabfessions = [:]
				} else {
					def c = Cabfession.createCriteria()
					def cabfessions = c {
						and {
							eq('cab', cab)
							if(olderThan && olderThan != '') {
								gt('creationDate', U.DATE_FORMATTER.parse(olderThan) )
							}
						}
						order("creationDate", "desc")
						maxResults(maxNumberResults)
					}
					returnMap.cabfessions = cabfessions
				}
				returnMap.cab = cab
				render U.wrapResponse(returnMap,true) as JSON  
			} else {
				//can't find city
				render U.wrapError("city is invalid")
			}
		} else {
			//cab badge and city are both required
			render U.wrapError("cab_badge and city are both required fields.") as JSON		
		}
	}
	
	
	def by_location = {
		def maxNumberResults = Math.min(params.int("max") ? params.int("max") : 50, 100)
		def olderThan = params.older_than
		def latitude = params.double("latitude")
		def longitude = params.double("longitude")
		def range = params.range ? params.double("range") : U.DEFAULT_GEO_RANGE
		def userHashKey = params.user_key
		
		if (!U.verifyUser(userHashKey)) {
			render U.wrapUserKeyError() as JSON
			return
		}
		
		if (latitude && longitude) {
			def c = Cabfession.createCriteria()
			def cabfessions = c {
				and {
					between("latitude", latitude - range, latitude + range)
					between("longitude", longitude - range, longitude + range)
					if(olderThan && olderThan != '') {
						gt('creationDate', U.DATE_FORMATTER.parse(olderThan) )
					}
				}
				order("creationDate", "desc")
				maxResults(maxNumberResults)
			}
			def output = [cabfessions:cabfessions]
			render U.wrapResponse(output,true) as JSON
			return
		} else {
			render U.wrapError("A valid user_key, latitude and longitude are required.") as JSON
			return
		}
	}
	

	def by_tag = {
		def maxNumberResults = Math.min(params.int("max") ? params.int('max') : 50, 100)
		def offsetNumber = params.int("offset") ? params.int("offset") : 0 
		def tag = params.tag
		def range = params.range
		def userHashKey = params.user_key
		
		String rangeToday = "today"
		String rangeWeek = "week"
		String rangeMonth = "month"
				
		if (!U.verifyUser(userHashKey)) {
			render U.wrapUserKeyError() as JSON
			return
		}
		
		if (!tag || !TagService.VALID_TAGS.contains(tag)) {
			render U.wrapError("tag is invalid or not supplied") as JSON
			return
		}
		
		
		Calendar cal = Calendar.getInstance()
		Date beginDate = DateUtils.round(new Date(), Calendar.DATE)
		
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
		} else {
			cal.set(2010,01,01)
			beginDate = cal.getTime()
		}
		
		def hql = "select cabfession, count(tags.tag) as counter from TagCabfessionEvent as tags " +
		"where tags.tag = :tag " + 
		" and tags.creationDate > :beginDate " +
		"group by tags.tag, tags.cabfession "  +
		"order by count(tags.tag) desc"
		
		def cabfessions = Cabfession.executeQuery(hql, [beginDate:beginDate, tag:tag], [offset:offsetNumber, max:maxNumberResults])
		
		render U.wrapResponse([cabfessions:cabfessions],true) as JSON
	}
	

	def create = {
		def cabBadge = params.cab_badge
		def city = params.city
		def userHashKey = params.user_key
		def cabfessionInstance = new Cabfession()
		cabfessionInstance.category = params.category
		cabfessionInstance.longitude = params.double('longitude') 
		cabfessionInstance.latitude = params.double('latitude') 
		cabfessionInstance.text = params.text 
		cabfessionInstance.creationDate = new Date() 
		
		def output
		
		//check and verify the user
		User user = U.verifyUser(userHashKey)
		
		if (!user) {
			//user is required and must be valid
			render U.wrapUserKeyError() as JSON
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
			render U.wrapError("cab_badge and city are required.") as JSON
			return
		}
		
		//now set the cab
		cabfessionInstance.cab = cab
		
		
		if (cabfessionInstance.latitude && cabfessionInstance.longitude) {
			cabfessionInstance.neighborhood = neighborhoodService.getHood(params.latitude, params.longitude, cab.city)
		}
		
		if (cabfessionInstance.save(flush: true)) {
			render U.wrapResponse([cabfession: cabfessionInstance],true) as JSON
		} else {
			render U.wrapResponse(cabfessionInstance.errors,false) as JSON  
		}		
	}
	
	
	def tag = {
		def cabfessionId = params.long("cabfession_id")
		def tag = params.tag
		def userHashKey = params.user_key
		
		//check and verify the user
		User user = U.verifyUser(userHashKey)
		
		if (!user) {
			//user is required and must be valid
			render U.wrapUserKeyError() as JSON
			return
		}
		
		TagCabfessionEvent event
		
		if (cabfessionId && tag && userHashKey) {			
			event = tagService.tagCabfession(user, Cabfession.findById(cabfessionId)
			, tag)
		} 
	
		
		if (event) {
			render U.wrapResponse([tag_event:event],true) as JSON
		} else {
			render U.wrapError("Unknown error. Ensure proper cabfession_id and tag were supplied.") as JSON
		}
	}
	


}
