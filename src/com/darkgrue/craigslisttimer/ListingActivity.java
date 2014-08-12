package com.darkgrue.craigslisttimer;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ListingActivity extends Activity {
	// TODO Get contact information dynamically and present
	// the appropriate actions as options for the user

	ListingFragment listing;
	private String tag = "DARKGRUE";
	String url = "";
	String picURL = "";
	String title = "";
	String description = "";
	int fragId;

	// //////////////////////////////////////////////////
	// Activity Lifecycle Methods
	// //////////////////////////////////////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		// Pack data into this intent that will help describe
		// how the UI should be formatted for this specific result

		/*
		 * For right now it assumed that each listing will have one image, a
		 * title, and a short description
		 */
		this.url = intent.getStringExtra("url");

		// TODO Start the finding of content here

		FragmentManager fm = getFragmentManager();
		if (fm.findFragmentById(android.R.id.content) == null) {
			this.listing = new ListingFragment("Loading Listing Title...",
					"Loading Listing Description...");
			fm.beginTransaction().add(android.R.id.content, this.listing)
					.commit();
			this.fragId = this.listing.getId();
		}

		// NEXT STEP changeTitle() and changeDescription() here then redraw()
		testUIMethods();
	}

	@Override
	protected void onStart() {
		super.onStart();
		// TODO Fill this in
	}

	@Override
	protected void onResume() {
		super.onResume();
		// TODO Fill this in
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		// TODO Fill this in
	}

	@Override
	protected void onPause() {
		super.onPause();
		// TODO Fill this in
	}

	@Override
	protected void onStop() {
		super.onStop();
		// TODO Fill this in
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// TODO Fill this in
	}

	// //////////////////////////////////////////////////
	// Interaction Methods
	// //////////////////////////////////////////////////

	public void redraw() {
		FragmentManager fm = getFragmentManager();
		if (fm.findFragmentById(android.R.id.content) == null) {
			Log.d(this.tag, "Attempting to redraw the screen");
			this.listing = new ListingFragment(this.title, this.description);
			fm.beginTransaction().add(android.R.id.content, this.listing)
					.commit();
			this.fragId = this.listing.getId();

		}
	}

	public void changeTitle(String newTitle) {
		this.title = newTitle;
		this.setTitle(this.title);
	}

	public void changeDescription(String newDesc) {
		this.description = newDesc;
	}
	
	// //////////////////////////////////////////////////
	// Data Model Methods
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// JSoup stuff
	// //////////////////////////////////////////////////
	// TODO Fetch the page here provided the URL
	// TODO Get the title of the page
	// TODO Get the content of the page
	// TODO Get Image from the page

	// //////////////////////////////////////////////////
	// Test Cases
	// //////////////////////////////////////////////////

	private void testUIMethods() {
		Log.d(this.tag, "Attempting to change ListingFragment data");
		changeTitle("NEW TITLE. TEST WORKED.");
		changeDescription("NEW DESC. TEST WORKED.");
		redraw();
	}

}
