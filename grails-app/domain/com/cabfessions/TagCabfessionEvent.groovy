package com.cabfessions

import com.cabfessions.services.TagCabfessionService;

class TagCabfessionEvent {
	Date creationDate
	User user
	String tag
	Cabfession cabfession
	
	String toString() {
		return "Tag: $tag, Cabfession: $cabfession.id"
	}
	
//	List getValidValues() {
//		["funny","scary","crazy"] 
//	}
	
	static constraints = {
		creationDate(nullable: false)
		user(nullable: false)
		tag(nullable: false, inList:TagCabfessionService.VALID_TAGS)
		cabfession(nullable: false)
	}
	
}
