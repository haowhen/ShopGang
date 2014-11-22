package com.cis490.haonguyen.shopgang.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cis490.haonguyen.shopgang.R;

/**
 * Created by Hao on 11/20/2014.
 */
public class SplashFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_splash,
				container, false);
		return view;
	}
}
