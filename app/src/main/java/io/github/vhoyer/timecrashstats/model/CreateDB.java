package io.github.vhoyer.timecrashstats.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vhoyer on 05/05/17.
 */

public class CreateDB extends SQLiteOpenHelper {
	public static final String DB_NAME = "TCStats.db";
	public static final String TABLE = "playsStats";
	public static final String ID = "_id";
	public static final String DATETIME = "dateTime";
	public static final String LASTSYNCED = "lastSynced";
	public static final String POINTS = "points";
	public static final int VERSION = 2;

	public CreateDB(Context context){
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE + "("
			+ ID + " integer primary key autoincrement,"
			+ DATETIME + " text,"
			+ LASTSYNCED + " text,"
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
