package com.cis490.haonguyen.shopgang.fragment;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cis490.com.cis490.slidingmenu.adaptors.MainListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getActivity(), ItemListActivity.class);
                intent.putExtra(SyncStateContract.Constants.DATA.toString(), position);
                startActivity(intent);
            }
        });
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
