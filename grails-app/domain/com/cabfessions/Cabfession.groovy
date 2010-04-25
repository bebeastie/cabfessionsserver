package com.cabfessions


class Cabfession {
	Date creationDate
	User owner
	Cab cab  
	String text
	Double latitude
	Double longitude
	Long tagCountFunny = 0 
	Long tagCountCrazy = 0
	Long tagCountScary = 0 

	void increaseTagCountFunny() {
		tagCountFunny++
	}
	
	void increaseTagCountCrazy() {
		tagCountCrazy++
	}
	
	void increaseTagCountScary() {
		tagCountScary++
	}
	
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
//		tagCountFunny(nullable: true)
//		tagCountCrazy(nullable: true)
//		tagCountScary(nullable: true)
    }
}
