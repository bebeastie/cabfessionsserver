package com.cabfessions

class Cab {
	City city
	String badge
	
	String toString() {
		"$city, $badge"
	}
	
    static constraints = {
    	badge(size: 4..4, nullable: false)
    	city(nullable: false)
    }
}
