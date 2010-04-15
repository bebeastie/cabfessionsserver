package com.cabfessions

class User {
	String clientType
	String clientId
	String key
	
	String toString() {
		"$key"
	}
	
    static constraints = {
    	clientType(nullable: false)
    	clientId(nullable: true)
    	key(nullable: false)
    }
}
