package com.cabfessions

class Cab {
	String badge
	
	String toString() {
		"$badge"
	}
	
    static constraints = {
    	badge(size: 4..4, nullable: false)
    }
}
