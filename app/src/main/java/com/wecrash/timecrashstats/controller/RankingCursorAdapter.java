package com.wecrash.timecrashstats.controller;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.github.vhoyer.timecrashstats.R;
import com.wecrash.timecrashstats.model.CreateDB;

/**
 * Created by vhoyer on 12/05/17.
 */

public class RankingCursorAdapter extends CursorAdapter {
	public RankingCursorAdapter(Context context, Cursor c) {
		super(context, c, 0);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return LayoutInflater.from(context).inflate(R.layout.ranking_item_layout, parent, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// Find fields to populate in inflated template
		TextView tvBody = (TextView) view.findViewById(R.id.rankitem_points);
		TextView tvPriority = (TextView) view.findViewById(R.id.rankitem_date);

		// Extract properties from cursor
		String points = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.POINTS));
		String datetime = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.DATETIME));
        try { //converting date format to be more convenient
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dt = dateFormat.parse(datetime);

            dateFormat = android.text.format.DateFormat.getDateFormat(context);
            datetime = dateFormat.format(dt);
        } catch (Exception err) { }

        // Populate fields with extracted properties
		tvBody.setText(points);
		tvPriority.setText(datetime);
	}
}
