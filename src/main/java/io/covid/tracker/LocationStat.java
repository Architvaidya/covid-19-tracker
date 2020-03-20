package io.covid.tracker;

public class LocationStat {
	private String state;
	private String country;
	private int latestReportedCases;
	private int diffFromPreviousDay;
	
	
	public int getDiffFromPreviousDay() {
		return diffFromPreviousDay;
	}
	public void setDiffFromPreviousDay(int diffFromPreviousDay) {
		this.diffFromPreviousDay = diffFromPreviousDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestReportedCases() {
		return latestReportedCases;
	}
	public void setLatestReportedCases(int latestReportedCases) {
		this.latestReportedCases = latestReportedCases;
	}
	@Override
	public String toString() {
		return "LocationStat [state=" + state + ", country=" + country + ", latestReportedCases=" + latestReportedCases
				+ "]";
	}
	
	
	
	
	
}
