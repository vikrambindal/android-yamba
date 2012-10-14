package vikram.app.yamba.fragments;

import vikram.app.yamba.R;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TweetCRUDFragment extends Fragment implements TextWatcher, OnClickListener {

	private EditText msgTxt;
	private Button postBtn;
	private TextView charsRemTxt;
	
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
		String message = msgTxt.getText().toString();
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
}
