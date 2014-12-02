package com.cis490.haonguyen.shopgang.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cis490.com.cis490.slidingmenu.adaptors.NavDrawerListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.AddStoreFragment;
import com.cis490.haonguyen.shopgang.fragment.SelectionFragment;
import com.cis490.haonguyen.shopgang.fragment.SplashFragment;
import com.cis490.haonguyen.shopgang.model.Store;
import com.cis490.slidingmenu.models.NavDrawerItem.NavDrawerItem;
import com.facebook.AppEventsLogger;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
import com.facebook.widget.UserSettingsFragment;
import java.util.ArrayList;


public class MainActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    //private Store store;

	private boolean isResumed = false;

	LinearLayout drawerll;

	private ProfilePictureView mUserImage;
	private TextView mUserName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        //store = new Store();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		drawerll = (LinearLayout) findViewById(R.id.drawerll);
        navDrawerItems = new ArrayList<NavDrawerItem>();

		mUserImage = (ProfilePictureView) findViewById(R.id.profile_pic);
		mUserImage.setCropped(true);
		mUserName = (TextView) findViewById(R.id.user_name);
		Session session = Session.getActiveSession();
		makeMeRequest(session);

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        navMenuIcons.recycle();

        adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
        mDrawerList.setAdapter(adapter);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer,R.string.app_name,R.string.app_name){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {

			// on first time display view for first nav item

			displayView(0);

		}
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
		}

	//  private method that will be called due to session state changes.
	// The method shows the relevant fragment based on the person's authenticated state.
	private void onSessionStateChange(final Session session, SessionState state, Exception exception) {

		if(session != null && session.isOpened()){
			//get user's data
			makeMeRequest(session);
		}

		// Only make changes if the activity is visible
		if (isResumed) {
			FragmentManager manager = getSupportFragmentManager();
			// Get the number of entries in the back stack
			int backStackSize = manager.getBackStackEntryCount();
			// Clear the back stack
			for (int i = 0; i < backStackSize; i++) {
				manager.popBackStack();
			}
			if (state.isOpened()) {
				// If the session state is open:
				// Show the authenticated fragment
                SelectionFragment selFrag = new SelectionFragment();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainLayout, selFrag);
                transaction.addToBackStack(null);
                transaction.commit();
			} else if (state.isClosed()) {
				// If the session state is closed:
				// Show the login fragment
                SplashFragment splashFrag = new SplashFragment();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainLayout, splashFrag);
                transaction.addToBackStack(null);
                transaction.commit();
			}
		}
	}

	// fragments are newly instantiated and the authenticated versus nonauthenticated UI needs to be properly set
	@Override
	protected void onResumeFragments() {
		super.onResumeFragments();
		Session session = Session.getActiveSession();
        FragmentManager manager = getSupportFragmentManager();
		if (session != null && session.isOpened()) {
			// if the session is already open,
			// try to show the selection fragment
            SelectionFragment selFrag = new SelectionFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainLayout, selFrag);
            transaction.addToBackStack(null);
            transaction.commit();
		} else {
			// otherwise present the splash screen
			// and ask the person to login.
            SplashFragment splashFrag = new SplashFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainLayout, splashFrag);
            transaction.addToBackStack(null);
            transaction.commit();
		}
	}

	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback =
			new Session.StatusCallback() {
				@Override
				public void call(final Session session,
								 final SessionState state, final Exception exception) {
					onSessionStateChange(session, state, exception);
				}
			};


	@Override
	public void onResume() {
		super.onResume();

		isResumed = true;
		uiHelper.onResume();

		// Logs 'install' and 'app activate' App Events.
		AppEventsLogger.activateApp(this);

	}

	@Override
	public void onPause() {
		super.onPause();

		isResumed = false;
		uiHelper.onPause();

		// Logs 'app deactivate' App Event.
		AppEventsLogger.deactivateApp(this);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		boolean drawerOpen = mDrawerLayout.isDrawerOpen(drawerll);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);

		return super.onPrepareOptionsMenu(menu);

	}

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


	/**

	 * Slide menu item click listener

	 * */

	private class SlideMenuClickListener implements ListView.OnItemClickListener{

		@Override

		public void onItemClick(AdapterView<?> parent, View view, int position,

								long id) {

			// display view for selected nav drawer item

			displayView(position);

		}

	}

	private void displayView(int position) {

		// update the main content by replacing fragments

		Fragment fragment = null;

		switch (position) {

			case 0:

				fragment = new SelectionFragment();

				break;

			case 1:
                fragment = new SelectionFragment();

				break;

			case 2:
				break;

			case 3:
				break;
			case 4:
				break;
			case 5:
				fragment = new UserSettingsFragment();
				break;
			default:

				break;

		}




		if (fragment != null) {

			FragmentManager manager = getSupportFragmentManager();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();



			// update selected item and title, then close the drawer

			mDrawerList.setItemChecked(position, true);

			mDrawerList.setSelection(position);

			setTitle(navMenuTitles[position]);

			mDrawerLayout.closeDrawer(drawerll);

		} else {

			// error in creating fragment

			Log.e("MainActivity", "Error in creating fragment");

		}

	}

	private void makeMeRequest(final Session session) {
		// Make an API call to get user data and define a
		// new callback to handle the response.
		Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
			@Override
			public void onCompleted(GraphUser user, Response response) {
				// If the response is successful
				if (session == Session.getActiveSession()) {
					if (user != null) {
						// Set the id for the ProfilePictureView
						// view that in turn displays the profile picture.
						mUserImage.setProfileId(user.getId());
						mUserName.setText(user.getName());
					}
				}
				if (response.getError() != null) {
					// Handle errors, will do so later.
				}
			}
		});
		request.executeAsync();
	}
    //public Store getCurrentAddStore() {
    //    return store;
    //}
}
