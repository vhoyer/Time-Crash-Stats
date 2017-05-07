package io.github.vhoyer.timecrashstats.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vhoyer on 07/05/17.
 */

public class dbController {

	private SQLiteDatabase db;
	private createPlayerStats playerStats;

	public dbController(Context context){
		playerStats = new createPlayerStats(context);
	}

	public long insert(String datetime, String points){
		ContentValues values;
		SimpleDateFormat databaseDateTimeFormate;
		String curdatetime;
		long result;

		databaseDateTimeFormate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		curdatetime = databaseDateTimeFormate.format(new Date()); //2009-06-30 08:29:36

		db = playerStats.getWritableDatabase();
		values = new ContentValues();
		values.put(createPlayerStats.DATETIME, datetime);
		values.put(createPlayerStats.LASTSYNCED, curdatetime);
		values.put(createPlayerStats.POINTS, points);

		result = db.insert(createPlayerStats.TABLE, null, values);
		db.close();

		return result;
	}
}
