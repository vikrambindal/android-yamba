package vikram.app.yamba.fragments;

import vikram.app.yamba.R;
import vikram.app.yamba.adapter.YambaListAdapter;
import vikram.app.yamba.db.YambaDBHelper;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TweetListFragment extends ListFragment{

	static final String[] PROJECTION = {YambaDBHelper.COL_ID, YambaDBHelper.COL_USER, YambaDBHelper.COL_CREATEDAT, YambaDBHelper.COL_TEXT };
	static final String[] FROM = {YambaDBHelper.COL_USER, YambaDBHelper.COL_CREATEDAT, YambaDBHelper.COL_TEXT };
	static final int[] TO = { R.id.txt_list_user, R.id.txt_list_datetime, R.id.txt_list_msg };
	
	YambaDBHelper yambaDBHelper;
	SQLiteDatabase db;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		yambaDBHelper = new YambaDBHelper(getActivity());
		db = yambaDBHelper.getReadableDatabase();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		db.close();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Cursor c = db.query(YambaDBHelper.TABLE, PROJECTION, null, null, null, null, YambaDBHelper.COL_CREATEDAT + " desc");
		getActivity().startManagingCursor(c);
		
		YambaListAdapter adapter = new YambaListAdapter(getActivity(), R.layout.tweet_list_adapter, c, FROM, TO);
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tweet_list_fragment, container, false);
		return view;
	}
}
