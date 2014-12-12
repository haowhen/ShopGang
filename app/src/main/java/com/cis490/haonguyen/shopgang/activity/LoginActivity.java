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

import com.cis490.Parse.Application;
import com.cis490.com.cis490.slidingmenu.adaptors.NavDrawerListAdapter;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.FriendListFragment;
import com.cis490.haonguyen.shopgang.fragment.LogOutFragment;
import com.cis490.haonguyen.shopgang.fragment.SelectionFragment;
import com.cis490.slidingmenu.models.NavDrawerItem.NavDrawerItem;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hao on 12/6/2014.
 */
public class LoginActivity extends FragmentActivity {

	//Navmenu stuff
	LinearLayout drawerll;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	private ProfilePictureView mUserImage;
	private TextView mUserName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		//NavMenu stuff
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

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		navMenuIcons.recycle();

		adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
		mDrawerList.setAdapter(adapter);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
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

		// Fetch Facebook user info if the session is active
		Session session = ParseFacebookUtils.getSession();
		if (session != null && session.isOpened()) {
			makeMeRequest();

			makeMeFriendRequest();
		}
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
	 */

	private class SlideMenuClickListener implements ListView.OnItemClickListener {

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
				fragment = new FriendListFragment();
				break;
			case 5:
				fragment = new LogOutFragment();
				break;
			default:

				break;

		}


		if (fragment != null) {

			FragmentManager manager = getSupportFragmentManager();

			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.mainLayout, fragment);
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

	@Override
	public void onResume() {
		super.onResume();

		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			// Check if the user is currently logged
			// and show any cached content
			updateViewsWithProfileInfo();
		} else {
			// If the user is not logged in, go to the
			// activity showing the login view.
			startMainActivity();
		}
	}

	private void makeMeRequest() {
		Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
				new Request.GraphUserCallback() {
					@Override
					public void onCompleted(GraphUser user, Response response) {
						if (user != null) {
							// Create a JSON object to hold the profile info
							JSONObject userProfile = new JSONObject();
							try {
								// Populate the JSON object
								userProfile.put("facebookId", user.getId());
								userProfile.put("name", user.getName());
								if (user.getProperty("gender") != null) {
									userProfile.put("gender", user.getProperty("gender"));
								}
								if (user.getProperty("email") != null) {
									userProfile.put("email", user.getProperty("email"));
								}

								// Save the user profile info in a user property
								ParseUser currentUser = ParseUser.getCurrentUser();
								currentUser.put("profile", userProfile);
								currentUser.saveInBackground();

								// Show the user info
								updateViewsWithProfileInfo();
							} catch (JSONException e) {
								Log.d(Application.TAG, "Error parsing returned user data. " + e);
							}

						} else if (response.getError() != null) {
							if ((response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_RETRY) ||
									(response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_REOPEN_SESSION)) {
								Log.d(Application.TAG, "The facebook session was invalidated." + response.getError());
								logout();
							} else {
								Log.d(Application.TAG,
										"Some other error: " + response.getError());
							}
						}
					}
				}
		);
		request.executeAsync();
	}


	private void makeMeFriendRequest() {
		Request friendRequest = Request.newMyFriendsRequest(ParseFacebookUtils.getSession(),
				new Request.GraphUserListCallback() {
					@Override
					public void onCompleted(List<GraphUser> users,
											Response response) {
						if (users != null) {
							final List<String> friendsList = new ArrayList<String>();
							for (final GraphUser user : users) {
								friendsList.add(user.getId());
							}

							ParseUser currentUser = ParseUser.getCurrentUser();
							currentUser.addAll("friendslist", friendsList);
							currentUser.saveInBackground();

						}
					}
				});
		friendRequest.executeAsync();
	}


	private void updateViewsWithProfileInfo() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser.has("profile")) {
			JSONObject userProfile = currentUser.getJSONObject("profile");
			try {

				if (userProfile.has("facebookId")) {
					mUserImage.setProfileId(userProfile.getString("facebookId"));
				} else {
					// Show the default, blank user profile picture
					mUserImage.setProfileId(null);
				}

				if (userProfile.has("name")) {
					mUserName.setText(userProfile.getString("name"));
				} else {
					mUserName.setText("");
				}

			} catch (JSONException e) {
				Log.d(Application.TAG, "Error parsing saved user data.");
			}
		}
	}

	public void onLogoutClick(View v) {
		logout();
	}


	private void logout() {
		// Log the user out
		ParseUser.logOut();

		// Go to the login view
		startMainActivity();
	}

	private void startMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
