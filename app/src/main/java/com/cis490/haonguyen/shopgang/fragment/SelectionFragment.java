package com.cis490.haonguyen.shopgang.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.cis490.Parse.Application;
import com.cis490.com.cis490.slidingmenu.adaptors.MainListAdapter;
import com.cis490.com.cis490.slidingmenu.adaptors.RefreshAdapters.StoredMainListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.AddStoreActivity;
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;
import com.cis490.haonguyen.shopgang.activity.ListsofItemsActivity;
import com.cis490.haonguyen.shopgang.activity.StoreSelectionActivity;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hao on 11/20/2014.
 */
public class SelectionFragment extends Fragment {

    private ListView listView;
    private MainListAdapter adapter;
    private StoredMainListAdapter storedAdapter;
    private View v;
    private ViewGroup parent;
    private boolean refresh = false;

    @Override
    public void onStart(){
        super.onStart();
            CreateViewListObj();
    }

    private void CreateViewListObj()
    {
        Application globalState = (Application)getActivity().getApplicationContext();

        listView = (ListView) getView().findViewById(R.id.listViewMain);

        ArrayList<Store> tempStores = globalState.MainStoreRefresh();

        if(globalState.MainStoreRefresh().size() == 0)
        {
            adapter = new MainListAdapter(getActivity());
            globalState.FORCEREFRESH();
            listView.setAdapter(adapter);
            adapter.loadObjects();
        }

        else
        {
            if(!globalState.getStoreRefreshState())
            {
                globalState.PurgeExtras();
            }
            storedAdapter = new StoredMainListAdapter(getActivity(),R.layout.listview_mainpage, globalState.MainStoreRefresh());
            listView.setAdapter(null);
            listView.setAdapter(storedAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Store selectedStore = (Store) listView.getItemAtPosition(position);
                String selectedFromList = selectedStore.getStoreName();  // Or whatever method you need
                Intent intent = new Intent(getActivity(), ListsofItemsActivity.class);
                intent.putExtra("selectedStore", selectedFromList);
                startActivity(intent);
            }
        });

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
