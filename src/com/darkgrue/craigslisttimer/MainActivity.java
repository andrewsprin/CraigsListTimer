package com.darkgrue.craigslisttimer;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements
		ResultListFragment.OnResultSelectedListener {

	/*
	 * TODO Rework this class to hold Queries instead of results
	 * 
	 * TODO Write class to form the queries that will be stored in this class
	 * 
	 * TODO Map object? that holds the relationship of result titles and url for
	 * that specific result
	 * 
	 * TODO Save state to a database
	 * 
	 * TODO FIXME Fix the rendering stuttering issue (Async stuff regarding
	 * refresh)
	 */

	private ResultListFragment list;
	private ArrayList<String> queryDescriptionList;
	private ArrayList<Query> queryList;

	private final String dgTag = "DARKGRUE-ANDROID"; // Used for Log.d() calls

	// //////////////////////////////////////////////////
	// Activity Life Cycle Methods
	// //////////////////////////////////////////////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.queryDescriptionList = new ArrayList<String>();
		this.queryList = new ArrayList<Query>();

		loadQueryList(); // TODO DUMMY DATA - LOAD WITH REAL DATA

		FragmentManager fm = getFragmentManager();
		if (fm.findFragmentById(android.R.id.content) == null) {
			this.list = new ResultListFragment();
			fm.beginTransaction().add(android.R.id.content, this.list).commit();
			refresh();
		}

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
	@Override
	public void onResultSelected(int position) {
		/*
		 * TODO For the main class this
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu, menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void getResultPage(ResultListFragment list, CLQueryPage page) {
		// TODO FIXME This needs to handle waiting for the results to come back
		boolean resultsHad = false;
		while (!resultsHad) {
			Log.d(this.dgTag, "Attempting to get data from " + page.getURL());
			list.updateResultList(this, page.getPageAsArrayList());

			if (page.pageComplete) {
				resultsHad = true;
			}
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_refresh:
			refresh();
			return true;
		case R.id.action_settings:
			// TODO FIXME Setting panel launch here
			return true;
		case R.id.new_search:
			// TODO Launch activity for a new search here
			Intent intent = new Intent(this, NewQuery.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void refresh() {
		list.updateResultList(this, this.queryDescriptionList);
	}

	// //////////////////////////////////////////////////
	// Model/Data Management Methods
	// //////////////////////////////////////////////////

	public void addQuery(Query _query_) {
		this.queryList.add(_query_);
		this.queryDescriptionList.add(_query_.getDescription());
	}

	public void updateQuery(int position, Query _query_) {
		this.queryList.set(position, _query_);
		this.queryDescriptionList.set(position, _query_.getDescription());
	}
	
	public void removeQuery(int position){
		this.queryList.remove(position);
		this.queryDescriptionList.remove(position);
	}

	// //////////////////////////////////////////////////
	// Dummy Data Methods TODO DELETE THIS SECTION
	// //////////////////////////////////////////////////

	private void loadQueryList() {
		this.queryDescriptionList.add("Macbook");
		this.queryDescriptionList.add("Motorcycle");
		this.queryDescriptionList.add("Apartment");
		this.queryDescriptionList.add("New Job");
	}

	// /////////////////////////////////////////////////////
	// Deprecated Code Below
	// /////////////////////////////////////////////////////

	/*
	 * public void sendMessage(View view) {
	 * 
	 * // Do something in response to button 1
	 * 
	 * Intent intent = new Intent(this, ListViewActivity.class);
	 * 
	 * startActivity(intent); }
	 */

}