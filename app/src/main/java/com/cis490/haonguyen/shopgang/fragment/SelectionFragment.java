package com.cis490.haonguyen.shopgang.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cis490.com.cis490.slidingmenu.adaptors.MainListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by Hao on 11/20/2014.
 */
public class SelectionFragment extends Fragment {

    private ListView listView;
    private MainListAdapter adapter;

    @Override
    public void onStart(){
        super.onStart();
        fillList();
    }

    private void fillList()
    {
        listView = (ListView) getView().findViewById(R.id.listViewMain);
        adapter = new MainListAdapter(getActivity());
        listView.setAdapter(adapter);
        adapter.loadObjects();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_selection,
				container, false);

		return view;
	}
}
