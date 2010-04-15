package com.cabfessions


class Cabfession {
	Date creationDate
	User owner
	Cab cab  
	String text
	Double latitude
	Double longitude
	
	static hasMany = [tags: Tag]

    static constraints = {
		creationDate(nullable: false)
		owner(nullable: true)
		cab(nullable: false)
		text(nullable: false)
		cab(nullable: false)
		latitude(nullable: true)
		longitude(nullable: true)
    }
}
