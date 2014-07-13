package com.darkgrue.craigslisttimer;

import com.darkgrue.craigslisttimer.URLMaker.Category;

public class Query {
	private URLMaker urlMaker;

	private String url;
	private String city;
	private Category category;
	private String searchQuery; // TODO Better name?
	private int minAsk;
	private int maxAsk;
	private boolean hasPic;
	private boolean searchTitle;

	public Query(Category category_, String searchQuery_, int minAsk_,
			int maxAsk_, boolean hasPic_, boolean searchTitle_, String city_) {

		this.category = category_;
		this.searchQuery = searchQuery_;
		this.minAsk = minAsk_;
		this.maxAsk = maxAsk_;
		this.hasPic = hasPic_;
		this.searchTitle = searchTitle_;
		this.city = city_;
		this.urlMaker = new URLMaker();

		this.url = this.urlMaker.createQuery(this.searchQuery, this.category,
				String.valueOf(this.minAsk), String.valueOf(this.maxAsk),
				this.hasPic, this.searchTitle, this.city);

		
	}

	public String getDescription() {
		// TODO This could be updated to show more results later
		return this.searchQuery;
	}
	
	public String getURL(){
		return this.url;
	}

}
