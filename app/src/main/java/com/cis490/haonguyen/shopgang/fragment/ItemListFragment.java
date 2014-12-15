package com.cis490.haonguyen.shopgang.fragment;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.cis490.Parse.Application;
import com.cis490.com.cis490.slidingmenu.adaptors.ItemListAdapter;
import com.cis490.com.cis490.slidingmenu.adaptors.StoreItemListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.AddItemActivity;
import com.cis490.haonguyen.shopgang.activity.DetailsActivity;
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;
import com.cis490.haonguyen.shopgang.activity.ListsofItemsActivity;
import com.cis490.haonguyen.shopgang.activity.StoreSelectionActivity;
import com.cis490.haonguyen.shopgang.model.Item;
import com.cis490.haonguyen.shopgang.model.ItemList;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseUser;

/**
 * Created by Alex on 11/29/2014.
 */
public class ItemListFragment extends Fragment {

    private ListView listView;
    private ItemListAdapter adapter;
    private Button btnAddItem;

    @Override
    public void onStart(){
        super.onStart();
        fillList();
    }

    private void fillList()
    {
        btnAddItem = (Button)getView().findViewById(R.id.btnAddItemList);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemList list = new ItemList();
                list.setOwnedBy();
                list.setStoreName(((ListsofItemsActivity)getActivity()).getStore());
                Application globalState = (Application) getActivity().getApplicationContext();
                list.saveEventually();

                ItemListFragment fragment = new ItemListFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ItemListcontainer, fragment);
                transaction.commit();
            }
        });

		final String storeTitle = ((ListsofItemsActivity)getActivity()).getStore();

		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setTitle(storeTitle);

        listView = (ListView) getView().findViewById(R.id.listviewItemList);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        adapter = new ItemListAdapter(getActivity(), storeTitle, manager);
        listView.setAdapter(adapter);
        adapter.loadObjects();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ItemList selectedList = (ItemList) listView.getItemAtPosition(position);
                Application globalState = (Application) getActivity().getApplicationContext();
                globalState.setItemList(selectedList);
                Intent intent = new Intent(getActivity(), ItemListActivity.class);
                intent.putExtra("selectedStore", storeTitle);
                startActivity(intent);
                }});
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_lists_items,
                container, false);

        return view;
    }
}
