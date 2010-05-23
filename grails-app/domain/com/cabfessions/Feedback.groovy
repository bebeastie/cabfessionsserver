package com.cabfessions

class Feedback {
	User user
	Date dateCreated
	String text

    static constraints = {
		user(nullable: false)
		dateCreated(nullable: false)
		text(nullable: false, blank: false)
    }
}
