package com.cabfessions.services

import java.util.List;
import java.util.List;
import yelp4j.Yelp;
import com.cabfessions.Neighborhood;
import com.cabfessions.City;

class NeighborhoodService {
	private static final String YELP_KEY = "1jjphsaqYpfSWA6S0tUELw"
		
    boolean transactional = true

    def Neighborhood getHood(latitude, longitude, City city) {
    	Neighborhood dbHood
    	Yelp yelp = new Yelp(YELP_KEY);
    	
    	List<yelp4j.Neighborhood> yelpHoods =  yelp.getNeighborhoodInfo(latitude, longitude);
    	
    	if (yelpHoods.size() > 0) {
    		yelp4j.Neighborhood yelpHood = yelpHoods.get(0)
    		
    		Neighborhood queryHood = new Neighborhood(city: city, name: yelpHood.getName(), 
					borough:yelpHood.getBorough(), state:yelpHood.getState(), 
					stateCode: yelpHood.getStateCode(), 
					country: yelpHood.getCountry(), countryCode: yelpHood.getCountryCode())
    		
    		dbHood = Neighborhood.findByCityAndName(city, yelpHood.getName())
    		if (!dbHood && !queryHood.hasErrors() && queryHood.save(flush: true)) {
    			dbHood = queryHood
    		}
    	}
    	return dbHood;
    }
}
