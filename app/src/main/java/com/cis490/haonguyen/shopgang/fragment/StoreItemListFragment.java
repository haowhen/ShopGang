package com.cis490.haonguyen.shopgang.fragment;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cis490.com.cis490.slidingmenu.adaptors.StoreItemListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.AddItemActivity;
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;

/**
 * Created by Alex on 11/29/2014.
 */
public class StoreItemListFragment extends Fragment {

    private ListView listView;
    private StoreItemListAdapter adapter;
    private Button btnAddItem;

    @Override
    public void onStart(){
        super.onStart();
        fillList();
    }

    private void fillList()
    {
        btnAddItem = (Button)getView().findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddItemActivity.class);
                intent.putExtra("selectedStore", ((ItemListActivity) getActivity()).getStore());
                startActivity(intent);
            }
        });

		String storeTitle = ((ItemListActivity)getActivity()).getStore();

		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setTitle(storeTitle);

        listView = (ListView) getView().findViewById(R.id.listviewItemList);
        adapter = new StoreItemListAdapter(getActivity(), storeTitle);
        listView.setAdapter(adapter);
        adapter.loadObjects();
        Toast toast = Toast.makeText(getActivity(),"Now displaying "+ storeTitle +" items.", Toast.LENGTH_LONG);
        toast.show();
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
