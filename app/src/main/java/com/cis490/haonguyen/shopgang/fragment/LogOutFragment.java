package com.cis490.haonguyen.shopgang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.MainActivity;
import com.parse.ParseUser;

/**
 * Created by Hao on 12/6/2014.
 */
public class LogOutFragment  extends Fragment {
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_logout,
				container, false);

		return view;
	}

}
