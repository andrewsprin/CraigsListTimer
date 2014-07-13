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

import com.darkgrue.craigslisttimer.URLMaker.Category;

public class NewQuery extends Activity implements OnItemSelectedListener {

	NewSearchFragment searchFragment;

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
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	// //////////////////////////////////////////////////
	// Interaction Methods
	// //////////////////////////////////////////////////

	public void sendSubmit(View v) {
		// TODO FIXME Data needs to be validated
		Log.d("DARKGRUE", "sendSubmit()");

	}
	
	// ///////OnItemSelectedListener Methods

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// FOR SPINNER
		// An item was selected. you can retrieve the selected item using
		// parent.getItemAtPosition(pos)
		Log.d("DarkGrue", "SPINNER CLICK");
		this.category = stringToCategory(parent.getItemAtPosition(pos)
				.toString());
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// FOR SPINNER
		// Another interface callback
		Log.d("DARKGRUE", "SPINNER CLICK");
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
			Log.d("DARKGRUE",
					"For str " + catAsString + " returning " + cat.toString());
			return cat;
		} else {
			Log.d("DARKGRUE_ERROR", "Could not find category for string "
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
			Log.d("DARKGRUE",
					"KEY:" + vals[i] + " VALUE: " + keys[i].toString());
		}
		return revMap;
	}

}
