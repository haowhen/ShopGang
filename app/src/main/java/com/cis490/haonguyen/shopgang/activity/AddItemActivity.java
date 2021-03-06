package com.cis490.haonguyen.shopgang.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.AddItemFragment;
import com.cis490.haonguyen.shopgang.model.Item;
import com.cis490.haonguyen.shopgang.model.ItemList;

public class AddItemActivity extends FragmentActivity {

    private Item item;
    private String store;
    private ItemList list;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			// Respond to the action bar's Up/Home button
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

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
        transaction.commit();
    }

    public Item getAddItem() {
        return item;
    }
    public ItemList getItemList() {return list;}
    public String getStore() {
        return store;
    }

}