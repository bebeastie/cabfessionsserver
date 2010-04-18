package com.cabfessions

class Cab {
	String badge
	String city
	
	String toString() {
		"$badge"
	}
	
    static constraints = {
    	badge(size: 4..4, nullable: false)
    	city(nullable: false)
    }
}
