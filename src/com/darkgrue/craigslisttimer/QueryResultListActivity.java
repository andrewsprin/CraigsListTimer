package com.darkgrue.craigslisttimer;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.darkgrue.craigslisttimer.URLMaker.Category;

public class QueryResultListActivity extends Activity implements
		QueryResultListFragment.OnQueryResultSelectedListener {

	private Query query;
	private QueryResultListFragment listFrag;
	private String tag = "DARKGRUE";

	// //////////////////////////////////////////////////
	// Activity Life Cycle Methods
	// //////////////////////////////////////////////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FragmentManager fm = getFragmentManager();
		if (fm.findFragmentById(android.R.id.content) == null) {
			this.listFrag = new QueryResultListFragment();
			fm.beginTransaction().add(android.R.id.content, this.listFrag).commit();
			unpackQuery(getIntent());
			new ResultPageFetcher().execute();
			refresh();
		}
	}

	// //////////////////////////////////////////////////
	// Interaction Methods
	// //////////////////////////////////////////////////

	@Override
	public void onQueryResultSelected(int position) {
		// TODO Auto-generated method stub
		// TODO This Needs to eventually launch a specific result
	}
	


	public void refresh() {
		this.listFrag.updateResultList(this, query.getResultDescriptionList());
	}

	// //////////////////////////////////////////////////
	// Data Model Methods
	// //////////////////////////////////////////////////
	private void unpackQuery(Intent intent) {
		// TODO FIXME Unbundle the intents and make a new query
		String _url = intent.getStringExtra("url");
		String _city = intent.getStringExtra("city");
		Category _category = (Category) intent.getSerializableExtra("category");
		String _searchQuery = intent.getStringExtra("searchQuery");
		int _minAsk = intent.getIntExtra("minAsk", 0);
		int _maxAsk = intent.getIntExtra("maxAsk", 0);
		boolean _hasPic = intent.getBooleanExtra("hasPic", false);
		boolean _searchTitle = intent.getBooleanExtra("searchTitle", false);

		this.query = new Query(_category, _searchQuery, _minAsk, _maxAsk,
				_hasPic, _searchTitle, _city);
	}
	
	// ResultPageFetcher
	private class ResultPageFetcher extends AsyncTask<Void, Void, Void> {
		ArrayList<String> queryResultURLList = new ArrayList<String>();
		ArrayList<String> descList = new ArrayList<String>();
		ArrayList<QueryResult> _resultList_ = new ArrayList<QueryResult>();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				// Connect to the web site
				Document document = Jsoup.connect(query.getURL()).get();
				Elements rawResultList = document.select("p[class]");
				//TODO Check to see if there is a next page
				for (int i = 0; i < rawResultList.size(); i++) {
					_resultList_
							.add(elementToQueryResult(rawResultList.get(i)));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Send the results to the Query class containing this in this
			// method
			query.setResultList(_resultList_);
			Log.d(tag, "QueryResultList set");
			refresh();
			
		}

		public QueryResult elementToQueryResult(Element res) {
			return new QueryResult(getChildURL(res), getChildDescription(res));
		}

		public String getChildURL(Element parent) {
			return parent.children().select("a").first().attr("abs:href");
		}

		public String getChildDescription(Element parent) {
			Elements pl = parent.children().select("span.pl");
			return pl.select("a").first().text();
		}

		public ArrayList<String> strArrToArrayList(String[] arr) {
			ArrayList<String> returnMe = new ArrayList<String>();
			for (int i = 0; i < arr.length; i++) {
				returnMe.add(arr[i]);
			}
			return returnMe;
		}
		
		public boolean hasNextPage(Document doc){
			// TODO Check to see if there is a next page here
			return false;
		}
	}

}
