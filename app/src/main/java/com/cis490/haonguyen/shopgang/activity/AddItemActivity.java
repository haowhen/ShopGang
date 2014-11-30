package com.cis490.haonguyen.shopgang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.AddItemFragment;
import com.cis490.haonguyen.shopgang.model.Item;

public class AddItemActivity extends FragmentActivity {

    private Item item;
    private String store;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        item = new Item();
        store = getIntent().getStringExtra("selectedStore");

        AddItemFragment fragment = new AddItemFragment();

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.AddItemContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public Item getAddItem() {
        return item;
    }
    public String getStore() {
        return store;
    }

}