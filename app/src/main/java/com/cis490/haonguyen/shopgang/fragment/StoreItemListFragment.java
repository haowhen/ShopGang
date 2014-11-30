package com.cis490.haonguyen.shopgang.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cis490.com.cis490.slidingmenu.adaptors.StoreItemListAdapter;
import com.cis490.haonguyen.shopgang.R;

/**
 * Created by Alex on 11/29/2014.
 */
public class StoreItemListFragment extends Fragment {

    private ListView listView;
    private StoreItemListAdapter adapter;

    @Override
    public void onStart(){
        super.onStart();
        fillList();
    }

    private void fillList()
    {
        listView = (ListView) getView().findViewById(R.id.listviewItemList);
        adapter = new StoreItemListAdapter(getActivity());
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
        View view = inflater.inflate(R.layout.fragment_storeitemlist,
                container, false);

        return view;
    }
}
