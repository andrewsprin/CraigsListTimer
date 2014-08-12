package com.darkgrue.craigslisttimer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListingFragment extends Fragment {

	private String titleStr = "Please refresh this page";
	private TextView title;
	private TextView description;
	private String descStr;

	LayoutInflater inflater;
	ViewGroup container;

	public ListingFragment(String _title_, String _description_) {
		this.titleStr = _title_;
		this.descStr = _description_;
	}

	// **NOTE** Anytime that this fragment needs to be redrawn, it should
	// be destroyed and a new copy created with the updated information
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;
		View view = inflater.inflate(R.layout.fragment_listing, container,
				false);

		// Get the text views
		this.title = (TextView) view.findViewById(R.id.listing_title);
		this.description = (TextView) view
				.findViewById(R.id.listing_description);

		// Set the text views
		this.title.setText(this.titleStr);
		this.description.setText(this.descStr);
		
		
		// changeTitle(this.titleStr); // Deprecated code TODO Remove
		// changeDescription(this.descStr); // Deprecated code TODO Remove

		return view;
	}

	// ////////////////////////////////////////
	// Deprecated Code Below
	// ////////////////////////////////////////

	/*
	 * private void refresh() { View view =
	 * this.inflater.inflate(R.layout.fragment_listing, this.container, false);
	 * 
	 * }
	 * 
	 * private void changeTitle(String newTitle) { this.titleStr = newTitle;
	 * this.title = (TextView) getView().findViewById(R.id.fragment_listing)
	 * .findViewById(R.id.listing_title); this.title.setText(this.titleStr); }
	 * 
	 * private void changeDescription(String newDesc) { this.descStr = newDesc;
	 * this.description = (TextView) getView().findViewById(
	 * R.id.fragment_listing).findViewById(R.id.listing_description);
	 * this.description.setText(this.descStr); }
	 * 
	 * public void setTitle(String newTitle) { this.titleStr = newTitle;
	 * refresh(); }
	 * 
	 * public void setDescription(String newDesc) { this.descStr = newDesc;
	 * refresh(); }
	 */

}
