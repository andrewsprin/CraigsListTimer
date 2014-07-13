package com.darkgrue.craigslisttimer;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import android.os.AsyncTask;
import android.util.Log;

public class CLQueryPage extends AsyncTask<Void, Void, ArrayList<String>> {
	private ArrayList<String> pageArr = new ArrayList<String>();
	private String url;
	public boolean pageComplete = false;
	private Document document;

	public Document getDocument() {
		if (this.pageComplete) {
			return this.document;
		} else {
			Log.d("JSOUP-DARKGRUE", "Attempting to return an empty document");
			return null;
		}
	}

	public CLQueryPage(String URL) {
		this.url = URL;
	}

	public String getURL() {
		return this.url;
	}

	public ArrayList<String> getPageAsArrayList() {
		if (this.pageComplete) {
			return this.pageArr;
		} else {
			return new ArrayList<String>();
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("JSOUP-DARKGRUE", "Attempting to open connection to " + this.url);
	}

	@Override
	protected ArrayList<String> doInBackground(Void... params) {
		String[] pageStrArr;

		try {
			document = Jsoup.connect(this.url).get();
			pageStrArr = document.text().split(" ");
			for (int i = 0; i < pageStrArr.length; i++) {
				this.pageArr.add(pageStrArr[i]);
			}
			// if (document.hasText()) {
			this.pageComplete = true;
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pageArr;
	}

	@Override
	protected void onPostExecute(ArrayList<String> result) {
		// TODO Should this function return the results Document or
		// ArrayList<String>?
		if (pageArr.size() > 0) {
			// This if statement essentially double checks pageComplete
			Log.d("JSOUP-DARKGRUE", "Attempt to open url was succesfull");
			for (int i = 0; i < this.pageArr.size(); i++) {
				Log.d("JSOUP-DARKGRUE", this.pageArr.get(i));
			}
		} else {
			Log.d("JSOUP-DARKGRUE", "Attempt to open url FAILED.");
		}
	}


}
