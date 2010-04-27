package com.cabfessions.services

import java.util.List;
import yelp4j.Neighborhood;
import java.util.List;
import yelp4j.Neighborhood;
import yelp4j.Yelp;

class NeighborhoodService {

    boolean transactional = true

    def String getHood(String latitude, String longitude) {
    	Yelp yelp = new Yelp();
    	List<Neighborhood> neighborhoods =  yelp.getNeighborhoodInfo( latitude, longitude);
    	if (neighborhoods.size() > 0) 
    		return neighborhoods.get(0).getName();
    }
}
