package com.darkgrue.craigslisttimer;

import java.util.EnumMap;
import java.util.HashMap;

import android.app.Activity;
import android.app.FragmentManager;
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
		// TODO FIXME Data needs to be validated
		Log.d(this.tag, "sendSubmit()");
		getEditTexts();

		// TODO after validating information, bundle up the info and send it
		// over to the main activity?

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
		Log.d(this.tag, "Search query: "  + this.searchQuery);

		// min_ask_text
		EditText _minAskText_ = (EditText) findViewById(R.id.min_ask_text);
		this.minAsk = Integer.valueOf(_minAskText_.getText().toString().trim());
		Log.d(this.tag, "Min asking value: " + this.minAsk);
		// TODO Validate that is integer here

		// max_ask_text
		EditText _maxAskText_ = (EditText) findViewById(R.id.max_ask_text);
		this.maxAsk = Integer.valueOf(_maxAskText_.getText().toString().trim());
		Log.d(this.tag, "Max asking value: " + this.maxAsk);
		// TODO Validate that is integer here

	}

	// //////////////////////////////////////////////////
	// Data model methods
	// //////////////////////////////////////////////////

	public Category stringToCategory(String catAsString) {
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

	public HashMap<String, Category> reverseEnumMap(
			EnumMap<Category, String> catMap) {
		HashMap<String, Category> revMap = new HashMap<String, Category>();
		String[] vals = catMap.values().toArray(new String[catMap.size()]);
		Category[] keys = catMap.keySet().toArray(new Category[catMap.size()]);

		for (int i = 0; i < catMap.size(); i++) {
			revMap.put(vals[i], keys[i]);
			Log.d(this.tag, "KEY:" + vals[i] + " VALUE: " + keys[i].toString());
		}
		return revMap;
	}

}
