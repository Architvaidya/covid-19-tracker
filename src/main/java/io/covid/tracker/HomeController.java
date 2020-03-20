package io.covid.tracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@Autowired
	DataService service;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<LocationStat> allStats = service.getLocationStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestReportedCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
	}

}
