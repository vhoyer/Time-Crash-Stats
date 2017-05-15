package com.wecrash.timecrashstats.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vhoyer on 07/05/17.
 */

public class dbController {

	private SQLiteDatabase sqliteDB;
	private CreateDB db;

	public dbController(Context context){
		db = new CreateDB(context);
	}

	public long insert(String datetime, String points){
		SimpleDateFormat dateFormat;
		ContentValues values;
		String curdatetime;
		long result;

		dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		curdatetime = dateFormat.format(new Date()); //2009-06-30 08:29:36
		try { //converting date format to be more convenient
			Date dt = dateFormat.parse(datetime);
			datetime = dateFormat.format(dt);
		} catch (Exception err) { }

		sqliteDB = db.getWritableDatabase();
		values = new ContentValues();
		values.put(CreateDB.DATETIME, datetime);
		values.put(CreateDB.LASTSYNCED, curdatetime);
		values.put(CreateDB.POINTS, points);

		result = sqliteDB.insert(CreateDB.TABLE, null, values);
		sqliteDB.close();

		return result;
	}

	public Cursor loadAll(){
		String[] campos =  {db.ID, db.LASTSYNCED, db.POINTS, db.DATETIME};
		sqliteDB = db.getReadableDatabase();
		Cursor cursor = sqliteDB.query(db.TABLE, campos, null, null, null, null, null, null);

		if(cursor!=null){
			cursor.moveToFirst();
		}
		sqliteDB.close();
		return cursor;
	}

	public Cursor loadRanking(){
		String query = "SELECT * FROM " + db.TABLE + " ORDER BY cast(" + db.POINTS + " as unsigned) DESC";
		sqliteDB = db.getReadableDatabase();
		Cursor cursor = sqliteDB.rawQuery(query, null);

		if(cursor!=null){
			cursor.moveToFirst();
		}
		sqliteDB.close();
		return cursor;
	}
}
