package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cis490.Parse.Application;
import com.cis490.haonguyen.shopgang.R;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hao on 12/9/2014.
 */
public class FriendListAdapter extends ParseQueryAdapter<ParseUser> {


	private ProfilePictureView mUserImage;
	private TextView mUserName;
	private TextView mUserBirthday;

	public FriendListAdapter(Context context) {

		super(context, new QueryFactory<ParseUser>() {
			public ParseQuery<ParseUser> create() {
				ParseQuery<ParseUser> query = ParseUser.getQuery();
				ParseUser currentUser = ParseUser.getCurrentUser();
				JSONObject userProfile = currentUser.getJSONObject("profile");
				try {
					String currentUserId = userProfile.getString("facebookId");
					return query.whereEqualTo("friendslist", currentUserId);
				}
				catch (JSONException e){
					Log.d(Application.TAG, "Error getting current user name");
					return null;
				}
			}
		});
	}

	@Override
	public View getItemView(ParseUser object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.listview_friendlist, null);
		}
		super.getItemView(object, v, parent);

		final View finalV = v;


		Request.newMyFriendsRequest(ParseFacebookUtils.getSession(), new Request.GraphUserListCallback(){
			@Override
			public void onCompleted(List<GraphUser> users, Response response){
				if(users != null){

					String[] names = new String[users.size()];
					String[] ids = new String[users.size()];
					for(int i = 0; i < users.size(); i++) {
						names[i] = users.get(i).getName();
						ids[i] = users.get(i).getId();


					}
					mUserImage = (ProfilePictureView)finalV.findViewById(R.id.imgUser);
					mUserImage.setCropped(true);
					mUserImage.setProfileId(ids[0]);
					mUserName = (TextView)finalV.findViewById(R.id.textViewUserName);
					mUserName.setText(names[0]);

				}
			}
		}).executeAsync();

		return v;
	}

}
