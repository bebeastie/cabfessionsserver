package com.cabfessions.services

import com.cabfessions.domain.*

class TagCabfessionService {

	boolean transactional = true
	
	def serviceMethod() {
		
	}
	
	def tagCabfession(cabfession, tag) {
		cabfession.addToTags tag
		cabfession.save()
	}
	
}
