package com.cabfessions


class Cabfession {
	Date creationDate
	User owner
	Cab cab  
	String text
	Double latitude
	Double longitude
	Neighborhood neighborhood
	Long tagCountDevil = 0 
	Long tagCountAngel = 0
	Long tagCountLol = 0
	Long tagCountOmg = 0
	Long tagCountWtf = 0
	Long tagCountCute = 0
	Long tagCountEw = 0
	Long tagCountGeeky = 0
	Long tagCountTrashy = 0
	Long tagCountOld = 0
	//if you add new tags you need to manually set them to 0 in the db!

	static hasMany = [tags : TagCabfessionEvent]
	                  
	void increaseTagCountDevil() {
		tagCountDevil++
	}
	
	void increaseTagCountAngel() {
		tagCountAngel++
	}
	
	void increaseTagCountLol() {
		tagCountLol++
	}
	
	void increaseTagCountOmg() {
		tagCountOmg++
	}
	
	void increaseTagCountWtf() {
		tagCountWtf++
	}
	
	void increaseTagCountCute() {
		tagCountCute++
	}
	
	void increaseTagCountEw() {
		tagCountEw++
	}
	
	void increaseTagCountGeeky() {
		tagCountGeeky++
	}
	
	void increaseTagCountTrashy() {
		tagCountTrashy++
	}
	
	void increaseTagCountOld() {
		tagCountOld++
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
    }
}
