package vikram.app.yamba.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class YambaDBHelper extends SQLiteOpenHelper {

	private static final String TAG = "YambaDBHelper";
	
	private static final int VERSION = 2;
	private static final String DATABASE = "Yamba.db";
	
	public static final String TABLE = "timeline";
	public static final String COL_ID = "_id";
	public static final String COL_CREATEDAT = "createdAt";
	public static final String COL_TEXT = "message";
	public static final String COL_USER = "user";
	
	public YambaDBHelper(Context context) {
		super(context, DATABASE, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.d(TAG, "creating DB");
		db.execSQL("create table " + TABLE + " ( " + COL_ID + " int primary key, " +
													 COL_USER + " text," +
													 COL_TEXT + " text," +
													 COL_CREATEDAT + " int )");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("drop table " + TABLE);
		onCreate(db);
	}

	public void closeDB(){
		close();
	}
}
