package com.cabfessions.api.controllers

import com.cabfessions.*
import com.cabfessions.api.ApiUtil as U
import grails.converters.JSON

class ApiCommentController {
	
	def tagService
	
	def create = {
		def cabfessionId = params.long("cabfession_id")
		def userHashKey = params.user_key
		def text = params.text
		def ownerCommentId = params.long("reply_to")
		
		//check and verify the user
		User user = U.verifyUser(userHashKey)
		
		if (!user) {
			//user is required and must be valid
			render U.wrapUserKeyError() as JSON
			return
		}
		
		if (!cabfessionId) {
			render U.wrapError("A valid cabfession_id is required.") as JSON
			return
		}
		
		def comment = new Comment()
		comment.user = user
		comment.creationDate = new Date()
		comment.cabfession = Cabfession.get(cabfessionId)
		comment.text = text
		
		if (ownerCommentId)
			comment.parent = Comment.get(ownerCommentId)
		
		if (comment.save(flush:true)) {
			render U.wrapResponse([comment:comment], true) as JSON
		} else {
			render U.wrapResponse(comment.errors, false) as JSON
		}
	}
	
	def vote = {
		def commentId = params.long("comment_id")
		def userHashKey = params.user_key
		def direction = params.direction
		
		//check and verify the user
		User user = U.verifyUser(userHashKey)
		
		if (!user) {
			//user is required and must be valid
			render U.wrapUserKeyError() as JSON
			return
		}
		
		if (!commentId) {
			render U.wrapError("A valid comment_id is required.") as JSON
			return
		}
		
		if (!("up".equals(direction) || "down".equals(direction))) {
			render U.wrapError("A valid direction is required: 'down' or 'up'.") as JSON
			return
		}
		
		Comment comment = Comment.get(commentId)
		
		def event = tagService.tagComment(user, comment, "up".equals(direction))
		
		if (event) {
			render U.wrapResponse([vote_event:event],false) as JSON
		} else {
			render U.wrapError("Unknown error while voting on comment") as JSON
		}
	}
	
	def list0 = {
		def maxNumberResults = Math.min(params.int("max") ? params.int('max') : 50, 100)
		def offsetNumber = params.int("offset") ? params.int("offset") : 0 
		def cabfessionId = params.long("cabfession_id")
		def userHashKey = params.user_key

		//check and verify the user
		User user = U.verifyUser(userHashKey)
		
		if (!user) {
			//user is required and must be valid
			render U.wrapUserKeyError() as JSON
			return
		}
		
		if (!cabfessionId) {
			render U.wrapError("A valid cabfession_id is required.") as JSON
		}

		//TODO
		//still need to figure out max results
		
		//now get comments ranked by upvotes
		def c = Comment.createCriteria()
		def comments = c {
			and {
				eq("cabfession.id", cabfessionId)
			}
			order("score", "desc")
			order("creationDate", "desc")
			firstResult(offsetNumber)
			maxResults(maxNumberResults)
		}
		render U.wrapResponse([comments:comments], true) as JSON
	}
}
