import grails.converters.JSON;

import com.cabfessions.*

class BootStrap {
	
	def init = { servletContext ->
		// Create some test data
		new Cab(badge:"4F45").save()
		new Cab(badge:"9f78").save()
		new User(clientId: "4444", clientType: "iPhone").save()
		new User(clientId: "5555", clientType: "iPhone").save()
		new Tag(type:"Funny").save()
		new Tag(type:"Crazy").save()
		new Tag(type: "Scary").save()
		
		JSON.registerObjectMarshaller(Cabfession) {
			def returnMap = [:]
			returnMap.id = it.id
			returnMap.creationDate = it.creationDate
			returnMap.cab = it.cab
			returnMap.text = it.text
			returnMap.latitude = it.latitude
			returnMap.longitude = it.longitude	
			return returnMap
		}
		
		JSON.registerObjectMarshaller(Cab) {
			def returnMap = [:]
			returnMap.badge = it.badge
			return returnMap
		}
	}
	
	def destroy = {
	}
} 