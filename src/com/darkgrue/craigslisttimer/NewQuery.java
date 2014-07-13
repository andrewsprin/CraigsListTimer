package com.darkgrue.craigslisttimer;

import java.util.EnumMap;
import java.util.HashMap;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;

import com.darkgrue.craigslisttimer.URLMaker.Category;

public class NewQuery extends Activity implements OnItemSelectedListener {

	/*
	 * TODO Add input checking upon sendSubmit() TODO Come up with solution for
	 * determining city
	 */

	NewSearchFragment searchFragment;
	final private String tag = "DARKGRUE";
	final private String tagErr = "DARKGRUE-ERROR";

	// //////////////////////////////////////////////////
	// Resources to return that form query
	private String searchQuery = "";
	private Category category;
	private int minAsk;
	private int maxAsk;
	private boolean hasPic;
	private boolean searchTitle;
	private String city = ""; // TODO FIXME Come up with a solution for this
	// TODO Need timer
	

	// //////////////////////////////////////////////////
	// Activity Lifecycle Methods
	// //////////////////////////////////////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.hasPic = false;
		this.searchTitle = false;

		FragmentManager fm = getFragmentManager();
		if (fm.findFragmentById(android.R.id.content) == null) {

			// //////////////////////////////////////////////////
			// Setting up Fragment
			this.searchFragment = new NewSearchFragment();

			fm.beginTransaction()
					.add(android.R.id.content, this.searchFragment).commit();
		}

	}

	@Override
	protected void onStart() {
		// TODO Fill this in
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Fill this in
		super.onResume();
	}

	@Override
	protected void onRestart() {
		// TODO Fill this in
		super.onRestart();
	}

	@Override
	protected void onPause() {
		// TODO Fill this in
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Fill this in
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Fill this in
		super.onDestroy();
	}

	// //////////////////////////////////////////////////
	// Interaction Methods
	// //////////////////////////////////////////////////

	public void sendSubmit(View v) {
		Intent intent = new Intent();

		// TODO FIXME Data needs to be validated
		Log.d(this.tag, "sendSubmit()");
		getEditTexts();

		// TODO after validating information, bundle up the info and send it
		// over to the main activity?
		if (inputIsGood()) {
			Log.d(this.tag, "INSIDE sendSubmit()"); // TODO RTL
			intent.putExtra("searchQuery", this.searchQuery);
			intent.putExtra("minAsk", this.minAsk);
			intent.putExtra("maxAsk", this.maxAsk);
			intent.putExtra("hasPic", this.hasPic);
			intent.putExtra("searchTitle", this.searchTitle);
			intent.putExtra("category", this.category);
			setResult(RESULT_OK, intent);
			this.finish();
		} else {
			setResult(RESULT_CANCELED);
			this.finish();
		}

	}

	// ///////OnItemSelectedListener Methods

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// FOR SPINNER
		// An item was selected. you can retrieve the selected item using
		// parent.getItemAtPosition(pos)
		Log.d(this.tag, "SPINNER CLICK");
		this.category = stringToCategory(parent.getItemAtPosition(pos)
				.toString());
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// FOR SPINNER
		// Another interface callback
		Log.d(this.tag, "SPINNER NOTHING CLICK");
	}

	public void getEditTexts() {
		// search_query
		EditText _searchQuery_ = (EditText) findViewById(R.id.search_query);
		this.searchQuery = _searchQuery_.getText().toString().trim();
		// Log.d(this.tag, "Search query: " + this.searchQuery);

		// min_ask_text
		EditText _minAskText_ = (EditText) findViewById(R.id.min_ask_text);
		String _minAsk_ = "0";
		if(!_minAskText_.getText().toString().trim().isEmpty()){
			_minAsk_ = _minAskText_.getText().toString().trim();
		}
		this.minAsk = Integer.valueOf(_minAsk_);
		// Log.d(this.tag, "Min asking value: " + this.minAsk);

		// max_ask_text
		EditText _maxAskText_ = (EditText) findViewById(R.id.max_ask_text);
		String _maxAsk_ = "0";
		if(!_maxAskText_.getText().toString().trim().isEmpty()){
			_maxAsk_ = _maxAskText_.getText().toString().trim();
		}
		this.maxAsk = Integer.valueOf(_maxAsk_);
		// Log.d(this.tag, "Max asking value: " + this.maxAsk);

	}

	public void onCheckBoxClicked(View view){
		//A checkbox has been clicked
		
		switch(view.getId()){
		case R.id.has_pic_box:
			this.hasPic = !this.hasPic;
			break;
		case R.id.search_title_box:
			this.searchTitle = !this.searchTitle;
			break;
		}
		
	}

	// //////////////////////////////////////////////////
	// Data model methods
	// //////////////////////////////////////////////////

	private Category stringToCategory(String catAsString) {
		HashMap<String, Category> map = reverseEnumMap(new URLMaker()
				.getCategoriesMap());
		Category cat;

		if (map.containsKey(catAsString)) {
			cat = map.get(catAsString);
			Log.d(this.tag,
					"For str " + catAsString + " returning " + cat.toString());
			return cat;
		} else {
			Log.d(this.tagErr, "Could not find category for string "
					+ catAsString);
			return null;
		}
	}

	private HashMap<String, Category> reverseEnumMap(
			EnumMap<Category, String> catMap) {
		HashMap<String, Category> revMap = new HashMap<String, Category>();
		String[] vals = catMap.values().toArray(new String[catMap.size()]);
		Category[] keys = catMap.keySet().toArray(new Category[catMap.size()]);

		for (int i = 0; i < catMap.size(); i++) {
			revMap.put(vals[i], keys[i]);
			// Log.d(this.tag, "KEY:" + vals[i] + " VALUE: " +
			// keys[i].toString());
		}
		return revMap;
	}

	private boolean inputIsGood() {
		// FIXME This method needs to actually verify data TODO
		if(this.searchQuery == null){
			searchQuery = " ";
		}
		
		if(this.category == null){
			this.category = Category.sss;
		}
		
		
		return true;
	}

}
