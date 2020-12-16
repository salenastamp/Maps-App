package com.tts.MapsApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MapController {
	@Autowired
	private MapService mapService;
	@GetMapping(value = {"/home", "/"})
	public String getDefaultMap(Model model) {
		model.addAttribute("location", new Location());
		return "index";
	}
		
	@PostMapping("/home")
	public String getMapForLocation(Location location, Model model) {
		mapService.addCoordinates(location);
		model.addAttribute("location", location);
		return "index";
	}
	
	@GetMapping("/random")
	public String getRandomMap(Model model) {
		Location location = mapService.randomCoordinates();
		model.addAttribute("location", location);
		return "index";
	}
//	@PostMapping("/random")
//	public String getRandomCoord(Model model) {
//		List<Double> coord = new ArrayList<>();
//		coord = mapService.randomCoordinates();
//		Location.setLat(coord[0]);
//		Location.setLng(coord[1]);
//		mapService.addCoordinates(location);
//		model.addAttribute(location);
//		return "index";
//	}
	
}
