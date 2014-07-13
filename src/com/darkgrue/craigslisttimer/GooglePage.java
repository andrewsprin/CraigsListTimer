package com.darkgrue.craigslisttimer;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.os.AsyncTask;
import android.util.Log;


// ////////////////////////////////////////////////////////////
// ////////////////////////////////////////////////////////////
public class GooglePage extends AsyncTask<Void, Void, Void> {
	private ArrayList<String> pageArr = new ArrayList<String>();
	private String url = "http://www.google.com";
	public boolean pageComplete = false;

	public ArrayList<String> getPageAsArrayList() {
		if (pageArr.size() > 0) {
			Log.d("JSOUP-DARKGRUE", "Attempt to open url was succesfull");
			for (int i = 0; i < this.pageArr.size(); i++) {
				Log.d("JSOUP-DARKGRUE", this.pageArr.get(i));
			}
		} else {
			Log.d("JSOUP-DARKGRUE", "Attempt to open url FAILED.");
		}

		if (this.pageComplete) {
			return this.pageArr;
		} else {
			return new ArrayList<String>();
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("JSOUP-DARKGRUE", "Attempting to open connection to "
				+ this.url);
	}

	@Override
	protected Void doInBackground(Void... params) {
		String testText = "";
		String[] testArr;

		try {
			// Connect to the website
			Document document = Jsoup.connect(url).get();
			testText = document.body().text();
			testArr = testText.split(" ");
			for (int i = 0; i < testArr.length; i++) {
				this.pageArr.add(testArr[i]);
			}
			pageComplete = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		/*
		 * if(pageArr.size() > 0){ Log.d("JSOUP-DARKGRUE",
		 * "Attempt to open url was succesfull"); for(int i = 0; i <
		 * this.pageArr.size(); i++){ Log.d("JSOUP-DARKGRUE",
		 * this.pageArr.get(i)); } } else { Log.d("JSOUP-DARKGRUE" ,
		 * "Attempt to open url FAILED."); }
		 */
	}

}
