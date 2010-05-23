package com.cabfessions;

public class TagCommentEvent {
	Comment comment
	User user
	boolean isUp
	Date creationDate
	
	static constraints = {
		comment(nullable: false)
		user(nullable: false)
		isUp(nullable: false)
		creationDate(nullable: false)
	}

}
