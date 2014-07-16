package com.darkgrue.craigslisttimer;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

public class QueryResultListFragment extends TemplateResultListFragment {

	OnQueryResultSelectedListener mCallBack;
	private String[] default_result = new String[] { "Loading..." };
	
	public interface OnQueryResultSelectedListener {
		public void onQueryResultSelected(int position);
	}

	// //////////////////////////////////////////////////
	// LifeCycle Methods
	// //////////////////////////////////////////////////

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception.
		try {
			mCallBack = (OnQueryResultSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnQueryResultSelectedListener.");
		}
	}

	// //////////////////////////////////////////////////
	// Interaction Methods
	// //////////////////////////////////////////////////

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO FIXME Show the result info
		mCallBack.onQueryResultSelected(position); // Communicate to the
													// activity
													// what was clicked
	}

	// //////////////////////////////////////////////////
	// Dummy Data Methods / Debug Methods
	// //////////////////////////////////////////////////
	private void fillWithDummyData() {
		ArrayList<String> data = new ArrayList<String>();
		data.add("TESTING");
		this.updateResultList(getActivity(), data);
	}

}
