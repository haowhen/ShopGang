package com.cis490.haonguyen.shopgang.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cis490.com.cis490.slidingmenu.adaptors.ExistingStoreAdapter;
import com.cis490.com.cis490.slidingmenu.adaptors.StoreItemListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;
import com.cis490.haonguyen.shopgang.model.Item;

/**
 * Created by Alex on 12/8/2014.
 */
public class ExistingStoreFragment extends Fragment {

    private ListView listView;
    private ExistingStoreAdapter adapter;

    @Override
    public void onStart(){
        super.onStart();
        fillList();
    }

    private void fillList()
    {
        listView = (ListView) getView().findViewById(R.id.listViewExistingStore);
        adapter = new ExistingStoreAdapter(getActivity());
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
        View view = inflater.inflate(R.layout.fragment_existing_store_list,
                container, false);

        return view;
    }
}