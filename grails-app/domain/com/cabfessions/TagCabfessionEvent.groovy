package com.cabfessions

import com.cabfessions.services.TagService;

class TagCabfessionEvent {
	Date creationDate
	User user
	String tag
	Cabfession cabfession
	
	static belongsTo = Cabfession
	
	String toString() {
		return "Tag: $tag, Cabfession: $cabfession.id"
	}
	
//	List getValidValues() {
//		["funny","scary","crazy"] 
//	}
	
	static constraints = {
		creationDate(nullable: false)
		user(nullable: false)
		tag(nullable: false, inList:TagService.VALID_TAGS)
		cabfession(nullable: false)
	}
	
}
