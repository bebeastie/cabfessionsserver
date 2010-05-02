package com.cabfessions

class City {
	String name
	
	String toString() {
		"$name"
	}
    static constraints = {
		name(nullable: false, unique:true, inList: ["New York","London"])
    }
}
