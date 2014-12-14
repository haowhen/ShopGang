package com.cis490.haonguyen.shopgang.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cis490.com.cis490.slidingmenu.adaptors.FriendListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.LoginActivity;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Hao on 12/8/2014.
 */
public class FriendListFragment extends Fragment {

	private ListView listView;
	private FriendListAdapter adapter;
	ParseUser parseUser;

	@Override
	public void onStart(){
		super.onStart();

		CreateViewListObj();
		parseFillList();
	}


	private void CreateViewListObj(){
		listView = (ListView)getView().findViewById(R.id.listViewFriendList);
		adapter = new FriendListAdapter(getActivity());
		listView.setAdapter(adapter);
	}

	private void parseFillList(){
		adapter.loadObjects();
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_friendlist,
				container, false);

		return view;
	}
}
