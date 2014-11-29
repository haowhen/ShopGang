package com.cis490.haonguyen.shopgang.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.AddStoreFragment;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseFile;

/*
 * NewMealActivity contains two fragments that handle
 * data entry and capturing a photo of a given meal.
 * The Activity manages the overall meal data.
 */
public class AddStoreActivity extends FragmentActivity {

	private Store store;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		store = new Store();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);

        AddStoreFragment fragment = new AddStoreFragment();

            FragmentManager manager = getSupportFragmentManager();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
	}

	public Store getAddStore() {
		return store;
	}

}
