package com.cis490.haonguyen.shopgang.fragment;

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

import com.cis490.com.cis490.slidingmenu.adaptors.MainListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Hao on 11/20/2014.
 */
public class SelectionFragment extends Fragment {

    private ListView listView;
    private MainListAdapter adapter;
    private List<Store> stores;
    private View v;
    private ViewGroup parent;
    private boolean refresh = false;
    private Button btnRefresh;

    @Override
    public void onStart(){
        super.onStart();
        refreshButtonActivate();
        if(stores == null) {
            fillList();
        }
        else
        {
            fillListStored();
        }
    }

    private void refreshButtonActivate() {
        btnRefresh = (Button)getView().findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillList();
            }
        });
    }

    private void fillList()
    {
        refresh = false;
        listView = (ListView) getView().findViewById(R.id.listViewMain);
        adapter = new MainListAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Store selectedStore = (Store) listView.getItemAtPosition(position);
                String selectedFromList = selectedStore.getStoreName();  // Or whatever method you need
                Intent intent = new Intent(getActivity(), ItemListActivity.class);
                intent.putExtra("selectedStore", selectedFromList);
                startActivity(intent);
            }
        });
        //Prevent having to refresh the page each time.
        for (int i = 0; i < adapter.getCount(); i++) {
            stores.add(adapter.getItem(i));
        }
        adapter.loadObjects();
    }

    private void fillListStored()
    {
        listView = (ListView) getView().findViewById(R.id.listViewMain);
        adapter = new MainListAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Store selectedStore = (Store) listView.getItemAtPosition(position);
                String selectedFromList = selectedStore.getStoreName();  // Or whatever method you need
                Intent intent = new Intent(getActivity(), ItemListActivity.class);
                intent.putExtra("selectedStore", selectedFromList);
                startActivity(intent);
            }
        });
        //Prevent having to refresh the page each time.
        for (int i = 0; i < stores.size(); i++) {
           adapter.getItemView(stores.get(i), v, parent);
        }
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
