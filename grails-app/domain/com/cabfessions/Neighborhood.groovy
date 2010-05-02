package com.cabfessions

class Neighborhood {
	City city
	String name
	String borough
	String state
	String stateCode
	String country
	String countryCode
	
    static constraints = {
		city(nullable: false)
		name(nullable: false, unique:true)
		borough(nullable:true)
		state(nullable:false)
		stateCode(nullable:true)
		country(nullable:false)
		countryCode(nullable:true)
    }
}
