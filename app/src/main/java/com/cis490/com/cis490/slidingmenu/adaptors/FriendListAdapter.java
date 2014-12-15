package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cis490.Parse.Application;
import com.cis490.haonguyen.shopgang.R;
import com.facebook.widget.ProfilePictureView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Hao on 12/9/2014.
 */
public class FriendListAdapter extends ParseQueryAdapter<ParseUser> {

	private ProfilePictureView mUserImage;
	private TextView mUserName;
	private TextView mJoinDate;

	public FriendListAdapter(Context context) {

		super(context, new QueryFactory<ParseUser>() {
			public ParseQuery<ParseUser> create() {
				ParseQuery<ParseUser> query = ParseUser.getQuery();
				ParseUser currentUser = ParseUser.getCurrentUser();
				JSONObject userProfile = currentUser.getJSONObject("profile");
				try {
					String currentUserId = userProfile.getString("facebookId");
					return query.whereEqualTo("friendslist", currentUserId);
				} catch (JSONException e) {
					Log.d(Application.TAG, "Error getting current user profile.");
					return null;
				}
			}
		});
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}


	@Override
	public View getItemView(ParseUser object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.listview_friendlist, null);
		}
		super.getItemView(object, v, parent);

		JSONObject userProfile = object.getJSONObject("profile");

		try {
			mUserImage = (ProfilePictureView) v.findViewById(R.id.imgUser);
			mUserImage.setCropped(true);
			mUserImage.setProfileId(userProfile.getString("facebookId"));
			mUserName = (TextView) v.findViewById(R.id.textViewUserName);
			mUserName.setText(userProfile.getString("name"));
			mJoinDate = (TextView)v.findViewById(R.id.textViewJoinDate);

			mJoinDate.setText("Join Date: " + android.text.format.DateFormat.format("MMM dd, yyyy", object.getCreatedAt()));
		}
		catch(JSONException e){

		}


		return v;
	}

}
