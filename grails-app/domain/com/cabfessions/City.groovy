package com.cabfessions

class City {
	String name
	
	String toString() {
		"$name"
	}
	
	static constraints = {
		name(nullable: false, inList: ["nyc","london"])
	}
}
