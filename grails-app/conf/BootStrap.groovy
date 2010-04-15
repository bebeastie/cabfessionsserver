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
	}
	
	def destroy = {
	}
} 