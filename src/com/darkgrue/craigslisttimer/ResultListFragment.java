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
import android.widget.ListView;

public class ResultListFragment extends ListFragment {
	// Listener for communicating between this fragment and the activity
	OnResultSelectedListener mCallback;

	public interface OnResultSelectedListener {
		public void onResultSelected(int position);
	}

	final String[] default_result = new String[] { "No Results" };
	private List<String> result_list = new ArrayList<String>();
	private List<String> empty_list = Arrays.asList(default_result);
	private ArrayAdapter<String> adapter;
	// String[] empty_list = new String[] { "No Results" };
	int clickCount = 0; // TODO Debug line RMTL

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO FIXME Show the result info

		/*
		 * // TODO FIXME BEGIN DEBUG STUFF this.clickCount++;
		 * result_list.add(String.valueOf(clickCount)); adapter = new
		 * ArrayAdapter<String>(getActivity().getLayoutInflater() .getContext(),
		 * android.R.layout.simple_list_item_1, this.result_list);
		 * setListAdapter(adapter); // TODO FIXME END DEBUG STUFF
		 */

		/*
		 * Eventually this method will need to call an intent to launch a new
		 * activity that is the result page for the corresponding result
		 */
		mCallback.onResultSelected(position); // Communicate to the activity
												// what was clicked
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (!this.result_list.isEmpty()) {
			adapter = new ArrayAdapter<String>(inflater.getContext(),
					android.R.layout.simple_list_item_1, result_list);
			setListAdapter(adapter);
		} else {
			adapter = new ArrayAdapter<String>(inflater.getContext(),
					android.R.layout.simple_list_item_1, empty_list);
			setListAdapter(adapter);
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public void updateResultList(Activity activity, String[] result_list_) {
		ArrayList<String> newList = new ArrayList<String>();
		for (int i = 0; i < result_list_.length; i++) {
			newList.add(result_list_[i]);
		}
		updateResultList(activity, newList);
	}

	public void updateResultList(Activity activity,
			ArrayList<String> result_list_) {
		this.result_list = result_list_;
		if (result_list.isEmpty()) {
			adapter = new ArrayAdapter<String>(activity.getLayoutInflater()
					.getContext(), android.R.layout.simple_list_item_1,
					this.empty_list);
			setListAdapter(adapter);
		} else {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity
					.getLayoutInflater().getContext(),
					android.R.layout.simple_list_item_1, this.result_list);
			setListAdapter(adapter);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception.
		try {
			mCallback = (OnResultSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnResultSelectedListener.");
		}
	}
}
