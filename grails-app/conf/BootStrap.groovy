import com.cabfessions.api.controllers.CabfessionsController;

import grails.converters.JSON;

import com.cabfessions.*

class BootStrap {
	
	def init = { servletContext ->
		// Create some test data
		City nyc = new City(name: "New York").save()
		City london = new City(name: "London").save()
		
		Cab cab1 = new Cab(badge:"4F45", city:nyc).save()
		Cab cab2 = new Cab(badge:"9f78", city:nyc).save()
		
		User user1 = new User(clientId: "1111", clientType: "iPhone").save()
		User user2 = new User(clientId: "2222", clientType: "iPhone").save()
		
		Cabfession cabfession1 = new Cabfession(user:user1, cab:cab1, text:"Cabfession 1", latitude:10, longitude:10)
		Cabfession cabfession2 = new Cabfession(user:user2, cab:cab2, text:"Cabfession 2", latitude:20, longitude:20)
		
		Tag funny = new Tag(type:"Funny").save()
		Tag crazy = new Tag(type:"Crazy").save()
		Tag scary = new Tag(type: "Scary").save()
				
		JSON.registerObjectMarshaller(Cabfession) {
			def returnMap = [:]
			returnMap.id = it.id
			returnMap.creationDate = CabfessionsController.DATE_FORMATTER.format(it.creationDate)
			returnMap.cab = it.cab
			returnMap.text = it.text
			returnMap.latitude = it.latitude
			returnMap.longitude = it.longitude	
			return returnMap
		}
		
		JSON.registerObjectMarshaller(Cab) {
			def returnMap = [:]
		    returnMap.id = it.id
			returnMap.badge = it.badge
			return returnMap
		}
	}
	
	def destroy = {
	}
} 