package io.github.vhoyer.timecrashstats;


import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import io.github.vhoyer.timecrashstats.controller.RankingCursorAdapter;
import io.github.vhoyer.timecrashstats.model.dbController;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment implements View.OnClickListener {


	public RankingFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
		fab.setVisibility(View.VISIBLE);
		fab.setOnClickListener(this);

		dbController crud = new dbController(getActivity());
		Cursor cursor = crud.loadRanking();

		ListView rankList = (ListView) getActivity().findViewById(R.id.rank_rankList);
		RankingCursorAdapter adptr = new RankingCursorAdapter(getActivity(), cursor);
		rankList.setAdapter(adptr);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_ranking, container, false);
	}

	@Override
	public void onClick(View view) {
		Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
			.setAction("Action", null).show();
	}

	@Override
	public void onDestroyView(){
		super.onDestroyView();

		FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
		fab.setVisibility(View.INVISIBLE);
	}
}
