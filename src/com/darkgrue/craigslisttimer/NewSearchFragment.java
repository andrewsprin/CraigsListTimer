package com.darkgrue.craigslisttimer;

import java.util.EnumMap;
import java.util.HashMap;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.darkgrue.craigslisttimer.URLMaker.Category;

public class NewSearchFragment extends Fragment{

	Category category;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new_search, container,
				false);
		setSpinnerContent(view);

		return view;
	}

	private void setSpinnerContent(View view) {
		Spinner spinner = (Spinner) view.findViewById(R.id.search_category);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.categories_strings,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener((OnItemSelectedListener) this.getActivity());

	}


}
