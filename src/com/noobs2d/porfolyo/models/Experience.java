package com.noobs2d.porfolyo.models;

public class Experience {

    public String company;
    public String duration;
    public String title;

    /** @params rawData String formatted like this: title|company|duration (e.g., Flash Developer|Some Company|May 2012 - May 2013) */
    public Experience(String rawData) {
	// each string MUST be split into three indexes
	final int TITLE = 0, COMPANY = 1, DURATION = 2;

	String[] bundle = rawData.split("\\|", -1); // -1 so it doesn't have a limit
	title = bundle[TITLE];
	company = bundle[COMPANY];
	duration = bundle[DURATION];
    }

    public Experience(String company, String duration, String title) {
	this.company = company;
	this.duration = duration;
	this.title = title;
    }
}
