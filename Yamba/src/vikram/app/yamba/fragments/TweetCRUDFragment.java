package vikram.app.yamba.fragments;

import vikram.app.yamba.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TweetCRUDFragment extends Fragment {

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
		return view;
	}
}
