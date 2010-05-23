package com.cabfessions

class Comment {
	Cabfession cabfession
	User user
	String text
	Date creationDate
	Long upVote = 0
	Long downVote = 0
	Long score = 0 
	Comment parent
	
	
	void upVote() {
		upVote++
		score++
	}
	
	void downVote() {
		downVote++
		score--
	}
	
	String toString() {
		"$id: $text"
	}
	
    static constraints = {
    	cabfession(nullable: false)
    	user(nullable: false)
    	text(nullable: false, blank: false)
    	creationDate(nullable: false)
    	parent(nullable: true)
    }
	
}
