package com.darkgrue.craigslisttimer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class TemplateResultListFragment extends ListFragment {

	private String[] default_result = new String[] { "No Results" };
	final List<String> empty_list = Arrays.asList(default_result);
	private List<String> resultList = new ArrayList<String>();
	private ArrayAdapter<String> adapter;

	// //////////////////////////////////////////////////
	// LifeCycle Methods
	// //////////////////////////////////////////////////

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (!this.resultList.isEmpty()) {
			adapter = new ArrayAdapter<String>(inflater.getContext(),
					android.R.layout.simple_list_item_1, resultList);
			setListAdapter(adapter);
		} else {
			adapter = new ArrayAdapter<String>(inflater.getContext(),
					android.R.layout.simple_list_item_1, empty_list);
			setListAdapter(adapter);
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	// //////////////////////////////////////////////////
	// Interaction Methods
	// //////////////////////////////////////////////////

	// Handle these in the classes that inherit from this
	// these include any custom interface methods and onAttach()

	// //////////////////////////////////////////////////
	// Data Model Methods
	// //////////////////////////////////////////////////
	public void updateResultList(Activity activity,
			ArrayList<String> result_list_) {
		this.resultList = result_list_;
		if (resultList.isEmpty()) {
			adapter = new ArrayAdapter<String>(activity.getLayoutInflater()
					.getContext(), android.R.layout.simple_list_item_1,
					this.empty_list);
			setListAdapter(adapter);
		} else {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity
					.getLayoutInflater().getContext(),
					android.R.layout.simple_list_item_1, this.resultList);
			setListAdapter(adapter);
		}
	}

	public void updateResultList(Activity activity, String[] result_list_) {
		ArrayList<String> newList = new ArrayList<String>();

		if (result_list_.length > 0) {
			for (int i = 0; i < result_list_.length; i++) {
				newList.add(result_list_[i]);
			}
		}
		updateResultList(activity, newList);
	}

}
