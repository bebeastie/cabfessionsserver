package com.cabfessions

class User {
	String clientType
	String clientId
	String hashKey
	
	String toString() {
		"$hashKey"
	}
	
    static constraints = {
    	clientType(nullable: false, blank:false)
    	clientId(nullable: false, blank:false)
    	hashKey(nullable: false)
    }
}
