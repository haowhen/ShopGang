package com.cis490.haonguyen.shopgang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.StoreItemListFragment;

public class ItemListActivity extends FragmentActivity {

    private String store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        StoreItemListFragment fragment = new StoreItemListFragment();

        FragmentManager manager = getSupportFragmentManager();
        store = getIntent().getStringExtra("selectedStore");
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ItemListcontainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public String getStore(){return store;}
}
