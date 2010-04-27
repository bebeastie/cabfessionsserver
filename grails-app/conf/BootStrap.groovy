import com.cabfessions.api.controllers.ApiController;
import grails.converters.JSON;
import com.cabfessions.*
import com.cabfessions.util.*

class BootStrap {
	
	def init = { servletContext ->
		if (!Cabfession.get(1)) {
			// Create some test data
			
			Cab cab1 = new Cab(badge:"4F45", city:"nyc").save()
			Cab cab2 = new Cab(badge:"9f78", city:"nyc").save()
			
			User user1 = new User(hashKey:Utils.generateUserHashKey(), clientId:"1111", clientType:"iPhone").save()
			User user2 = new User(hashKey:Utils.generateUserHashKey(), clientId:"2222", clientType:"iPhone").save()
			
			Cabfession cabfession1 = new Cabfession(creationDate:new Date(), owner:user1, cab:cab1, text:"Cabfession 1", latitude:10, longitude:10).save()
			Cabfession cabfession2 = new Cabfession(creationDate:new Date(), owner:user2, cab:cab2, text:"Cabfession 2", latitude:20, longitude:20).save()

		}
		
		JSON.registerObjectMarshaller(Cabfession) {
			def returnMap = [:]
			returnMap.id = it.id
			returnMap.creation_date = ApiController.DATE_FORMATTER.format(it.creationDate)
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
			returnMap.city = it.city
			return returnMap
		}
		
		JSON.registerObjectMarshaller(TagCabfessionEvent) {
			def returnMap = [:];
			returnMap.id = it.id;
			returnMap.creation_date = it.creationDate;
			returnMap.tag = it.tag;
			returnMap.cabfession = it.cabfession;
			return returnMap
		}
	}
	
	def destroy = {
	}
} 