package com.cabfessions

class User {
	String clientType
	String clientId
	String userKey
	
	String toString() {
		"$userKey"
	}
	
    static constraints = {
    	clientType(nullable: false)
    	clientId(nullable: true)
    	userKey(nullable: false)
    }
}
