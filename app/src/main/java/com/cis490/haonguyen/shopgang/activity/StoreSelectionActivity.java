package com.cis490.haonguyen.shopgang.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.StoreSelectionFragment;

/**
 * Created by Alex on 12/8/2014.
 */
public class StoreSelectionActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_selection);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        StoreSelectionFragment fragment = new StoreSelectionFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.StoreSelectionContainer, fragment);
        transaction.commit();
    }
}
