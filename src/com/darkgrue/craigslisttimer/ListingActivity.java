package com.darkgrue.craigslisttimer;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ListingActivity extends Activity {
	// TODO Get contact information dynamically and present
	// the appropriate actions as options for the user

	ListingFragment listing;
	String url = "";
	String picURL = "";
	String title = "Loading...";
	String content = "";

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
			this.listing = new ListingFragment();
			TextView titleText = (TextView) findViewById(R.id.listing_title);
			titleText.setText(this.title);
			
			
			fm.beginTransaction().add(android.R.id.content, this.listing)
					.commit();

		}

	}

	@Override
	protected void onStart(){
		super.onStart();
		// TODO Fill this in
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		//TODO Fill this in
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();
		// TODO Fill this in
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		// TODO Fill this in
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		// TODO Fill this in
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		// TODO Fill this in
	}
	
	
	// //////////////////////////////////////////////////
	// Interaction Methods
	// //////////////////////////////////////////////////
	
	public void setNewTitle(String newTitle){
		this.title = newTitle;
		//TODO TextView.setText() used here?
	}
	
	
	// //////////////////////////////////////////////////
	// Data Model Methods
	// //////////////////////////////////////////////////
	
	
	// //////////////////////////////////////////////////
	// JSoup stuff
	// //////////////////////////////////////////////////
	// TODO Fetch the page here provided the URL
}
