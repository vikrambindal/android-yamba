package vikram.app.yamba.adapter;

import vikram.app.yamba.R;
import vikram.app.yamba.db.YambaDBHelper;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class YambaListAdapter extends SimpleCursorAdapter {

	public YambaListAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to, CursorAdapter.NO_SELECTION);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		
		super.bindView(view, context, cursor);
		
		long timeStamp = cursor.getLong(cursor.getColumnIndex(YambaDBHelper.COL_CREATEDAT));
		TextView createdAtTxt = (TextView)view.findViewById(R.id.txt_list_datetime);
		createdAtTxt.setText(DateUtils.getRelativeTimeSpanString(timeStamp));
	}

}
