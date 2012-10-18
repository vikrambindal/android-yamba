package vikram.app.yamba.fragments;

import vikram.app.yamba.R;
import vikram.app.yamba.YambaApplication;
import winterwell.jtwitter.Twitter;
import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TweetCRUDFragment extends Fragment implements TextWatcher, OnClickListener {

	private static final String TAG = "TweetCRUDFragment";
	private EditText msgTxt;
	private Button postBtn;
	private TextView charsRemTxt;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tweet_crud_fragment, container, false);
		
		msgTxt = (EditText)view.findViewById(R.id.txt_crud_msg);
		charsRemTxt = (TextView)view.findViewById(R.id.txt_crud_charsrem);
		postBtn = (Button)view.findViewById(R.id.btn_crud_post);
		
		msgTxt.addTextChangedListener(this);
		postBtn.setOnClickListener(this);
		return view;
	}

	public void onClick(View view) {
		Log.d(TAG, "onClick");
		String message = msgTxt.getText().toString();
		new PostToTwitter().execute(message);
		
	}
	
	public void afterTextChanged(Editable editableText) {
		int count = 140 - msgTxt.length(); 
		charsRemTxt.setText(Integer.toString(count) + " Characters");
		charsRemTxt.setTextColor(Color.GREEN); 
	    if (count < 10)
	    	charsRemTxt.setTextColor(Color.YELLOW);
	    if (count < 0)
	    	charsRemTxt.setTextColor(Color.RED);
	}

	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}
	
	class PostToTwitter extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... params) {
			YambaApplication application = (YambaApplication)getActivity().getApplication();
			Twitter.Status updateStatus = application.getTwitter().updateStatus(params[0]);
			return updateStatus.getText();
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
		}		
		
	}
}
