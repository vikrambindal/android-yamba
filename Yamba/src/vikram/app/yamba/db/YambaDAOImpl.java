package vikram.app.yamba.db;

import java.util.List;

import winterwell.jtwitter.Twitter.Status;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class YambaDAOImpl {

	private static final String TAG = "YambaDAOImpl";
	private YambaDBHelper yambaDBHelper;
	
	public YambaDAOImpl(Context context){
		yambaDBHelper = new YambaDBHelper(context);
		Log.d(TAG, "Initialised data");
	}
	
	public void saveMsg(Status status){
		
		SQLiteDatabase db = yambaDBHelper.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(YambaDBHelper.COL_ID, status.id);
		contentValues.put(YambaDBHelper.COL_USER, status.user.name);
		contentValues.put(YambaDBHelper.COL_TEXT, status.text);
		contentValues.put(YambaDBHelper.COL_CREATEDAT, status.createdAt.getTime());
		try{
			db.insertWithOnConflict(YambaDBHelper.TABLE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
		}finally{
			yambaDBHelper.closeDB();
		}
	}
	
	public void saveBulkMsgs(List<Status> statuses){
		
		SQLiteDatabase db = yambaDBHelper.getWritableDatabase();
		
		for (Status status : statuses) {
			
			ContentValues contentValues = new ContentValues();
			contentValues.put(YambaDBHelper.COL_ID, status.id);
			contentValues.put(YambaDBHelper.COL_USER, status.user.name);
			contentValues.put(YambaDBHelper.COL_TEXT, status.text);
			contentValues.put(YambaDBHelper.COL_CREATEDAT, status.createdAt.getTime());
			db.insertWithOnConflict(YambaDBHelper.TABLE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
			
		}
		yambaDBHelper.closeDB();
		
	}
	
	public Cursor getAllMsgs(){
		
		SQLiteDatabase db = yambaDBHelper.getReadableDatabase();
		return db.query(YambaDBHelper.TABLE, null, 
					null, null, null, null, YambaDBHelper.COL_CREATEDAT + " desc" );
	}
	
	public long getTimeStampOfLatestRecord(){
		
		try{
			SQLiteDatabase db = yambaDBHelper.getReadableDatabase();
			Cursor cursor = db.query(YambaDBHelper.TABLE, new String[]{"max(" + YambaDBHelper.COL_CREATEDAT + ")"}, 
					null, null, null, null, null);
			try{
				return cursor.moveToNext() ? cursor.getLong(0): Long.MIN_VALUE;
			}finally{
				cursor.close();
			}
		}finally{
			yambaDBHelper.closeDB();
		}
		
	}
	
	public String getMsgById(long id){
		
		try{
			SQLiteDatabase db = yambaDBHelper.getReadableDatabase();
			Cursor cursor = db.query(YambaDBHelper.TABLE, new String[]{YambaDBHelper.COL_TEXT}, 
			YambaDBHelper.COL_ID + " = " + id, null, null, null, null);
			try{
				return cursor.moveToNext() ? cursor.getString(0) : null;
			}finally{
				cursor.close();
			}
		}finally{
			yambaDBHelper.closeDB();
		}
	}
}
