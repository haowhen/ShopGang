package com.cis490.haonguyen.shopgang.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.AddItemFragment;
import com.cis490.haonguyen.shopgang.fragment.DetailsFragment;
import com.cis490.haonguyen.shopgang.model.Item;

public class DetailsActivity extends FragmentActivity {

    private Item item;
    private String store;

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

        DetailsFragment fragment = new DetailsFragment();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.AddItemContainer, fragment);
        transaction.commit();
    }

}