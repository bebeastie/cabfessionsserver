package com.cabfessions.services

import com.cabfessions.domain.*
import com.cabfessions.*

class TagService {
	public static List VALID_TAGS = ["up_vote","lol","omg","wtf","ew"] 
	
	boolean transactional = true
			
	def TagCabfessionEvent tagCabfession(User user, Cabfession cabfession, String tag) {
		
		TagCabfessionEvent event
	
		if (cabfession && user && tag) {		
			if(tag?.equals("up_vote")) {
				cabfession.increaseTagCountUpVote()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"up_vote")	
			} else if(tag?.equals("lol")) {
				cabfession.increaseTagCountLol()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"lol")			
			} else if(tag?.equals("omg")) {
				cabfession.increaseTagCountOmg()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"omg")			
			} else if(tag?.equals("wtf")) {
				cabfession.increaseTagCountWtf()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"wtf")					
			} else if(tag?.equals("ew")) {
				cabfession.increaseTagCountEw()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"ew")				
			} else {
				//do nothing, maybe throw exception?
			}
		}
		
		cabfession?.save()
		event?.save()
		return event
	}
	
	def tagComment(User user, Comment comment, boolean isUp) {
		TagCommentEvent event
		
		if (user && comment) {
			event = new TagCommentEvent(creationDate: new Date(), user: user, comment: comment, isUp: isUp)
			isUp ? comment.upVote() : comment.downVote()
		}
		
		comment?.save()
		event?.save()
		return event
	}
	
}
