package io.github.vhoyer.timecrashstats.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vhoyer on 05/05/17.
 */

public class createPlayerStats extends SQLiteOpenHelper {
	private static final String DB_NAME = "TCStats.db";
	private static final String TABLE = "playsStats";
	private static final String ID = "id";
	private static final String DATATIME = "dateTime";
	private static final String POINTS = "points";
	private static final int VERSION = 1;

	public createPlayerStats(Context context){
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE + "("
			+ ID + " integer primary key autoincrement,"
			+ DATATIME + " text,"
			+ POINTS + " text"
			+")";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion >= newVersion){
			return;
		}
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		this.onCreate(db);
	}
}
