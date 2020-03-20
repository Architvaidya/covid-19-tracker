package io.covid.tracker;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service; 

@Service
public class DataService {
	
	private static String URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	
	private List<LocationStat> locationStats = new ArrayList<LocationStat>();
	
	
	public List<LocationStat> getLocationStats() {
		return locationStats;
	}

	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchData() throws IOException, InterruptedException{
		
		List<LocationStat> newStats = new ArrayList<LocationStat>();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				   			  			 .uri(URI.create(URL))
				   			  			 .build();
		
		HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
		
		

		StringReader reader = new StringReader(response.body());
		Iterable<CSVRecord > records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
		for (CSVRecord record : records) {
			LocationStat stat = new LocationStat(); 
			stat.setState(record.get("Province/State"));
			stat.setCountry(record.get("Country/Region"));
			int latestCases = Integer.parseInt(record.get(record.size() - 1));
			int prevCases = Integer.parseInt(record.get(record.size() - 2));
			
			stat.setLatestReportedCases(latestCases);
			stat.setDiffFromPreviousDay(latestCases - prevCases);
			
			newStats.add(stat);
		    
		}
		
		this.locationStats = newStats;
		
	}

}
