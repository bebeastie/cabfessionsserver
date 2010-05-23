package com.cabfessions.api.controllers

import com.cabfessions.api.ApiUtil as U
import com.cabfessions.*
import grails.converters.JSON

class ApiFeedbackController {
	
	def create = {
		def userHashKey = params.user_key
		def text = params.text
		
		//check and verify the user
		User user = U.verifyUser(userHashKey)
		
		if (!user) {
			//user is required and must be valid
			render U.wrapUserKeyError() as JSON
			return
		}
		
		Feedback feedback = new Feedback(dateCreated:new Date(), user:user, text: text)
		
		if (feedback.save(flush: true)) {
			render U.wrapResponse([feedback: feedback], true) as JSON
		} else {
			render U.wrapResponse(feedback.errors, false) as JSON
		}
	}
	
}
