package com.darkgrue.craigslisttimer;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

public class ResultListFragment extends TemplateResultListFragment {
	// Listener for communicating between this fragment and the activity
	OnResultSelectedListener mCallback;

	public interface OnResultSelectedListener {
		public void onResultSelected(int position);
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
			mCallback = (OnResultSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnResultSelectedListener.");
		}
	}

	// //////////////////////////////////////////////////
	// Interaction Methods
	// //////////////////////////////////////////////////

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO FIXME Show the result info
		mCallback.onResultSelected(position); // Communicate to the activity
												// what was clicked
	}


}
