package com.cabfessions.services

import com.cabfessions.domain.*
import com.cabfessions.*

class TagCabfessionService {
	public static List VALID_TAGS = ["funny","scary","crazy"] 
	
	boolean transactional = true
			
	def TagCabfessionEvent tagCabfession(User user, Cabfession cabfession, String tag) {
		
		TagCabfessionEvent event
	
		if (cabfession && user && tag) {
			if (tag?.equals("funny")) {
				cabfession.increaseTagCountFunny()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"funny")
			}else if(tag?.equals("crazy")) {
				cabfession.increaseTagCountCrazy()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"crazy")			
			}else if(tag?.equals("scary")) {
				cabfession.increaseTagCountScary()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"scary")		
			}else {
				//do nothing, maybe throw exception?
			}
		}
		
		cabfession?.save()
		event?.save()
		return event
	}
	
}
