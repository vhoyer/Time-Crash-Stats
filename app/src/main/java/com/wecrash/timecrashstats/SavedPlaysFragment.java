package com.wecrash.timecrashstats;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.wecrash.timecrashstats.model.dbController;

import io.github.vhoyer.timecrashstats.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedPlaysFragment extends Fragment implements View.OnClickListener {


	public SavedPlaysFragment() {
		// Required empty public constructor
	}

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);

		Button btnOk = (Button) getActivity().findViewById(R.id.savedPlays_btnOk);
		btnOk.setOnClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_saved_plays, container, false);
	}

	public void onClick(View view) {
		DatePicker dtpicker = (DatePicker) getActivity().findViewById(R.id.savedPlays_dtPicker);
		EditText edtPoints = (EditText) getActivity().findViewById(R.id.savedPlays_edtPoints);

		String dttime = dtpicker.getYear() + "-" + dtpicker.getMonth() + "-"  + dtpicker.getDayOfMonth() + " 08:29:36";

		long res = new dbController(getActivity()).insert(dttime, edtPoints.getText().toString());

		Snackbar.make(view, "wow! " + res, Snackbar.LENGTH_LONG).setAction("Action", null).show();
	}
}
