package com.salena.MapsApp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {
	@Value("${api_key}")
	private String apiKey;
	
	public void addCoordinates(Location location) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getCity() + "," + location.getState() + "&key=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();
		GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
		Location coordinates = response.getResults().get(0).getGeometry().getLocation();
		location.setLat(coordinates.getLat());
		location.setLng(coordinates.getLng());
	}
	
	public Location randomCoordinates() {
		double minLat = 15.00;
	    double maxLat = 80.00;
	    double latitude = minLat + (double)(Math.random() * ((maxLat - minLat) + 1));
	    double minLon;
	    double maxLon;
	    if (latitude > 70 ) {
	    	minLon = -80.00;
	    	maxLon = -120.00;
	    } else if (latitude > 60) {
	    	minLon = -60.00;
	    	maxLon = -165.00;
	    } else if (latitude > 50) {
	    	minLon = -60.00;
	    	maxLon = -130.00;
	    } else if (latitude > 45) {
	    	minLon = -60.00;
	    	maxLon = -125.00;
	    } else if (latitude > 40) {
	    	minLon = -70.00;
	    	maxLon = -125.00;
	    } else if (latitude > 30) {
	    	minLon = -75.00;
	    	maxLon = -120.00;
	    } else if (latitude > 25) {
	    	minLon = -98.00;
	    	maxLon = -115.00;
	    } else if (latitude > 20) {
	    	minLon = -98.00;
	    	maxLon = -105.00;
	    } else {
	    	minLon = -87.00;
	    	maxLon = -105.00;
	    }
	    double longitude = minLon + (double)(Math.random() * ((maxLon - minLon) + 1));
	    DecimalFormat df = new DecimalFormat("#.#####");
	    String randlat = String.valueOf(latitude);
	    String randlng = String.valueOf(longitude);
	    Location randomLocation = new Location();
	    randomLocation.setLat(randlat);
	    randomLocation.setLng(randlng);
	    String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + randlat + "," + randlng + "&key=" + apiKey;
	    RestTemplate restTemplate = new RestTemplate();		
	    GeocodingResponse response = restTemplate.getForObject(url,  GeocodingResponse.class);
	    List<Address_components> addresscomponents = response.getResults().get(0).getAddressComponents();
	   
	    for (int i = 0; i < addresscomponents.size(); i++) {
	    	if (addresscomponents.get(i).getTypes().get(0).equals("locality")) {
	    		randomLocation.setCity(addresscomponents.get(i).getLongName());
	    	}
	    	if (addresscomponents.get(i).getTypes().get(0).equals("administrative_area_level_1")) {
	    		randomLocation.setState(addresscomponents.get(i).getShortName());
	    	}
	    }
		return randomLocation;
	}
}
