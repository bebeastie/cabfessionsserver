package com.cabfessions

class TagCabfessionEvent {
	Date creationDate
	User user
	Tag tag
	Cabfession cabfession
	
	static constraints = {
		creationDate(nullable: false)
		user(nullable: true)
		tag(nullable: false)
		cabfession(nullable: false)
	}
	
}
