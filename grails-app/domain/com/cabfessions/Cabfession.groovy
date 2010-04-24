package com.cabfessions


class Cabfession {
	Date creationDate
	User owner
	Cab cab  
	String text
	Double latitude
	Double longitude
	
	static hasMany = [tags: TagCabfessionEvent]

	String toString() {
		"$text"
	}
	
    static constraints = {
		creationDate(nullable: false)
		owner(nullable: true)
		cab(nullable: false)
		text(nullable: false, blank:false)
		cab(nullable: false)
		latitude(nullable: true)
		longitude(nullable: true)
    }
}
