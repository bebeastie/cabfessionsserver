package com.cabfessions

class Cab {
	String badge
	City city
	
	String toString() {
		"$city, $badge"
	}
	
    static constraints = {
    	badge(size: 4..4, nullable: false)
    	city(nullable: false)
    }
}
