import java.text.SimpleDateFormat;
import grails.converters.JSON;
import com.cabfessions.*
import com.cabfessions.util.*
import com.cabfessions.controller.api.*
import com.cabfessions.api.ApiUtil

class BootStrap {

	def init = { servletContext ->
		if (!Cabfession.get(1)) {
			// Create some test data
			
			City nyc = new City(name: "New York").save()
			City london = new City(name: "London").save()
			
			Cab cab1 = new Cab(badge:"4F45", city:nyc).save()
			Cab cab2 = new Cab(badge:"9F78", city:nyc).save()
			Cab cab3 = new Cab(badge:"4F44", city:nyc).save()
			
			User user1 = new User(hashKey:ApiUtil.generateUserHashKey(), 
					clientId:"1111", clientType:"iPhone").save()
			User user2 = new User(hashKey:ApiUtil.generateUserHashKey(), 
					clientId:"2222", clientType:"iPhone").save()
			
			Cabfession cabfession1 = new Cabfession(creationDate:new Date(), owner:user1, 
					cab:cab1, text:"Cabfession 1", latitude:10, longitude:10).save()
			Cabfession cabfession2 = new Cabfession(creationDate:new Date(), owner:user2, 
					cab:cab2, text:"Cabfession 2", latitude:20, longitude:20).save()

		}
		
		JSON.registerObjectMarshaller(Cabfession) {
			def returnMap = [:]
			returnMap.id = it.id
			returnMap.creation_date = ApiUtil.DATE_FORMATTER.format(it.creationDate)
			returnMap.cab = it.cab
			returnMap.neighborhood = it.neighborhood
			returnMap.category = it.category
			returnMap.text = it.text
			returnMap.latitude = it.latitude
			returnMap.longitude = it.longitude	
			returnMap.tagCountUpVote = it.tagCountUpVote
//			returnMap.tagCountLol = it.tagCountLol
//			returnMap.tagCountOmg = it.tagCountOmg
//			returnMap.tagCountWtf = it.tagCountWtf
//			returnMap.tagCountEw = it.tagCountEw
			return returnMap
		}
		
		JSON.registerObjectMarshaller(Cab) {
			def returnMap = [:]
			returnMap.badge = it.badge
			returnMap.city = it.city
			return returnMap
		}
		
		JSON.registerObjectMarshaller(TagCabfessionEvent) {
			def returnMap = [:];
			returnMap.creation_date = ApiUtil.DATE_FORMATTER.format(it.creationDate);
			returnMap.tag = it.tag;
			returnMap.cabfession = it.cabfession;
			return returnMap
		}
		
		JSON.registerObjectMarshaller(Neighborhood) {
			def returnMap = [:]
			returnMap.name = it.name
			returnMap.borough = it.borough
			return returnMap
		}
		
		JSON.registerObjectMarshaller(City) {
			def returnMap = [:]
			returnMap.name = it.name
			return returnMap
		}
		
		JSON.registerObjectMarshaller(Feedback) {
			def returnMap = [:]
			returnMap.text = it.text
			return returnMap
		}
		
		JSON.registerObjectMarshaller(Comment) {
			def returnMap = [:]
			returnMap.id = it.id
			returnMap.text = it.text
			returnMap.creation_date =  ApiUtil.DATE_FORMATTER.format(it.creationDate);
			return returnMap
		}
		
		JSON.registerObjectMarshaller(TagCommentEvent) {
			def returnMap = [:];
			returnMap.creation_date = ApiUtil.DATE_FORMATTER.format(it.creationDate);
			returnMap.direction = it.isUp ? "up" : "down"
			returnMap.comment = it.comment;
			return returnMap		
		}
	}
	
	def destroy = {
			
	}
} 