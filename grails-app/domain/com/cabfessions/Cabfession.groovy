package com.cabfessions

import java.util.List;


class Cabfession {
	public static List VALID_CATEGORIES = ["anger", "envy", "gluttony", "pride", "lust", "greed", "sloth", "other"] 
	                                 
	Date creationDate
	User owner
	Cab cab  
	String text
	String category
	Double latitude
	Double longitude
	Neighborhood neighborhood
	
	
	Long tagCountLol = 0
	Long tagCountOmg = 0
	Long tagCountWtf = 0
	Long tagCountEw = 0
	Long tagCountUpVote = 0
	
	//if you add new tags you need to manually set them to 0 in the db!

	static hasMany = [tags : TagCabfessionEvent]
	                  
	void increaseTagCountLol() {
		tagCountLol++
	}
	
	void increaseTagCountOmg() {
		tagCountOmg++
	}
	
	void increaseTagCountWtf() {
		tagCountWtf++
	}

	
	void increaseTagCountEw() {
		tagCountEw++
	}
	
	void increaseTagCountUpVote() {
		tagCountUpVote++
	}
	
	String toString() {
		"$text"
	}
	

    static constraints = {
		creationDate(nullable: false)
		owner(nullable: true)
		cab(nullable: false)
		text(nullable: false, blank:false)
		cab(nullable: false)
		latitude(nullable: true)
		longitude(nullable: true)
		neighborhood(nullable: true)
		category(nullable: true, inList: VALID_CATEGORIES)
    }
}
