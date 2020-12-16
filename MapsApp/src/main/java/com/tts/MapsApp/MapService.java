package com.tts.MapsApp;

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
		double minLat = 30.00;
	    double maxLat = 70.00;
	    double latitude = minLat + (double)(Math.random() * ((maxLat - minLat) + 1));
	    double minLon = -70.00;
	    double maxLon = -130.00;
	    double longitude = minLon + (double)(Math.random() * ((maxLon - minLon) + 1));
	    DecimalFormat df = new DecimalFormat("#.#####");
	    String randlat = String.valueOf(latitude);
	    String randlng = String.valueOf(longitude);
//	    String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + randlat + "," + randlng + "&key=" + apiKey;
//	    RestTemplate restTemplate = new RestTemplate();		
//	    GeocodingResponse response = restTemplate.getForObject(url,  GeocodingResponse.class);
//	    Address_components address = response.getResults().get(0).getAddress_components();
	    Location randomLocation = new Location();
//	    randomLocation.setCity(response.getResults().get(0).getAddress_components().get(3).getLong_name());
//	    randomLocation.setState(response.getResults().get(0).getAddress_components().get(5).getShort_name());
	    randomLocation.setLat(randlat);
	    randomLocation.setLng(randlng);
	    return randomLocation;
	}
	
}
