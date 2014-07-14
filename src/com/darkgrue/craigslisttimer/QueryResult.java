package com.darkgrue.craigslisttimer;

import android.util.Log;

public class QueryResult {

	private String url;
	private String title;
	private final String tag = "DARKGRUE";

	public QueryResult(String _url_, String _title_) {
		this.url = _url_;
		this.title = _title_;
		
		//logMe();
	}

	public String getDescription() {
		return this.title;
	}

	public String getURL() {
		return this.url;
	}
	
	private void logMe(){
		Log.d(tag, "URL: " + this.url);
		Log.d(tag, "Title: " + this.title);
	}

}
