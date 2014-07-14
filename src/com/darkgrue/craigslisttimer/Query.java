package com.darkgrue.craigslisttimer;

import java.util.ArrayList;


import com.darkgrue.craigslisttimer.URLMaker.Category;

public class Query {
	private URLMaker urlMaker;

	private String url; // Represents a search Query url
	private String city;
	private Category category;
	private String searchQuery; // TODO Better name?
	private int minAsk;
	private int maxAsk;
	private boolean hasPic;
	private boolean searchTitle;
	private ArrayList<QueryResult> resultList = new ArrayList<QueryResult>();
	private boolean isReady = false;

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

	public String getURL() {
		return this.url;
	}

	public ArrayList<QueryResult> getResultList() {
		return this.resultList;
	}
	
	public void setResultList(ArrayList<QueryResult> newResultList){
		this.resultList = newResultList;
	}

	public void addToResultList(QueryResult result) {
		this.resultList.add(result);
	}

	public ArrayList<String> getResultDescriptionList() {
		ArrayList<String> returnMe = new ArrayList<String>();
		for (int i = 0; i < resultList.size(); i++) {
			returnMe.add(resultList.get(i).getDescription());
		}
		return returnMe;
	}
	
	public String getCity() {
		return city;
	}
	
	public Category getCategory() {
		return category;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public int getMinAsk() {
		return minAsk;
	}

	public int getMaxAsk() {
		return maxAsk;
	}

	public boolean isHasPic() {
		return hasPic;
	}

	public boolean isSearchTitle() {
		return searchTitle;
	}
	
	public boolean isReady(){
		return this.isReady;
	}

	// ////////////////////////////////////////////////
	// Query Result List Building Methods
	// ////////////////////////////////////////////////
	/*
	 * These methods rely heavily upon the JSOUP library
	 */



}
