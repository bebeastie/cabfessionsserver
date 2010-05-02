package com.cabfessions.services

import com.cabfessions.domain.*
import com.cabfessions.*

class TagCabfessionService {
	public static List VALID_TAGS = ["devil","angel","lol","omg","wtf","cute","ew","geeky","trashy","old"] 
	
	boolean transactional = true
			
	def TagCabfessionEvent tagCabfession(User user, Cabfession cabfession, String tag) {
		
		TagCabfessionEvent event
	
		if (cabfession && user && tag) {
			if (tag?.equals("devil")) {
				cabfession.increaseTagCountDevil()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"devil")
			} else if(tag?.equals("angel")) {
				cabfession.increaseTagCountAngel()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"angel")			
			} else if(tag?.equals("lol")) {
				cabfession.increaseTagCountLol()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"lol")			
			} else if(tag?.equals("omg")) {
				cabfession.increaseTagCountOmg()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"omg")			
			} else if(tag?.equals("wtf")) {
				cabfession.increaseTagCountWtf()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"wtf")			
			} else if(tag?.equals("cute")) {
				cabfession.increaseTagCountCute()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"cute")			
			} else if(tag?.equals("ew")) {
				cabfession.increaseTagCountEw()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"ew")			
			} else if(tag?.equals("geeky")) {
				cabfession.increaseTagCountGeeky()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"geeky")			
			} else if(tag?.equals("trashy")) {
				cabfession.increaseTagCountTrashy()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"trashy")			
			} else if(tag?.equals("old")) {
				cabfession.increaseTagCountOld()
				event = new TagCabfessionEvent(creationDate:new Date(), user:user, cabfession:cabfession,tag:"old")			
			} else {
				//do nothing, maybe throw exception?
			}
		}
		
		cabfession?.save()
		event?.save()
		return event
	}
	
}
